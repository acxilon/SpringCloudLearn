package com.service.consumer.service;

import com.service.consumer.pojo.User;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;//Eureka客户端，可以获取到服务实例信息

    public List<User> queryUserByIds(List<Long> ids){
        List<ServiceInstance> instances = discoveryClient.getInstances("service-provider");
        ServiceInstance serviceInstance = instances.get(0);
        String baseUrl = "http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/user/";
        List<User> users = new ArrayList<>();
        for (Long id : ids) {
            users.add(restTemplate.getForObject(baseUrl+id,User.class));
        }
        return users;
    }
}
