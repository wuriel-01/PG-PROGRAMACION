package components;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComponenteCategoria extends JPanel {

    private JComboBox<String> cmbCategoria;

    
    public ComponenteCategoria() {

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
        return cmbCategoria.getSelectedItem().toString();
    }
}