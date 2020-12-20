package com.service.consumer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.consumer.client.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user2")
public class User2Controller {
    @Resource
    private UserClient userClient;

    @GetMapping("/getUser")
    public String queryUserById(Long id){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(userClient.queryUserById(id));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
