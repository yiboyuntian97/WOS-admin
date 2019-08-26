package com.wangyb.sell.controller;

import com.wangyb.sell.constant.CookieConstant;
import com.wangyb.sell.constant.RedisConstant;
import com.wangyb.sell.dataObject.SellerInfo;
import com.wangyb.sell.enums.ResultEnum;
import com.wangyb.sell.service.SellerService;
import com.wangyb.sell.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;



    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid")String openid, HttpServletResponse response,Map<String, Object> map){
        //1.openid去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if(sellerInfo == null) {
            map.put("msg", ResultEnum.LOGIN_FAAIL.getMessage());
            map.put("url","/sell/seller/order/list");
            return new ModelAndView("order/common/error",map);
        }
        //2.设置token志redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;

        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

        //3.设置token至cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);
        return new ModelAndView("redirect:"+ "http://sell.com" +"/seller/order/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletResponse response, HttpServletRequest request,Map<String,Object> map){
        //1.从cookie查询
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if(cookie != null) {
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
            //3.清除cookie
            CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        }
       map.put("msg",ResultEnum.LOGOUT_SUCCESS.getMessage());
        map.put("url","/sell/seller/order/list");
        return new ModelAndView("order/common/success",map);

    }

}
