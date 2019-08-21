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
        Page<ProductInfo> ProductInfoPage = productService.findAll(request);
        map.put("ProductInfoPage",ProductInfoPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/product/list",map);
    }

}
