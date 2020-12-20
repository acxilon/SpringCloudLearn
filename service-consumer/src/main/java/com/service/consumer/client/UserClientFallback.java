package com.service.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component//注册到spring容器中
public class UserClientFallback implements UserClient{
    @Override
    public String queryUserById(Long id) {
        User user = new User();
        user.setUsername("服务器正忙,请稍后再试试!");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
