package com.zemoga.portfolio;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfiguration.class})
@SpringBootTest(classes = {PortfolioApplication.class})
@AutoConfigureMockMvc
public class SwaggerTest {

  private static final String SWAGGER_API_DOCS = "/v2/api-docs";

  @Autowired
  private MockMvc mockMvc;

  @Value("#{environment.SWAGGER_API_DOCS_OUTPUT ?: 'build/api-docs.json'}")
  private String swaggerApiDocsOutput;

  @Test
  public void generateSwaggerDoc() throws Exception {
    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get(SWAGGER_API_DOCS);
    MvcResult mvcResult = this.mockMvc.perform(builder).andDo(print()).andReturn();
    File file = new File(swaggerApiDocsOutput);
    try (Writer w = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
        PrintWriter out = new PrintWriter(w)) {
      out.println(mvcResult.getResponse().getContentAsString());
    }
  }
}
