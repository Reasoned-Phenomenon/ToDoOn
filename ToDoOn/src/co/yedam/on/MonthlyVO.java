package co.yedam.on;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonthlyVO {
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	List<String> listM = new ArrayList<>();
	

	
	int noM;
	String inputDateM;
	String expiryDateM;
	String finishDateM;
	String memoIndexM;
	String memoContentM;
	
	
	
	public SimpleDateFormat getFormat() {
		return format;
	}
	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}
	public List<String> getListM() {
		return listM;
	}
	public void setListM(List<String> listM) {
		this.listM = listM;
	}
	public int getNoM() {
		return noM;
	}
	public void setNoM(int noM) {
		this.noM = noM;
	}
	public String getInputDateM() {
		Date time = new Date();
		String now = format.format(time);
		return now;
	}
	public void setInputDateM(String inputDateM) {
		this.inputDateM = inputDateM;
	}
	public String getExpiryDateM() {
		return expiryDateM;
	}
	public void setExpiryDateM(String expiryDateM) {
		this.expiryDateM = expiryDateM;
	}
	public String getFinishDateM() {
		return finishDateM;
	}
	public void setFinishDateM(String finishDateM) {
		this.finishDateM = finishDateM;
	}
	public String getMemoIndexM() {
		return memoIndexM;
	}
	public void setMemoIndexM(String memoIndexM) {
		this.memoIndexM = memoIndexM;
	}
	public String getMemoContentM() {
		return memoContentM;
	}
	public void setMemoContentM(String memoContentM) {
		this.memoContentM = memoContentM;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}
