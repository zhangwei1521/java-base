package com.zhangwei.javabase.ioc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewIocContainer {
    private Map<Class,Object> beanMap;
    private List<Class<?>> beanClasses;

    public NewIocContainer(){
        beanMap = new HashMap<>();
        beanClasses = this.getClassesWithAnnotation(Bean.class);
        for (Class<?> clazz : beanClasses){
            Class<?>[] clz = clazz.getInterfaces();
            if(clz != null){
                beanMap.put(clz[0],this.createBean(clazz));
            }
            else {
                beanMap.put(clazz,this.createBean(clazz));
            }
        }
    }

    public <T> T getBean(Class<T> obj){
        Object bean = beanMap.get(obj);
        return (T)bean;
    }

    private List<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> annotation){
        List<Class<?>> classList = new ArrayList<>();
        URL classpath = Thread.currentThread().getClass().getResource("/");
        String path = classpath.getPath().substring(1);
        this.getClassesWithAnnotation(null,path,annotation,classList);

        return classList;
    }

    private void getClassesWithAnnotation(String packageName,String filePath,Class<? extends Annotation> annotation,List<Class<?>> classList){
        Path dir = Paths.get(filePath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
            for(Path path : stream){
                String fileName = String.valueOf(path.getFileName());
                if(Files.isDirectory(path)){
                    String npkgName = packageName!=null ? packageName+"."+fileName : fileName;
                    getClassesWithAnnotation(npkgName,path.toString(),annotation,classList);
                }
                else {
                    if(!fileName.endsWith("class")){
                        continue;
                    }
                    String className = fileName.substring(0,fileName.length()-6);
                    String fullName = packageName+"."+className;
                    try {
                        Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(fullName);
                        if(clazz != null && clazz.getAnnotation(annotation) != null){
                            classList.add(clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private <T> T createBean(Class<T> clazz){
        if(beanMap.get(clazz) != null){
            return null;
        }
        try {
            for(Class item : beanClasses){
                if(Arrays.asList(item.getInterfaces()).contains(clazz)){
                    clazz = item;
                }
            }
            Constructor[] constructors = clazz.getDeclaredConstructors();
            for(Constructor<T> constructor : constructors){
                if(null != constructor.getAnnotation(Inject.class)){
                    Class<?> dependClass = constructor.getAnnotation(Inject.class).value();
                    Object dependBean = createBean(dependClass);
                    if(dependBean == null){
                        dependBean = beanMap.get(dependClass);
                    }
                    T bean = constructor.newInstance(dependBean);
                    return bean;
                }
            }
            T bean = clazz.newInstance();
            return bean;
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
