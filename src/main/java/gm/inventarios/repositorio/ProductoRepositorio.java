package gm.inventarios.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gm.inventarios.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer >{
    List<Producto> findByEstado(Integer estado);

}
