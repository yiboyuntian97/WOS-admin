package com.wangyb.sell.handler;

import com.wangyb.sell.exception.ResponseBankException;
import com.wangyb.sell.exception.SellException;
import com.wangyb.sell.exception.SellerAuthorizeException;
import com.wangyb.sell.util.ResultVOUtil;
import com.wangyb.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class SellerExceptionHandler {

//    @Autowired
//    private ProjectUrlConfig projectUrlConfig;

    // 拦截登录异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeExcrption() {
        return new ModelAndView( "localhost:8080/sell/seller/order/list");
//        return new ModelAndView("redirect:" + "sell.com/sell/wechat/qrAuthorize/?returnurl=/sell/seller/login");
    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){
        return ResultVOUtil.error(e.getCode(),e.getMessage());
    }

    @ExceptionHandler(value = ResponseBankException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleResponseBankException(){

    }
}
