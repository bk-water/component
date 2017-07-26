package com.koabs.component.test.controller;

import com.koabs.component.logs.annotation.BizLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author: koabs
 * 2/16/17.
 */
@Controller
@RequestMapping("/test/")
public class TestController {
    /**
     * 支付介绍页面
     * @return
     */
    @RequestMapping(value = "1")
    @ResponseBody
    @BizLog(type = "ss", desc = "ssssss")
    public String payIntroduction(){
        return "成功";
    }
}
