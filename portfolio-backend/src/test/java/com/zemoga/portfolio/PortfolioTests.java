package com.zemoga.portfolio;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.zemoga.portfolio.repository.PortfolioRepository;
import com.zemoga.portfolio.repository.model.PortfolioEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfiguration.class})
@SpringBootTest(classes = {PortfolioApplication.class})
@AutoConfigureMockMvc
public class PortfolioTests extends AbstractBaseTest{

	@Value("classpath:integration-test/front-side/portfolio-modify-request.json")
	private InputStream portfolioModifyRequest;

	@Value("classpath:integration-test/front-side/portfolio-get-response.json")
	private InputStream portfolioGetResponse;

 @MockBean
	private PortfolioRepository portfolioRepository;

	@Test
	public void getPortfolioInformationByUsername() throws Exception {
		PortfolioEntity portfolioEntity = getPortfolioEntity();
		Mockito.when(portfolioRepository.findByTwitterUserName("Daenerys")).thenReturn(Optional.of(portfolioEntity));
		mockMvc
			.perform(
					get("/portfolio/v1/information/{user}", "Daenerys")
						.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(asString(portfolioGetResponse)));
	}

	@Test
	public void modifyPortfolio() throws Exception {
		PortfolioEntity portfolioEntity = getPortfolioEntity();
		Mockito.when(portfolioRepository.save(Mockito.any(PortfolioEntity.class))).thenReturn(portfolioEntity);
		mockMvc
			.perform(
					put("/portfolio/v1/information")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(asString(portfolioModifyRequest)))
			.andExpect(status().isOk())
			.andExpect(content().string("\"http://localhost/portfolio/v1/information/Daenerys\""));
	}

	private PortfolioEntity getPortfolioEntity() {
		return PortfolioEntity.builder()
			.id(2)
			.description("The Mother of Dragons!!")
			.imageUrl("https://pbs.twimg.com/profile_images/1117967801652617216/i8PWXebo_400x400.jpg")
			.twitterUserName("Daenerys")
			.title("Daenerys Targaryen")
			.build();
	}

}
