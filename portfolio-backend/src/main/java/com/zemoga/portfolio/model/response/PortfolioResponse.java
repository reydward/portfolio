package com.zemoga.portfolio.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PortfolioResponse {

  private Integer id;
  private String description;
  private String imageUrl;
  private String twitterUserName;
  private String title;

}
