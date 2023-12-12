package com.star.wlh.status;

public class StatusMain {
	public static void main(String[] args) {
		Order order = new Order();
		System.out.println(order.getOrderState());
		order.nextState();
		System.out.println(order.getOrderState());
		order.nextState();
		System.out.println(order.getOrderState());
	}
}
