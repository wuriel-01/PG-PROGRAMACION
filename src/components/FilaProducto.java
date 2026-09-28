package components;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Producto;

public class FilaProducto extends JPanel {

    private Producto producto;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnMovimientos;
    private NumberFormat formatoMoneda;

    public FilaProducto(Producto producto) {

        this.producto = producto;
        formatoMoneda = NumberFormat.getCurrencyInstance(
                Locale.forLanguageTag("es-AR")
        );

        setLayout(new GridLayout(1, 9, 8, 4));
        setName(producto.getNombre());

        add(new JLabel(codigoVisible(producto.getCodigoBarras())));
        add(new JLabel(producto.getNombre()));
        add(new JLabel(formatear(producto.getPrecio())));
        add(new JLabel(String.valueOf(producto.getStock())));
        add(new JLabel(producto.getCategoria()));
        add(new JLabel(formatear(producto.getValorStock())));

        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnMovimientos = new JButton("Movimientos");

        add(btnEditar);
        add(btnEliminar);
        add(btnMovimientos);
    }

    private String formatear(double importe) {
        return formatoMoneda.format(importe);
    }

    private String codigoVisible(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            return "—";
        }
        return codigo;
    }

    public void eventoEditar(ActionListener evento) {
        btnEditar.addActionListener(evento);
    }

     public void eventoEliminar(ActionListener evento) {
        btnEliminar.addActionListener(evento);
    }

     public void eventoVerMovimientos(ActionListener evento) {
        btnMovimientos.addActionListener(evento);
    }
}
