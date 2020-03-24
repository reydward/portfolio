package com.zemoga.portfolio.controller;

import com.zemoga.portfolio.delegate.PortfolioDelegate;
import com.zemoga.portfolio.exception.PortfolioNotFoundException;
import com.zemoga.portfolio.exception.PortfolioSaveException;
import com.zemoga.portfolio.model.request.PortfolioRequest;
import com.zemoga.portfolio.model.response.PortfolioResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.IOException;
import java.net.URI;
import java.util.Objects;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/portfolio")
@Api(value = "/portfolio")
@Slf4j
public class PortfolioController {

  private final PortfolioDelegate portfolioDelegate;

  public PortfolioController(PortfolioDelegate portfolioDelegate) {
    this.portfolioDelegate = portfolioDelegate;
  }

  @ApiOperation(
    value = "Gets profile information.",
    produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "The profile information has been successfully obtained."),
      @ApiResponse(code = 404, message = "The portfolio does not exist."),
      @ApiResponse(code = 500, message = "There was an internal server error.")
    })
  @RequestMapping(
    value = "/information/{user}",
    method = {RequestMethod.GET, RequestMethod.OPTIONS},
    produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin(origins = "*")
  public ResponseEntity<PortfolioResponse> getInformation(@PathVariable String user)
    throws IOException {
    PortfolioResponse portfolioResponse = portfolioDelegate.getInformationByUsername(user);

    if(Objects.isNull(portfolioResponse)) {
      throw new PortfolioNotFoundException("user: " + user);
    }
    return ResponseEntity.ok(portfolioResponse);
  }

  @ApiOperation(
    value = "Modify a portfolio.",
    produces = MediaType.APPLICATION_JSON_VALUE)
  @ApiResponses(
    value = {
      @ApiResponse(code = 200, message = "The portfolio has been successfully modified."),
      @ApiResponse(code = 400, message = "The input payload is malformed."),
      @ApiResponse(code = 500, message = "There was an internal server error.")
    })
  @RequestMapping(
    value = "/information",
    method = {RequestMethod.PUT, RequestMethod.OPTIONS},
    produces = MediaType.APPLICATION_JSON_VALUE)
  @CrossOrigin(origins = "*")
  public ResponseEntity<URI> modify(@RequestBody PortfolioRequest request)
    throws IOException {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    Set<ConstraintViolation<PortfolioRequest>> violations = validator.validate(request);
    if (violations.isEmpty()) {
      URI uri = portfolioDelegate.modifyPortfolio(request);
      if(Objects.isNull(uri)) {
        throw new PortfolioSaveException("user: " + request.getTwitterUserName());
      }
      return ResponseEntity.ok(uri);
    } else {
      return ResponseEntity.badRequest().build();
    }

  }

}
