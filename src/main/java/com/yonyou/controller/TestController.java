package com.yonyou.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("test")
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public Object getTest(){
        Map map = new HashMap();
        map.put("code","1001");
        map.put("result","success");
        return map;
    }


}
