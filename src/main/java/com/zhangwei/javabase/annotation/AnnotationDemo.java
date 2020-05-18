package com.zhangwei.javabase.annotation;

import com.zhangwei.javabase.common.MyObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationDemo {
    public static void main(String[] args) throws IOException {
        test01();
    }

    public static void test01(){
        Class myObjectClass = MyObject.class;
        Field[] myObjFields = myObjectClass.getDeclaredFields();
        Method[] myObjMethods = myObjectClass.getDeclaredMethods();

        List<Annotation> annotationList = new ArrayList<>();
        annotationList.addAll(Arrays.asList(myObjectClass.getDeclaredAnnotations()));
        for (Field field : myObjFields){
            annotationList.addAll(Arrays.asList(field.getDeclaredAnnotations()));
        }
        for (Method method : myObjMethods){
            annotationList.addAll(Arrays.asList(method.getDeclaredAnnotations()));
        }
        for(Annotation anno : annotationList){
            System.out.println(anno.annotationType());
            if(anno instanceof MyClassAnno){
                System.out.println("str : "+((MyClassAnno)anno).str());
                System.out.println("val : "+((MyClassAnno)anno).val());
            }
            if(anno instanceof MyFieldAnno){
                System.out.println("value : "+((MyFieldAnno)anno).value());
            }
            if(anno instanceof MyMeghodAnno){
                System.out.println("value : "+((MyMeghodAnno)anno).value());
                System.out.println("logging : "+((MyMeghodAnno)anno).logging());
            }
        }
    }

}


