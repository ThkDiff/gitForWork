package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class ControllerForTask3 {

    @FXML
    private TextField ForFrequency;

    @FXML
    private TextField UpFr;

    @FXML
    private TextField DownFr;

    @FXML
    private TextField TextF1;

    @FXML
    private TextField TextF2;

    @FXML
    private LineChart<String, Number> MyChat;
    private XYChart.Series<String, Number> series;
    private ClassForLogic clf = new ClassForLogic();

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
        try {
            clf.getNextDiscreteSignal(TextF2,TextF1,t,u,ForFrequency);
            clf.DirectFourierTransform(MyChat,a,b,u,TextF2,series);
        } catch (MyException e) {
            e.printStackTrace();
        }
        MyChat.getData().addAll(series);
    }

    public void SignalsFilter(ActionEvent event) {
        MyChat.getData().clear();
        series = new XYChart.Series();
        for (int i = 0; i < Integer.parseInt(TextF2.getText()); i++) {
            float ff = i / Float.parseFloat(TextF1.getText());
            if ((ff < Float.parseFloat(DownFr.getText())) || (ff > Float.parseFloat(UpFr.getText()))) {
                a[i] = 0;
                b[i] = 0;
            }
        }
        clf.InverseFourierTransform(a, b, TextF2, series, t);
        MyChat.getData().addAll(series);
    }
}
