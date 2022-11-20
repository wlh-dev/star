package com.star.wlh.common.entity.base;

public class NodeTest {
    public static void main(String[] args) {
        Node node1 = new Node();
        Node node2 = new Node();
        node1.setNext(node2);
        node1.setData(1);
        node2.setData(2);
        System.out.println(node1);
    }
}
