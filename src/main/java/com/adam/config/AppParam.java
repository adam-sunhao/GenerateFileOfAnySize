package com.adam.config;

import com.adam.util.FileUtil;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存需要用到的参数
 *
 * @author AdamSun
 * @date 2020/8/15 11:03
 */
public class AppParam {

    // 文件保存路径
    public static String path = null;
    public static String fileType = null;
    // 用户输入的文件类型
    public static String inputFileType = null;
    // 主frame
    public static JFrame mainFrame = null;
    // 当前状态
    public static StatusCode currentCode = StatusCode.FREE;
    // 生成文件按钮
    public static JButton generateButton = null;
    // 重置按钮
    public static JButton resetButton = null;

    // 需要被清空的内容的组件
    public static List<JComponent> needToBeEmptyList = new ArrayList<>();
    public static Map<String, Integer> inputUnitAndSizeMap = new LinkedHashMap<>();

    static {
        // 初始化所有输入值大小
        Map<String, Long> unitAndBytesMap = FileUtil.unitAndBytesMap;
        for (String key : unitAndBytesMap.keySet()) {
            inputUnitAndSizeMap.put(key, 0);
        }
    }


}
