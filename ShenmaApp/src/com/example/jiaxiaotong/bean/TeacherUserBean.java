package com.example.jiaxiaotong.bean;

/**
 * teacher bean
 * @author Arthur
 *
 */
public class TeacherUserBean {
	private String userAccount = null;
	private String userName = null;
	private String iconAddr = null;
	private String role = null;
	private String student = null;
	private String lastwords = null;
	private String ConTime = null;
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String usrAccount) {
		this.userAccount = usrAccount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIconAddr() {
		return iconAddr;
	}
	public void setIconAddr(String iconAddr) {
		this.iconAddr = iconAddr;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public void setLastWord(String lastwords){
		this.lastwords = lastwords;
	}
	public CharSequence getLastWord() {
		// TODO Auto-generated method stub
		return lastwords;
	}
	public void setConTime(String ConTime){
		this.ConTime  = ConTime;
	}
	public CharSequence getConTime() {
		// TODO Auto-generated method stub
		return ConTime;
	}
	
	
}
