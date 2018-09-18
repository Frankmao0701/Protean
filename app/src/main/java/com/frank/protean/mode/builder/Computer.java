package com.frank.protean.mode.builder;

public abstract class Computer {
    public Computer setBoard(String board) {
        this.board = board;
        return this;
    }

    public Computer setDisplay(String display) {
        this.display = display;
        return this;
    }

    protected String board;
    protected String display;
    protected String os;


    public abstract void setOs();

    @Override
    public String toString() {
        return "Computer{" +
                "board='" + board + '\'' +
                ", display='" + display + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
