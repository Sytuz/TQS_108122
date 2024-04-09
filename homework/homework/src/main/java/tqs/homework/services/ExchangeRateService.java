package tqs.homework.services;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {
    
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/960f5a9a59d6249b6dc185bd/latest/EUR";
    
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private Map<String, Double> exchangeRates;

    public ExchangeRateService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Cacheable(value = "exchangeRateCache")
    public Map<String, Double> cacheExchangeRates() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
        } catch (Exception e) {
            // Handle exception
        }
        
        String exchangeRateJson = restTemplate.getForObject(API_URL, String.class);

        return parseExchangeRates(exchangeRateJson);
    }

    public Map<String, Double> getExchangeRates()  {
        if (exchangeRates == null) {
            exchangeRates = cacheExchangeRates();
        }
        return exchangeRates;
    }

    private Map<String, Double> parseExchangeRates(String exchangeRateJson) {
        exchangeRates = new HashMap<>();
        try {
            JsonNode rootNode = objectMapper.readTree(exchangeRateJson);
            JsonNode conversionRatesNode = rootNode.path("conversion_rates");
            for (JsonNode currencyNode : conversionRatesNode) {
                String currency = currencyNode.fieldNames().next();
                double rate = currencyNode.get(currency).asDouble();
                exchangeRates.put(currency, rate);
            }
        } catch (Exception e) {
            // Handle exception
        }
        return exchangeRates;
    }

    @CacheEvict(value = "exchangeRateCache", allEntries = true)
    public void evictCache() {
        // Clear cache
        exchangeRates = null;
        getExchangeRates();
    }
}
