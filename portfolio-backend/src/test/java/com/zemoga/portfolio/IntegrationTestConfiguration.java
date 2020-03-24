/**
 * Grupo Aval Acciones y Valores S.A. CONFIDENTIAL
 *
 * <p>Copyright (c) 2018 . All Rights Reserved.
 *
 * <p>NOTICE: This file is subject to the terms and conditions defined in file 'LICENSE', which is
 * part of this source code package.
 */
package com.zemoga.portfolio;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = {"com.zemoga.portfolio"})
public class IntegrationTestConfiguration {

  @Primary
  public OkHttpClient.Builder configureHttpClientBuilder(OkHttpClient.Builder httpClientBuilder) {
    httpClientBuilder.connectTimeout(1, TimeUnit.SECONDS);
    return httpClientBuilder;
  }
}
