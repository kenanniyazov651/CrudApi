package com.rest.app.service.Impl;

import com.rest.app.model.Product;
import com.rest.app.repository.ProductRepository;
import com.rest.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
private ProductRepository productRepository;



    public Product getById( int id) {
        Product product=this.productRepository.findById(id);

     return product;





    }

}
