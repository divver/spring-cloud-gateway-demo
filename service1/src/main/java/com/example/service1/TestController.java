package com.example.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@GetMapping(value = "/test3")
	public Flux<String> test() {
		logger.info("-------------------->test3");
		return Flux.just("1", "2", "3");
	}

	@GetMapping(value = "/startApp")
	public Mono<String> startApp() {
		return Mono.just("1");
	}
}
