package mainPackage;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class MainMenuController implements Initializable {

    @FXML
    private JFXButton helloWorldBtn;

    @FXML
    void helloWorld(MouseEvent event) {
        System.out.println("Hello World!");
    }

    @FXML
    void initialize() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
