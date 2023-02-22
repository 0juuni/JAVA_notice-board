package com.DingTons.java.AM.dto;

//Member 클래스 (사용자 정보)
public class Member extends Dto {
	public String loginId;
	public String loginPw;
	public String name;

	public Member(int id, String regDate, String loginId, String loginPw, String name) {
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.name = name;
	}

}