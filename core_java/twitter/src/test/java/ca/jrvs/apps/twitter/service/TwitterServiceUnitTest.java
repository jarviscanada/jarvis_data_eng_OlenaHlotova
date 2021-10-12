package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.TweetUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TwitterServiceUnitTest {
  public String hashtag = "#Test";
  public String text = "work1ng? "+hashtag;
  public float lat = 22.99f;
  public float lon = -19.088f;
  public long id = 15888633215478l;

  @Mock
  CrdDao dao;

  @InjectMocks
  TwitterService service;
  Tweet tweet = TweetUtil.buildTweet(text, lon, lat);



  @Test
  public void postTweet() {
    assertNotNull(dao);
    when(dao.create(ArgumentMatchers.any())).thenReturn(new Tweet());
    service.postTweet(tweet);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0);
    assertEquals(text, tweet.getText());


  }

  @Test
  public void showTweet() {
    assertNotNull(dao);
    String id_str = String.valueOf(id);
    tweet.setId_str(id_str);
    when(dao.findById(ArgumentMatchers.anyString())).thenReturn(new Tweet());
    service.showTweet(tweet.getId_str(), null);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0);
    assertEquals(text, tweet.getText());


  }

  @Test
  public void deleteTweets() {
    assertNotNull(dao);
    when(dao.deleteById(ArgumentMatchers.any())).thenReturn(new Tweet());
    String[] ids = {"15888633215478"};
    service.deleteTweets(ids);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0);

  }
}