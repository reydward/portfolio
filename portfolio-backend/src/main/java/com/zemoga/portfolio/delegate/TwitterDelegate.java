package com.zemoga.portfolio.delegate;

import com.zemoga.portfolio.config.TwitterConfig;
import com.zemoga.portfolio.model.Tweet;
import com.zemoga.portfolio.model.response.TimelineResponse;
import com.zemoga.portfolio.repository.PortfolioRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
@Setter
@Slf4j
public class TwitterDelegate {
  private final TwitterConfig twitterConfig;

  private Twitter twitter;

  public TwitterDelegate(TwitterConfig twitterConfig) {
    this.twitterConfig = twitterConfig;
    this.twitter = twitterConfig.getTwitterFactory().getInstance();
  }

  public TimelineResponse getTimeLine(String user, Integer count) throws TwitterException {
    TimelineResponse timelineResponse = new TimelineResponse();
    List<Tweet> portfolioTimeLine = new ArrayList<>();
    List<Status> timeline = twitter.getUserTimeline(user).subList(0, count);

    if(!CollectionUtils.isEmpty(timeline)){
      timeline.forEach(item -> {
        portfolioTimeLine.add(Tweet.builder()
          .userName(item.getUser().getName())
          .text(item.getText())
          .createdDate(item.getCreatedAt())
          .build());
      });
      timelineResponse.setTimeline(portfolioTimeLine);
    }
    return timelineResponse;
  }
}
