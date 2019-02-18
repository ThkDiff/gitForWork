package Lesson5;

import java.lang.reflect.Array;

public class main {
    static int veriableForArray = 0;
    public static void main(String[] args) {
        float[] array = new float[10000000];
        withOneStream(array);
        try {
            withDifferentStreams(array,2);
        }catch (MyException e){
            e.printStackTrace();
        }

    }

    public static void enchance(){
        veriableForArray++;
    }

    public static void withOneStream(float[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public static void withDifferentStreams(float[] array, int n) throws MyException {
        if((n % 2) != 0){
            throw new MyException("You can use only even numbers");
        }
        float[][] arrays = new float[n][array.length/n];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1;
        }
        Thread[] threads = new Thread[n];
        for (int i = 0; i < n; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < array.length / n; j++)
                        arrays[veriableForArray][j] = (float) (arrays[veriableForArray][j] * Math.sin(0.2f + j / 5) *
                                Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                    enchance();

                }
            });
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            System.arraycopy(array, i * (array.length / n), arrays[i], 0, array.length / n);
        }
        for (int i = 0; i < n; i++){
            threads[i].start();
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(arrays[i], 0, array, i * (array.length / n), array.length / n);
        }
        System.out.println(System.currentTimeMillis() - a);
    }
}