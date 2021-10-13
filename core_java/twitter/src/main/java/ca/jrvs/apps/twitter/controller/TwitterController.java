package ca.jrvs.apps.twitter.controller;

import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.util.TweetUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class TwitterController implements Controller{
  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;
  String text;
  float lat;
  float lon;

  @Autowired
  public TwitterController(Service service) {
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("Invalid Input. Usage: post text lat:lon");
    }
    text = args[1];
    String coordinates = args[2];
    String[] coords = coordinates.split(COORD_SEP);


    if(coords.length !=2) {
      throw new IllegalArgumentException("Invalid number of coordinates. Usage: lat:lon");
    }

    if (text.isEmpty()) {
      throw new IllegalArgumentException("Text is not provided");
    }

    try {
      lat = Float.parseFloat(coords[0]);
      lon = Float.parseFloat(coords[1]);
    }catch(NumberFormatException e) {
      throw new IllegalArgumentException("Invalid coordinates format");
    }

    Tweet tweet = TweetUtil.buildTweet(text, lon, lat);
    return service.postTweet(tweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    if (args.length > 3 || args.length <2) {
      throw new IllegalArgumentException("Invalid Input. Usage: show post_id fields");
    }
    String id = args[1];
    if (args.length == 2) {
      return service.showTweet(id, null);
    }
    String fields = args[2];


    String[] fields_array = fields.split(",");
    return service.showTweet(id, fields_array);
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid Input. Usage: delete post_IDs");
    }
    String[] ids = args[1].split(COMMA);
    return service.deleteTweets(ids);
  }
}
