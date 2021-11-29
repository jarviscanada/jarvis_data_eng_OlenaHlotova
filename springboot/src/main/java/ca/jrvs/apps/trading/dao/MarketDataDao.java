package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import ca.jrvs.apps.trading.model.domain.IexQuote;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MarketDataDao is responsible for getting Quotes from IEX
 */

@Repository
public class MarketDataDao implements CrudRepository<IexQuote, String> {

  private static final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
  private final String IEX_BATCH_URL;

  private Logger logger = LoggerFactory.getLogger(MarketDataDao.class);
  private HttpClientConnectionManager httpClientConnectionManager;

  @Autowired
  public MarketDataDao(HttpClientConnectionManager httpClientConnectionManager, MarketDataConfig marketDataConfig) {
    this.httpClientConnectionManager = httpClientConnectionManager;
    IEX_BATCH_URL = marketDataConfig.getHost() + IEX_BATCH_PATH + marketDataConfig.getToken();
  }


  /**
   * Get an IexQuote (helper method findAllById)
   * @param ticker
   * @throws DataRetrievalFailureException if HTTP request failed
   * @throws IllegalArgumentException if a given ticker is invalid
   */
  @Override
  public Optional<IexQuote> findById(String ticker) {
    Optional<IexQuote> iexQuote;
    List<IexQuote> quotes = findAllById(Collections.singletonList(ticker));

    if (quotes.size() == 0) {
      return Optional.empty();
    } else if (quotes.size() == 1) {
      iexQuote = Optional.of(quotes.get(0));
    } else {
      throw new DataRetrievalFailureException("Unexpected number of quotes");
    }
    return iexQuote;
  }

  /**
   * Get quotes from IEX
   * @param tickers is a list of tickers
   * @return a list of IexQuote object
   * @throws DataRetrievalFailureException if HTTP request failed
   * @throws IllegalArgumentException if any ticker is invalid or is empty
   */
  @Override
  public List<IexQuote> findAllById(Iterable<String> tickers) {
    List<IexQuote> quotes = new ArrayList<>();
    IexQuote iexQuote;
    //get tickers' IDs and create URL
    String result = "";
    int counter = 0;
    for(String a : tickers) {
      result += a + ",";
      counter ++;
    }
    result = result.toLowerCase(Locale.ROOT);
    String new_result = result.substring(0, result.length()-1);
    String url = String.format(IEX_BATCH_URL, new_result);
    //HTTP response
    String response = null;

    try {
      response = executeHttpGet(url)
            .orElseThrow(() -> new IllegalArgumentException("Invalid ticker"));
    } catch (IOException e) {
      logger.debug("Invalid URL", e);
    } catch (URISyntaxException e) {
      logger.debug("HTTP request failed", e);
      throw new DataRetrievalFailureException(e.toString());
    }

    JSONObject IexQuotesJson = new JSONObject(response);
    if (IexQuotesJson.length() == 0) {
      throw new IllegalArgumentException("Invalid ticker");
    } else if (IexQuotesJson.length() != counter) {
      throw new IllegalArgumentException("HTTP request failed");
    }
    //separate objects by tickers
    String q = "quote";
    for (String t : tickers) {
      JSONObject o = IexQuotesJson.getJSONObject(t);
      Object n = o.get(q);
      try {
        iexQuote = toObjectFromJson(n.toString(), IexQuote.class);
        quotes.add(iexQuote);
      } catch (IOException e) {
        logger.debug("Invalid Input",e);
      }
    }
    System.out.println(quotes);
    return quotes;
  }

  public static <T> T toObjectFromJson(String json, Class cl)
      throws IOException {
    ObjectMapper m = new ObjectMapper();
    return (T) m.readValue(json, cl);
  }

  /**
   * Execute a get and return http entity/body as a string
   * @param ur resource URL
   * @return http response or Optional.empty for 404 response
   * @throws DataRetrievalFailureException if Http failed or status code is unexpected
   */
  private Optional<String> executeHttpGet(String ur) throws IOException, URISyntaxException {
    Optional<String> result;
    URI uri = new URI(ur);
    CloseableHttpClient con = getHttpClient();
    HttpUriRequest request = new HttpGet(uri);
    HttpResponse response = con.execute(request);
    if (response.getStatusLine().getStatusCode() == 404) {
      return Optional.empty();
    } else if (response.getStatusLine().getStatusCode() == 200) {
      String r = new BufferedReader(new InputStreamReader(response.getEntity().getContent()))
          .lines().parallel().collect(Collectors.joining("\n"));
      result = Optional.of(r);
      return result;
  }else {
      throw new DataRetrievalFailureException("Http failed or status code is unexpected");
    }
  }

  /**
   * Borrow an HTTP client from the httpClientManager
   * @return a httpClient
   */
  private CloseableHttpClient getHttpClient () {
    return HttpClients.custom()
        .setConnectionManager(httpClientConnectionManager)
        //prevent connectionManager shutdown when calling httpClient.close()
        .setConnectionManagerShared(true)
        .build();
  }

//  public static void main(String[] args) throws IOException {
//    MarketDataConfig m = new MarketDataConfig();
//    m.setHost("https://cloud.iexapis.com/v1");
//    m.setToken("pk_ebca0853d10b4040ae12fea1f5445f52");
//    MarketDataDao tt = new MarketDataDao(null, m);
//    List <String> aa = new ArrayList<String>();
//    aa.add("AAPL");
//    aa.add("TSLA");
//    String ticker = aa.toString();
//    Iterable<String> iterable = aa;
//    List<IexQuote> quoteList = tt.findAllById(iterable);
//    System.out.println(quoteList);
//  }



  @Override
  public boolean existsById(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public Iterable<IexQuote> findAll() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteById(String s) {
    throw new UnsupportedOperationException("Not implemented");

  }

  @Override
  public void delete(IexQuote iexQuote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends IexQuote> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> S save(S s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public <S extends IexQuote> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

}
