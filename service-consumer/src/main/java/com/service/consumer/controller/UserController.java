package com.service.consumer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.service.consumer.pojo.User;
import com.service.consumer.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("consume")
@DefaultProperties(defaultFallback = "fallbackMethod")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public List<User> consume(@RequestParam("ids") List<Long> ids){

        return this.userService.queryUserByIds(ids);
    }

    @GetMapping("/get")
    @ResponseBody
    //@HystrixCommand(fallbackMethod = "queryUserByIdsFallBack")
    @HystrixCommand
    public String queryUserByIds(@RequestParam("ids") List<Long> ids){
        List<User> users = this.userService.queryUserByIds(ids);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(users);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //回退方法的参数和返回类型要和目标方法一致
    public String queryUserByIdsFallBack(List<Long> ids){
        return "服务器正忙，请稍后再试!";
    }

    //默认熔断方法，返回值和所有方法一致，参数不能写
    public String fallbackMethod(){
        return "服务器很忙，等会儿再试试!";
    }
}
