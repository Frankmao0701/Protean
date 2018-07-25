package com.frank.protean.mode;

/**
 * 单例模式
 * 内部类实现，线程安全，效率高
 */
public class SingleInstance {
    private SingleInstance() {

    }

    public static SingleInstance getInstance() {
        return SingleHolder.mInstance;
    }

    private static class SingleHolder {
        private static final SingleInstance mInstance = new SingleInstance();
    }
}
