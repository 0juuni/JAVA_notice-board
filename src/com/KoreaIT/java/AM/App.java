package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class App {

	public static List<Article> articles;

	static {
		articles = new ArrayList<>();
	}

	public void run() {
		System.out.println("==프로그램 시작==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		int lastArticleId = 3;

		while (true) {
			System.out.printf("명령어 ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
				} else {
					System.out.println("   번호     /     제목       /   조회  ");
					for (int i = articles.size() - 1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("  %4d    /   %7s      /  %4d    \n", article.id, article.title, article.hit);
					}
				}

			} else if (command.equals("article write")) {
				int id = lastArticleId + 1;
				System.out.printf("제목 : ");
				String regDate = Util.getNowDateTimeStr();
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, title, body);
				articles.add(article);

				System.out.println(id + "번 글이 생성되었습니다");
				lastArticleId++;
			} else if (command.startsWith("article detail ")) {

				String[] cmdBits = command.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				Article foundArticle = getArticleById(id);

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				foundArticle.increaseHit();

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("조회수 : %d\n", foundArticle.hit);

			} else if (command.startsWith("article modify ")) {

				String[] cmdBits = command.split(" ");

				int id = Integer.parseInt(cmdBits[2]);
				// ---> 다른방식 int foundIndex = -1;
				Article foundArticle = getArticleById(id);

				/* for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						// --->> 다른 방식 foundIndex = i;
						break;
					} 
				} */

				if (foundArticle == null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}

				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();

				foundArticle.title = title;
				foundArticle.body = body;
				/*
				 ---> 다른 방식 : Article 자체를 덮어씌우는 방식 그러나 효율성 떨어짐 
				 Article article2 = new Article(id,regDate, title, body); 
				 articles.set(foundIndex, article2);
				 */

				System.out.printf("%d번 글을 수정했습니다.\n", id);

			} else if (command.startsWith("article delete ")) {

				String[] cmdBits = command.split(" ");

				int id = Integer.parseInt(cmdBits[2]);

				int foundIndex = getArticleIndexById(id);
				
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				// size() -> 3
				// index : 0, 1, 2
				// id : 1, 2, 3

				articles.remove(foundIndex);
				System.out.printf("%d번 글을 삭제했습니다.\n", id);

			}

			else {
				System.out.println("존재하지 않는 명령어 입니다");
			}
		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}
	private int getArticleIndexById(int id) {
		int i =0;
		for (Article article : articles) {
			if(article.id == id) {
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public Article getArticleById(int id) {
		/* 1번 방식
		 for (int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if (article.id == id) {
				return article;
			} */
		/* 2번방식\
		 for (Article article : articles) {
			if(article.id == id) {
				return article;
			}
		}
		return null; */
		
		//3번방식
		int index = getArticleIndexById(id);
		
		if(index != -1) {
			return articles.get(index);
		}
		return null;
	}
	static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");
		articles.add(new Article(1, Util.getNowDateTimeStr(), "제목1", "내용1", 11));
		articles.add(new Article(2, Util.getNowDateTimeStr(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDateTimeStr(), "제목3", "내용3", 33));
	}


}
