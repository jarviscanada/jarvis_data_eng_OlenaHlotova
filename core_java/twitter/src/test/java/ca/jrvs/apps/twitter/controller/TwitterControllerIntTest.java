package ca.jrvs.apps.twitter.controller;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Entities;
import ca.jrvs.apps.twitter.model.Hashtag;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.TweetUtil;

import java.util.List;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterControllerIntTest {

  Tweet tweet;
  Controller controller;
  HttpHelper httpHelper;
  //Hashtag[] hashtag;
  //Hashtag hash;
  Entities entities;
  static String text;
  static String id;
  float lon = 1f;
  float lat = -1f;
  public String hashtag = "#Test";

  @Before
  public void setUp() throws Exception {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey+"|"+consumerSecret+"|"+accessToken+"|"+tokenSecret);

    httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    controller = new TwitterController(new TwitterService(new TwitterDao(httpHelper)));

    //text = "test_controller2check2 " +hashtag + " " + System.currentTimeMillis();
    //float[] coords = {lat, lon};
    //Coordinates coordinates = new Coordinates();
    //coordinates.setCoordinates(coords);
    //tweet = TweetUtil.buildTweet(text, lat, lon);
    //tweet.setCoordinates(coordinates);
    //tweet.setId_str("1448478081937809408");
    //tweet.setText(text);
    //entities = new Entities();
    //hash = new Hashtag();
    //hash.setText("#let me test controller");
    //hashtag = new Hashtag[]{hash};
    //entities.setHashtags(hashtag);
    //tweet.setEntities(entities);

  }
  @Test
  public void apostTweet() {
    text = "test_controller2check2 " +hashtag + " " + System.currentTimeMillis();
    tweet = TweetUtil.buildTweet(text, lat, lon);
    String[] args = {"post", tweet.getText(), tweet.getCoordinates().getCoordinates()[0] + ":" + tweet.getCoordinates().getCoordinates()[1]};
    Tweet response = controller.postTweet(args);
    id = response.getId_str();
    assertEquals(args[1], response.getText());
    assertNotNull(response.getCoordinates());
    assertEquals(2, response.getCoordinates().getCoordinates().length);
    assertEquals(1.0, response.getCoordinates().getCoordinates()[0], 0);
    assertEquals(-1.0, response.getCoordinates().getCoordinates()[1], 0);
    assertTrue(hashtag.contains(response.getEntities().getHashtags()[0].getText()));
  }

  @Test
  public void bshowTweet() {
    String[] args = {"show", id};
    Tweet response = controller.showTweet(args);
    assertEquals(args[1], id);
    assertNotNull(response.getCoordinates());
    assertEquals(2, response.getCoordinates().getCoordinates().length);
    assertEquals(1.0, response.getCoordinates().getCoordinates()[0], 0);
    assertEquals(-1.0, response.getCoordinates().getCoordinates()[1], 0);
    assertTrue(hashtag.contains(response.getEntities().getHashtags()[0].getText()));
  }

  @Test
  public void deleteTweet() {
    String[] args = {"delete", id};
    List<Tweet> responses = controller.deleteTweet(args);
    for (Tweet response : responses) {
      assertEquals(args[1], id);
      assertNotNull(response.getCoordinates());
      assertEquals(2, response.getCoordinates().getCoordinates().length);
      assertEquals(1.0, response.getCoordinates().getCoordinates()[0], 0);
      assertEquals(-1.0, response.getCoordinates().getCoordinates()[1], 0);
      assertTrue(hashtag.contains(response.getEntities().getHashtags()[0].getText()));
    }

  }
}