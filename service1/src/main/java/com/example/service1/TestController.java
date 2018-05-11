package com.example.service1;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class TestController {

	@GetMapping(value = "/test3")
	public Flux<String> test() {
		return Flux.just("1", "2", "3");
	}
}
