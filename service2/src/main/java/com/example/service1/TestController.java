package com.example.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServerRequest;

import javax.annotation.PostConstruct;

@RestController
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);

	@GetMapping(value = "/test3")
	public Flux<String> test() {
		logger.info("-------------------->test3");
		return Flux.just("1", "2", "3");
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
