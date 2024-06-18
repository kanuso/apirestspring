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
        // TODO Auto-generated method stub
       return this.productoRepositorio.findAll();

    }

    @Override
    public Producto buscarProductoPorId(Integer idProducto) {
        // TODO Auto-generated method stub
        Producto  producto = this.productoRepositorio.findById(idProducto).orElse(null);
        return producto;
    }

    @Override
    public Producto GuardarProducto(Producto producto) {
        // TODO Auto-generated method stub

        return this.productoRepositorio.save(producto);
    }

    @Override
    public void EliminarProductoPorId(Integer idProducto) {
        // TODO Auto-generated method stub
        this.productoRepositorio.deleteById(idProducto);
    }

}
