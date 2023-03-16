package com.star.wlh.designpattern.interpreter;

public abstract class Expression {
	abstract Long interpreter(Context context);
	Integer findNextNumber(Context context){
		return 0;
	};
}
