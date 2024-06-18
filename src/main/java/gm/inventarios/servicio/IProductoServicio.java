package gm.inventarios.servicio;

import java.util.List;

import gm.inventarios.modelo.Producto;

public interface IProductoServicio {
    
public List <Producto> ListarProductos();

public Producto  buscarProductoPorId(Integer idProducto);
 
public Producto  GuardarProducto(Producto producto);

public void EliminarProductoPorId(Integer idProducto);
}
