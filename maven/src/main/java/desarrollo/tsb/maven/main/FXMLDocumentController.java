package desarrollo.tsb.maven.main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class FXMLDocumentController implements Initializable {

    private GestorDocument gestor = GestorDocument.getInstance();

    @FXML
    private TextField txtPalabra;
    @FXML
    private Label lblResultado;
    @FXML
    private Label lblArchivo;
    @FXML
    private TextArea txtArchivo;
    
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnCargarClick(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Seleccionar archivo de texto");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Documento de texto (*.txt)", "*.txt"));
        File file = fc.showOpenDialog(null);
        if (file != null)
        {
            lblArchivo.setText(file.getAbsolutePath());
            gestor.getArchivo().setFile(file);
            gestor.getArchivo().leerArchivo();
            txtArchivo.setText("Archivo: " + gestor.getArchivo().toString());
        }
    }

    @FXML
    private void btnBuscarClick(ActionEvent event) {
        if(txtPalabra.getText().isEmpty())
            lblResultado.setText("No ingresó una palabra");
        else
        {
            String x = txtPalabra.getText();
            //if(archPalabras.buscarPalabra(x))
            if(true) {
                lblResultado.setText("Palabra encontrada");
            }
            else {
                lblResultado.setText("Palabra NO encontrada");
            }
        }
    }

    @FXML
    private void btnLimpiarClick(ActionEvent event) {
        txtPalabra.setText("");
        lblResultado.setText("");
    }
    
}
