package com.star.web;

public class ListNode {
    Integer val;
    ListNode next = null;

    public ListNode() {
    }

    public ListNode(Integer val) {
        this.val = val;
    }

    public ListNode(Integer val, ListNode next) {
        this.val = val;
        this.next = next;

    }
}