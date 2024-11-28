package org.example.evaluations.evaluation.clients;

import org.example.evaluations.evaluation.dtos.FakeStoreCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreCart getCartById(Long cartId) {
       return requestForEntity("https://fakestoreapi.com/carts/{cartId}",HttpMethod.GET,null,FakeStoreCart.class,cartId).getBody();
    }

    public FakeStoreCart[] getCartsByUserId(Long userId) {
        return requestForEntity("https://fakestoreapi.com/carts/user/{userId}",HttpMethod.GET,null,FakeStoreCart[].class,userId).getBody();
    }

    public FakeStoreCart deleteCartById(Long cartId) {
        return requestForEntity("https://fakestoreapi.com/carts/{cartId}",HttpMethod.DELETE,null,FakeStoreCart.class,cartId).getBody();
    }

    public FakeStoreCart updateCart(Long cartId,FakeStoreCart request) {
        return requestForEntity("https://fakestoreapi.com/carts/{cartId}",HttpMethod.PUT,request,FakeStoreCart.class,cartId).getBody();
    }

    public FakeStoreCart addCart(FakeStoreCart request) {
        return requestForEntity("https://fakestoreapi.com/carts",HttpMethod.POST,request,FakeStoreCart.class).getBody();
    }


    private <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
