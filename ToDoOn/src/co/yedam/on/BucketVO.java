package co.yedam.on;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BucketVO {
	
	SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
	List<String> listB = new ArrayList<>();
	

	
	int noB;
	String inputDateB;
	String expiryDateB;
	String finishDateB;
	String memoIndexB;
	String memoContentB;
	
	
	
	public SimpleDateFormat getFormat() {
		return format;
	}
	public void setFormat(SimpleDateFormat format) {
		this.format = format;
	}
	public List<String> getlistB() {
		return listB;
	}
	public void setlistB(List<String> listB) {
		this.listB = listB;
	}
	
	
	
	public int getNoB() {
		return noB;
	}
	public void setNoB(int noB) {
		this.noB = noB;
	}
	public String getInputDateB() {
		Date time = new Date();
		String now = format.format(time);
		return now;
	}
	public void setInputDateB(String inputDateB) {
		this.inputDateB = inputDateB;
	}
	public String getExpiryDateB() {
		return expiryDateB;
	}
	public void setExpiryDateB(String expiryDateB) {
		this.expiryDateB = expiryDateB;
	}
	public String getFinishDateB() {
		return finishDateB;
	}
	public void setFinishDateB(String finishDateB) {
		this.finishDateB = finishDateB;
	}
	public String getMemoIndexB() {
		return memoIndexB;
	}
	public void setMemoIndexB(String memoIndexB) {
		this.memoIndexB = memoIndexB;
	}
	public String getMemoContentB() {
		return memoContentB;
	}
	public void setMemoContentB(String memoContentB) {
		this.memoContentB = memoContentB;
	}
	
	
	
	
	

	
	
	
	
	
	
	
	
	
}
