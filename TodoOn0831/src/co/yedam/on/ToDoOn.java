package co.yedam.on;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ToDoOn {
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	Scanner sc = new Scanner(System.in);
	List<String> tdo = new ArrayList<>();
	
	DAO dao = new DAO();
	DailyVO dvo = new DailyVO();
	DailyDAO ddao = new DailyDAO();
	
	
	int no;
	String inputDate;
	String expiryDate;
	String finishDate;
	String memoIndex;
	String memoContent;
	
	String daily;
	String weekly;
	String monthly;
	String yearly;
	String bucket;
	
	boolean finish;
	
	ToDoOn () {}

	//getter setter
	
	
	
	
	// 오늘까지 남아있는 모든 인덱스 보여주기
	public void showAll() {
		
		// 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String sqld = "";
//	        String sqlw = "";
//	        String sqlm = "";
//	        String sqly = "";
//	        String sqlb = "";

		Date time = new Date();
		String now = format.format(time);
		
		
		try {
			con = dao.connect();
			// 일일
			sqld = "SELECT NO, MEMO_INDEX FROM daily WHERE( expiry_date > TO_DATE(?,'YY/MM/DD') AND (memo_check = 0) ) ";
			pstmt = con.prepareStatement(sqld);
			pstmt.setString(1, now);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dvo.setNoD(rs.getInt("NO"));
	        	dvo.setMemoIndexD(rs.getString("MEMO_INDEX"));
	        	System.out.println("일일:"+ dvo.getNoD() + "-" + dvo.getMemoIndexD()+" ");
			}
			
			
		}
			
			// 주간

			// 월간

			// 연간

			// 버킷

		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.disconnect();
		}

	}
	
	
	public void search () {
		System.out.println();
		System.out.println("검색하고싶은 인덱스 또는 내용을 입력하세요.");
		System.out.print("인덱스 또는 내용>>");
		String look = sc.nextLine();
		dvo.listD = new ArrayList<>();
		//연결
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sqld = "";
        
        //일일 테이블에서 검색내용 출력
        try {
            con=dao.connect();
            sqld = "SELECT NO, MEMO_INDEX FROM daily WHERE(( MEMO_INDEX LIKE ? ) OR ( MEMO_CONTENT LIKE ?))";
            pstmt = con.prepareStatement(sqld);
            pstmt.setString(1, "%"+look+"%");
            pstmt.setString(2, "%"+look+"%");
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	dvo.listD.add(Integer.toString(rs.getInt("NO")));
            	dvo.listD.add(rs.getString("MEMO_INDEX"));
			}
        	}catch (Exception e) {
                e.printStackTrace();
            } finally {
            dao.disconnect();
            }
        
        for( int i = 0 ; i < (dvo.listD.size())/2 ; i++ ) {
        	int a = 2*i;
        	int b = 2*i+1;
        	System.out.println("일일:"+dvo.listD.get(a)+"-"+dvo.listD.get(b));
        	
        }
        
        
	}

//"찾으시는 내용이 없습니다." << 넣어야하는데	
	
	
	
	
	public void check () {
		
		System.out.println("수행하실 작업을 선택하세요.");
		System.out.println("1.완료 2.완료취소");
		System.out.print("선택>>");
		
		int choice = Integer.parseInt(sc.nextLine());
		
		switch (choice) {
		case 1: checkF(); break;
		case 2: checkB(); break;	
		}
	}
	
	
	
	//완료하기
	public void checkF () {
	//완료하지 않은 인덱스들 보여주기
	System.out.println("=== 완료되지 않은 인덱스 ===");
	//일일
	ddao.checkFSD();
	//주간
	//월간
	//연간
	//버킷
	
	System.out.println("완료하실 인덱스를 선택하세요.");
	System.out.println("1.일일 2.주간 3.월간 4.연간 5.버킷");
	System.out.print("선택>>");
	
	int choice = Integer.parseInt(sc.nextLine());
	
	switch (choice) {
	case 1: ddao.checkFD(); break;
	case 2: break;
	case 3: break;
	case 4: break;
	case 5: break;
	
	}
	}
	
	
	
	//취소하기
	public void checkB () {
		//완료된 인덱스들 보여주기
		System.out.println("=== 완료된 인덱스 ===");
		//일일
		ddao.checkBSD();
		//주간
		//월간
		//연간
		//버킷
		System.out.println("취소하실 인덱스를 선택하세요.");
		System.out.println("1.일일 2.주간 3.월간 4.연간 5.버킷");
		System.out.print("선택>>");
		
		int choice = Integer.parseInt(sc.nextLine());
		
		switch (choice) {
		case 1: ddao.checkBD(); break;
		case 2: break;
		case 3: break;
		case 4: break;
		case 5: break;
		
		}
		}
	
}
