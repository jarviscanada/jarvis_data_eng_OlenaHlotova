package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.model.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TwitterDao implements CrdDao<Tweet, String>{

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy.json";
  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  
  static final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

  @Autowired
  public TwitterDao(HttpHelper httpHelper) {this.httpHelper = httpHelper;}

  @Override
  public Tweet create(Tweet entity) {
    URI uri;
    uri = getPostUri(entity);
    HttpResponse response = httpHelper.httpPost(uri);
    //System.out.println(response.getAllHeaders());
    return parseResponseBody(response, HTTP_OK);
  }

  Tweet parseResponseBody(HttpResponse response, int expectedStatusCode) {
    Tweet tweet;
    int status = response.getStatusLine().getStatusCode();
    if (status != expectedStatusCode) {
      try {
        logger.debug(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        logger.debug("ERROR: Response has no entity");
      }
      throw new RuntimeException("ERROR: Unexpected HTTP status:" + status);
    }

    if (response.getEntity() == null) {
      throw new RuntimeException("ERROR: Empty response body");
    }
    //Convert Response Entity to String
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      throw new RuntimeException("ERROR: Failed to convert entity to String", e);
    }
    //JSON string to Tweet object
    try {
      tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("ERROR: Unable to covert JSON string to Object", e);
    }
    return tweet;
  }

  @Override
  public Tweet findById(String s) {
    URI uri;
    uri = getShowUri(s);
    HttpResponse response = httpHelper.httpGet(uri);
    return parseResponseBody(response, HTTP_OK);
  }

  @Override
  public Tweet deleteById(String s) {
    URI uri;
    uri = getDeleteUri(s);
    HttpResponse response = httpHelper.httpPost(uri);
    return parseResponseBody(response, HTTP_OK);
  }


  private URI getPostUri(Tweet tweet) {
    URI uri;
    String longitude;
    String latitude;
    try{
      String message = URLEncoder.encode(tweet.getText(), StandardCharsets.UTF_8.toString());
      if (tweet.getCoordinates() != null) {
        longitude = String.valueOf(tweet.getCoordinates().getCoordinates()[0]);
        latitude = String.valueOf(tweet.getCoordinates().getCoordinates()[1]);
        uri = new URI(API_BASE_URI+POST_PATH+QUERY_SYM+"status"+EQUAL+message+AMPERSAND
            +"long"+EQUAL+longitude+AMPERSAND+"lat"+EQUAL+latitude);
      }else {
        uri = new URI (API_BASE_URI+POST_PATH+QUERY_SYM+"status"+EQUAL+message);
      }
      return uri;
    } catch (URISyntaxException | UnsupportedEncodingException e) {
      throw new IllegalArgumentException("Invalid uri input", e);
    }
  }

  private URI getShowUri(String s) {
    try{
      return new URI(API_BASE_URI+SHOW_PATH+QUERY_SYM+"id"+EQUAL+s);
    }
    catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid Input", e);
    }
  }

  private URI getDeleteUri(String s) {
    try {
      return new URI(API_BASE_URI+DELETE_PATH+QUERY_SYM+"id"+EQUAL+s);
    }    catch (URISyntaxException e) {
      throw new IllegalArgumentException("Invalid Input", e);
    }
  }

}
