import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		String cmd = sc.nextLine();
		
		System.out.printf("입력된 명령어 : %s\n", cmd);
		
		System.out.println("==프로그램 끝==");
		
		sc.close();
		
		
		System.out.println("Hello world!");
	}
}