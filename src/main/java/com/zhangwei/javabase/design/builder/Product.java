package com.zhangwei.javabase.design.builder;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
public class Product {
    private final int id;
    private String name;
    private Double price;
    private Date produceDate;

    private Product(ProductBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.price = builder.price;
        this.produceDate = builder.produceDate;
    }

    public static class ProductBuilder{
        private final int id;
        private String name;
        private Double price;
        private Date produceDate;

        public ProductBuilder(int id){
            this.id = id;
        }

        public ProductBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder price(Double price){
            this.price = price;
            return this;
        }

        public ProductBuilder produceDate(Date produceDate){
            this.produceDate = produceDate;
            return this;
        }

        public Product build(){
            return new Product(this);
        }

    }
}
