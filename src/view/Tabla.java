package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Model.Producto;
import components.ComponenteBuscador;

public class Tabla extends JPanel {

    private final JPanel panelProductos;
    private final JLabel lblTotal;
    private final ArrayList<Producto> listaProductos;
    private final NumberFormat formatoMoneda;

    public Tabla() {
        setLayout(new BorderLayout(0, 8));
        listaProductos = new ArrayList<>();
        formatoMoneda = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-AR"));
        panelProductos = new JPanel();
        panelProductos.setLayout(new BoxLayout(panelProductos, BoxLayout.Y_AXIS));

        JPanel encabezado = new JPanel(new GridLayout(1, 8, 8, 4));
        encabezado.add(new JLabel("Código"));
        encabezado.add(new JLabel("Nombre"));
        encabezado.add(new JLabel("Precio"));
        encabezado.add(new JLabel("Stock"));
        encabezado.add(new JLabel("Categoría"));
        encabezado.add(new JLabel("Valor stock"));
        encabezado.add(new JLabel("Editar"));
        encabezado.add(new JLabel("Eliminar"));

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
        if (producto == null) {
            return;
        }
        listaProductos.add(producto);
        dibujarLista();
    }

    // ========================================================
    // MÉTODO NUEVO: BUSCAR POR CÓDIGO DE BARRAS
    // ========================================================
   public Producto buscarPorCodigoBarras(String codigoBarras) {

    if (codigoBarras == null || codigoBarras.trim().isEmpty()) {
        return null;
    }

    for (Producto p : listaProductos) {

        if (codigoBarras.equalsIgnoreCase(p.getCodigoBarras())) {
            return p;
        }
    }

    return null;
}
    // ========================================================
    // MÉTODO NUEVO: REFRESCAR / ACTUALIZAR TABLA
    // ========================================================
    public void actualizarTabla() {
        dibujarLista();
    }

    // Redibuja todos los elementos de la lista en pantalla
    private void dibujarLista() {
        panelProductos.removeAll();

        for (Producto producto : listaProductos) {
            JPanel fila = new JPanel(new GridLayout(1, 8, 8, 4));
            JLabel lblCodigo = new JLabel(codigoVisible(producto.getCodigoBarras()));
            JLabel lblNombre = new JLabel(producto.getNombre());
            JLabel lblPrecio = new JLabel(formatear(producto.getPrecio()));
            JLabel lblStock = new JLabel(String.valueOf(producto.getStock()));
            JLabel lblCategoria = new JLabel(producto.getCategoria());
            JLabel lblValorStock = new JLabel(formatear(producto.getValorStock()));
            JButton btnEditar = new JButton("Editar");
            JButton btnEliminar = new JButton("Eliminar");

            fila.setName(producto.getNombre());
            fila.add(lblCodigo);
            fila.add(lblNombre);
            fila.add(lblPrecio);
            fila.add(lblStock);
            fila.add(lblCategoria);
            fila.add(lblValorStock);
            fila.add(btnEditar);
            fila.add(btnEliminar);

            btnEliminar.addActionListener(e -> {
                listaProductos.remove(producto);
                dibujarLista();
            });

            btnEditar.addActionListener(e -> new VentanaEditar(producto, () -> {
                dibujarLista();
            }));

            panelProductos.add(fila);
        }

        actualizarTotal();
        panelProductos.revalidate();
        panelProductos.repaint();
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

    private void actualizarTotal() {
        double total = 0;
        for (Producto producto : listaProductos) {
            total += producto.getValorStock();
        }
        lblTotal.setText("Valor total del stock: " + formatear(total));
    }
}