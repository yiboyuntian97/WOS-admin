package com.wangyb.sell.service.impl;

import com.wangyb.sell.dataObject.ProductInfo;
import com.wangyb.sell.dto.CartDTO;
import com.wangyb.sell.enums.ProductSattusEnum;
import com.wangyb.sell.enums.ResultEnum;
import com.wangyb.sell.exception.SellException;
import com.wangyb.sell.repository.ProductInfoRepository;
import com.wangyb.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;



    @Override
    public ProductInfo findByProductId(String productId) {
         return repository.findByProductId(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductSattusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findByProductId(cartDTO.getProductId());
            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    // TODO: 扣库存避免超卖待优化
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findByProductId(cartDTO.getProductId());
            if(productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();

            if(result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findByProductId(productId);
        if(productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductSattusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductSattusEnum.UP.getCode());
        // 更新
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findByProductId(productId);
        if(productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum() == ProductSattusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductSattusEnum.DOWN.getCode());
        // 更新
        return repository.save(productInfo);
    }
}
