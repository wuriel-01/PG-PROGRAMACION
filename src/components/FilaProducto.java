package components;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import Model.Producto;

public class FilaProducto extends JPanel {

    private Producto producto;
    private JButton btnEditar, btnEliminar, btnMovimientos;
    private NumberFormat formatoMoneda;

    public FilaProducto(Producto producto) {

        this.producto = producto;
        formatoMoneda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-AR"));

        setLayout(new GridBagLayout());

        agregarDato(codigoVisible(producto.getCodigoBarras()), 0, 1.4);
        agregarDato(producto.getNombre(), 1, 1.5);
        agregarDato(formatear(producto.getPrecio()), 2, 1);
        agregarDato(String.valueOf(producto.getStock()), 3, 0.7);
        agregarDato(producto.getCategoria(), 4, 1.2);
        agregarDato(formatear(producto.getValorStock()), 5, 1.2);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 3));

        btnEditar = crearBoton("Editar", new Color(50, 120, 220));
        btnEliminar = crearBoton("Eliminar", new Color(210, 60, 60));
        btnMovimientos = crearBoton("Historial", new Color(110, 90, 180));

        acciones.add(btnEditar);
        acciones.add(btnEliminar);
        acciones.add(btnMovimientos);

        GridBagConstraints gbc = posicion(6, 2.5);
        add(acciones, gbc);
    }

    private void agregarDato(String texto, int columna, double ancho) {
        add(new JLabel(texto), posicion(columna, ancho));
    }

    private GridBagConstraints posicion(int columna, double ancho) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = columna;
        gbc.weightx = ancho;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 8, 4, 8);
        return gbc;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(80, 30));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        return boton;
    }

    private String formatear(double importe) {
        return formatoMoneda.format(importe);
    }

    private String codigoVisible(String codigo) {
        return codigo == null || codigo.trim().isEmpty() ? "—" : codigo;
    }

    public void eventoEditar(ActionListener e) {
        btnEditar.addActionListener(e);
    }

    public void eventoEliminar(ActionListener e) {
        btnEliminar.addActionListener(e);
    }

    public void eventoVerMovimientos(ActionListener e) {
        btnMovimientos.addActionListener(e);
    }

    public Producto getProducto() {
        return producto;
    }
}