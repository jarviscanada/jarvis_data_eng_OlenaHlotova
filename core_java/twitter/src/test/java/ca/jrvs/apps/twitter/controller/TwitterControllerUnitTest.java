package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterControllerUnitTest {

  Tweet tweet;

  @Mock
  Service service;

  @InjectMocks
  TwitterController controller;


  @Before
  public void setUp() {
    float lon = 1f;
    float lat = -1f;
    String text = "test_test";
    Coordinates coordinates = new Coordinates();
    float[] coords = {lon, lat};
    coordinates.setCoordinates(coords);
    tweet = TweetUtil.buildTweet(text, lon, lat);
    tweet.setCoordinates(coordinates);
    tweet.setId_str("1446224785604636674");
    tweet.setText(text);
  }

  @Test
  public void postTweet() {
    when(service.postTweet(ArgumentMatchers.any())).thenReturn(tweet);
    String[] args = {"post", tweet.getText(), tweet.getCoordinates().getCoordinates()[0] + ":" + tweet.getCoordinates().getCoordinates()[1]};
    Tweet response = controller.postTweet(args);
    assertNotNull(response);
  }

  @Test
  public void showTweet() {
    when(service.showTweet(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(tweet);
    String[] args = {"show", tweet.getId_str()};
    Tweet response = controller.showTweet(args);
    assertNotNull(response);
  }

  @Test
  public void deleteTweet() {
    when(service.deleteTweets(ArgumentMatchers.any())).thenReturn(new ArrayList<>());
    String[] args = {"delete", tweet.getId_str()};
    List<Tweet> response = controller.deleteTweet(args);
    assertNotNull(response);
  }
}