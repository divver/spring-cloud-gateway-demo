package com.example.apigateway;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.gateway.mvc.ProxyExchange;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
//import java.util.function.Function;

//@RestController
public class ApiRouterController {

    @Value("${remote.home}")
    private URI home;

//    @GetMapping(path="/test")
//    public ResponseEntity<Object> proxy(ProxyExchange<Object> proxy) throws Exception {
//        return proxy.uri(home.toString() + "/image/png")
//                .get(header("X-TestHeader", "foobar"));
//    }
//
//    @GetMapping("/test2")
//    public ResponseEntity<Object> proxyFoos(ProxyExchange<Object> proxy) throws Exception {
//        return proxy.uri(home.toString() + "/image/webp").get(header("X-AnotherHeader", "baz"));
//    }
//
//    private Function<ResponseEntity<Object>, ResponseEntity<Object>> header(String key,
//                                                                            String value) {
//        return response -> ResponseEntity.status(response.getStatusCode())
//                .headers(response.getHeaders()).header(key, value)
//                .body(response.getBody());
//    }
}
