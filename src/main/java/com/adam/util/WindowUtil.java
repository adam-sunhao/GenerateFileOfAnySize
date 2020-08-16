package com.adam.util;

import java.awt.*;

/**
 * @author AdamSun
 * @date 2020/8/14 22:19
 */
public class WindowUtil {

    public static Dimension getWindowDimension() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        return screenSize;
    }

    /**
     * 获取使程序居屏幕中间的坐标
     *
     * @param width
     * @param height
     * @return
     */
    public static Point getCenterOfWindow(int width, int height) {
        Dimension dimension = getWindowDimension();
        if (dimension == null) {
            return new Point(0, 0);
        }
        int maxWidth = (int) dimension.getWidth();
        int maxHeight = (int) dimension.getHeight();

        int centerOfWidth = (maxWidth - width) / 2;
        int centerOfHeight = (maxHeight - height) / 2;
        return new Point(centerOfWidth, centerOfHeight);
    }
}
