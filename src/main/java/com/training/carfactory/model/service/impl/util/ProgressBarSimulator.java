package com.training.carfactory.model.service.impl.util;

import javafx.scene.control.ProgressBar;

import java.util.concurrent.CountDownLatch;

public class ProgressBarSimulator {

    private static PartStatus status = PartStatus.PENDING;

    public void simulateProgress(ProgressBar progressBar, int delay, CountDownLatch cdl) {
        new Thread(() -> {
            status = PartStatus.INSTALLING;
            for (double i = 0; i <= 1.01; i = i + 0.01) {
                simulateProcess(progressBar, delay, i);
            }
            status = PartStatus.PENDING;
            cdl.countDown();
        }).start();
    }

    public void simulateDownTimeProgress(ProgressBar progressBar, int delay, CountDownLatch cdl){
        new Thread(() -> {
            for (double i = 1; i > 0; i = i - 0.01) {
                status = PartStatus.REMOVING;
                simulateProcess(progressBar, delay, i);
            }
            status = PartStatus.PENDING;
            cdl.countDown();
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

    public static PartStatus getStatus() {
        return status;
    }
}
