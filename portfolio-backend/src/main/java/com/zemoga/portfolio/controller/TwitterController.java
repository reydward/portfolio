package com.zemoga.portfolio.controller;

import com.zemoga.portfolio.constants.RestConstants;
import com.zemoga.portfolio.delegate.TwitterDelegate;
import com.zemoga.portfolio.exception.TimelineNotFoundException;
import com.zemoga.portfolio.model.response.TimelineResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

@RestController
@RequestMapping(value = RestConstants.TIMELINE)
@Api(value = RestConstants.TIMELINE)
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
      @ApiResponse(code = 401, message = "There was a problem related to authorization."),
      @ApiResponse(code = 500, message = "There was an internal server error.")
    })
  @GetMapping(
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
