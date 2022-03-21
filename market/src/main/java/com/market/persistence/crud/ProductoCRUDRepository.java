package com.market.persistence.crud;

import com.market.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCRUDRepository extends CrudRepository<Producto, Integer> {

    List<Producto> finByIdCategoria(int idCategoria);

    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);

}
