package co.yedam.on;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeeklyVO {
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	List<String> listW = new ArrayList<>();
	

	
	int noW;
	String inputDateW;
	String expiryDateW;
	String finishDateW;
	String memoIndexW;
	String memoContentW;
	
	
	
	public SimpleDateFormat getFormat() {
		return format;
	}
	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}
	public List<String> getListW() {
		return listW;
	}
	public void setListW(List<String> listW) {
		this.listW = listW;
	}
	public int getNoW() {
		return noW;
	}
	public void setNoW(int noW) {
		this.noW = noW;
	}
	public String getInputDateW() {
		Date time = new Date();
		String now = format.format(time);
		return now;
	}
	public void setInputDateW(String inputDateW) {
		this.inputDateW = inputDateW;
	}
	public String getExpiryDateW() {
		return expiryDateW;
	}
	public void setExpiryDateW(String expiryDateW) {
		this.expiryDateW = expiryDateW;
	}
	public String getFinishDateW() {
		return finishDateW;
	}
	public void setFinishDateW(String finishDateW) {
		this.finishDateW = finishDateW;
	}
	public String getMemoIndexW() {
		return memoIndexW;
	}
	public void setMemoIndexW(String memoIndexW) {
		this.memoIndexW = memoIndexW;
	}
	public String getMemoContentW() {
		return memoContentW;
	}
	public void setMemoContentW(String memoContentW) {
		this.memoContentW = memoContentW;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}
