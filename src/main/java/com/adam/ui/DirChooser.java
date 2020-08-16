package com.adam.ui;

import com.adam.config.AppConfig;
import com.adam.config.AppParam;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 目录选择的组件
 *
 * @author AdamSun
 * @date 2020/8/14 22:51
 */
public class DirChooser extends JPanel {
    private static final int HEIGHT = 100;
    private static final int PATH_FIELD_WIDTH = 200;
    private static final int PATH_FIELD_HEIGHT = 80;
    private static final int PATH_FIELD_COLUMNS = 30;

    public DirChooser() {
        init();
    }

    /**
     * 初始化界面
     */
    private void init() {
        JLabel filePathLabel = new JLabel("保存目录");
        // setSize(AppConfig.APP_WIDTH, HEIGHT); // not working
        final JTextField pathField = new JTextField(PATH_FIELD_COLUMNS);
        pathField.setEditable(false);
        pathField.setText(AppParam.path);
        // pathField.setSize(PATH_FIELD_WIDTH, PATH_FIELD_HEIGHT);

        JButton selectorBtn = new JButton("选择保存目录");
        selectorBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(null);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    pathField.setText(file.getAbsolutePath());
                    AppParam.path = file.getAbsolutePath();
                }
            }
        });
        // setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new FlowLayout(FlowLayout.LEFT, AppConfig.H_GAP, AppConfig.V_GAP));
        filePathLabel.setLabelFor(pathField);
        add(filePathLabel);
        add(pathField);
        add(selectorBtn);
    }
}
