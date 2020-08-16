package com.adam.util;

import com.adam.config.AppParam;

import javax.swing.*;

/**
 * @author AdamSun
 * @date 2020/8/15 22:25
 */
public class MessageDialogUtil {

    /**
     * 打开普通信息对话框
     *
     * @param content
     * @param title
     */
    public static void showInfoDialog(String content, String title) {
        showDialog(content, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 打开对话框
     *
     * @param title
     */
    public static void showDialog(String content, String title, int messageType) {
        JFrame frame = AppParam.mainFrame;
        JOptionPane.showMessageDialog(frame, content, title, messageType);
    }
}
