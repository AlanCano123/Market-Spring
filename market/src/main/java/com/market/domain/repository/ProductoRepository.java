package com.market.domain.repository;

import com.market.persistence.crud.ProductoCRUDRepository;
import com.market.domain.Product;
import com.market.persistence.entity.Producto;
import com.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired
    private ProductoCRUDRepository productoCRUDRepository;
    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getall(){
        List<Producto> productos= (List<Producto>) productoCRUDRepository.findAll();
        return mapper.toProducts(productos);
    }


    public void delete(int productId){
    productoCRUDRepository.deleteById(productId);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto>productos =productoCRUDRepository.finByIdCategoria(categoryId);
        return Optional.of(mapper.toProducts(productos));

    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos= productoCRUDRepository.findByCantidadStockLessThanAndEstado(quantity,true);

        return productos.map(prods-> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCRUDRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProduct(product);
        return mapper.toProduct(productoCRUDRepository.save(producto));
    }



}
