package com.sahil.ProductService.query.api.projections;

import com.sahil.ProductService.command.api.data.Product;
import com.sahil.ProductService.command.api.data.ProductRepository;
import com.sahil.ProductService.command.api.model.ProductRestModel;
import com.sahil.ProductService.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjections {

    private ProductRepository productRepository;

    public ProductProjections(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product> products = productRepository.findAll();


        List<ProductRestModel> productRestModels =
                products.stream().map(product -> ProductRestModel
                        .builder()
                        .quantity(product.getQuantity())
                        .price(product.getPrice())
                        .name(product.getName())
                        .build())
                        .collect(Collectors.toList());

        return productRestModels;

    }

}
