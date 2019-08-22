package com.wangyb.sell.controller;

import com.wangyb.sell.dataObject.ProductInfo;
import com.wangyb.sell.dto.OrderDTO;
import com.wangyb.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    /**
     * 商品列表
     * @param page
     * @param size
     * @param map
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page, @RequestParam(value = "size",defaultValue = "10") Integer size, Map<String,Object> map) {
        PageRequest request = new PageRequest(page - 1,size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/product/list",map);
    }

    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,Map<String,Object> map) {
        try{
            productService.onSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("order/common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("order/common/success",map);
    }

    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,Map<String,Object> map) {
        try{
            productService.offSale(productId);
        }catch (Exception e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("order/common/error",map);
        }
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("order/common/success",map);
    }

}
