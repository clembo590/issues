package com.example;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import feign.RequestLine;
import feign.hystrix.HystrixFeign;
import feign.hystrix.HystrixFeign.Builder;

public class ShouldWork {

    private final Builder sessionRestClient = HystrixFeign.builder()
            .setterFactory((target, method) -> HystrixCommand.Setter
                    .withGroupKey(HystrixCommandGroupKey.Factory.asKey(target.name()))
                    .andCommandKey(HystrixCommandKey.Factory.asKey(method.getAnnotation(RequestLine.class).value()))
                    .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withMaxQueueSize(10).withCoreSize(10))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5000)));

}