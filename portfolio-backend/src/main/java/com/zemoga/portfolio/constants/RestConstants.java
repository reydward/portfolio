/**
 * Grupo Aval Acciones y Valores S.A. CONFIDENTIAL
 *
 * <p>Copyright (c) 2018 . All Rights Reserved.
 *
 * <p>NOTICE: This file is subject to the terms and conditions defined in file 'LICENSE', which is
 * part of this source code package.
 */
package com.zemoga.portfolio.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestConstants {

  public static final String BASE_PATH = "/portfolio/v1";

  public static final String INFORMATION = BASE_PATH + "/information";

  public static final String TIMELINE = BASE_PATH + "/timeline";

}
