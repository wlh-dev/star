package com.star.wlh.status;

public class Order {
	private Order order;
	private OrderStateEnum orderState;

	public Order() {
		this.orderState = OrderStateEnum.PAY;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderStateEnum getOrderState() {
		return orderState;
	}

	public void setOrderState(OrderStateEnum orderStateEnum) {
		this.orderState = orderStateEnum;
	}
	public void nextState(){
		orderState.nextState(this);
	}
}

