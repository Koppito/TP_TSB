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
public class Main {

    /*
    @Override
    public void start(Stage stage) throws Exception {
        throw new Exception();

        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);

        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }*/

    public static void main(String[] args) {
        TSB_OAHashtable<Integer, Integer> hashTabla = new TSB_OAHashtable(25);
        System.out.println(hashTabla.size());
        hashTabla.put(2, 3);
        System.out.println(hashTabla.get(2));
        System.out.println(hashTabla.put(2, 45));
        System.out.println(hashTabla.get(2));
        System.out.println(hashTabla.get(3));
    }

}
