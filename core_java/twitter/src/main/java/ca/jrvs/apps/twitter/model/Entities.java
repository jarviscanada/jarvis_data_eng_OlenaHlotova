package ca.jrvs.apps.twitter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "hashtags",
    "user_mentions"
})

public class Entities {

  @JsonProperty("hashtags")
  public Hashtag[] hashtags;
  @JsonProperty("user_mentions")
  public UserMention[] user_mentions;

  public Hashtag[] getHashtags() {
    return hashtags;
  }

  public void setHashtags(Hashtag[] hashtags) {
    this.hashtags = hashtags;
  }

  public UserMention[] getUser_mentions() {
    return user_mentions;
  }

  public void setUser_mentions(UserMention[] user_mentions) {
    this.user_mentions = user_mentions;
  }
}
