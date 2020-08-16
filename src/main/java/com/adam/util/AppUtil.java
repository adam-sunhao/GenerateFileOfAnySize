package com.adam.util;

import com.adam.config.AppParam;

import javax.swing.*;
import java.util.List;
import java.util.Map;

/**
 * @author AdamSun
 * @date 2020/8/15 18:54
 */
public class AppUtil {

    /**
     * 验证前置条件是否满足
     */
    public static void validationPreconditions() {

    }

    /**
     * 将除保存路径外所有组件恢复原值
     */
    public static void empty() {

        // 清空界面组件上的值
        List<JComponent> componentList = AppParam.needToBeEmptyList;
        for (JComponent component : componentList) {
            if (component instanceof JTextField) {
                JTextField tempTextField = (JTextField) component;
                tempTextField.setText(null);
            } else if (component instanceof JSpinner) {
                JSpinner tempSpinner = (JSpinner) component;
                tempSpinner.setValue(0);
            }
        }

        // 清空缓存中的值
        AppParam.inputFileType = null;
        Map<String, Integer> inputUnitAndSizeMap = AppParam.inputUnitAndSizeMap;
        for (String key : inputUnitAndSizeMap.keySet()) {
            inputUnitAndSizeMap.put(key, 0);
        }
    }
}
