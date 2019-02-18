package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sun.jvm.hotspot.debugger.cdbg.IndexableFieldIdentifier;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ControllerForTask2 implements Initializable {

    @FXML
    private Button ButForFourier;

    @FXML
    private TextField ForPhase;

    @FXML
    private TextField ForFrequency;

    @FXML
    private TextField TextF1;

    @FXML
    private TextField TextF2;

    @FXML
    private LineChart<String, Number> MyChat;

    private XYChart.Series<String,Number> series;

    private ClassForLogic clf = new ClassForLogic();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ButForFourier.setVisible(false);
    }

    private float[] t;
    private double[] u;
    private float[] a;
    private float[] b;

    public void getSpectrum(ActionEvent event){
        MyChat.getData().clear();
        series = new XYChart.Series();
        a = new float[Integer.parseInt(TextF2.getText())];
        b = new float[Integer.parseInt(TextF2.getText())];
        t = new float[Integer.parseInt(TextF2.getText())];
        u = new double[Integer.parseInt(TextF2.getText())];
        ButForFourier.setVisible(true);
        try {
            clf.getDiscreteSignal(TextF2,TextF1,t,u,ForFrequency);
            clf.DirectFourierTransform(MyChat,a,b,u,TextF2,series);
        } catch (MyException e) {
            e.printStackTrace();
        }
        MyChat.getData().addAll(series);
    }

    public void getSignalFromSpectrum(ActionEvent event){
        MyChat.getData().clear();
        series = new XYChart.Series();
        clf.InverseFourierTransform(a,b,TextF2,series,t);
        MyChat.getData().addAll(series);
    }

    public void clear(ActionEvent event){
        MyChat.getData().clear();
    }
}
