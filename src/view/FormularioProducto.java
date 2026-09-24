package view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Producto;
import components.ComponenteCategoria;
import components.ComponenteCodigoBarras;
import components.ComponenteNombre;
import components.ComponentePrecio;
import components.ComponenteStock;

public class FormularioProducto extends JPanel {

    private ComponenteNombre objetoNombre;
    private ComponenteStock objetoStock;
    private ComponentePrecio objetoPrecio;
    private ComponenteCategoria objetoCategoria;
    private ComponenteCodigoBarras objetoCodigoBarras;
    
    private JButton btnAgregar;
    private JButton btnLimpiar;

    public FormularioProducto() {

        // Usamos GridBagLayout para un control preciso de la alineación y espacios
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10); // Márgenes internos (arriba, izquierda, abajo, derecha)
        gbc.fill = GridBagConstraints.HORIZONTAL; // Para que se ajusten bien
        gbc.anchor = GridBagConstraints.WEST;

        objetoNombre = new ComponenteNombre();
        objetoCodigoBarras = new ComponenteCodigoBarras();
        objetoPrecio = new ComponentePrecio();
        objetoStock = new ComponenteStock();
        objetoCategoria = new ComponenteCategoria();

        // Fila 0: Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(objetoNombre, gbc);

        // Fila 1: Código de Barras
        gbc.gridy = 1;
        add(objetoCodigoBarras, gbc);

        // Fila 2: Precio
        gbc.gridy = 2;
        add(objetoPrecio, gbc);

        // Fila 3: Stock
        gbc.gridy = 3;
        add(objetoStock, gbc);

        // Fila 4: Categoría
        gbc.gridy = 4;
        add(objetoCategoria, gbc);

        // Panel para los botones (así quedan centrados/alineados juntos y con tamaño normal)
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");
        
        panelBotones.add(btnAgregar);
        panelBotones.add(btnLimpiar);

        // Fila 5: Panel de Botones
        gbc.gridy = 5;
        add(panelBotones, gbc);

        eventoLimpiar();
    }

    public void eventoLimpiar() {
        btnLimpiar.addActionListener(e -> limpiarFormulario());
    }

    public void eventoAgregar(ActionListener evento) {
        btnAgregar.addActionListener(evento);
    }

    public Producto crearProducto() {

        if (!objetoNombre.validacionNombre()) {
            return null;
        }

        if (!objetoCodigoBarras.validacionCodigoBarras()) {
            return null;
        }

        if (!objetoPrecio.validacionPrecio()) {
            return null;
        }

        if (!objetoStock.validacionStock()) {
            return null;
        }

        if (!objetoCategoria.validacionCategoria()) {
            return null;
        }

        String nombre = objetoNombre.getNombre();
        String codigoBarras = objetoCodigoBarras.getCodigoBarras();
        double precio = objetoPrecio.getPrecio();
        int stock = objetoStock.getStock();
        String categoria = objetoCategoria.getCategoria();

        return new Producto(codigoBarras, nombre, precio, stock, categoria);
    }

    public void limpiarFormulario() {
        objetoNombre.limpiarNombre();
        objetoStock.limpiarStock();
        objetoPrecio.limpiarPrecio();
        objetoCategoria.limpiarCategoria();
        objetoCodigoBarras.limpiar();
    }
}