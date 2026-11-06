package no.hvl.dat108.f24a.solution;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.kucoin.sdk.KucoinClientBuilder;
import com.kucoin.sdk.KucoinRestClient;

@Service
public class PrisfinnerKucoin implements Prisfinner {

	@Override
	public double finnPris(String kryptoCode, String fiatCode) 
			throws IOException {
		
		KucoinRestClient kucoinRestklient = new KucoinClientBuilder()
				.withBaseUrl("https://openapi-v2.kucoin.com")
				.buildRestClient();

		return kucoinRestklient.currencyAPI()
				.getFiatPrice(fiatCode, kryptoCode)
				.get(kryptoCode).doubleValue();
	}
}
