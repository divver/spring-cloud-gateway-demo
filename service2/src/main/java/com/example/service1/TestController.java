package com.example.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	WebClient webClient;

	@GetMapping(value = "/test3")
	public Flux<String> test() {
		logger.info("-------------------->test3");
		return Flux.just("1", "2", "3");
	}

	@GetMapping(value = "/test31")
	public Flux<String> test31() {
		logger.info("-------------------->test31");
		return webClient.mutate().baseUrl("http://127.0.0.1:8082/test3")
				.build().get().retrieve().bodyToFlux(String.class);
	}

	@GetMapping(value = "/test4")
	public Flux<String> test4() {
		logger.info("-------------------->test4");
		return Flux.just("10", "20", "30");
	}

	@PostMapping(path="/test5", consumes = "application/json")
	public Mono<UUSData> test5(@RequestBody UUSData uusData) {
		logger.info("-------------------->test5" + uusData.toString());
		uusData.setService("test5");
		return Mono.just(uusData);
	}

	@RequestMapping(value = "/startApp")
	public Mono<User> startApp() {
		logger.info("-------------------->startApp");
		return Mono.just(new User(1L, "test"));
	}

}
