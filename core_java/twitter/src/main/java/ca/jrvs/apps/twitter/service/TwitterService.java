package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.model.Tweet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@org.springframework.stereotype.Service
public class TwitterService implements Service{

  private CrdDao dao;

  private static final int TWEET_LIMIT = 280;
  private static final float LONG_MIN = -180;
  private static final float LONG_MAX = 180;
  private static final float LAT_MIN = -90;
  private static final float LAT_MAX = 90;

  @Autowired
  public TwitterService(CrdDao dao) {
    this.dao = dao;
  }
  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateShow(id, fields);
    return (Tweet) dao.findById(id);
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {

    Arrays.stream(ids).forEach ((id) -> validateId(id));
    List<Tweet> deleted = new ArrayList<Tweet>();
    Arrays.stream(ids).forEach((id) -> deleted.add((Tweet)dao.deleteById(id)));
    return deleted;
  }

  public void validatePostTweet (Tweet tweet) {
    float[] coords = tweet.getCoordinates().getCoordinates();
    //check if tweet's length doesn't exceed max allowed characters
    if (tweet.getText().length()>TWEET_LIMIT) {
      throw new IllegalArgumentException("Exceeded maximum number of allowed characters (280)");
    }
    else if (coords[0] > LONG_MAX || coords[0] < LONG_MIN || coords[1]>LAT_MAX||coords[1]<LAT_MIN) {
      throw new IllegalArgumentException("Invalid geo position");
    }
  }

  public void  validateId(String id) {
    try {
      //parse string id as a long
      Long.parseLong(id);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid ID. Must be numerical", e);
    }
  }

  public void validateShow(String id, String[] fields){
    validateId(id);

    String[] valid = {"created_at",
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
    //check if input fields are correct
    if (fields != null) {
      Arrays.stream(fields).forEach((field) -> {
        if (Arrays.binarySearch(valid, field) == -1) {
          throw new IllegalArgumentException("Invalid field");
        }
      });
    }
  }
}

