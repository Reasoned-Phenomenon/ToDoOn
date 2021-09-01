package co.yedam.on;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		
		int choice = 0;
		
		do {
		System.out.println();
		System.out.println("원하시는 메뉴를 선택하세요.");
		System.out.println("1.입력 2.조회 3.수정 4.삭제 5.종료");
		System.out.print("선택>>");
			
		choice = Integer.parseInt(sc.nextLine());
				
		switch ( choice ) {
		case MENU.INSERT: insert(); break;
		case MENU.SEARCH: search(); break;
		case MENU.UPDATE: update(); break;
		case MENU.DELETE: delete(); break;
		case MENU.EXIT: exit();
		}
		} 
		while(choice != 5);
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
		String beforeTable = null;
		
		switch (choiceT) {
		case 1: beforeTable = "daily"; break;
		case 2: beforeTable = "weekly"; break;
		case 3: beforeTable = "monthly"; break;
		case 4: beforeTable = "yearly"; break;
		case 5: beforeTable = "bucket"; break;
		} 
		
		System.out.print("번호>>");
		String beforeNo = sc.nextLine();
		
		System.out.println("수정하고 싶은 곳을 선택하세요.");
		System.out.println("1.인덱스 2.내용 3.만기날짜(YY/MM/DD)");
		System.out.print("선택>>");
		
		int choiceC = Integer.parseInt(sc.nextLine());
		String beforeColumn = null;
		
		switch (choiceC) {
		case 1: beforeColumn = "memo_index"; break;
		case 2: beforeColumn = "memo_content"; break;
		case 3: beforeColumn = "expiry_date"; break;
		} 
		
		System.out.println("수정하세요.");
		System.out.print("입력>>");
		String afterColumn = sc.nextLine();
		
		
		//연결
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sqld = "";
        
        try {
            con=dao.connect();
            sqld = "UPDATE ? SET ? = ? WHERE NO = ? ";
            pstmt = con.prepareStatement(sqld);
            pstmt.setString(1, beforeTable);
            pstmt.setString(2, beforeColumn);
            pstmt.setString(1, afterColumn);
            pstmt.setString(4, beforeNo);
            
            result = pstmt.executeUpdate();
            con.commit();
            System.out.println(result+"건 수정 완료됐습니다.");
            
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dao.disconnect();
            }
            
            
            
            
            
            
	}

	
        
        
        
        
        
        
	public void delete() {
		
	}
	
	public void exit() {
		
	}
	
	
	
	
	
}
