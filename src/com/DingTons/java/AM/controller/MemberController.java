package com.DingTons.java.AM.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.DingTons.java.AM.dto.Article;
import com.DingTons.java.AM.dto.Member;
import com.DingTons.java.AM.util.Util;

public class MemberController extends Controller {

	private List<Member> members;
	private Scanner sc;
	private String command;
	private String actionMethodName;

	public MemberController(Scanner sc) {
		this.members = new ArrayList<>();
		this.sc = sc;
	}

	int lastMemberId = 3;

	public void doAction(String command, String actionMethodName) {
		this.command = command;
		this.actionMethodName = actionMethodName;

		switch (actionMethodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어입니다");
			break;
		}
	}

	public void makeTestData() {
		System.out.println("테스트를 위한 회원 데이터를 생성합니다(3명)");
		members.add(new Member(1, Util.getNowDateTimeStr(), "test1", "test1", "김철수"));
		members.add(new Member(2, Util.getNowDateTimeStr(), "test2", "test2", "김영희"));
		members.add(new Member(3, Util.getNowDateTimeStr(), "test3", "test3", "박영수"));
	}

	private void doLogout() {
		if (isLogined() == false) {
			System.out.println("로그아웃 상태입니다");
			return;
		}

		loginedMember = null;

		System.out.println("== 로그아웃 되었습니다 ==");
		System.out.println("== 시스템 종료 : system exit ==");
	}

	private void doLogin() {
		System.out.printf("로그인 아이디 : ");
		String loginId = sc.nextLine();
		System.out.printf("로그인 비밀번호 : ");
		String loginPw = sc.nextLine();

		Member member = getMemberByLoginId(loginId);

		if (member == null) {
			System.out.println("해당 회원은 존재하지 않습니다");
			return;
		}

		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해주세요");
			return;
		}

		loginedMember = member;

		System.out.printf("%s님 환영합니다\n", loginedMember.name);
		System.out.println("==명령어 안내==");
		System.out.println("1. 전체 게시물 확인 : article list");
		System.out.println("2. 게시물 작성 : article write");
		System.out.println("3. 게시물 조회 : article detail 번호");
		System.out.println("4. 로그아웃 : member logout\n");
	}

	private Member getMemberByLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return null;
		}

		return members.get(index);
	}

	private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);

		if (index == -1) {
			return true;
		}

		return false;
	}

	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	private void doJoin() {
		int id = lastMemberId + 1;
		String regDate = Util.getNowDateTimeStr();
		String loginId = null;

		while (true) {

			System.out.printf("로그인 아이디 : ");
			loginId = sc.nextLine();

			if (isJoinableLoginId(loginId) == false) {
				System.out.println("이미 사용중인 아이디입니다");
				continue;
			}

			break;
		}

		String loginPw = null;
		String loginPwConfirm = null;
		while (true) {
			System.out.printf("로그인 비밀번호 : ");
			loginPw = sc.nextLine();
			System.out.printf("로그인 비밀번호 재확인: ");
			loginPwConfirm = sc.nextLine();

			if (loginPw.equals(loginPwConfirm) == false) {
				System.out.println("비밀번호를 다시 입력해주세요");
				continue;
			}
			break;
		}
		System.out.printf("이름 : ");
		String name = sc.nextLine();

		Member member = new Member(id, regDate, loginId, loginPw, name);
		members.add(member);

		System.out.println("== " + id + "번 회원이 가입되었습니다 ==");
		System.out.println("== 로그인 명령어 member login ==\n");
		lastMemberId++;
		
	}

}
