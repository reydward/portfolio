package com.zemoga.portfolio.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("twitter")
@Getter
@Setter
public class TwitterProperties {
  private String appId;

  private String appSecret;

  private String accessToken;

  private String accessTokenSecret;
}
