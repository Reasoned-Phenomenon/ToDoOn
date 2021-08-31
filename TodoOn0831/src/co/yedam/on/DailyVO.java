package co.yedam.on;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyVO {

	List<String> listD = new ArrayList<>();
	
	
	
	int noD;
	String inputDateD;
	String expiryDateD;
	String finishDateD;
	String memoIndexD;
	String memoContentD;
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	
	
	
	public int getNoD() {
		return this.noD;
	}
	public void setNoD(int noD) {
		this.noD = noD;
	}
	public String getInputDateD() {
		Date time = new Date();
		String now = format.format(time);
		return now;
	}
	public void setInputDateD(String inputDateD) {
		this.inputDateD = inputDateD;
	}
	public String getExpiryDateD() {
		return expiryDateD;
	}
	public void setExpiryDateD(String expiryDateD) {
		this.expiryDateD = expiryDateD;
	}
	public String getFinishDateD() {
		return finishDateD;
	}
	public void setFinishDateD(String finishDateD) {
		this.finishDateD = finishDateD;
	}
	public String getMemoIndexD() {
		return memoIndexD;
	}
	public void setMemoIndexD(String memoIndexD) {
		this.memoIndexD = memoIndexD;
	}
	public String getMemoContentD() {
		return memoContentD;
	}
	public void setMemoContentD(String memoContentD) {
		this.memoContentD = memoContentD;
	}
	@Override
	public String toString() {
		return "일일 [" + listD + "]";
	}
	
	
	
	
	
	
	
}
