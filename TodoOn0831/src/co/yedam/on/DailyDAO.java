package co.yedam.on;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DailyDAO extends DAO {

	Scanner sc = new Scanner(System.in);
	DailyVO dvo = new DailyVO();
	DAO dao = new DAO();
	
	//일일
	//no. / 입력날짜 / 만기날짜 / 완료날짜 / 인덱스 / 내용
	public void insertD () {
		System.out.println("다음을 입력하세요.");
		System.out.print("인덱스>>");
		String index = sc.nextLine();
		
		System.out.print("내용>>");
		String content = sc.nextLine();
		
		System.out.print("만기날짜(YY/MM/DD)>>");
		String expireDate = sc.nextLine();
		
		dvo.setMemoIndexD(index);
		dvo.setMemoContentD(content);
		dvo.setExpiryDateD(expireDate);
		
		//연결
		int rowCount = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "";
 
        try {
            con=dao.connect();
            sql = "INSERT INTO daily(no,input_Date,expiry_Date,finish_Date,memo_Index,memo_Content) " + " VALUES(D_SEQ.NEXTVAL,?,?,null,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, dvo.getInputDateD());
            pstmt.setString(2, dvo.getExpiryDateD());
            pstmt.setString(3, dvo.memoIndexD);
            pstmt.setString(4, dvo.memoContentD);
            
            rowCount = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.disconnect();
        }
        System.out.println(rowCount+"건 입력완료");
	}
	
	
	
	
	//인덱스로 검색->수정
	public void updateD () {
		
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
            sqld = "UPDATE daily SET "+  beforeColumn + " = ? WHERE NO = ? ";
            pstmt = con.prepareStatement(sqld);
            pstmt.setString(1, afterColumn);
            pstmt.setString(2, beforeNo);
            
            result = pstmt.executeUpdate();
            con.commit();
            System.out.println(result+"건 수정 완료됐습니다.");
            
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dao.disconnect();
            }
            
	}
	
	
	
	
	//인덱스로 검색->삭제
	public void deleteD () {
		
		System.out.print("번호>>");
		String no = sc.nextLine();
		
		//연결
		int rowCount = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "";
 
        try {
            con=dao.connect();
            sql = "DELETE FROM daily WHERE NO = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, no);
            rowCount = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.disconnect();
        }
        System.out.println(rowCount+"건 삭제완료");
	

	}
	
	
	
	
	
	
	
}
