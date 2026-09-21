package view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;

public class FormularioProducto extends JPanel {
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JComboBox<String> cmbCategoria; 
    private JButton btnAgregar;
    private JButton btnLimpiar;
   
    public FormularioProducto (){

        setLayout(new GridLayout(5, 2, 10, 10));

        txtNombre= new JTextField();
        txtPrecio= new JTextField();
        txtStock= new JTextField();
        cmbCategoria = new JComboBox<>();


        cmbCategoria.addItem("Almacén");
        cmbCategoria.addItem("Bebidas");
        cmbCategoria.addItem("Limpieza");
        cmbCategoria.addItem("Verduleria");
        cmbCategoria.addItem("Otros");

   
        add(new JLabel("Nombre:"));
        add(txtNombre);
        add(new JLabel("Precio:"));
        add(txtPrecio);
        add(new JLabel("Stock:"));
        add(txtStock);
        add(new JLabel("Categoria:"));
        add(cmbCategoria);




        btnAgregar = new JButton("Agregar");
        add(btnAgregar);
        btnLimpiar = new JButton("Limpiar");
        add(btnLimpiar);
         

    }



public void eventoAgregar(ActionListener evento) {
    btnAgregar.addActionListener(evento);
}

public void limpiar() {

    txtNombre.setText("");
    txtPrecio.setText("");
    txtStock.setText("");

    cmbCategoria.setSelectedIndex(0);

    txtNombre.requestFocus();
}

public void eventoLimpiar() {
btnLimpiar.addActionListener(e -> limpiar());
}
   



// GETTERS y SETTERS


public String getNombre() {
    return txtNombre.getText().trim();
}

public String getPrecio() {
    return txtPrecio.getText().trim();
}

public String getStock() {
    return txtStock.getText().trim();
}

public String getCategoria() {
    return cmbCategoria.getSelectedItem().toString();
}



public void validacionNombre(){
     txtNombre.requestFocus();
}
public void validacionPrecio(){
    txtPrecio.requestFocus();
}

public void validacionStock(){
  txtStock.requestFocus();
}

}
