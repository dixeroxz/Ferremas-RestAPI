package cl.duoc.miprimeraapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.duoc.miprimeraapi.model.Sucursal;
import cl.duoc.miprimeraapi.model.Producto;
import cl.duoc.miprimeraapi.model.BaseDeDatos;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @GetMapping
    public ResponseEntity<List<Sucursal>> listarSucursales() {
        return new ResponseEntity<>(
            new ArrayList<>(BaseDeDatos.sucursales.values()), HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Sucursal> crearSucursal(@RequestBody Sucursal sucursal) {
        if (sucursal.getNombre() == null || sucursal.getNombre().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        sucursal.setId(++BaseDeDatos.contadorSucursal);
        BaseDeDatos.sucursales.put(sucursal.getId(), sucursal);

        return new ResponseEntity<>(sucursal, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/productos")
    public ResponseEntity<List<Producto>> productosDeSucursal(
        @PathVariable Long id,
        @RequestParam(required = false) Long productoId,
        @RequestParam(required = false) String nombre
    ) {
        if (!BaseDeDatos.sucursales.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Producto> productosEnSucursal = BaseDeDatos.productos.values().stream()
            .filter(p -> id.equals(p.getSucursalId()))
            .filter(p -> productoId == null || p.getId().equals(productoId))
            .filter(p -> nombre == null || p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .toList();

        return new ResponseEntity<>(productosEnSucursal, HttpStatus.OK);
    }

}
