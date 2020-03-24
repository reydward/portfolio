package com.zemoga.portfolio.config;

import com.zemoga.portfolio.config.properties.TwitterProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@Getter
@Setter
public class TwitterConfig {

  private ConfigurationBuilder configurationBuilder;
  private TwitterFactory twitterFactory;

  @Autowired
  private TwitterProperties twitterProperties;

  public TwitterFactory getTwitterFactory() {
    configurationBuilder = new ConfigurationBuilder();
    configurationBuilder.setDebugEnabled(true)
      .setOAuthConsumerKey(twitterProperties.getAppId())
      .setOAuthConsumerSecret(twitterProperties.getAppSecret())
      .setOAuthAccessToken(twitterProperties.getAccessToken())
      .setOAuthAccessTokenSecret(twitterProperties.getAccessTokenSecret());
    return new TwitterFactory(configurationBuilder.build());
  }
}
