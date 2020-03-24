package com.zemoga.portfolio.delegate;

import com.zemoga.portfolio.model.request.PortfolioRequest;
import com.zemoga.portfolio.model.response.PortfolioResponse;
import com.zemoga.portfolio.repository.PortfolioRepository;
import com.zemoga.portfolio.repository.model.PortfolioEntity;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
@Setter
@Slf4j
public class PortfolioDelegate {

  private final PortfolioRepository portfolioRepository;

  public PortfolioDelegate(PortfolioRepository portfolioRepository) {
    this.portfolioRepository = portfolioRepository;
  }

  public PortfolioResponse getInformationByUsername(String user) {
    PortfolioResponse portfolioResponse = null;
    Optional<PortfolioEntity> optionalPortfolioEntity = portfolioRepository.findByTwitterUserName(user);

    if (optionalPortfolioEntity.isPresent()) {
      PortfolioEntity portfolioEntity = optionalPortfolioEntity.get();
      portfolioResponse = PortfolioResponse.builder()
        .id(portfolioEntity.getId())
        .description(portfolioEntity.getDescription())
        .imageUrl(portfolioEntity.getImageUrl())
        .twitterUserName(portfolioEntity.getTwitterUserName())
        .title(portfolioEntity.getTitle())
        .build();
    }

    return portfolioResponse;
  }

  public URI modifyPortfolio(PortfolioRequest request) {
    URI location = null;

    PortfolioEntity portfolioEntity = PortfolioEntity.builder()
      .id(request.getId())
      .description(request.getDescription())
      .imageUrl(request.getImageUrl())
      .twitterUserName(request.getTwitterUserName())
      .title(request.getTitle())
      .build();

    if(Objects.nonNull(portfolioRepository.save(portfolioEntity))){
      location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{twitterUserName}")
        .buildAndExpand(portfolioEntity.getTwitterUserName()).toUri();
    }

    return location;
  }
}
