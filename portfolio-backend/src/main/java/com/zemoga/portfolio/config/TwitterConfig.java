package com.zemoga.portfolio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@ConfigurationProperties(prefix = "twitter")
@Getter
@Setter
public class TwitterConfig {

  private ConfigurationBuilder configurationBuilder;
  private TwitterFactory twitterFactory;
  private String appId;
  private String appSecret;
  private String accessToken;
  private String accessTokenSecret;

  public TwitterConfig() {
    configurationBuilder = new ConfigurationBuilder();
/*    configurationBuilder.setDebugEnabled(true)
      .setOAuthConsumerKey(appId)
      .setOAuthConsumerSecret(appSecret)
      .setOAuthAccessToken(accessToken)
      .setOAuthAccessTokenSecret(accessTokenSecret);*/
    configurationBuilder.setDebugEnabled(true)
      .setOAuthConsumerKey("QjAjb1h1rt3CGH4AZjKxy3mtO")
      .setOAuthConsumerSecret("5ObLdPn58SlWWOIOTMan1OFiIFwl86njkenXPELtcGGHR2qSr9")
      .setOAuthAccessToken("73032661-hs6UyaVURXuHXo6wQAwGW2BB7WzftvUoVIev9lIi5")
      .setOAuthAccessTokenSecret("euVhfz47eX1hFyfhyXVv9fto7sUIPVHg9jRojecOgbaRP");
    twitterFactory = new TwitterFactory(configurationBuilder.build());
  }
}
