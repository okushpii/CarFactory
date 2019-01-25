package com.training.carfactory.model.service.impl.util;

import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

public class ProgressBarSimulator {

    public void simulateProgress(ProgressBar progressBar, int delay, Button toEnable) {
        new Thread(() -> {
            for (double i = 0; i <= 1.01; i = i + 0.01) {
                simulateProcess(progressBar, delay, i);
            }
            toEnable.setDisable(false);
        }).start();
    }

    public void simulateDownTimeProgress(ProgressBar progressBar, int delay, Button toEnable){
        new Thread(() -> {
            for (double i = 1; i > 0; i = i - 0.01) {
                simulateProcess(progressBar, delay, i);
            }
            toEnable.setDisable(false);
        }).start();
    }

    private void simulateProcess(ProgressBar progressBar, int delay, double i) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        progressBar.setProgress(i);
    }
}
