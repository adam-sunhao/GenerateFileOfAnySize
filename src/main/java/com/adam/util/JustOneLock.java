package com.adam.util;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

/**
 * 该类用于控制生成后的Jar仅可运行一个
 * @author AdamSun
 * @date 2020/8/15 22:36
 */
public class JustOneLock {
    private String appName;
    private File file;
    private FileChannel channel;
    private FileLock lock;

    public JustOneLock(String appName) {
        this.appName = appName;
    }

    public boolean isAppActive() {
        try {
            file = new File
                    (System.getProperty("user.home"), appName + ".tmp");
            channel = new RandomAccessFile(file, "rw").getChannel();

            try {
                lock = channel.tryLock();
            } catch (OverlappingFileLockException e) {
                // already locked
                closeLock();
                return true;
            }

            if (lock == null) {
                closeLock();
                return true;
            }

            Runtime.getRuntime().addShutdownHook(new Thread() {
                // destroy the lock when the JVM is closing
                public void run() {
                    closeLock();
                    deleteFile();
                }
            });
            return false;
        } catch (Exception e) {
            closeLock();
            return true;
        }
    }

    private void closeLock() {
        try {
            lock.release();
        } catch (Exception e) {
        }
        try {
            channel.close();
        } catch (Exception e) {
        }
    }

    private void deleteFile() {
        try {
            file.delete();
        } catch (Exception e) {
        }
    }
}
