package Model;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Movimientos {
    private String uuid;
    private String fecha;
    private ArrayList<Cambio> cambios;
    private Cambio cambio;
    private Producto producto;

    public Movimientos(Producto producto){
        this.producto=producto;
         this.uuid=producto.getId();
         this.fecha=LocalDateTime.now().toString();
         cambios=new ArrayList<>();
    }
    

    public ArrayList<Cambio> getCambios() {
    return cambios;
}

    public String getFecha(){
        return this.fecha;
    }

    public void CamposMoviminetos(String nombreAnterior, String codigoBarraAnterior, int StockAnterior, double precioAnterior, String categoriaAnterior){
        String NuevoNombre= producto.getNombre();
        String NuevaCategoria= producto.getCategoria();
        String NuevoCodigoBarra=producto.getCodigoBarras();
        int NuevoStock= producto.getStock();
        double NuevoPrecio=producto.getPrecio();

        if(!nombreAnterior.equals(NuevoNombre)){
           cambio= new Cambio("Campo Nombre", NuevoNombre, nombreAnterior, uuid);
           cambios.add(cambio);
        }
        if(!categoriaAnterior.equals(NuevaCategoria)){
           cambio= new Cambio("Campo Categoria", NuevaCategoria, categoriaAnterior, uuid);
           cambios.add(cambio);
        }
        if(!codigoBarraAnterior.equals(NuevoCodigoBarra)){
           cambio= new Cambio("Campo Codigo de Barras", NuevoCodigoBarra, codigoBarraAnterior, uuid);
           cambios.add(cambio);
        }
        if (StockAnterior != NuevoStock) {
            String stockAnterior = String.valueOf(StockAnterior);
            String stockNuevo = String.valueOf(NuevoStock);  
            cambio= new Cambio("Campo Stock",stockNuevo ,stockAnterior, uuid );
           cambios.add(cambio);
        }

        if (StockAnterior != NuevoStock) {
            String PrecioAnterior = String.valueOf(precioAnterior);
            String precioNuevo = String.valueOf(NuevoPrecio);  
            cambio= new Cambio("Campo Stock",precioNuevo ,PrecioAnterior, uuid );
           cambios.add(cambio);
        }
        
    }
    public boolean tieneCambios() {
        return !cambios.isEmpty();
    }

     public String getUuid() {
        return uuid;
    }

}
