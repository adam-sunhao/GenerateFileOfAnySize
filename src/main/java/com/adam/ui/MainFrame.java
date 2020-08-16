package com.adam.ui;

import com.adam.config.AppConfig;
import com.adam.config.AppParam;
import com.adam.config.StatusCode;
import com.adam.ui.menu.MyMenuBar;
import com.adam.util.WindowUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author AdamSun
 * @date 2020/8/14 22:14
 */
public class MainFrame extends JFrame {
    public MainFrame() throws HeadlessException {
        init();
    }

    private void init() {
        setTitle(AppConfig.APP_TITLE);
        setLocation(WindowUtil.getCenterOfWindow(AppConfig.APP_WIDTH, AppConfig.APP_HEIGHT));
        setSize(AppConfig.APP_WIDTH, AppConfig.APP_HEIGHT);

        // add Menu Bar
        new MyMenuBar(this);

        JPanel operationPanel = new OperationPanel();
        add(operationPanel, BorderLayout.CENTER);

        setVisible(true);
        setResizable(AppConfig.RESIZABLE);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new MyWindowListener(this));

        // 开启线程，监听状态
        new UpdateUIStatusThread().start();
    }
}

class MyWindowListener extends WindowAdapter {
    private JFrame frame;

    public MyWindowListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        StatusCode currentCode = AppParam.currentCode;
        String message = "确定要退出吗？";
        int messageType = JOptionPane.QUESTION_MESSAGE;
        if (StatusCode.START == currentCode) {
            messageType = JOptionPane.WARNING_MESSAGE;
            message = "文件正在生成中，确定退出吗？";
        }
        int a = JOptionPane.showConfirmDialog(this.frame, message, AppConfig.TIP_TITLE, JOptionPane.OK_CANCEL_OPTION, messageType);
        if (a == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
