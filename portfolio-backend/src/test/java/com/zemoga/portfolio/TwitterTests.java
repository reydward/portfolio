package com.zemoga.portfolio;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.easymock.IExpectationSetters;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.json.DataObjectFactory;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {IntegrationTestConfiguration.class})
@SpringBootTest(classes = {PortfolioApplication.class})
@AutoConfigureMockMvc
public class TwitterTests extends AbstractBaseTest{

	@Value("classpath:integration-test/back-side/twitter-timeline-response.json")
	private InputStream twitterTimelineResponse;

	@Value("classpath:integration-test/front-side/portfolio-timeline-response.json")
	private InputStream portfolioTimelineResponse;

	@MockBean
	private Twitter twitter;

	@Test
	public void getTimelineByUsername() throws Exception {
		twitter = buildMockTwitterService(asString(twitterTimelineResponse));
		mockMvc
			.perform(
				get("/portfolio/v1/timeline")
					.param("user", "daenerys")
					.param("count", "2")
					.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(asString(portfolioTimelineResponse)));
	}

	@SuppressWarnings("unchecked")
	public static ResponseList<Status> buildUserTimelineResponseList(String twitterRawJSONResponse) throws Exception {
		ResponseList<Status> responseList = null;
		JSONArray array = new JSONArray(twitterRawJSONResponse);
		final List<Status> list = new ArrayList<Status>();

		if (array.length() > 0) {
			for (int x = 0; x < array.length(); x++) {
				JSONObject obj = (JSONObject) array.get(x);
				Status status = DataObjectFactory.createStatus(obj.toString());
				list.add(status);
			}
		}

		responseList = EasyMock.createMock(ResponseList.class);
		EasyMock.expect(responseList.toArray(EasyMock.isA(Status[].class))).andDelegateTo(list).anyTimes();
		EasyMock.replay(responseList);

		return responseList;
	}

	public static Twitter buildMockTwitterService(String twitterJSONResponse) throws Exception {
		Twitter ret = EasyMock.createMock(Twitter.class);
		IExpectationSetters<ResponseList<Status>> expectation = EasyMock.expect(ret.getUserTimeline(EasyMock.isA(String.class),
		EasyMock.isA(Paging.class)));
		expectation.andReturn(buildUserTimelineResponseList(twitterJSONResponse)).times(1);
		EasyMock.replay(ret);
		return ret;
	}

}
