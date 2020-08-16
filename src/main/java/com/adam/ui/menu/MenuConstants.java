package com.adam.ui.menu;

/**
 * @author AdamSun
 * @date 2020/8/15 9:31
 */
public interface MenuConstants {
    // 菜单
    final String fileText = "File";
    final String viewText = "View";
    final String helpText = "Help";

    final String viewStatusBar = "Status Bar";
    // about
    final String helpHelpTopic = "Help Topic";
    final String helpAbout = "About";

    String aboutTitle = "关于";
    String aboutText = "<html><br/>" +
            "建议使用JAVA 7及以上版本，否则可能导致部分功能无法使用<hr/>" +
            "本工具可用于生成任意大小、任意格式的文件<hr/>" +
            "但所有生成的文件均无法正常使用，仅可用于测试文件上传大小时使用<hr/><br/>" +
            "<p>Made By Adam</p>" +
            "<html>";
}
