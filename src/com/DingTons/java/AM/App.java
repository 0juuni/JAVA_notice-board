package com.DingTons.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.DingTons.java.AM.controller.ArticleController;
import com.DingTons.java.AM.controller.Controller;
import com.DingTons.java.AM.controller.MemberController;

public class App {
	
	// 영향을 주는 요소 do / 출력 동작 show
	public void run() {
		System.out.println("==프로그램 시작==");

		Scanner sc = new Scanner(System.in);
		
		//Member/Article 컨트롤러
		// MemberController -> 회원가입, 로그인 기능, Article Controller -> 게시글 기능
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		//테스트 데이터 생성
		articleController.makeTestData(); 
		memberController.makeTestData();

		// 컨트롤러
		Controller controller;

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

			String[] cmdBits = command.split(" ");
			String controllerName = cmdBits[0];

			if (cmdBits.length == 1) {
				System.out.println("명령어를 확인해주세요");
				continue;
			}

			String actionMethodName = cmdBits[1];

			controller = null;

			if (controllerName.equals("article")) {
				controller = articleController;
			} else if (controllerName.equals("member")) {
				controller = memberController;
			} else {
				System.out.println("존재하지 않는 명령어입니다");
				continue;
			}
			
			// APP의 기능을 controller에 도입
			controller.doAction(command, actionMethodName);
			
			/* if (command.equals("member join")) {
				memberController.doJoin();
			} else if (command.equals("article list")) {
				articleController.showList();
			} else if (command.equals("article write")) {
				articleController.doWrite();
			} else if (command.startsWith("article detail ")) {
				articleController.showDetail(command);
			} else if (command.startsWith("article modify ")) {
				articleController.doModify(command);
			} else if (command.startsWith("article delete ")) {
				articleController.doDelete(command);
			} else {
				System.out.println("존재하지 않는 명령어 입니다");
			} */
		}

		System.out.println("==프로그램 끝==");

		sc.close();

	}
}
