package mainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Koppito
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        /*
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();*/
        throw new Exception();
    }

    public static void main(String[] args) {
        TSB_OAHashtable hashTabla = new TSB_OAHashtable(25);
        System.out.println(hashTabla.size());
        return;
        //launch(args);
    }

}
