package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Controller {

    @FXML
    private AnchorPane MainPane;

    public void changeToTask1(ActionEvent event){
        try {
            AnchorPane paneForTask1 = FXMLLoader.load(getClass().getResource("Task1.fxml"));
            MainPane.getChildren().setAll(paneForTask1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToTask2(ActionEvent event){
        try {
            AnchorPane paneForTask1 = FXMLLoader.load(getClass().getResource("Task2.fxml"));
            MainPane.getChildren().setAll(paneForTask1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToTask3(ActionEvent event){
        try {
            AnchorPane paneForTask1 = FXMLLoader.load(getClass().getResource("Task3.fxml"));
            MainPane.getChildren().setAll(paneForTask1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
