package com.zemoga.portfolio.controller;

import com.zemoga.portfolio.config.TwitterConfig;
import com.zemoga.portfolio.delegate.PortfolioDelegate;
import com.zemoga.portfolio.delegate.TwitterDelegate;
import com.zemoga.portfolio.exception.TimelineNotFoundException;
import com.zemoga.portfolio.model.response.TimelineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@RestController
@RequestMapping(value = "/twitter")
@Api(value = "/twitter")
@Slf4j
public class TwitterController {

  private final TwitterDelegate twitterDelegate;

  public TwitterController(TwitterDelegate twitterDelegate) {
    this.twitterDelegate = twitterDelegate;
  }

  @ApiOperation(
    value = "Get tweets timeline.",
    produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "The tweets timeline has been successfully obtained."),
      @ApiResponse(code = 404, message = "The tweets timeline does not exist."),
      @ApiResponse(code = 500, message = "There was an internal server error.")
    })
  @RequestMapping(
    value = "/timeline",
    method = {RequestMethod.GET, RequestMethod.OPTIONS},
    produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin(origins = "*")
  public ResponseEntity<TimelineResponse> getTimeline(@RequestParam("user") String user, @RequestParam("count") Integer count)
    throws TwitterException {

    TimelineResponse timelineResponse = twitterDelegate.getTimeLine(user, count);
    if(CollectionUtils.isEmpty(timelineResponse.getTimeline())){
      throw new TimelineNotFoundException("user: " + user);
    }
    return ResponseEntity.ok(timelineResponse);
  }

}
