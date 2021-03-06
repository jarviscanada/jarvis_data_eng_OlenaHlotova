package ca.jrvs.apps.twitter.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
    "coordinates",
    "type"
})

public class Coordinates {

  @JsonProperty("coordinates")
  public float[] coordinates;
  @JsonProperty("type")
  public String type;

  @JsonProperty("coordinates")
  public float[] getCoordinates() {
    return coordinates;
  }
  @JsonProperty("coordinates")
  public void setCoordinates(float[] coordinates) {
    this.coordinates = coordinates;
  }

  @JsonProperty("type")
  public String getType() {
    return type;
  }
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }
}
