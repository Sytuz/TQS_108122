package tqs.lab2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(MockitoExtension.class)
public class TotalValueTest {
    @Mock
    IStockMarket stockMarket;

    @InjectMocks
    StocksPortfolio stocksPortfolio = new StocksPortfolio(stockMarket);

    @Test
    public void testValueTest() {
        when(stockMarket.lookUpPrice("APPL")).thenReturn(100.0);
        when(stockMarket.lookUpPrice("GME")).thenReturn(200.0);
        when(stockMarket.lookUpPrice("TSLA")).thenReturn(300.0);

        stocksPortfolio.addStock(new Stock("APPL", 2));
        stocksPortfolio.addStock(new Stock("GME", 1));
        stocksPortfolio.addStock(new Stock("TSLA", 3));

        assertThat(1300.0, equalTo(stocksPortfolio.totalValue()));
        verify(stockMarket, times(3)).lookUpPrice(anyString());
    }
}
