package co.yedam.on;

import java.util.Scanner;

public class AppMain implements MENU{

	static AppMain main = new AppMain();
	ToDoOn tdo = new ToDoOn();
	DAO dao = new DAO();
	DailyDAO ddao = new DailyDAO();
	WeeklyDAO wdao = new WeeklyDAO();
	MonthlyDAO mdao = new MonthlyDAO();
	YearlyDAO ydao = new YearlyDAO();
	BucketDAO bdao = new BucketDAO();
	
	Scanner sc = new Scanner(System.in);
	
	
	//메인 메서드
	public static void main(String[] args) throws Exception {
		main.start();
	}
	
	//시작 메서드
	public void start() throws Exception{
		System.out.println("O-Neul은 ON을 하자. ToDoOn입니다.");
		System.out.println();
		System.out.println("===============남아있는 인덱스==============");
		tdo.showAll();
		System.out.println("========================================");  //= 40개
		
		int choice = 0;
		
		do {
		System.out.println("****************************************");
		System.out.println("원하시는 메뉴를 선택하세요.");
		System.out.println("1.입력 2.조회 3.수정 4.삭제 5.완료 6.종료");
		System.out.print("선택>>");
		
		try {
		choice = Integer.parseInt(sc.nextLine());
		} catch (Exception e) {
		System.out.println("****************************************");
		System.out.println("정확히 입력해주세요.");
		continue;
		}
		
		if ((choice > 6)||(choice < 1)) {
			System.out.println("****************************************");
			System.out.println("정확히 입력해주세요.");
			} else {
		
		System.out.println("========================================");
		
			switch ( choice ) {
			case MENU.INSERT: insert(); break;
			case MENU.SEARCH: search(); break;
			case MENU.UPDATE: update(); break;
			case MENU.DELETE: delete(); break;
			case MENU.CHECK: check(); break;
			case MENU.EXIT: exit();
			} 
			} 
		}while(choice != 6);
	}
	
	
	
	//입력 메서드
	public void insert() {
		
		int choice=0;
		
		do {
		System.out.println("========================================");
		System.out.println("0.메인메뉴 1.일일 2.주간 3.월간 4.연간 5.버킷");
		System.out.print("선택>>");
		
		try {
			choice = Integer.parseInt(sc.nextLine());
			
			if ((choice > 5)||(choice < 0)) {
				System.out.println("****************************************");
				System.out.println("정확히 입력해주세요.");
			} else {
			
			switch ( choice ) {
			case MENU.DAILY: ddao.insertD(); break;
			case MENU.WEEKLY: wdao.insertW(); break;
			case MENU.MONTHLY: mdao.insertM(); break;
			case MENU.YEARLY: ydao.insertY(); break;
			case MENU.BUCKET: bdao.insertB(); break;
			}
			} 
		}catch (Exception e) {
			System.out.println("****************************************");
			System.out.println("정확히 입력해주세요.");
		} 
		} while (choice != 0); 
	}
	
	
	
	//조회 메서드
	public void search() {
		
		int choice=0;
		
		do {
		System.out.println("수행하실 작업을 선택하세요.");
		System.out.println("0.메인메뉴 1.검색 2.시간경과 인덱스 확인");
		System.out.print("선택>>");
		
		try {
			choice = Integer.parseInt(sc.nextLine());
			
			if ((choice > 2)||(choice < 0)) {
				System.out.println("****************************************");
				System.out.println("정확히 입력해주세요.");
			} else {
			switch (choice) {
			case 1: tdo.search(); break;
			case 2: tdo.showLapse(); break;	
			}
			} 
		}catch (Exception e) {
			System.out.println("****************************************");
			System.out.println("정확히 입력해주세요.");
		}
		}while (choice !=0);
	}
	
	
	
	//수정 메서드
	public void update() {
		
		int choiceT=0;
		
		
		do {
		
		System.out.println("========================================");
		System.out.println("수정하고싶은 인덱스를 선택하세요.");
		System.out.println("0.메인메뉴 1.일일 2.주간 3.월간 4.연간 5.버킷 6.검색");
		System.out.print("선택>>");
		
		try {
			choiceT = Integer.parseInt(sc.nextLine());
			
			if ((choiceT > 6)||(choiceT < 0)) {
				System.out.println("****************************************");
				System.out.println("정확히 입력해주세요.");
			} else {
		
			switch (choiceT) {
			case MENU.DAILY: ddao.updateD(); break;
			case MENU.WEEKLY: wdao.updateW(); break;
			case MENU.MONTHLY: mdao.updateM(); break;
			case MENU.YEARLY: ydao.updateY(); break;
			case MENU.BUCKET: bdao.updateB(); break;
			case 6:tdo.search(); break;
			} 
			} 
		}catch (Exception e) {
			System.out.println("****************************************");
			System.out.println("정확히 입력해주세요.");
		} 
		} while (choiceT != 0);
	}
        
	
	
    //삭제 메서드
	public void delete() {
		
		int choiceT=0;
		
		do {
		
		tdo.showAll();
		System.out.println("========================================");
		System.out.println("삭제하고싶은 인덱스를 선택하세요.");
		System.out.println("0.메인메뉴 1.일일 2.주간 3.월간 4.연간 5.버킷");
		
		System.out.print("선택>>");
		
		try {
			choiceT = Integer.parseInt(sc.nextLine());
			
			if ((choiceT > 5)||(choiceT < 0)) {
				System.out.println("****************************************");
				System.out.println("정확히 입력해주세요.");
			} else {
		
			switch (choiceT) {
			case MENU.DAILY: ddao.deleteD(); break;
			case MENU.WEEKLY: wdao.deleteW();break;
			case MENU.MONTHLY: mdao.deleteM();break;
			case MENU.YEARLY: ydao.deleteY();break;
			case MENU.BUCKET: bdao.deleteB();break;
			} 
			} 
		}catch (Exception e) {
			System.out.println("****************************************");
			System.out.println("정확히 입력해주세요.");
		} System.out.println("========================================");
		} 
		while (choiceT != 0);
	}
	
	
	
	//체크 - 완료, 취소 메서드
	public void check () {
		
		int choice=0;
		
		do {
		System.out.println("========================================");
		System.out.println("수행하실 작업을 선택하세요.");
		System.out.println("0.메인메뉴 1.완료 2.완료취소");
		System.out.print("선택>>");
		
		try {
			choice = Integer.parseInt(sc.nextLine());
			
			if ((choice > 2)||(choice < 0)) {
				System.out.println("****************************************");
				System.out.println("정확히 입력해주세요.");
			} else {
			switch (choice) {
			case 1: tdo.checkF(); break;
			case 2: tdo.checkB(); break;	
			}
			} 
		}catch (Exception e) {
			System.out.println("****************************************");
			System.out.println("정확히 입력해주세요.");
		} 
		} while(choice != 0);	
	}
	
	
	
	//종료 메서드
	public void exit() {
		System.out.println("ToDoOn을 이용해주셔서 감사합니다.");
	}

	
	
	
	
}
