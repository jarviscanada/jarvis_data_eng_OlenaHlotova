package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.config.MarketDataConfig;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.http.conn.HttpClientConnectionManager;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class tt {


  public static void main(String[] args) {

    final String IEX_BATCH_PATH = "/stock/market/batch?symbols=%s&types=quote&token=";
    final String IEX_BATCH_URL = "https://cloud.iexapis.com/v1" + IEX_BATCH_PATH + "pk_ebca0853d10b4040ae12fea1f5445f52";
    String s = "{\"AAPL\":{\"quote\":{\"avgTotalVolume\":66596865,\"calculationPrice\":\"tops\",\"change\":0.92,\"changePercent\":0.00613,\"close\":null,\"closeSource\":\"official\",\"closeTime\":null,\"companyName\":\"Apple Inc\",\"currency\":\"USD\",\"delayedPrice\":null,\"delayedPriceTime\":null,\"extendedChange\":null,\"extendedChangePercent\":null,\"extendedPrice\":null,\"extendedPriceTime\":null,\"high\":null,\"highSource\":null,\"highTime\":null,\"iexAskPrice\":150.93,\"iexAskSize\":300,\"iexBidPrice\":150.91,\"iexBidSize\":600,\"iexClose\":150.92,\"iexCloseTime\":1637093813649,\"iexLastUpdated\":1637093813649,\"iexMarketPercent\":0.01256296181932889,\"iexOpen\":150.92,\"iexOpenTime\":1637093813649,\"iexRealtimePrice\":150.92,\"iexRealtimeSize\":4,\"iexVolume\":525984,\"lastTradeTime\":1637093813649,\"latestPrice\":150.92,\"latestSource\":\"IEX real time price\",\"latestTime\":\"3:16:53 PM\",\"latestUpdate\":1637093813649,\"latestVolume\":null,\"low\":null,\"lowSource\":null,\"lowTime\":null,\"marketCap\":2476053435240,\"oddLotDelayedPrice\":null,\"oddLotDelayedPriceTime\":null,\"open\":null,\"openTime\":null,\"openSource\":\"official\",\"peRatio\":13.44,\"previousClose\":150,\"previousVolume\":59222803,\"primaryExchange\":\"NASDAQ\",\"symbol\":\"AAPL\",\"volume\":null,\"week52High\":157.03,\"week52Low\":111.9,\"ytdChange\":0.1435535082690689,\"isUSMarketOpen\":true}},\"MSFT\":{\"quote\":{\"avgTotalVolume\":23683917,\"calculationPrice\":\"tops\",\"change\":3.55,\"changePercent\":0.01056,\"close\":null,\"closeSource\":\"official\",\"closeTime\":null,\"companyName\":\"Microsoft Corporation\",\"currency\":\"USD\",\"delayedPrice\":null,\"delayedPriceTime\":null,\"extendedChange\":null,\"extendedChangePercent\":null,\"extendedPrice\":null,\"extendedPriceTime\":null,\"high\":null,\"highSource\":null,\"highTime\":null,\"iexAskPrice\":339.63,\"iexAskSize\":100,\"iexBidPrice\":323.9,\"iexBidSize\":117,\"iexClose\":339.62,\"iexCloseTime\":1637093815044,\"iexLastUpdated\":1637093815044,\"iexMarketPercent\":0.027879069406262,\"iexOpen\":339.62,\"iexOpenTime\":1637093815044,\"iexRealtimePrice\":339.62,\"iexRealtimeSize\":100,\"iexVolume\":397792,\"lastTradeTime\":1637093815044,\"latestPrice\":339.62,\"latestSource\":\"IEX real time price\",\"latestTime\":\"3:16:55 PM\",\"latestUpdate\":1637093815044,\"latestVolume\":null,\"low\":null,\"lowSource\":null,\"lowTime\":null,\"marketCap\":2549860318391,\"oddLotDelayedPrice\":null,\"oddLotDelayedPriceTime\":null,\"open\":null,\"openTime\":null,\"openSource\":\"official\",\"peRatio\":37.99,\"previousClose\":336.07,\"previousVolume\":16723009,\"primaryExchange\":\"NASDAQ\",\"symbol\":\"MSFT\",\"volume\":null,\"week52High\":338.79,\"week52Low\":206.81,\"ytdChange\":0.5314135279620372,\"isUSMarketOpen\":true}}}\n";
    JSONObject IexQuotesJson = new JSONObject(s);

    List <String> aa = new ArrayList<String>();
    aa.add("AAPL");
    aa.add("TSLA");
    String ticker = aa.toString();
    Iterable<String> iterable = aa;
    String result = "";
    for(String a : aa) {
      result += a + ",";
    }
    result = result.toLowerCase(Locale.ROOT);
    String new_result = result.substring(0, result.length()-1);
    System.out.println(new_result);
    String url = String.format(IEX_BATCH_URL, new_result);

    System.out.println(url);
  }

}
