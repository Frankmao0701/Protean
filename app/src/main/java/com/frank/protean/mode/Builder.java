package com.frank.protean.mode;

public abstract class Builder {
    public abstract Builder buildBoard(String board);
    public abstract Builder buildDisplay(String display);
    public abstract Builder buildOS();
    public abstract Computer create();
}
