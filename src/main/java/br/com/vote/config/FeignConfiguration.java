package br.com.vote.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.Retryer.Default;

@Configuration
@EnableFeignClients(basePackages = {"br.com.vote.gateway.http"})
public class FeignConfiguration {

  @Value("${feign.connectionTimeout:60000}")
  private long feignConnectionTimeout;

  @Value("${feign.readTimeout:60000}")
  private long feignReadTimeout;

  @Bean
  public Retryer retry() {
    return new Default();
  }

  @Bean
  public Request.Options requestOptions() {
    return new Request.Options(
        feignConnectionTimeout,
        TimeUnit.MILLISECONDS,
        feignReadTimeout,
        TimeUnit.MILLISECONDS,
        Boolean.TRUE);
  }

  @Bean
  public Logger.Level feignLoggerLevel() {
    return Logger.Level.FULL;
  }
}
