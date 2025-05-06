package cl.duoc.miprimeraapi.model;

import java.util.HashMap;
import java.util.Map;

import cl.duoc.miprimeraapi.model.Producto;
import cl.duoc.miprimeraapi.model.Sucursal;

public class BaseDeDatos {

    public static Map<Long, Producto> productos = new HashMap<>();
    public static Map<Long, Sucursal> sucursales = new HashMap<>();

    public static Long contadorProducto = 0L;
    public static Long contadorSucursal = 0L;

}
