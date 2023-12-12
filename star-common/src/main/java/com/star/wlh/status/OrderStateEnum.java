package com.star.wlh.status;


public enum OrderStateEnum implements OrderState {
	PAY {
		@Override
		public void nextState(Order order) {
			order.setOrderState(UNPAY);
		}
	},
	UNPAY {
		@Override
		public void nextState(Order order) {
			order.setOrderState(FINISH);
		}
	},
	FINISH {
		@Override
		public void nextState(Order order) {

		}
	};
}
