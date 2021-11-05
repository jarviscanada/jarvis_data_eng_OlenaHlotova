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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.core.annotation.Order;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterServiceIntTest {
  public TwitterDao dao;
  public String hashtag = "#Test";
  static String text;
  public float lat = 22.99f;
  public float lon = -19.088f;
  private TwitterService service;
  static String id;
  static String tweetText;

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
  public void apostTweet() throws JsonProcessingException {

    //valid input and tests
    text = "work1ng? "+hashtag + " " + System.currentTimeMillis();
    Tweet postTweet = TweetUtil.buildTweet(text, lon, lat);
    Tweet tweet = service.postTweet(postTweet);
    id = tweet.getId_str();
    System.out.println(id);
    tweetText = tweet.getText();
    System.out.println(tweetText);
    assertEquals(text, tweetText);
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
  public void bshowTweet() throws JsonProcessingException {
    //valid inputs and tests
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
    Tweet tweet = service.showTweet(id, validFields);
    assertEquals(text, tweetText);
    assertEquals(lon, tweet.getCoordinates().getCoordinates()[0], 0);
    assertEquals(lat, tweet.getCoordinates().getCoordinates()[1], 0);
    assertTrue(hashtag.contains(tweet.getEntities().getHashtags()[0].getText()));

    //invalid inputs and tests
    String invalidId = "Should be numerical";
    String idError = "Invalid ID. Must be numerical";

    String[] invalidFields = {"", "", ""};
    String fieldsError = "Invalid field";

    try {
      service.showTweet(invalidId, validFields);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(idError, e.getMessage());
    }
    try {
      service.showTweet(id, invalidFields);
      fail();
    }catch (IllegalArgumentException e) {
      assertEquals(fieldsError, e.getMessage());
    }

  }

  @Test
  public void deleteTweets() throws JsonProcessingException {
    //valid inputs and tests
    System.out.println(id);
    String[] Ids = {id};
    List<Tweet> tweets = service.deleteTweets(Ids);
    for(Tweet tweet : tweets) {
      assertTrue((text.equals(tweet.getText())) || tweetText.equals(tweet.getText()));
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