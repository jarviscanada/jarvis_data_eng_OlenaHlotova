package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import org.junit.Test;

public class TwitterDaoTest {

  @Test
  public void httpPost() throws Exception{
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,accessToken, tokenSecret);
      TwitterDao ttt = new TwitterDao(httphelper);
      //System.out.println(ttt.deleteById("1445497579089776645"));
      Tweet tweet = new Tweet();
      tweet.setText("test tweet create works new?5");
      System.out.println(ttt.create(tweet));
  }

  @Test
  public void findById() {
  }

  @Test
  public void deleteById() {
  }
}