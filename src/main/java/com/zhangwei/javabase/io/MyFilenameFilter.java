package com.zhangwei.javabase.io;

import java.io.File;
import java.io.FilenameFilter;

public class MyFilenameFilter implements FilenameFilter {
    private String extName;

    public MyFilenameFilter(String extName){
        this.extName="."+extName;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extName);
    }
}
