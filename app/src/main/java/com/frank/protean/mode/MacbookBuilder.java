package com.frank.protean.mode;

public class MacbookBuilder extends Builder {
    protected Computer computer = new Macbook();

    @Override
    public Builder buildBoard(String board) {
        computer.setmBoard(board);
        return this;
    }

    @Override
    public Builder buildDisplay(String display) {
        computer.setmDisplay(display);
        return this;
    }

    @Override
    public Builder buildOS() {
        computer.setmOS();
        return this;
    }

    @Override
    public Computer create() {
        return computer;
    }
}
