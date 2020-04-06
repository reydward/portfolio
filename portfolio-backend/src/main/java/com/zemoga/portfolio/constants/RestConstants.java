package com.zemoga.portfolio.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestConstants {

  public static final String BASE_PATH = "/portfolio/v1";

  public static final String INFORMATION = BASE_PATH + "/information";

  public static final String TIMELINE = BASE_PATH + "/timeline";

}
