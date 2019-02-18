package sample;

import javafx.event.ActionEvent;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

public class ClassForLogic {

    public void getDiscreteSignal(LineChart<Number,Number> MyChat, TextField TextField2,
                          TextField TextField1,XYChart.Series<Number,Number> series,float[] t,
                                   double[] u) throws MyException{
        for (int i = 0;i < Integer.parseInt(TextField2.getText());i++){
            t[i] = (i * Float.parseFloat(TextField1.getText())/Integer.parseInt(TextField2.getText()));
            u[i] = Math.sin(2*Math.PI*1*t[i]);
            series.getData().add(new XYChart.Data("" + t[i],u[i]));
        }
    }

    public void getDiscreteSignal(TextField TextField2, TextField TextField1,
                                  float[] t, double[] u, TextField ForFrequency) throws MyException{
        for (int i = 0;i < Integer.parseInt(TextField2.getText());i++){
            t[i] = (i * Float.parseFloat(TextField1.getText())/Integer.parseInt(TextField2.getText()));
            u[i] = Math.sin(2*Math.PI*Float.parseFloat(ForFrequency.getText()) *t[i]);
        }
    }

    public void getNextDiscreteSignal(TextField TextField2, TextField TextField1,
                                  float[] t, double[] u, TextField ForFrequency) throws MyException{
        for (int i = 0;i < Integer.parseInt(TextField2.getText());i++){
            t[i] = (i * Float.parseFloat(TextField1.getText())/Integer.parseInt(TextField2.getText()));
            u[i] = Math.sin(4*Math.PI*Float.parseFloat(ForFrequency.getText()) *t[i]) +
                    Math.cos(2*Math.PI * Float.parseFloat(ForFrequency.getText()) *t[i]) +
                    (0.5*Math.cos(6*Math.PI* Float.parseFloat(ForFrequency.getText()) *t[i]));
        }
    }

    public void DirectFourierTransform(LineChart<String,Number> MyChat,float[] a,float[] b,
                                       double[] u,TextField TextField2,XYChart.Series<String,Number> series){
        double[] A = new double[Integer.parseInt(TextField2.getText())];

        for (int k = 0;k < Integer.parseInt(TextField2.getText());k++){
            a[k] = 0;
            b[k] = 0;
            for (int i = 0; i < Integer.parseInt(TextField2.getText());i++){
                a[k] += u[i]*Math.cos(2.0*Math.PI*i*k/Integer.parseInt(TextField2.getText()));
                b[k] -= u[i]*Math.sin(2.0*Math.PI*i*k/Integer.parseInt(TextField2.getText()));
            }
            a[k] *= 2.0/Integer.parseInt(TextField2.getText());
            b[k] *= 2.0/Integer.parseInt(TextField2.getText());
            A[k] = Math.sqrt((a[k]*a[k]) + (b[k]*b[k]));
            if (k < Integer.parseInt(TextField2.getText())/2)
                series.getData().add(new XYChart.Data("" + k,A[k]));
        }
        a[0] *= 0.5;
    }

    public void InverseFourierTransform(float[] a,float[] b,
                                        TextField TextF2,XYChart.Series<String,Number> series,float[] t){
        double[] f = new double[Integer.parseInt(TextF2.getText())];
        float coef = 1.0f / Integer.parseInt(TextF2.getText());
        double arg = 2.0f * Math.PI * coef;
        for (int j = Integer.parseInt(TextF2.getText()) - 1; j >= 0; --j)
        {
            f[j] = 0.0f;
            for (int i = 0; i < Integer.parseInt(TextF2.getText()); ++i)
            {
                double r = a[i] * Math.cos(arg*i*(j+1));
                double m = b[i] * Math.sin(arg*i*(j+1));
                r *= (i==0) ? 2.0f : 1.0f;
                f[j] += r + m;
            }
            f[j] *= 0.5;
            series.getData().add(new XYChart.Data<String, Number>("" + t[j],f[j]));
        }
    }
}
