package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public class TwitterCLIApp {
  private Controller controller;
  public static final String INVALID_INPUT = "Invalid Input. Usage: post|show|delete [options]";

  public TwitterCLIApp(Controller controller) {this.controller = controller;}

  public static void main(String[] args) {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    HttpHelper helper = new TwitterHttpHelper(consumerKey, consumerSecret, accessToken, tokenSecret);
    CrdDao dao = new TwitterDao(helper);
    Service service = new TwitterService(dao);
    Controller controller = new TwitterController(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);

    app.run(args);
  }

  private void run(String[] args) {
    if (args.length == 0) {
      throw new IllegalArgumentException(INVALID_INPUT);
    }

    switch (args[0].toLowerCase()) {
      case "post":
        printTweet(controller.postTweet(args));
        break;
      case "show":
        printTweet(controller.showTweet(args));
        break;
      case "delete":
        List<Tweet> tweets = controller.deleteTweet(args);
        tweets.forEach(this::printTweet);
        break;
      default:
        throw new IllegalArgumentException(INVALID_INPUT);
    }
  }

  private void printTweet(Tweet tweet) {
    try {
      System.out.println(JsonUtil.toJson(tweet, true, false));
    }catch (JsonProcessingException e) {
      throw new RuntimeException("Unable to convert object", e);
    }
  }


}