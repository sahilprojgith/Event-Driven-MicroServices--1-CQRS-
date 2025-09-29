package com.sahil.ProductService.command.api.events;

import com.sahil.ProductService.command.api.data.Product;
import com.sahil.ProductService.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {


    private ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception{

        Product product = new Product();
        BeanUtils.copyProperties(event,product);
        productRepository.save(product);

        throw new Exception("Exception Occured");
    }


    @ExceptionHandler // of Axon framework not spring Framework
    public void handle(Exception exception) throws Exception{
        throw exception;
    }

}
