package components;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ComponenteCategoria extends JPanel {

    private JComboBox<String> cmbCategoria;

    public ComponenteCategoria() {
        // Establecer alineación a la izquierda
        setLayout(new FlowLayout(FlowLayout.LEFT));

        cmbCategoria = new JComboBox<>();

        cmbCategoria.addItem("Almacén");
        cmbCategoria.addItem("Bebidas");
        cmbCategoria.addItem("Limpieza");
        cmbCategoria.addItem("Verduleria");
        cmbCategoria.addItem("Otros");

        add(new JLabel("Categoria:"));
        add(cmbCategoria);
    }

    public ComponenteCategoria(String categoriaSeleccionada) {
        this();
        cmbCategoria.setSelectedItem(categoriaSeleccionada);
    }

    public String getCategoria() {
        Object seleccion = cmbCategoria.getSelectedItem();
        return seleccion == null ? "" : seleccion.toString();
    }

    public boolean validacionCategoria() {
        if (getCategoria().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            cmbCategoria.requestFocusInWindow();
            return false;
        }
        return true;
    }

    public void limpiarCategoria() {
        cmbCategoria.setSelectedIndex(0);
    }
}