package gm.inventarios.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gm.inventarios.excepcion.RecursoNoEncontradoExcepcion;
import gm.inventarios.modelo.Producto;
import gm.inventarios.servicio.ProductoServicio;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
//este de abajo es para agregar una extencion a la url ejemplo localhost:8080/inventario-app
@RequestMapping("inventario-app")
//este de abajo es para hacer peticiones desde angular
@CrossOrigin(value = "http://localhost:4200")
public class ProductoControlador {

private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(ProductoControlador.class);


@Autowired
private ProductoServicio productoServicio;

@GetMapping("/productos")
public List<Producto> optenerProductos(){
    List<Producto> productos = productoServicio.ListarProductos();
    Logger.info("Productos Optenidos");
    productos.forEach(producto -> Logger.info(producto.toString()));
    return productos;
}

@PostMapping("/productos")
public Producto agregaProducto(@RequestBody Producto producto) {
    Logger.info("Productos a agregar",producto);
    return this.productoServicio.GuardarProducto(producto);


}

@GetMapping("/productos/{id}")
public ResponseEntity<Producto> optenerProductoPorId(
    @PathVariable int id){
        Producto producto = this.productoServicio.buscarProductoPorId(id);
        if (producto != null) {
            return  ResponseEntity.ok(producto);
        }else
      throw  new RecursoNoEncontradoExcepcion("no se encontro el id del producto");
      
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> actualizarProducto(
        @PathVariable int id,
        @RequestBody Producto productoRecibido){
            Producto producto = this.productoServicio.buscarProductoPorId(id);
            producto.setDescripcion(productoRecibido.getDescripcion());
            producto.setPrecio(productoRecibido.getPrecio());
            producto.setExistencia(productoRecibido.getExistencia());
            this.productoServicio.GuardarProducto(producto);
            return ResponseEntity.ok(producto);
    }
    
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Map<String, Boolean>> EliminarProducto(@PathVariable int id){
        Producto producto = productoServicio.buscarProductoPorId(id);
        if (producto == null)
        throw new RecursoNoEncontradoExcepcion("no se encontro el id del producto"+ id);
        this.productoServicio.EliminarProductoPorId(producto.getIdProducto());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
  
}
 