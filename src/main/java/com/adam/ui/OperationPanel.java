package com.adam.ui;

import javax.swing.*;

/**
 * @author AdamSun
 * @date 2020/8/14 22:35
 */
public class OperationPanel extends JPanel {
    public OperationPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setAlignmentY(10);
        //setLayout(new FlowLayout(FlowLayout.LEFT, 0, 15));
        //setLayout(new GridLayout(4, 1));
        add(new DirChooser());
        add(new FileTypeComboBox());
        add(new FileSizeInputGroup());
        add(new OperationButtons());
    }
}
