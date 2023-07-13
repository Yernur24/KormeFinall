package kz.bit.kormefinall.services;


import kz.bit.kormefinall.dto.ProductDTO;
import kz.bit.kormefinall.mapper.PostMapper;
import kz.bit.kormefinall.models.Product;
import kz.bit.kormefinall.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final PostMapper postMapper;

    public List<ProductDTO> getPosts(){
        return postMapper.toDtoList(productRepository.findAll());
    }

    public ProductDTO getPosts(Long id){
        return postMapper.toDto(productRepository.findById(id).orElse(new Product()));
    }

    public ProductDTO addPosts(ProductDTO course){
        return postMapper.toDto(productRepository.save(postMapper.toModel(course)));
    }


    public ProductDTO updatePosts(ProductDTO course){
        return postMapper.toDto(productRepository.save(postMapper.toModel(course)));
    }

    public void deletePosts(Long id){
        productRepository.deleteById(id);
    }



    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public  void deleteRequest(Long id){
        productRepository.deleteById(id);
    }

    public Product findRequest(Long id){
        return  productRepository.findById(id).orElse(null);
    }
}
