package com.zemoga.portfolio.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PortfolioRequest {

  private Integer id;
  private String description;
  private String imageUrl;
  private String twitterUserName;
  private String title;

}
