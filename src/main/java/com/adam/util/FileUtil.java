package com.adam.util;

import com.adam.config.AppParam;
import com.adam.exception.AppException;
import com.adam.exception.DataAbnormalException;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author AdamSun
 * @date 2020/8/14 21:34
 */
public class FileUtil {

    public static byte[] KBCache = new byte[1 << 10];
    public static byte[] MBCache = new byte[1 << 10 << 10];
    public static Map<String, Long> unitAndBytesMap = new LinkedHashMap<>();

    static {
        unitAndBytesMap.put("TB", 1L << 10 << 10 << 10 << 10);
        unitAndBytesMap.put("GB", 1L << 10 << 10 << 10);
        unitAndBytesMap.put("MB", 1L << 10 << 10);
        unitAndBytesMap.put("KB", 1L << 10);
    }

    /**
     * 在指定位置以指定格式生成文件
     */
    public static void generateFile() {
        String filepath = AppParam.path;
        if (StringUtils.isBlank(filepath)) {
            throw new DataAbnormalException("请选择文件保存目录");
        }
        String filename = getFileName();
        File dir = new File(filepath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File generateFile = new File(dir, filename);
        // 如果文件包含kb，则kb单独建立缓存，其他默认为1M
        Map<String, Integer> inputUnitAndSizeMap = AppParam.inputUnitAndSizeMap;

        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(generateFile));) {
            for (String key : inputUnitAndSizeMap.keySet()) {
                Integer size = inputUnitAndSizeMap.get(key);
                if ("kb".equalsIgnoreCase(key)) {
                    for (int i = 0; i < size; i++) {
                        os.write(KBCache);
                    }
                } else {
                    long times = size * (unitAndBytesMap.get(key) / MBCache.length);
                    for (long i = 0; i < times; i++) {
                        os.write(MBCache);
                    }
                }
            }
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
    }

    /**
     * 生成文件名
     *
     * @return
     */
    public static String getFileName() {
        // 文件名格式: 0TB+1GB+0MB+1KB.txt
        Map<String, Integer> inputUnitAndSizeMap = AppParam.inputUnitAndSizeMap;
        StringBuffer filenameBuffer = new StringBuffer();
        for (String key : inputUnitAndSizeMap.keySet()) {
            Integer size = inputUnitAndSizeMap.get(key);
            if (size == null || size == 0) {
                continue;
            }
            if (filenameBuffer.length() != 0) {
                filenameBuffer.append("+");
            }
            filenameBuffer.append(size).append(key);
        }

        if (filenameBuffer.length() == 0) {
            throw new DataAbnormalException("请指定生成文件的大小");
        }

        filenameBuffer.append(".");
        if (StringUtils.isNotBlank(AppParam.inputFileType)) {
            filenameBuffer.append(AppParam.inputFileType.trim().toLowerCase());
        } else if (StringUtils.isNotBlank(AppParam.fileType)) {
            filenameBuffer.append(AppParam.fileType);
        } else {
            throw new DataAbnormalException("文件格式异常，请重新选择");
        }
        return filenameBuffer.toString();
    }

}
