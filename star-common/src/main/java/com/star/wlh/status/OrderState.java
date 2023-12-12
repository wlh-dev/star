package com.star.wlh.status;


public interface OrderState {

	void nextState(Order order);
}
