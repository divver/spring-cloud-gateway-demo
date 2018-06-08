package com.example.service1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.RequestContextUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.server.HttpServerRequest;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
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
//		return Flux.just("10", "20", "30");
		return webClient.mutate().baseUrl("http://127.0.0.1:8081/test3")
				.build().get().retrieve().bodyToFlux(String.class);
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


	// -------------post trace to zipkin---------------
	String msg1 = "[{\"traceId\":\"9c90648a941472a2\",\"parentId\":\"9c90648a941472a2\",\"id\":\"bb5ec09262238599\",\"kind\":\"CLIENT\",\"name\":\"post\",\"timestamp\":1528362967903827,\"duration\":25903,\"localEndpoint\":{\"serviceName\":\"credit-api-exposure-service\",\"ipv4\":\"172.20.165.209\"},\"tags\":{\"http.method\":\"POST\",\"http.path\":\"/test4\"}},{\"traceId\":\"9c90648a941472a2\",\"id\":\"9c90648a941472a2\",\"kind\":\"SERVER\",\"name\":\"post\",\"timestamp\":1528362967896074,\"duration\":79012,\"localEndpoint\":{\"serviceName\":\"credit-api-exposure-service\",\"ipv4\":\"172.20.165.209\"},\"tags\":{\"http.method\":\"POST\",\"http.path\":\"/test-service-2/test4\"}}]";
	String msg2 = "[{\"traceId\":\"9c90648a941472a2\",\"parentId\":\"bb5ec09262238599\",\"id\":\"cb498efcda01d198\",\"kind\":\"CLIENT\",\"name\":\"get\",\"timestamp\":1528362967925946,\"duration\":16086,\"localEndpoint\":{\"serviceName\":\"test-service-2\",\"ipv4\":\"172.20.165.209\"},\"tags\":{\"http.method\":\"GET\",\"http.path\":\"/test3\"}},{\"traceId\":\"9c90648a941472a2\",\"parentId\":\"9c90648a941472a2\",\"id\":\"bb5ec09262238599\",\"kind\":\"SERVER\",\"name\":\"post /test4\",\"timestamp\":1528362967909189,\"duration\":63795,\"localEndpoint\":{\"serviceName\":\"test-service-2\",\"ipv4\":\"172.20.165.209\"},\"remoteEndpoint\":{\"ipv4\":\"127.0.0.1\",\"port\":56734},\"tags\":{\"http.method\":\"POST\",\"http.path\":\"/test4\",\"mvc.controller.class\":\"TestController\",\"mvc.controller.method\":\"test4\"},\"shared\":true}]";
	String msg3 = "[{\"traceId\":\"9c90648a941472a2\",\"parentId\":\"bb5ec09262238599\",\"id\":\"cb498efcda01d198\",\"kind\":\"SERVER\",\"name\":\"get /test3\",\"timestamp\":1528362967934202,\"duration\":24685,\"localEndpoint\":{\"serviceName\":\"test-service-1\",\"ipv4\":\"172.20.165.209\"},\"remoteEndpoint\":{\"ipv4\":\"127.0.0.1\",\"port\":56735},\"tags\":{\"http.method\":\"GET\",\"http.path\":\"/test3\",\"mvc.controller.class\":\"TestController\",\"mvc.controller.method\":\"test\"},\"shared\":true}]";

	String msg11 = "[{\"traceId\":\"3c30f18ec25bb059\",\"parentId\":\"3c30f18ec25bb059\",\"id\":\"df1c6a9fbe738482\",\"kind\":\"CLIENT\",\"name\":\"post\",\"timestamp\":1528421862676980,\"duration\":1357173,\"localEndpoint\":{\"serviceName\":\"credit-api-exposure-service\",\"ipv4\":\"172.20.165.209\"},\"tags\":{\"http.method\":\"POST\",\"http.path\":\"/test4\"}},\n" +
			"{\"traceId\":\"3c30f18ec25bb059\",\"id\":\"3c30f18ec25bb059\",\"kind\":\"SERVER\",\"name\":\"post\",\"timestamp\":1528421860474737,\"duration\":4415886,\"localEndpoint\":{\"serviceName\":\"credit-api-exposure-service\",\"ipv4\":\"172.20.165.209\"},\"tags\":{\"http.method\":\"POST\",\"http.path\":\"/test-service-2/test4\"}},\n" +
			"{\"traceId\":\"3c30f18ec25bb059\",\"parentId\":\"df1c6a9fbe738482\",\"id\":\"2d734219fa89b7de\",\"kind\":\"CLIENT\",\"name\":\"get\",\"timestamp\":1528421863505925,\"duration\":1204769,\"localEndpoint\":{\"serviceName\":\"test-service-2\",\"ipv4\":\"172.20.165.209\"},\"tags\":{\"http.method\":\"GET\",\"http.path\":\"/test3\"}},\n" +
			"{\"traceId\":\"3c30f18ec25bb059\",\"parentId\":\"3c30f18ec25bb059\",\"id\":\"df1c6a9fbe738482\",\"kind\":\"SERVER\",\"name\":\"post /test4\",\"timestamp\":1528421863331307,\"duration\":1555194,\"localEndpoint\":{\"serviceName\":\"test-service-2\",\"ipv4\":\"172.20.165.209\"},\"remoteEndpoint\":{\"ipv4\":\"127.0.0.1\",\"port\":58987},\"tags\":{\"http.method\":\"POST\",\"http.path\":\"/test4\",\"mvc.controller.class\":\"TestController\",\"mvc.controller.method\":\"test4\"},\"shared\":true},\n" +
			"{\"traceId\":\"3c30f18ec25bb059\",\"parentId\":\"df1c6a9fbe738482\",\"id\":\"2d734219fa89b7de\",\"kind\":\"SERVER\",\"name\":\"get /test3\",\"timestamp\":1528421864522670,\"duration\":279418,\"localEndpoint\":{\"serviceName\":\"test-service-1\",\"ipv4\":\"172.20.165.209\"},\"remoteEndpoint\":{\"ipv4\":\"127.0.0.1\",\"port\":58988},\"tags\":{\"http.method\":\"GET\",\"http.path\":\"/test3\",\"mvc.controller.class\":\"TestController\",\"mvc.controller.method\":\"test\"},\"shared\":true}]";

	@RequestMapping("/test6")
	public Mono<String> test6(){
		WebClient webClient = WebClient.create("http://127.0.0.1:9411");
		return webClient
				.post()
				.uri("/api/v2/spans")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(JsonUtil.fromJson(msg11, List.class)), List.class)
				.retrieve().bodyToMono(Void.class).then(Mono.just("ok"));
	}

	public static void main(String[] args){
		TestController testController = new TestController();
		testController.test6().block();
	}
}
