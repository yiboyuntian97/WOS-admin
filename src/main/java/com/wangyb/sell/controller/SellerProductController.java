package com.wangyb.sell.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.wangyb.sell.dataObject.ProductCategory;
import com.wangyb.sell.dataObject.ProductInfo;
import com.wangyb.sell.dto.OrderDTO;
import com.wangyb.sell.enums.ProductSattusEnum;
import com.wangyb.sell.exception.SellException;
import com.wangyb.sell.form.ProductForm;
import com.wangyb.sell.service.CategoryService;
import com.wangyb.sell.service.ProductService;
import com.wangyb.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId",required = false) String productId,Map<String,Object> map){
        if(!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findByProductId(productId);
            map.put("productInfo",productInfo);
        }

        // 查询所有类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("order/product/index",map);
    }

    /**
     * 保存/更新
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
//    @CachePut(cacheNames = "product",key = "123")
    @CacheEvict(cacheNames = "product",key = "123")
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,Map<String,Object> map) {
        if(bindingResult.hasErrors()) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/product/index");
            return new ModelAndView("/order/common/error",map);
        }
        ProductInfo productInfo = new ProductInfo();
        try{
            // 如果productid为空，则新增
            if(!StringUtils.isEmpty(form.getProductId())){
                productInfo = productService.findByProductId(form.getProductId());
            }else {
                form.setProductId(KeyUtil.genUniqueKey());
            }
        BeanUtils.copyProperties(form,productInfo);
        productService.save(productInfo);
       }catch (SellException e){
           map.put("msg",e.getMessage());
           map.put("url","/sell/seller/product/index");
           return new ModelAndView("/order/common/error",map);
       }
        map.put("url","/sell/seller/product/list");
       return new ModelAndView("order/common/success",map);
    }

}
