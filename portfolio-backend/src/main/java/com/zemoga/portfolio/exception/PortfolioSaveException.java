package com.zemoga.portfolio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PortfolioSaveException extends RuntimeException {

  public PortfolioSaveException(String message) {
    super(message);
  }
}
