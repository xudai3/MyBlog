package com.xd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xd.domain.Article;
import com.xd.domain.User;
import com.xd.response.Response;
import com.xd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        User getUser = userService.findUserByUserId(uid);
        ObjectMapper mapper = new ObjectMapper();
        String res = null;

        try {
            res = mapper.writeValueAsString(getUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resp.success(res);
    }

    @RequestMapping(value = "/v1/users", method = RequestMethod.POST)
    public Response addUser(){
        Response resp = new Response();

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
    public ResponseEntity<List<User>> getUserList(){
        System.out.println("getting user list...");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        List<User> userList = userService.getUserList();

        return new ResponseEntity<List<User>>(userList,responseHeaders, HttpStatus.CREATED);
    }

}
