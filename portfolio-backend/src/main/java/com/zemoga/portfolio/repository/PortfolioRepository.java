package com.zemoga.portfolio.repository;

import com.zemoga.portfolio.repository.model.PortfolioEntity;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioEntity, Integer> {

  Optional<PortfolioEntity> findByTwitterUserName(String user);
}
