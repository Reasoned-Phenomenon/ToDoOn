package co.yedam.on;

import java.util.Scanner;

public class AppMain implements MENU {

	static AppMain main = new AppMain();
	DAO dao = new DAO();
	DailyDAO ddao = new DailyDAO();
	ToDoOn tdo = new ToDoOn();
	Scanner sc = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		main.start();
	}
	

	
	public void start() {
		System.out.println("안녕하세요.");
		System.out.println("남아있는 인덱스>>");
		tdo.showAll();
		System.out.println();
		System.out.println("원하시는 메뉴를 선택하세요.");
		System.out.println("1.입력 2.조회 3.수정 4.삭제 5.종료");
		System.out.print("선택>>");
		int choice = sc.nextInt(); sc.nextLine();
		
		switch ( choice ) {
		case INSERT: insert(); break;
		case SEARCH: search(); break;
		case UPDATE: update(); break;
		case DELETE: delete(); break;
		case EXIT: exit(); break;
		}
		
	}
	
	public void insert() {
		System.out.println("1.일일 2.주간 3.월간 4.연간 5.버킷");
		System.out.print("선택>>");
		int choice = sc.nextInt(); sc.nextLine();
		
		switch ( choice ) {
		case INSERT: ddao.insertD(); break;
//		case SEARCH: dao.insertW(); break;
//		case UPDATE: dao.insertM(); break;
//		case DELETE: dao.insertY(); break;
//		case EXIT: dao.insertB(); break;
		}
		
		
		
	}
	
	public void search() {
		tdo.search();
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}
	
	public void exit() {
		
	}
	
	
	
	
	
}
