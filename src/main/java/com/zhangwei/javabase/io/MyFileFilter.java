package com.zhangwei.javabase.io;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter {

    public boolean canRead;

    public boolean canWrite;

    public boolean canExecute;

    public long lastModifiedTime;

    @Override
    public boolean accept(File file) {
        if(canRead==file.canRead() &&
                canWrite==file.canWrite() &&
                canExecute==file.canExecute() &&
                lastModifiedTime<file.lastModified()){
            return true;
        }
        return false;
    }
}
