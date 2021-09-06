package co.yedam.on;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ToDoOn implements MENU{
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	Scanner sc = new Scanner(System.in);
	List<String> tdo = new ArrayList<>();
	
	DAO dao = new DAO();
	
	DailyVO dvo = new DailyVO();
	DailyDAO ddao = new DailyDAO();
	
	WeeklyVO wvo = new WeeklyVO();
	WeeklyDAO wdao = new WeeklyDAO();
	
	MonthlyVO mvo = new MonthlyVO();
	MonthlyDAO mdao = new MonthlyDAO();
	
	YearlyVO yvo = new YearlyVO();
	YearlyDAO ydao = new YearlyDAO();
	
	BucketVO bvo = new BucketVO();
	BucketDAO bdao = new BucketDAO();

	// 오늘까지 남아있는 모든 인덱스 보여주기
	public void showAll() {
		
		// 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		String sqld = "";
        String sqlw = "";
        String sqlm = "";
        String sqly = "";
        String sqlb = "";

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
	        	System.out.print("[일일:"+ dvo.getNoD() + "-" + dvo.getMemoIndexD()+"]\t");
			}System.out.println();
			
			// 주간
			sqlw = "SELECT NO, MEMO_INDEX FROM weekly WHERE( expiry_date > TO_DATE(?,'YY/MM/DD') AND (memo_check = 0) ) ";
			pstmt = con.prepareStatement(sqlw);
			pstmt.setString(1, now);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				wvo.setNoW(rs.getInt("NO"));
	        	wvo.setMemoIndexW(rs.getString("MEMO_INDEX"));
	        	System.out.print("[주간:"+ wvo.getNoW() + "-" + wvo.getMemoIndexW()+"]\t");
			}System.out.println();

			// 월간
			sqlm = "SELECT NO, MEMO_INDEX FROM monthly WHERE( expiry_date > TO_DATE(?,'YY/MM/DD') AND (memo_check = 0) ) ";
			pstmt = con.prepareStatement(sqlm);
			pstmt.setString(1, now);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mvo.setNoM(rs.getInt("NO"));
	        	mvo.setMemoIndexM(rs.getString("MEMO_INDEX"));
	        	System.out.print("[월간:"+ mvo.getNoM() + "-" + mvo.getMemoIndexM()+"]\t");
			}System.out.println();
			
			// 연간
			sqly = "SELECT NO, MEMO_INDEX FROM yearly WHERE( expiry_date > TO_DATE(?,'YY/MM/DD') AND (memo_check = 0) ) ";
			pstmt = con.prepareStatement(sqly);
			pstmt.setString(1, now);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				yvo.setNoY(rs.getInt("NO"));
	        	yvo.setMemoIndexY(rs.getString("MEMO_INDEX"));
	        	System.out.print("[연간:"+ yvo.getNoY() + "-" + yvo.getMemoIndexY()+"]\t");
			}System.out.println();
			
			// 버킷
			sqlb = "SELECT NO, MEMO_INDEX FROM bucket WHERE( expiry_date > TO_DATE(?,'YY/MM/DD') AND (memo_check = 0) ) ";
			pstmt = con.prepareStatement(sqlb);
			pstmt.setString(1, now);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bvo.setNoB(rs.getInt("NO"));
	        	bvo.setMemoIndexB(rs.getString("MEMO_INDEX"));
	        	System.out.print("[버킷:"+ bvo.getNoB() + "-" + bvo.getMemoIndexB()+"]\t");
			}System.out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.disconnect();
		}

	}
	
	//조회-검색(인덱스,내용)
	public void search () {
		System.out.println();
		System.out.println("검색하고싶은 인덱스 또는 내용을 입력하세요.");
		System.out.print("인덱스 또는 내용>>");
		String look = sc.nextLine();
		
		//연결
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        String sqld = "";
        String sqlw = "";
        String sqlm = "";
        String sqly = "";
        String sqlb = "";
        
        
        //일일 테이블에서 검색내용 출력
        try {
            con=dao.connect();
            
            //일일
            dvo.listD = new ArrayList<>();
            sqld = "SELECT NO, MEMO_INDEX FROM daily WHERE(( MEMO_INDEX LIKE ? ) OR ( MEMO_CONTENT LIKE ?))";
            pstmt = con.prepareStatement(sqld);
            pstmt.setString(1, "%"+look+"%");
            pstmt.setString(2, "%"+look+"%");
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	dvo.listD.add(Integer.toString(rs.getInt("NO")));
            	dvo.listD.add(rs.getString("MEMO_INDEX"));
			}
            
            for( int i = 0 ; i < (dvo.listD.size())/2 ; i++ ) {
            	int a = 2*i;
            	int b = 2*i+1;
            	System.out.println("[일일:"+dvo.listD.get(a)+"-"+dvo.listD.get(b)+"]");
            }
            
            //주간
            wvo.listW = new ArrayList<>();
            sqlw = "SELECT NO, MEMO_INDEX FROM weekly WHERE(( MEMO_INDEX LIKE ? ) OR ( MEMO_CONTENT LIKE ?))";
            pstmt = con.prepareStatement(sqlw);
            pstmt.setString(1, "%"+look+"%");
            pstmt.setString(2, "%"+look+"%");
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	wvo.listW.add(Integer.toString(rs.getInt("NO")));
            	wvo.listW.add(rs.getString("MEMO_INDEX"));
			}
            
            for( int i = 0 ; i < (wvo.listW.size())/2 ; i++ ) {
            	int a = 2*i;
            	int b = 2*i+1;
            	System.out.println("[주간:"+wvo.listW.get(a)+"-"+wvo.listW.get(b)+"]");
            }
            
            //월간
            mvo.listM = new ArrayList<>();
            sqlm = "SELECT NO, MEMO_INDEX FROM monthly WHERE(( MEMO_INDEX LIKE ? ) OR ( MEMO_CONTENT LIKE ?))";
            pstmt = con.prepareStatement(sqlm);
            pstmt.setString(1, "%"+look+"%");
            pstmt.setString(2, "%"+look+"%");
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	mvo.listM.add(Integer.toString(rs.getInt("NO")));
            	mvo.listM.add(rs.getString("MEMO_INDEX"));
			}
            
            for( int i = 0 ; i < (mvo.listM.size())/2 ; i++ ) {
            	int a = 2*i;
            	int b = 2*i+1;
            	System.out.println("[월간:"+mvo.listM.get(a)+"-"+mvo.listM.get(b)+"]");
            }
            
            
            //연간
            yvo.listY = new ArrayList<>();
            sqly = "SELECT NO, MEMO_INDEX FROM yearly WHERE(( MEMO_INDEX LIKE ? ) OR ( MEMO_CONTENT LIKE ?))";
            pstmt = con.prepareStatement(sqly);
            pstmt.setString(1, "%"+look+"%");
            pstmt.setString(2, "%"+look+"%");
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	yvo.listY.add(Integer.toString(rs.getInt("NO")));
            	yvo.listY.add(rs.getString("MEMO_INDEX"));
			}
            
            for( int i = 0 ; i < (yvo.listY.size())/2 ; i++ ) {
            	int a = 2*i;
            	int b = 2*i+1;
            	System.out.println("[연간:"+yvo.listY.get(a)+"-"+yvo.listY.get(b)+"]");
            }
            
            //버킷
            bvo.listB = new ArrayList<>();
            sqlb = "SELECT NO, MEMO_INDEX FROM bucket WHERE(( MEMO_INDEX LIKE ? ) OR ( MEMO_CONTENT LIKE ?))";
            pstmt = con.prepareStatement(sqlb);
            pstmt.setString(1, "%"+look+"%");
            pstmt.setString(2, "%"+look+"%");
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	bvo.listB.add(Integer.toString(rs.getInt("NO")));
            	bvo.listB.add(rs.getString("MEMO_INDEX"));
			}
            
            for( int i = 0 ; i < (bvo.listB.size())/2 ; i++ ) {
            	int a = 2*i;
            	int b = 2*i+1;
            	System.out.println("[버킷:"+bvo.listB.get(a)+"-"+bvo.listB.get(b)+"]");
            }
            
            
        	}catch (Exception e) {
                e.printStackTrace();
            } finally {
            dao.disconnect();
            }
        
        if ((dvo.listD.isEmpty())&&(wvo.listW.isEmpty())&&(mvo.listM.isEmpty())&&(yvo.listY.isEmpty())&&(bvo.listB.isEmpty())) {
        	System.out.println("찾으시는 내용이 없습니다.");
        }
        	
        }
        

	
	//완료하기
	public void checkF () {
	//완료하지 않은 인덱스들 보여주기
		System.out.println();
		System.out.println("=============== 완료되지 않은 인덱스 ===============");
		//일일
		ddao.checkFSD();
		System.out.println();
		//주간
		wdao.checkFSW();
		System.out.println();
		//월간
		mdao.checkFSM();
		System.out.println();
		//연간
		ydao.checkFSY();
		System.out.println();
		//버킷
		bdao.checkFSB();
		System.out.println();
		//완료되지 않은 인덱스가 없는 경우
		if ((ddao.dvo.listD.isEmpty())&&(wdao.wvo.listW.isEmpty())&&(mdao.mvo.listM.isEmpty())&&(ydao.yvo.listY.isEmpty())&&(bdao.bvo.listB.isEmpty())) {
			System.out.println("========================================");
			System.out.println("찾으시는 내용이 없습니다.");
			System.out.println();
			System.out.println("========================================");
			
		} else {
			System.out.println("========================================");
			System.out.println("완료하실 인덱스를 선택하세요.");
			System.out.println("1.일일 2.주간 3.월간 4.연간 5.버킷");
			System.out.print("선택>>");
			
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case MENU.DAILY: ddao.checkFD(); break;
			case MENU.WEEKLY: wdao.checkFW(); break;
			case MENU.MONTHLY: mdao.checkFM(); break;
			case MENU.YEARLY: ydao.checkFY(); break;
			case MENU.BUCKET: bdao.checkFB(); break;
			}
		}
		
		
	}
	
	
	
	//취소하기
	public void checkB () {
		//완료된 인덱스들 보여주기
		System.out.println();
		System.out.println("=============== 완료된 인덱스 ==============");
		
		//일일
		ddao.checkBSD();
		System.out.println();
		//주간
		wdao.checkBSW();
		System.out.println();
		//월간
		mdao.checkBSM();
		System.out.println();
		//연간
		ydao.checkBSY();
		System.out.println();
		//버킷
		bdao.checkBSB();
		System.out.println();
		
		//완료된 인덱스가 없는 경우
		if ((!ddao.dvo.listD.isEmpty())&&(!wdao.wvo.listW.isEmpty())&&(!mdao.mvo.listM.isEmpty())&&(!ydao.yvo.listY.isEmpty())&&(!bdao.bvo.listB.isEmpty())) {
			System.out.println("========================================");
			System.out.println();
			System.out.println("취소하실 인덱스를 선택하세요."); 
			System.out.println("1.일일 2.주간 3.월간 4.연간 5.버킷");
			System.out.print("선택>>");
			
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
			case MENU.DAILY: ddao.checkBD(); break;
			case MENU.WEEKLY: wdao.checkBW(); break;
			case MENU.MONTHLY: mdao.checkBM(); break;
			case MENU.YEARLY: ydao.checkBY(); break;
			case MENU.BUCKET: bdao.checkBB(); break;
			}
		
		} 
		System.out.println("찾으시는 내용이 없습니다.");
		System.out.println("========================================");
		
	}
	
}
