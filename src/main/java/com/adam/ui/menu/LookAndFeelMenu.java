package com.adam.ui.menu;

import com.adam.config.AppConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LookAndFeelMenu {
    public static final String METAL = "Metal";
    public static final String NIMBUS = "Nimbus";
    public static final String CDE_MOTIF = "CDE/Motif";
    public static final String WINDOWS = "Windows";
    public static final String WINDOWS_CLASSIC = "Windows Classic";

    public static void createLookAndFeelMenuItem(JMenu jmenu, Component cmp) {
        final UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();

        JRadioButtonMenuItem rbm[] = new JRadioButtonMenuItem[infos.length];
        ButtonGroup bg = new ButtonGroup();
        JMenu tmp = new JMenu("Change Look and Feel");
        tmp.setMnemonic('C');
        int index = -1; // 指定风格下标
        String classname = null;
        for (int i = 0; i < infos.length; i++) {
            rbm[i] = new JRadioButtonMenuItem(infos[i].getName());
            rbm[i].setMnemonic(infos[i].getName().charAt(0));
            tmp.add(rbm[i]);
            bg.add(rbm[i]);
            rbm[i].addActionListener(new LookAndFeelMenuListener(infos[i].getClassName(), cmp));
            if (AppConfig.CURRENT_LOOK_AND_FEEL.equals(rbm[i].getText())) {
                index = i;
                classname = infos[i].getClassName();
            }
        }
        if (index == -1 || classname == null) {
            rbm[0].setSelected(true);
        } else {
            rbm[index].setSelected(true);
            updateUI(classname, cmp);
        }
        jmenu.add(tmp);
    }

    public static void updateUI(String classname, Component jf) {
        try {
            UIManager.setLookAndFeel(classname);
            SwingUtilities.updateComponentTreeUI(jf);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class LookAndFeelMenuListener implements ActionListener {
    String classname;
    Component jf;

    LookAndFeelMenuListener(String cln, Component jf) {
        this.jf = jf;
        classname = new String(cln);
    }

    public void actionPerformed(ActionEvent ev) {
        try {
            UIManager.setLookAndFeel(classname);
            SwingUtilities.updateComponentTreeUI(jf);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
