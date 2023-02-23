package com.DingTons.java.AM.dto;

//Article 클래스
public class Article extends Dto {
	public String title;
	public String body;
	public int hit;
	
	public int memberId;
	public String membeld;
	
	public Article(int id, String regDate, int memberId, String title, String body) {
		this(id, regDate, memberId, title, body, 0);
	}

	public Article(int id, String regDate, int memberId, String title, String body, int hit) {
		this.id = id;
		this.regDate = regDate;
		this.memberId = memberId;
		this.title = title;
		this.body = body;
		this.hit = hit;
	}

	public void increaseHit() {
		this.hit++;
	}
}