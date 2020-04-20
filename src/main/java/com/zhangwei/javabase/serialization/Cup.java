package com.zhangwei.javabase.serialization;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

@Setter
@Getter
@ToString
public class Cup implements Externalizable {

    //private static final long serialVersionUID = 1L;
    private static final long serialVersionUID = 2L;

    private int size;
    private String shape;
    private String material;

    public Cup(){}

    public Cup(int size, String shape, String material) {
        this.size = size;
        this.shape = shape;
        this.material = material;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(shape);
        out.writeInt(size);
        out.writeObject(material);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        //读的顺序要和写的顺序保持一致
        shape = (String) in.readObject();
        size = in.readInt();
        material = (String) in.readObject();
    }
}
