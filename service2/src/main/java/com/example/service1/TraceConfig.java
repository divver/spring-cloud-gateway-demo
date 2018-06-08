package com.example.service1;

import brave.sampler.Sampler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import zipkin2.Span;
import zipkin2.reporter.Reporter;

/**
<<<<<<< HEAD
 * @Auther: xiaobin.gu
 * @Date: 18/6/7 17:10
 */
@Configuration
public class TraceConfig {
    @Primary
    @Bean
    public Reporter<Span> reporter(){
        return new LogReporter();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    static class LogReporter implements Reporter<Span> {
        private static final Logger looger = LoggerFactory.getLogger(LogReporter.class);

        @Override
        public void report(Span span) {
            looger.info("trace: {}", span);
        }

    }
=======
 * Created by divver on 2018/6/7.
 */
@Configuration
public class TraceConfig {

  @Primary
  @Bean
  public Reporter<Span> reporter(){
    return new LogReporter();
  }

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }

  static class LogReporter implements Reporter<Span> {
    private static final Logger looger = LoggerFactory.getLogger(LogReporter.class);

    @Override
    public void report(Span span) {
      looger.info("trace: {}", span);
    }

  }

//  @Bean
//  public ZipkinRestTemplateCustomizer myCustomizer(){
//    return new ZipkinRestTemplateCustomizer(){
//
//      @Override
//      public void customize(RestTemplate restTemplate) {
//        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
//          @Override
//          public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//            System.out.print(new String(body));
//            return null;
//          }
//        });
//      }
//
//    };
//  }

>>>>>>> 27e7068332c1828db8421d0056c3fd94d033b1d7
}
