package co.yedam.on;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class WeeklyDAO extends DAO {

	Scanner sc = new Scanner(System.in);
	WeeklyVO wvo = new WeeklyVO();
	DAO dao = new DAO();
	
	//일일
	//no. / 입력날짜 / 만기날짜 / 완료날짜 / 인덱스 / 내용
	public void insertW () {
		System.out.println("다음을 입력하세요.");
		System.out.print("인덱스>>");
		String index = sc.nextLine();
		
		System.out.print("내용>>");
		String content = sc.nextLine();
		
		System.out.print("만기날짜(YY/MM/DD)>>");
		String expireDate = sc.nextLine();
		
		wvo.setMemoIndexW(index);
		wvo.setMemoContentW(content);
		wvo.setExpiryDateW(expireDate);
		
		//연결
		int rowCount = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "";
 
        try {
            con=dao.connect();
            sql = "INSERT INTO weekly(no,input_Date,expiry_Date,finish_Date,memo_Index,memo_Content,memo_check) " + " VALUES(MEMO_SEQ.NEXTVAL,?,?,null,?,?,0)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, wvo.getInputDateW());
            pstmt.setString(2, wvo.getExpiryDateW());
            pstmt.setString(3, wvo.memoIndexW);
            pstmt.setString(4, wvo.memoContentW);
            
            rowCount = pstmt.executeUpdate();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.disconnect();
        }
        System.out.println(rowCount+"건 입력완료");
	}
	
	
	
	
	//인덱스로 검색->수정
	public void updateW () {
		
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
            sqld = "UPDATE weekly SET "+  beforeColumn + " = ? WHERE NO = ? ";
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
	public void deleteW () {
		
		System.out.print("번호>>");
		String no = sc.nextLine();
		
		//연결
		int rowCount = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "";
 
        try {
            con=dao.connect();
            sql = "DELETE FROM weekly WHERE NO = ?";
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
	
	
	
	//Forward Show Weekly
	public void checkFSW () {
	// 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		wvo.listW = new ArrayList<>();
		ResultSet rs = null;
		String sql = "";

		try {
			con = dao.connect();
			// 일일
			sql = "SELECT NO, MEMO_INDEX FROM weekly WHERE( memo_check = 0 ) ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				wvo.listW.add(Integer.toString(rs.getInt("NO")));
            	wvo.listW.add(rs.getString("MEMO_INDEX"));
			}
			
			 for( int i = 0 ; i < (wvo.listW.size())/2 ; i++ ) {
		        	int a = 2*i;
		        	int b = 2*i+1;
		        	System.out.println("일일:"+wvo.listW.get(a)+"-"+wvo.listW.get(b));
			
			 }
		}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.disconnect();
		}
}
	
	//Backward Show Weekly
	public void checkBSW () {
		// 연결
			Connection con = null;
			PreparedStatement pstmt = null;
			wvo.listW = new ArrayList<>();
			ResultSet rs = null;
			String sql = "";

			try {
				con = dao.connect();
				// 일일
				sql = "SELECT NO, MEMO_INDEX FROM weekly WHERE( memo_check = 1 ) ";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					wvo.listW.add(Integer.toString(rs.getInt("NO")));
	            	wvo.listW.add(rs.getString("MEMO_INDEX"));
				}
				
				 for( int i = 0 ; i < (wvo.listW.size())/2 ; i++ ) {
			        	int a = 2*i;
			        	int b = 2*i+1;
			        	System.out.println("일일:"+wvo.listW.get(a)+"-"+wvo.listW.get(b));
				
				 }
			}
			 catch (Exception e) {
				e.printStackTrace();
			} finally {
				dao.disconnect();
			}
	}
	
	
	
	
	//Forward Weekly
	public void checkFW () {
		
		System.out.println("완료하실 인덱스를 선택해주세요.");
		System.out.print("번호>>");
		String beforeNo = sc.nextLine();
		
		//연결
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sqld = "";
        
        try {
            con=dao.connect();
            sqld = "UPDATE weekly SET memo_check = 1 WHERE NO = ? ";
            pstmt = con.prepareStatement(sqld);
            pstmt.setString(1, beforeNo);
            
            result = pstmt.executeUpdate();
            con.commit();
            System.out.println(result+"건 완료됐습니다.");
            
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dao.disconnect();
            }
            
	}
	
	//backward Show Weekly
	public void checkBW () {
		
		System.out.println("되돌리실 인덱스를 선택해주세요.");
		System.out.print("번호>>");
		String beforeNo = sc.nextLine();
		
		//연결
        Connection con = null;
        PreparedStatement pstmt = null;
        int result = 0;
        String sqld = "";
        
        try {
            con=dao.connect();
            sqld = "UPDATE weekly SET memo_check = 0 WHERE NO = ? ";
            pstmt = con.prepareStatement(sqld);
            pstmt.setString(1, beforeNo);
            
            result = pstmt.executeUpdate();
            con.commit();
            System.out.println(result+"건 완료됐습니다.");
            
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                dao.disconnect();
            }
		
		
	}
	
	
	
	
}
