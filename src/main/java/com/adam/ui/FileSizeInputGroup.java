package com.adam.ui;

import com.adam.config.AppConfig;
import com.adam.config.AppParam;
import com.adam.util.FileUtil;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Map;

/**
 * 文件大小输入组
 *
 * @author AdamSun
 * @date 2020/8/15 17:19
 */
public class FileSizeInputGroup extends JPanel {

    private static final String FILE_SIZE_LABEL_TEXT = "文件大小";
    private static final int FILE_SIZE_INPUT_COLUMNS = 4;
    private static final int FRACTION = 0;

    public static final int MIN_NUM = 0;

    public FileSizeInputGroup() {
        init();
    }

    private void init() {
        setLayout(new FlowLayout(FlowLayout.LEFT, AppConfig.H_GAP, AppConfig.V_GAP));
        JLabel fileSizeLabel = new JLabel(FILE_SIZE_LABEL_TEXT);
        add(fileSizeLabel);

        Map<String, Long> unitAndBytesMap = FileUtil.unitAndBytesMap;

        for (String key : unitAndBytesMap.keySet()) {
            JSpinner tempSpinner = new JSpinner();
            JSpinner.NumberEditor editor = new JSpinner.NumberEditor(tempSpinner);

            editor.getFormat().setMinimumIntegerDigits(MIN_NUM);
            editor.getFormat().setMinimumFractionDigits(FRACTION);
            // editor.getTextField().getValue();

            /*JFormattedTextField jftf = ((JSpinner.DefaultEditor) tempSpinner.getEditor()).getTextField();
            jftf.setColumns(FILE_SIZE_INPUT_COLUMNS);*/
            tempSpinner.addChangeListener(new FileSizeChangeListener(key));

            add(tempSpinner);

            JLabel unitLabel = new JLabel(key);
            unitLabel.setLabelFor(tempSpinner);
            add(unitLabel);
            // 隐藏输入框右侧上下箭头
            hideSpinnerArrow(tempSpinner);
            // 添加到需要清空的组件集合
            AppParam.needToBeEmptyList.add(tempSpinner);
        }

    }

    /**
     * 隐藏右侧加减按钮
     *
     * @param spinner
     */
    public void hideSpinnerArrow(JSpinner spinner) {
        Dimension d = spinner.getPreferredSize();
        d.width = 70;
        /*spinner.setUI(new BasicSpinnerUI() {
            protected Component createNextButton() {
                return null;
            }

            protected Component createPreviousButton() {
                return null;
            }
        });*/
        spinner.setPreferredSize(d);
    }
}

class FileSizeChangeListener implements ChangeListener {

    private String unit;

    public FileSizeChangeListener(String unit) {
        this.unit = unit;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            JSpinner spinner = (JSpinner) e.getSource();
            SpinnerModel numberModel = spinner.getModel();
            if (numberModel instanceof SpinnerNumberModel) {
                Number number = ((SpinnerNumberModel) numberModel).getNumber();
                int value = number.intValue();
                if (value < FileSizeInputGroup.MIN_NUM) {
                    numberModel.setValue(FileSizeInputGroup.MIN_NUM);
                }
                AppParam.inputUnitAndSizeMap.put(unit, Math.max(value, FileSizeInputGroup.MIN_NUM));
            }
        }
    }
}
