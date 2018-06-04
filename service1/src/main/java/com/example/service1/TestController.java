package com.example.service1;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TestController {

	@GetMapping(value = "/test3")
	public Flux<String> test() {
		return Flux.just("1", "2", "3");
	}

	@GetMapping(value = "/startApp")
	public Mono<String> startApp() {
		return Mono.just("1");
	}
}
