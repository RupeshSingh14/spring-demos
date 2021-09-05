package com.singh.rupesh.soapService;

import com.singh.rupesh.demo.soap_service_demo.Country;
import com.singh.rupesh.demo.soap_service_demo.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rupesh Singh
 * This class acts as a repository for this service. It contains few hard coded country data for completion
 * of this demo but can be designed to subsitute with a repository interacting with DB's.
 */
@Component
public class CountryRepository {
    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country india = new Country();
        india.setName("India");
        india.setCapital("Delhi");
        india.setCurrency(Currency.EUR);
        india.setPopulation(1300000000);
        countries.put(india.getName(), india);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(23457700);
        countries.put(uk.getName(), uk);

        Country israel = new Country();
        israel.setName("Israel");
        israel.setCapital("Jerusalem");
        israel.setCurrency(Currency.PLN);
        israel.setPopulation(9000000);
        countries.put(israel.getName(), israel);
    }

    public Country findCountry(String name) {
        Assert.notNull(name, "The country's name must not be null");
        return countries.get(name);
    }
}
