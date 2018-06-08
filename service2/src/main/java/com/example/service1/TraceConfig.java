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
}
