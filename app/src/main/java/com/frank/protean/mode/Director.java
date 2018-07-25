package com.frank.protean.mode;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct(String board, String dispaly) {
        builder.buildBoard(board);
        builder.buildDisplay(dispaly);
        builder.buildOS();
    }
}
