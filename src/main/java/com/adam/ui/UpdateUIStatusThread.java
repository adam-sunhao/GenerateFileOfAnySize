package com.adam.ui;

import com.adam.config.AppConfig;
import com.adam.config.AppParam;
import com.adam.config.StatusCode;

import javax.swing.*;

/**
 * @author AdamSun
 * @date 2020/8/16 0:38
 */
public class UpdateUIStatusThread extends Thread {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(AppConfig.sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            StatusCode currentCode = AppParam.currentCode;
            if (currentCode == StatusCode.START) {
                // 更新按钮内容
                JButton generateButton = AppParam.generateButton;
                if (generateButton != null) {
                    generateButton.setEnabled(false);

                    String text = generateButton.getText();
                    int maxLength = 9;
                    if (text.length() < maxLength) {
                        generateButton.setText(text + ".");
                    } else {
                        generateButton.setText("生成文件中");
                    }
                }
                JButton resetButton = AppParam.resetButton;
                if (resetButton != null) {
                    resetButton.setEnabled(false);
                }
            } else if (currentCode == StatusCode.END || currentCode == StatusCode.FREE) {
                JButton generateButton = AppParam.generateButton;
                if (generateButton != null) {
                    generateButton.setEnabled(true);
                    generateButton.setText("生成文件");
                }
                JButton resetButton = AppParam.resetButton;
                if (resetButton != null) {
                    resetButton.setEnabled(true);
                }
            }
        }

    }
}
