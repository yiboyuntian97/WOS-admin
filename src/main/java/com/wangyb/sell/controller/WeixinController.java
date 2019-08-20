package com.wangyb.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

//    Logger log = LoggerFactory.getLogger(WeixinController.class);

    @GetMapping("auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。code={}",code);
    }
}
