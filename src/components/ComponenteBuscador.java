package components;

import java.awt.Component;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ComponenteBuscador extends JPanel {

    private JTextField txtBuscar;
    private JPanel panelProductos;

    public ComponenteBuscador(JPanel panelProductos) {

        this.panelProductos = panelProductos;

        txtBuscar = new JTextField(20);
        add(txtBuscar);

        BuscarProducto();
    }

    public void BuscarProducto() {

        txtBuscar.getDocument().addDocumentListener(
            new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    filtrarProductos();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    filtrarProductos();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    filtrarProductos();
                }
            }
        );
    }

    private void filtrarProductos() {

        String nombreBuscado = txtBuscar.getText().trim().toLowerCase(Locale.ROOT);

        for (Component componente : panelProductos.getComponents()) {

            String nombreProducto = componente.getName();

            if (nombreProducto != null) {

                if (nombreProducto.toLowerCase(Locale.ROOT).contains(nombreBuscado)) {
                    componente.setVisible(true);
                } else {
                    componente.setVisible(false);
                }
            }
        }

        panelProductos.revalidate();
        panelProductos.repaint();
    }
}
