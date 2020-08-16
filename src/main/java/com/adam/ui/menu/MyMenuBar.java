package com.adam.ui.menu;

import com.adam.config.AppParam;
import com.adam.util.MessageDialogUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author AdamSun
 * @date 2020/8/15 9:31
 */
public class MyMenuBar extends JMenuBar implements ActionListener {

    private JFrame frame;

    public MyMenuBar(JFrame frame) {
        this.frame = frame;
        /*
         * 创建JMenu
         * 创建子菜单项
         * 添加绑定事件
         * */
        JMenu viewMenu = MenuUtil.createMenu(MenuConstants.viewText, this);
        JMenu helpMenu = MenuUtil.createMenu(MenuConstants.helpText, this);

        // MenuUtil.createCheckBoxMenuItem(MenuConstants.viewStatusBar, MenuUtil.EMPTY_KEY, viewMenu, this).setSelected(true);
        LookAndFeelMenu.createLookAndFeelMenuItem(viewMenu, frame);

        JMenuItem temp;

        temp = MenuUtil.createMenuItem(MenuConstants.helpHelpTopic, MenuUtil.EMPTY_KEY, helpMenu, this);
        temp.setEnabled(false);
        helpMenu.addSeparator();
        MenuUtil.createMenuItem(MenuConstants.helpAbout, MenuUtil.EMPTY_KEY, helpMenu, this);

        frame.setJMenuBar(this);
        AppParam.mainFrame = this.frame;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String cmdText = ev.getActionCommand();
        if (MenuConstants.viewStatusBar.equals(cmdText)) {
            JCheckBoxMenuItem temp = (JCheckBoxMenuItem) ev.getSource();
            // statusBar.setVisible(temp.isSelected());
        } else if (MenuConstants.helpAbout.equals(cmdText)) {
            MessageDialogUtil.showInfoDialog(MenuConstants.aboutText, MenuConstants.aboutTitle);
        } else {
            JDialog dialog = new JDialog(MyMenuBar.this.frame, "该功能暂时未实现", true);
            dialog.setVisible(true);
        }
    }
}
