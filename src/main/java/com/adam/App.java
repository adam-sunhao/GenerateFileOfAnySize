package com.adam;

import com.adam.config.AppConfig;
import com.adam.ui.MainFrame;
import com.adam.util.JustOneLock;
import com.adam.util.MessageDialogUtil;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        JustOneLock justOneLock = new JustOneLock(AppConfig.APP_NAME);
        if (!justOneLock.isAppActive()) {
            try {
                new MainFrame();
            } catch (Exception e) {
                e.printStackTrace();
                MessageDialogUtil.showDialog(e.getMessage(), AppConfig.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
