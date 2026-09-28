package Model;

import java.util.ArrayList;

public class GestorProductos {

    private ArrayList<Producto> listaProductos;

    public GestorProductos() {
        listaProductos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) {
            listaProductos.add(producto);
        }
    }

    public void eliminarProducto(Producto producto) {
        listaProductos.remove(producto);
    }

    public Producto buscarPorCodigoBarras(String codigoBarras) {
        if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
            return null;
        }

        for (Producto producto : listaProductos) {
            if (codigoBarras.equalsIgnoreCase(producto.getCodigoBarras())) {
                return producto;
            }
        }

        return null;
    }

    public ArrayList<Producto> getProductos() {
        return listaProductos;
    }

    public double calcularValorTotal() {
        double total = 0;

        for (Producto producto : listaProductos) {
            total += producto.getValorStock();
        }

        return total;
    }
}