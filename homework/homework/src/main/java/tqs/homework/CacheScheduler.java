package tqs.homework;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tqs.homework.services.ExchangeRateService;

@Component
@EnableScheduling
public class CacheScheduler {

    private final ExchangeRateService exchangeRateService;

    public CacheScheduler(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @Scheduled(fixedRate = 6 * 60 * 60 * 1000) // 6 hours in milliseconds
    public void reloadCache()  {
        exchangeRateService.evictCache();
    }
}

