package com.singh.rupesh.soapService;

import com.singh.rupesh.demo.soap_service_demo.GetCountryRequest;
import com.singh.rupesh.demo.soap_service_demo.GetCountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * This class acts as a service endpoint, which is a POJO with a few Spring WS annotations to handle
 * the incoming SOAP requests.
 */
@Endpoint
//The @Endpoint annotation registers the class with Spring WS as a potential
// candidate for processing incoming SOAP messages.
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://rupesh.singh.com/demo/soap-service-demo";
    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    /*The @PayloadRoot annotation is used by Spring WS to pick the handler method,
    based on the message’s namespace and localPart.
    The @RequestPayload annotation indicates that the incoming message will be mapped
    to the method’s request parameter.
    The @ResponsePayload annotation makes Spring WS map the returned value to the response payload.
    */
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }
}