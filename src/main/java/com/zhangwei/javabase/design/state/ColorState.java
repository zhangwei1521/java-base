package com.zhangwei.javabase.design.state;

public enum ColorState implements State {
    RED {
        @Override
        public void pull(ColorBoard colorBoard){
            colorBoard.setState(BLUE);
        }
        @Override
        public void push(ColorBoard colorBoard){
            colorBoard.setState(GREEN);
        }
        @Override
        public String getName(){
            return "red";
        }
    },
    GREEN {
        @Override
        public void pull(ColorBoard colorBoard){
            colorBoard.setState(RED);
        }
        @Override
        public void push(ColorBoard colorBoard){
            colorBoard.setState(BLUE);
        }
        @Override
        public String getName(){
            return "green";
        }
    },
    BLUE {
        @Override
        public void pull(ColorBoard colorBoard){
            colorBoard.setState(GREEN);
        }
        @Override
        public void push(ColorBoard colorBoard){
            colorBoard.setState(RED);
        }
        @Override
        public String getName(){
            return "blue";
        }
    }
}
