package com.adam.ui;

import com.adam.config.AppConfig;
import com.adam.config.AppParam;
import com.adam.config.StatusCode;
import com.adam.util.AppUtil;
import com.adam.util.FileUtil;
import com.adam.util.MessageDialogUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author AdamSun
 * @date 2020/8/15 19:02
 */
public class OperationButtons extends JPanel {

    private static final String GENERATE_BUTTON_TEXT = "生成文件";
    private static final String RESET_BUTTON_TEXT = "重置";

    public OperationButtons() {
        init();
    }

    private void init() {
        // 如果不手动指定大小，默认适应内容，如果为BoxLayout则默认填充满，看实际布局
        /*
        Dimension d = getPreferredSize();
        d.setSize(550, 30);
        setPreferredSize(d);
        */
        JButton generateButton = new JButton(GENERATE_BUTTON_TEXT);
        generateButton.setBackground(new Color(106, 90, 205));
        generateButton.addActionListener(new GenerateButtonActionListener());
        add(generateButton);
        JButton resetButton = new JButton(RESET_BUTTON_TEXT);
        resetButton.addActionListener(new ResetButtonActionListener());
        add(resetButton);

        AppParam.generateButton = generateButton;
        AppParam.resetButton = resetButton;
    }
}

class GenerateButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AppParam.currentCode = StatusCode.START;
                    // 异步生成文件
                    FileUtil.generateFile();
                    AppParam.currentCode = StatusCode.END;
                    MessageDialogUtil.showDialog("文件生成完成", AppConfig.TIP_TITLE, JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    AppParam.currentCode = StatusCode.END;
                    MessageDialogUtil.showDialog(e.getMessage(), AppConfig.ERROR_TITLE, JOptionPane.ERROR_MESSAGE);
                } finally {
                    AppParam.currentCode = StatusCode.FREE;
                }
            }
        }).start();
    }
}

class ResetButtonActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        AppUtil.empty();
    }
}
