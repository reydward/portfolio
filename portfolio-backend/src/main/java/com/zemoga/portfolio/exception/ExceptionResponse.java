package com.zemoga.portfolio.exception;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionResponse {
  private Date timestamp;
  private String message;
  private String details;

  public ExceptionResponse(Date timestamp, String message, String details) {
    this.timestamp = timestamp;
    this.message = message;
    this.details = details;
  }
}
