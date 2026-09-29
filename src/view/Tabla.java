package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.GestorProductos;
import Model.Producto;
import components.ComponenteBuscador;
import components.FilaProducto;
import javafx.geometry.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class Tabla extends JPanel {

    private final JPanel panelProductos;
    private final JLabel lblTotal;
    private final GestorProductos gestorProductos;
    private final NumberFormat formatoMoneda;

 public Tabla() {
    setLayout(new BorderLayout(0, 8));
    gestorProductos = new GestorProductos();
    formatoMoneda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-AR"));

    panelProductos = new JPanel();
    panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));

    JPanel encabezado = new JPanel(new GridBagLayout());
    String[] titulos = {"Código", "Nombre", "Precio", "Stock", "Categoría", "Valor stock", "Acciones"};
    double[] anchos = {1.4, 1.5, 1, 0.7, 1.2, 1.2, 2.5};

    for (int i = 0; i < titulos.length; i++) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = i;
        gbc.weightx = anchos[i];
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new java.awt.Insets(4, 8, 4, 8);
        encabezado.add(new JLabel(titulos[i]), gbc);
    }

    JPanel superior = new JPanel(new BorderLayout(0, 4));
    superior.add(new ComponenteBuscador(panelProductos), BorderLayout.NORTH);
    superior.add(encabezado, BorderLayout.SOUTH);

    add(superior, BorderLayout.NORTH);
    add(new JScrollPane(panelProductos), BorderLayout.CENTER);

    lblTotal = new JLabel();
    add(lblTotal, BorderLayout.SOUTH);
    actualizarTotal();
}

    public void agregarProducto(Producto producto) {
        gestorProductos.agregarProducto(producto);
        dibujarLista();
    }

    public Producto buscarPorCodigoBarras(String codigoBarras) {
        return gestorProductos.buscarPorCodigoBarras(codigoBarras);
    }

    public void actualizarTabla() {
        dibujarLista();
    }

    private void dibujarLista() {
        panelProductos.removeAll();

        for (Producto producto : gestorProductos.getProductos()) {
            FilaProducto fila = new FilaProducto(producto);

            fila.eventoEditar(e -> new VentanaEditar(producto, this, () -> dibujarLista()));

            fila.eventoEliminar(e -> eliminarProducto(producto));

            fila.eventoVerMovimientos(e -> new VentanaMovimientos(producto));

            panelProductos.add(fila);
        }

        actualizarTotal();
        panelProductos.revalidate();
        panelProductos.repaint();
    }

    private void eliminarProducto(Producto producto) {
        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea eliminar el producto " + producto.getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            gestorProductos.eliminarProducto(producto);
            dibujarLista();

            JOptionPane.showMessageDialog(this,
                    "Producto eliminado correctamente.",
                    "Producto eliminado",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void actualizarTotal() {
        double total = gestorProductos.calcularValorTotal();
        lblTotal.setText("Valor total del stock: " + formatoMoneda.format(total));
    }
}