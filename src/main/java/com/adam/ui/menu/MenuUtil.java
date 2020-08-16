package com.adam.ui.menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author AdamSun
 * @date 2020/8/15 9:49
 */
public class MenuUtil {

    public static final int EMPTY_KEY = -1;// 传入此值代表不绑定按键监听

    /**
     * @param s
     * @param toMenuBar
     * @return
     */
    public static JMenu createMenu(String s, JMenuBar toMenuBar) {
        return createMenu(s, EMPTY_KEY, toMenuBar);
    }

    /**
     * 创建菜单
     *
     * @param s
     * @param key       绑定按键
     * @param toMenuBar
     * @return
     */
    public static JMenu createMenu(String s, int key, JMenuBar toMenuBar) {
        JMenu temp = new JMenu(s);
        if (EMPTY_KEY != key) {
            temp.setMnemonic(key);
        }
        toMenuBar.add(temp);
        return temp;
    }

    /**
     * 创建菜单项
     *
     * @param s
     * @param key
     * @param toMenu
     * @param al
     * @return
     */
    public static JMenuItem createMenuItem(String s, int key, JMenu toMenu, ActionListener al) {
        return createMenuItem(s, key, toMenu, EMPTY_KEY, al);
    }

    /**
     * 创建菜单项
     *
     * @param s
     * @param key
     * @param toMenu
     * @param aclKey
     * @param al
     * @return
     */
    public static JMenuItem createMenuItem(String s, int key, JMenu toMenu, int aclKey, ActionListener al) {
        JMenuItem temp = null;
        if (EMPTY_KEY == key) {
            temp = new JMenuItem(s);
        } else {
            temp = new JMenuItem(s, key);
        }
        temp.addActionListener(al);
        if (EMPTY_KEY != aclKey) {
            temp.setAccelerator(KeyStroke.getKeyStroke(aclKey, ActionEvent.CTRL_MASK));
        }
        toMenu.add(temp);
        return temp;
    }

    /**
     * 创建多选菜单项
     *
     * @param s
     * @param key
     * @param toMenu
     * @param al
     * @return
     */
    public static JCheckBoxMenuItem createCheckBoxMenuItem(String s, int key, JMenu toMenu, ActionListener al) {
        JCheckBoxMenuItem temp = new JCheckBoxMenuItem(s);
        if (EMPTY_KEY != key) {
            temp.setMnemonic(key);
        }
        temp.addActionListener(al);
        temp.setSelected(false);
        toMenu.add(temp);
        return temp;
    }
}
