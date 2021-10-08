package ca.jrvs.apps.twitter.util;

import ca.jrvs.apps.twitter.model.Coordinates;
import ca.jrvs.apps.twitter.model.Tweet;


public class TweetUtil {

  public static Tweet buildTweet(String text, float longitude, float latitude){
    Tweet tweet = new Tweet();

    Coordinates coordinates = new Coordinates();
    float[] coords = {longitude, latitude};
    coordinates.setCoordinates(coords);
    coordinates.setType("point");
    tweet.setText(text);
    tweet.setCoordinates(coordinates);

    return tweet;
  }

}