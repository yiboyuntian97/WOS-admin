package com.wangyb.sell.controller;

import com.wangyb.sell.dataObject.ProductCategory;
import com.wangyb.sell.exception.SellException;
import com.wangyb.sell.form.CategoryForm;
import com.wangyb.sell.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/seller/category")
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 类目列表
     * @param map
     * @return
     */
    @GetMapping("list")
    public ModelAndView list(Map<String,Object> map){
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("order/category/list",map);
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId",required = false)Integer categoryId,Map<String,Object>map) {
        if(categoryId != null) {
            ProductCategory productCategory = categoryService.findByCategoryId(categoryId);
            map.put("category",productCategory);
        }
        return new ModelAndView("order/category/index",map);
    }

    /**
     * 保存/更新
     * @param categoryForm
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm, BindingResult bindingResult,Map<String,Object>map) {
        if(bindingResult.hasErrors()) {
            map.put("msg",bindingResult.getFieldError().getDefaultMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("order/common/error",map);
        }

        ProductCategory productCategory = new ProductCategory();
        try {
            if(categoryForm.getCategoryId() != null) {
                productCategory = categoryService.findByCategoryId(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm,productCategory);
            categoryService.save(productCategory);
        }catch (SellException e){
            map.put("msg",e.getMessage());
            map.put("url","/sell/seller/category/index");
            return new ModelAndView("order/common/error",map);
        }
        map.put("url","/sell/seller/category/list");
        return new ModelAndView("order/common/success",map);
    }
}
