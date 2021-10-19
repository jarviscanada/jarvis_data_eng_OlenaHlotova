package ca.jrvs.apps.twitter.dao.helper;

import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class TwitterHttpHelperTest {
  static String text;
  @Test
  public void httpPost() throws Exception{
    text = "someone_test" + System.currentTimeMillis();
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");
    System.out.println(consumerKey + "|" + consumerSecret + "|" + accessToken + "|" + tokenSecret);
    HttpHelper httpHelper = new TwitterHttpHelper(consumerKey, consumerSecret,accessToken, tokenSecret);
    String str = "https://api.twitter.com/1.1/statuses/update.json?status=" + text;
    HttpResponse response = httpHelper.httpPost(new URI(str));
    //HttpResponse response = httpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/show.json?id=1445500402238066693"));
    //HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/destroy.json?id=1444002043371339785"));
    System.out.println(EntityUtils.toString(response.getEntity()));
  }
}