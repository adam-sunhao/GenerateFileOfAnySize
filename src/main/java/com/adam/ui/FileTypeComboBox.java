package com.adam.ui;

import com.adam.config.AppConfig;
import com.adam.config.AppParam;
import com.adam.ui.component.PlaceholderTextField;
import com.adam.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AdamSun
 * @date 2020/8/15 9:09
 */
public class FileTypeComboBox extends JPanel {
    public static String fileTypeLabelText = "文件类型";
    public static int fileTypeFieldColumns = 34;
    public static List<String> fileTypeList = new ArrayList<>();

    static {
        //常用的文档类型，如果不存在，可以手动输入
        fileTypeList.add("doc");
        fileTypeList.add("docx");
        fileTypeList.add("xls");
        fileTypeList.add("xlsx");
        fileTypeList.add("pdf");
        fileTypeList.add("ppt");
        fileTypeList.add("pptx");
        fileTypeList.add("txt");
        fileTypeList.add("jpg");
        fileTypeList.add("jpeg");
        fileTypeList.add("png");
        fileTypeList.add("gif");
        fileTypeList.add("bmp");
        fileTypeList.add("mp3");
        fileTypeList.add("mp4");
    }

    public FileTypeComboBox() {
        JLabel fileTypeLabel = new JLabel(fileTypeLabelText);

        JComboBox<String> fileTypeComboBox = new JComboBox<>();
        for (String fileType : fileTypeList) {
            fileTypeComboBox.addItem(fileType);
        }
        fileTypeComboBox.addItemListener(new FileTypeItemListener());

        PlaceholderTextField fileTypeField = new PlaceholderTextField(fileTypeFieldColumns);
        fileTypeField.setPlaceholder("如果没有找到需要的类型，可以手动输入，例： exe");
        fileTypeField.setToolTipText("仅可输入字母和数字");
        fileTypeField.addFocusListener(new FileTypeFieldFocusListener());

        setLayout(new FlowLayout(FlowLayout.LEFT, AppConfig.H_GAP, AppConfig.V_GAP));

        fileTypeLabel.setLabelFor(fileTypeComboBox);
        add(fileTypeLabel);
        add(fileTypeComboBox);
        add(fileTypeField);

        // 设置生成文件的默认类型
        AppParam.fileType = fileTypeList.get(0);
        AppParam.needToBeEmptyList.add(fileTypeField);
    }
}

class FileTypeItemListener implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            AppParam.fileType = e.getItem().toString();
        }
    }
}

class FileTypeFieldFocusListener implements FocusListener {

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField inputFileTypeField = (PlaceholderTextField) e.getSource();
        String fileType = inputFileTypeField.getText();
        if (StringUtils.isBlank(fileType)) {
            AppParam.inputFileType = null;
        } else {
            String regex = "[0-9a-zA-Z]+";
            if (fileType.matches(regex)) {
                AppParam.inputFileType = fileType.trim().toLowerCase();
            } else {
                inputFileTypeField.setText(null);
            }
        }
    }
}

