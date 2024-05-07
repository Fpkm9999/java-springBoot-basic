package com.example.springbootbasic1.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    private static final Logger log = LogManager.getLogger(HelloController.class);

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "heelo!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String hellMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; // "hello string"
    }

    // 요청 파라미터에 username, age가 있는 경우 예시
    @GetMapping("hello-ex1")
    @ResponseBody
    public String ex1(@RequestParam(value = "username") String username, @RequestParam(value = "age") int age) {
        log.info("ex1()...");
        return "hello " + username + "_" + age; // "hello string"
    }

    @ResponseBody
    @RequestMapping("/request-param-ver1")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @GetMapping("/request-param-v2")
    public String requestParamV3(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("requestParamV3()...");
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
    ) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}