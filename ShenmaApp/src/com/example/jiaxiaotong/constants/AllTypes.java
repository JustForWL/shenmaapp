package com.example.jiaxiaotong.constants;

/**
 * 用户类型
 * @author arthur
 *
 */
public enum AllTypes {
	
	HEADETEACHER(".activity.HeadTeacherFrame"), 
	TEACHERS(".activity.TeacherFrame"), 
	PARENTS(".activity.ParentFrame");
	
	private String type;
	
	private AllTypes(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.type;
	}
	
	

}
