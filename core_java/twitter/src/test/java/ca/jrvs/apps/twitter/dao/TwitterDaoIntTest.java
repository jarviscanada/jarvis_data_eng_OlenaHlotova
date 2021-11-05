package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;

public class TwitterDaoIntTest {

  public TwitterDao dao;
  public String hashtag = "#Test";
  static String text;
  public float lat = 22.99f;
  public float lon = -19.088f;
  static String id;
  @Before
  public void setup() throws Exception{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,accessToken, tokenSecret);

    this.dao = new TwitterDao(httpHelper);

  }

  @Test
  public void create() throws JsonProcessingException {
    text = "someone_test "+hashtag + " " + System.currentTimeMillis();
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    System.out.println(JsonUtil.toJson(postTweet, true, false));
    Tweet tweet = dao.create(postTweet);
    id = tweet.getId_str();
    System.out.println(id);

    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0.0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.0);
    assertTrue(hashtag.contains(tweet.getEntities().getHashtags()[0].getText()));

  }

  @Test
  public void findById() throws JsonProcessingException {
    //String id = "1448122607086231554";
    System.out.println(id);
    Tweet tweet = dao.findById(id);

    System.out.println(JsonUtil.toJson(tweet, true, false));

    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0.0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.0);
    assertTrue(hashtag.contains(tweet.getEntities().getHashtags()[0].getText()));
  }

  @Test
  public void deleteById() throws JsonProcessingException {
    //String id = "1448122607086231554";
    System.out.println(id);
    Tweet tweet = dao.deleteById(id);
    System.out.println(JsonUtil.toJson(tweet, true, false));

    assertEquals(text, tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2, tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0.0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0.0);
    assertTrue(hashtag.contains(tweet.getEntities().getHashtags()[0].getText()));
  }
}