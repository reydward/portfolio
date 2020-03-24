package com.zemoga.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TimelineNotFoundException extends RuntimeException {

  public TimelineNotFoundException(String message) {
    super(message);
  }
}
