package com.DingTons.java.AM.controller;

import com.DingTons.java.AM.dto.Member;

//추상 클래스 이용
public abstract class Controller {

	public static Member loginedMember = null;

	public static boolean isLogined() {
		return loginedMember != null;
	}

	public abstract void doAction(String command, String actionMethodName);

	public abstract void makeTestData();
}