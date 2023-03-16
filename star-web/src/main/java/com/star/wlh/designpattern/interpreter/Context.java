package com.star.wlh.designpattern.interpreter;

public class Context {
	private String command;
	private Long value;

	public Context(String command){
		this.command = command;
	}
}
