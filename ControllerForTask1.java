package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControllerForTask1 {
    @FXML
    private LineChart<Number,Number> MyChat;

    XYChart.Series<Number,Number> series;

    @FXML
    private AnchorPane paneForTask1;

    @FXML
    private TextField TextField2;

    @FXML
    private TextField TextField1;

    private float[] t;
    private double[] u;

    public void plotSignal(ActionEvent event){
        MyChat.getData().clear();
        t = new float[Integer.parseInt(TextField2.getText())];
        u = new double[Integer.parseInt(TextField2.getText())];
        series = new XYChart.Series<Number,Number>();
        ClassForLogic cfl = new ClassForLogic();
        try {
            cfl.getDiscreteSignal(MyChat,TextField2,TextField1,series,t,u);
        } catch (MyException e) {
            e.printStackTrace();
        }
        MyChat.getData().addAll(series);
    }

    public void clear(ActionEvent event){
        MyChat.getData().clear();
    }
}
