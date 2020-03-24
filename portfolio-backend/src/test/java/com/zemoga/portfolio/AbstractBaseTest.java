package com.zemoga.portfolio;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

public abstract class AbstractBaseTest {

  @Autowired
  protected MockMvc mockMvc;

  protected static String asString(InputStream resource) throws IOException {
    Reader reader = new InputStreamReader(resource, StandardCharsets.UTF_8);
    return FileCopyUtils.copyToString(reader);
  }
}
