package com.zemoga.portfolio.model.response;

import com.zemoga.portfolio.model.Tweet;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TimelineResponse {

  private List<Tweet> timeline;
}
