package Model;

/** Representa un producto del comercio. */
public class Producto {

    private String codigoBarras;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;

    
    public Producto(String nombre, double precio, int stock, String categoria) {
        this(null, nombre, precio, stock, categoria);
    }

    /** Crea un producto con todos sus datos. */
    public Producto(String codigoBarras, String nombre, double precio, int stock, String categoria) {
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /** Devuelve el valor total del stock disponible. */
    public double getValorStock() {
        return precio * stock;
    }
}
