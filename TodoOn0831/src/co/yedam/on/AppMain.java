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
		System.out.println("===남아있는 인덱스===");
		
		tdo.showAll();
		
		System.out.println("=== === === === ===");
		
		int choice = 0;
		
		do {
		System.out.println();
		System.out.println("원하시는 메뉴를 선택하세요.");
		System.out.println("1.입력 2.조회 3.수정 4.삭제 5.완료 6.종료");
		System.out.print("선택>>");
			
		choice = Integer.parseInt(sc.nextLine());
				
		switch ( choice ) {
		case MENU.INSERT: insert(); break;
		case MENU.SEARCH: search(); break;
		case MENU.UPDATE: update(); break;
		case MENU.DELETE: delete(); break;
		case MENU.CHECK: check(); break;
		case MENU.EXIT: exit();
		}
		} 
		while(choice != 6);
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
		
		tdo.search();
		
		System.out.println("수정하고싶은 인덱스를 선택하세요.");
		System.out.println("1.일일 2.주간 3.월간 4.연간 5.버킷");
		System.out.print("선택>>");
		
		int choiceT = Integer.parseInt(sc.nextLine());
		
		switch (choiceT) {
		case 1: ddao.updateD(); break;
//		case 2: break;
//		case 3: break;
//		case 4: break;
//		case 5: break;
		} 
	}
        
    //삭제
	//no. / 입력날짜 / 만기날짜 / 완료날짜 / 인덱스 / 내용
	public void delete() {
		
		tdo.showAll(); // search을 할지 고민
		
		System.out.println("삭제하고싶은 인덱스를 선택하세요.");
		System.out.println("1.일일 2.주간 3.월간 4.연간 5.버킷");
		
		System.out.print("선택>>");
		int choiceT = Integer.parseInt(sc.nextLine());
		
		switch (choiceT) {
		case 1: ddao.deleteD(); break;
//		case 2: break;
//		case 3: break;
//		case 4: break;
//		case 5: break;
		} 
	}
	
	public void check () {
		
		tdo.check(); //체크,취소
	}
	
	
	
	
	
	//종료
	public void exit() {
		
	}

	
	
	
	
}
