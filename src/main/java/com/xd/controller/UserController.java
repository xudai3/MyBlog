package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.entity.User;
import com.xd.response.Response;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;


@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/v1/users/{uid}", method = RequestMethod.GET)
    public Response getUserInfo(@PathVariable int uid){
        System.out.println("getting user info...");

        Response resp = new Response();
        User getUser = userService.getUserByUserId(uid);

        return resp.success(getUser);
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.POST)
    public Response addUser(@RequestBody User user){
        System.out.println("adding user...");

        Response resp = new Response();
        userService.saveUser(user);

        return resp.success();
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.DELETE)
    public Response deleteUser(){
        Response resp = new Response();

        return resp.success();
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.PUT)
    public Response updateUser(){
        Response resp = new Response();

        return resp.success();
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.GET)
    public Response getUserList(){
        System.out.println("getting user list...");
        Response resp = new Response();
        List<User> userList = userService.listUsers();
        System.out.println(resp.success(userList).toString());
        return resp.success(userList);
    }

}
