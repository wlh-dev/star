package com.star.wlh.juc;

public class Main {
    public static void main(String[] args) {
        double base = 12000L;
        double total = 0L;
        for (int i = 0; i < 10; i++) {
            total = (base + total) * 1.08;
        }
        System.out.println(total);
    }
}
