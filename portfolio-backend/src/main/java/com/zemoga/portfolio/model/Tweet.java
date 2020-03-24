package com.zemoga.portfolio.model;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Tweet {
  private String userName;
  private String text;
  private Date createdDate;
}
