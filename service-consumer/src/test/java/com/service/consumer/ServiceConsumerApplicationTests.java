package com.service.consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient;

import javax.annotation.Resource;

@SpringBootTest
class ServiceConsumerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private RibbonLoadBalancerClient client;
    @Test
    public void test(){
        for (int i = 0; i < 50; i++) {
            ServiceInstance instance = this.client.choose("service-provider");
            System.out.println(instance.getHost()+":"+instance.getPort());
        }
    }

}
