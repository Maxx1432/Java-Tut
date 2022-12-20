package com.maxx.DemoHib;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="alien_table")
public class Alien { //pojo

	@Id
	private int sid;
	private String sName;
	private String  classNo;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getClassNo() {
		return classNo;
	}
	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}
	@Override
	public String toString() {
		return "Alien [sid=" + sid + ", sName=" + sName + ", classNo=" + classNo + "]";
	}
	
	
	
}
