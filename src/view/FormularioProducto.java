package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.UUID;

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

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 10, 4, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;

        objetoNombre = new ComponenteNombre();
        objetoCodigoBarras = new ComponenteCodigoBarras();
        objetoPrecio = new ComponentePrecio();
        objetoStock = new ComponenteStock();
        objetoCategoria = new ComponenteCategoria();

        gbc.gridy = 0;
        add(objetoNombre, gbc);

        gbc.gridy = 1;
        add(objetoCodigoBarras, gbc);

        gbc.gridy = 2;
        add(objetoPrecio, gbc);

        gbc.gridy = 3;
        add(objetoStock, gbc);

        gbc.gridy = 4;
        add(objetoCategoria, gbc);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));

        btnAgregar = new JButton("Agregar");
        btnLimpiar = new JButton("Limpiar");

        btnAgregar.setPreferredSize(new Dimension(90, 30));
        btnLimpiar.setPreferredSize(new Dimension(90, 30));

        btnAgregar.setBackground(new Color(40, 167, 69));
        btnAgregar.setForeground(Color.WHITE);

        btnLimpiar.setBackground(new Color(108, 117, 125));
        btnLimpiar.setForeground(Color.WHITE);

        btnAgregar.setFocusPainted(false);
        btnLimpiar.setFocusPainted(false);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnLimpiar);

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

        if (!objetoNombre.validacionNombre()
                || !objetoCodigoBarras.validacionCodigoBarras()
                || !objetoPrecio.validacionPrecio()
                || !objetoStock.validacionStock()
                || !objetoCategoria.validacionCategoria()) {
            return null;
        }

        String nombre = objetoNombre.getNombre();
        String codigoBarras = objetoCodigoBarras.getCodigoBarras();
        double precio = objetoPrecio.getPrecio();
        int stock = objetoStock.getStock();
        String categoria = objetoCategoria.getCategoria();
        String id = UUID.randomUUID().toString();

        return new Producto(codigoBarras, nombre, precio, stock, categoria, id);
    }

    public void limpiarFormulario() {
        objetoNombre.limpiarNombre();
        objetoStock.limpiarStock();
        objetoPrecio.limpiarPrecio();
        objetoCategoria.limpiarCategoria();
        objetoCodigoBarras.limpiar();
    }
}