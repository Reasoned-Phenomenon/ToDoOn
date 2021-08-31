package co.yedam.on;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ToDoOn {
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	Scanner sc = new Scanner(System.in);
	
	DAO dao = new DAO();
	DailyVO dvo = new DailyVO();
	
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
			sqld = "SELECT MEMO_INDEX FROM daily WHERE( expiry_date > TO_DATE(?,'YY/MM/DD') ) ";
			pstmt = con.prepareStatement(sqld);
			pstmt.setString(1, now);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dvo.listD.add(rs.getString("MEMO_INDEX"));
			}
			System.out.println(dvo);

			// 주간

			// 월간

			// 연간

			// 버킷

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.disconnect();
		}

	}
	
	
	public void search () {
		System.out.println("검색하고싶은 인덱스 또는 내용을 입력하세요.");
		System.out.print("인덱스 또는 내용>>");
		String look = sc.nextLine();
		
		//연결
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sqld = "";
        
 
        try {
            con=dao.connect();
            sqld = "SELECT MEMO_INDEX FROM daily WHERE( MEMO_INDEX LIKE ? ) ";
            pstmt = con.prepareStatement(sqld);
            pstmt.setString(1, "%"+look+"%");
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
				dvo.listD.add(rs.getString("MEMO_INDEX"));
			}
			System.out.println(dvo);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.disconnect();
        }
        
	}
	
	
	
}
