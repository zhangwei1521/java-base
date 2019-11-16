package com.zhangwei.javabase.io;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.getName().endsWith("txt");
    }
}
