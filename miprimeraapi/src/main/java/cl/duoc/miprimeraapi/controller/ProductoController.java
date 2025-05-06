package cl.duoc.miprimeraapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.miprimeraapi.model.Producto;
import cl.duoc.miprimeraapi.model.BaseDeDatos;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable Long id) {
        Producto producto = BaseDeDatos.productos.get(id);
        if (producto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Producto> postProducto(@RequestBody Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isBlank() ||
            producto.getCategoria() == null || producto.getCategoria().isBlank() ||
            producto.getPrecio() == null || producto.getPrecio() < 0 ||
            producto.getStock() == null || producto.getStock() < 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Validar sucursal si viene especificada
        if (producto.getSucursalId() != null &&
            !BaseDeDatos.sucursales.containsKey(producto.getSucursalId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // sucursal inexistente
        }

        producto.setId(++BaseDeDatos.contadorProducto);
        BaseDeDatos.productos.put(producto.getId(), producto);
        return new ResponseEntity<>(producto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getTodosProductos() {
        return new ResponseEntity<>(
            new ArrayList<>(BaseDeDatos.productos.values()), HttpStatus.OK
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> putProducto(@PathVariable Long id, @RequestBody Producto request) {
        Producto existente = BaseDeDatos.productos.get(id);
        if (existente == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (request.getNombre() == null || request.getCategoria() == null ||
            request.getPrecio() == null || request.getStock() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (request.getSucursalId() != null &&
            !BaseDeDatos.sucursales.containsKey(request.getSucursalId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        existente.setNombre(request.getNombre());
        existente.setCategoria(request.getCategoria());
        existente.setPrecio(request.getPrecio());
        existente.setStock(request.getStock());
        existente.setSucursalId(request.getSucursalId());

        return new ResponseEntity<>(existente, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> patchProducto(@PathVariable Long id, @RequestBody Producto request) {
        Producto existente = BaseDeDatos.productos.get(id);
        if (existente == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if (request.getNombre() != null && !request.getNombre().isBlank())
            existente.setNombre(request.getNombre());

        if (request.getCategoria() != null && !request.getCategoria().isBlank())
            existente.setCategoria(request.getCategoria());

        if (request.getPrecio() != null && request.getPrecio() >= 0)
            existente.setPrecio(request.getPrecio());

        if (request.getStock() != null && request.getStock() >= 0)
            existente.setStock(request.getStock());

        if (request.getSucursalId() != null) {
            if (!BaseDeDatos.sucursales.containsKey(request.getSucursalId())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            existente.setSucursalId(request.getSucursalId());
        }

        return new ResponseEntity<>(existente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!BaseDeDatos.productos.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BaseDeDatos.productos.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
