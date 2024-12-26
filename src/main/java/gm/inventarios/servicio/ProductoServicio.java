package gm.inventarios.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gm.inventarios.modelo.Producto;
import gm.inventarios.repositorio.ProductoRepositorio;

@Service
public class ProductoServicio implements IProductoServicio{

    @Autowired

    private ProductoRepositorio productoRepositorio;



    @Override
    public List<Producto> ListarProductos() {
        // Retornar solo los productos que tienen estado 1
        return this.productoRepositorio.findByEstado(1);
    }
    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        // TODO Auto-generated method stub
        Producto  producto = this.productoRepositorio.findById(idProducto).orElse(null);
        return producto;
    }


    @Override
public Producto GuardarProducto(Producto producto) {
    try {
        if (producto.getEstado() == null) {
            producto.setEstado(1); // Establecer estado por defecto si es necesario
        }
        return this.productoRepositorio.save(producto);
    } catch (Exception e) {
      
        throw e; 
    }
}



    @Override
public void EliminarProductoPorId(Integer idProducto) {
  
    Producto producto = this.productoRepositorio.findById(idProducto).orElse(null);
    
    if (producto != null) {
        
        producto.setEstado(0);
        
        this.productoRepositorio.save(producto);
    }
}
}
