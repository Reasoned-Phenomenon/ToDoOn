package co.yedam.on;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class YearlyVO {
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	List<String> listY = new ArrayList<>();
	

	
	int noY;
	String inputDateY;
	String expiryDateY;
	String finishDateY;
	String memoIndexY;
	String memoContentY;
	
	
	
	public SimpleDateFormat getFormat() {
		return format;
	}
	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}
	public List<String> getListY() {
		return listY;
	}
	public void setListY(List<String> listY) {
		this.listY = listY;
	}
	public int getNoY() {
		return noY;
	}
	public void setNoY(int noY) {
		this.noY = noY;
	}
	public String getInputDateY() {
		Date time = new Date();
		String now = format.format(time);
		return now;
	}
	public void setInputDateY(String inputDateY) {
		this.inputDateY = inputDateY;
	}
	public String getExpiryDateY() {
		return expiryDateY;
	}
	public void setExpiryDateY(String expiryDateY) {
		this.expiryDateY = expiryDateY;
	}
	public String getFinishDateY() {
		return finishDateY;
	}
	public void setFinishDateY(String finishDateY) {
		this.finishDateY = finishDateY;
	}
	public String getMemoIndexY() {
		return memoIndexY;
	}
	public void setMemoIndexY(String memoIndexY) {
		this.memoIndexY = memoIndexY;
	}
	public String getMemoContentY() {
		return memoContentY;
	}
	public void setMemoContentY(String memoContentY) {
		this.memoContentY = memoContentY;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}
