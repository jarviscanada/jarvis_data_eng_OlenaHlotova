package ca.jrvs.apps.twitter.service;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import ca.jrvs.apps.twitter.util.TweetUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceIntTest {
  public TwitterDao dao;
  public String hashtag = "#Test";
  public String text = "work1ng? "+hashtag;
  public float lat = 22.99f;
  public float lon = -19.088f;
  private TwitterService service;

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,accessToken, tokenSecret);
    this.dao = new TwitterDao(httpHelper);
    this.service = new TwitterService(dao);
  }

  @Test
  public void postTweet() throws JsonProcessingException {

    //valid input and tests
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    Tweet tweet = service.postTweet(postTweet);
    assertEquals(text, tweet.getText());
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0);
    assertTrue(hashtag.contains(tweet.getEntities().getHashtags()[0].getText()));


    //invalid inputs
    String longText = new String("This text should be longer than two hundred and "
        + "eighty characters to test postTweet method. It's length is two hundred and ninety."
        + " This text should be longer than two hundred and eighty characters to test postTweet method."
        + " It's length is two hundred and ninety. This text should be longer t");
    float wrongLon = 1000;
    float wrongLat = -400;

    Tweet postWrongTextLentgh = TweetUtil.buildTweet(longText, lon, lat);  //text>280 characters
    String textLength = new String("Exceeded maximum number of allowed characters (280)");

    Tweet postWrongLat = TweetUtil.buildTweet(text, lon, wrongLat);//latitude > 90
    String wrongLatitude = new String("Invalid geo position");

    Tweet postWrongLon= TweetUtil.buildTweet(text, wrongLon, lat);//longitude <(-180)
    String wrongLongitude = new String("Invalid geo position");

    // Invalid tests

    try {
      service.postTweet(postWrongTextLentgh);
      fail();
    } catch (IllegalArgumentException e) {
      assertEquals(textLength, e.getMessage());
    }
    try {
      service.postTweet(postWrongLat);
      fail();
    } catch (IllegalArgumentException e){
      assertEquals(wrongLatitude, e.getMessage());
    }
    try {
      service.postTweet(postWrongLon);
      fail();
    } catch (IllegalArgumentException e){
      assertEquals(wrongLongitude, e.getMessage());
    }

  }

  @Test
  public void showTweet() throws JsonProcessingException {

    //valid inputs and tests
    String validId = "1448053157523820550";
    String[] validFields = {"created_at",
        "id",
        "id_str",
        "text",
        "entities",
        "coordinates",
        "retweet_count",
        "favorite_count",
        "favorited",
        "retweeted"
    };
    Tweet tweet = service.showTweet(validId, validFields);
    assertEquals(text, tweet.getText());
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0);
    assertTrue(hashtag.contains(tweet.getEntities().getHashtags()[0].getText()));

    //invalid inputs and tests
    String invalidId = "Should be numerical";
    String idError = "ID must be numerical characters.";

    String[] invalidFields = {"wrong_field1", "wrong_field2", "wrong_field3"};
    String fieldsError = "Invalid or Missing Fields(s)";

    try {
      service.showTweet(invalidId, validFields);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(idError, e.getMessage());
    }
    try {
      service.showTweet(validId, invalidFields);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(fieldsError, e.getMessage());
    }

  }

  @Test
  public void deleteTweets() throws JsonProcessingException {
    //valid inputs and tests
    String[] validIds = {"1448053157523820550"};
    List<Tweet> tweets = service.deleteTweets(validIds);
    String tweeted = "rainy_days";
    for(Tweet tweet : tweets) {
      assertTrue((text.equals(tweet.getText())) || tweeted.equals(tweet.getText()));
      assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0);
      assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0);
      assertTrue(hashtag.contains(tweet.getEntities().getHashtags()[0].getText()));
    }

    //invalid inputs and tests
    String[] invalidIds = {"Should be numerical", "Not numerical"};
    String invalidIdsError = "Invalid ID. Must be numerical";
    try{
      service.deleteTweets(invalidIds);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(invalidIdsError, e.getMessage());
    }
  }
}