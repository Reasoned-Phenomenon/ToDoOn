package co.yedam.on;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class BucketDAO extends DAO {

	Scanner sc = new Scanner(System.in);
	BucketVO bvo = new BucketVO();
	DAO dao = new DAO();
	
	//일일
	//no. / 입력날짜 / 만기날짜 / 완료날짜 / 인덱스 / 내용
	public void insertB () {
		System.out.println("다음을 입력하세요.");
		System.out.print("인덱스>>");
		String index = sc.nextLine();
		
		System.out.print("내용>>");
		String content = sc.nextLine();
		
		System.out.print("만기날짜(YY/MM/DD)>>");
		String expireDate = sc.nextLine();
		
		bvo.setMemoIndexB(index);
		bvo.setMemoContentB(content);
		bvo.setExpiryDateB(expireDate);
		
		//연결
		int rowCount = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "";
 
        try {
            con=dao.connect();
            sql = "INSERT INTO bucket(no,input_Date,expiry_Date,finish_Date,memo_Index,memo_Content,memo_check) " + " VALUES(MEMO_SEQ.NEXTVAL,?,?,null,?,?,0)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, bvo.getInputDateB());
            pstmt.setString(2, bvo.getExpiryDateB());
            pstmt.setString(3, bvo.memoIndexB);
            pstmt.setString(4, bvo.memoContentB);
            
            rowCount = pstmt.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.disconnect();
        }
        System.out.println(rowCount+"건 입력완료");
	}
	
	
	
	
	//인덱스로 검색->수정
	public void updateB () {
		
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
            sqld = "UPDATE bucket SET "+  beforeColumn + " = ? WHERE NO = ? ";
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
	public void deleteB () {
		
		System.out.print("번호>>");
		String no = sc.nextLine();
		
		//연결
		int rowCount = 0;
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "";
 
        try {
            con=dao.connect();
            sql = "DELETE FROM bucket WHERE NO = ?";
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
	
	
	
	//Forward Show Bucket
	public void checkFSB () {
	// 연결
		Connection con = null;
		PreparedStatement pstmt = null;
		bvo.listB = new ArrayList<>();
		ResultSet rs = null;
		String sql = "";

		try {
			con = dao.connect();
			// 일일
			sql = "SELECT NO, MEMO_INDEX FROM bucket WHERE( memo_check = 0 ) ";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				bvo.listB.add(Integer.toString(rs.getInt("NO")));
            	bvo.listB.add(rs.getString("MEMO_INDEX"));
			}
			
			 for( int i = 0 ; i < (bvo.listB.size())/2 ; i++ ) {
		        	int a = 2*i;
		        	int b = 2*i+1;
		        	System.out.print("[버킷:"+bvo.listB.get(a)+"-"+bvo.listB.get(b)+"]\t");
			
			 }
		}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
			dao.disconnect();
		}
}
	
	//Backward Show Bucket
	public void checkBSB () {
		// 연결
			Connection con = null;
			PreparedStatement pstmt = null;
			bvo.listB = new ArrayList<>();
			ResultSet rs = null;
			String sql = "";

			try {
				con = dao.connect();
				// 일일
				sql = "SELECT NO, MEMO_INDEX FROM bucket WHERE( memo_check = 1 ) ";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					bvo.listB.add(Integer.toString(rs.getInt("NO")));
	            	bvo.listB.add(rs.getString("MEMO_INDEX"));
				}
				
				 for( int i = 0 ; i < (bvo.listB.size())/2 ; i++ ) {
			        	int a = 2*i;
			        	int b = 2*i+1;
			        	System.out.print("[버킷:"+bvo.listB.get(a)+"-"+bvo.listB.get(b)+"]\t");
				
				 }
			}
			 catch (Exception e) {
				e.printStackTrace();
			} finally {
				dao.disconnect();
			}
	}
	
	
	
	
	//Forward Bucket
	public void checkFB () {
		
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
            sqld = "UPDATE bucket SET memo_check = 1 WHERE NO = ? ";
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
	
	//Backward Bucket
	public void checkBB () {
		
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
            sqld = "UPDATE bucket SET memo_check = 0 WHERE NO = ? ";
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
