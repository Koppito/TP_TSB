package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class Main {

	//@Override
	public void start(Stage stage) throws Exception 
	{
        Parent root = FXMLLoader.load(getClass().getResource("/main/FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("TP TSB");
        stage.show();
	}
	
	public static void main(String[] args) {
		//launch(args);
        TSB_OAHashtable tabla = new TSB_OAHashtable<Integer, String>(2);
        tabla.put(23, "Hola");
        tabla.put(45, "HEYY");
        tabla.put(47, "Pepe");

        Set<Integer> keys = tabla.keySet();
        for (Integer i : keys) {
            System.out.println("Key: " + i);
        }

        Collection<String> values = tabla.values();
        for (String s : values) {
            System.out.println(s);
        }

        Set<Map.Entry> entries = tabla.entrySet();
        for (Map.Entry e : entries) {
            System.out.println(e.toString());
        }

    }
}