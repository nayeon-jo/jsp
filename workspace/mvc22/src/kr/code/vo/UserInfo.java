package kr.code.vo;

public class UserInfo {
	
	private String userId;
	private String userPw;
	private String userName;
	private int userAge;
	private String userGender;
	private String userHobby;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserGender() {
		return userGender;
	}
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	public String getUserHobby() {
		return userHobby;
	}
	public void setUserHobby(String userHobby) {
		this.userHobby = userHobby;
	}
	
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("아이디:" + this.getUserId()+"\t");
		sb.append("이름 :" + this.getUserName()+"\t");
		sb.append("나이 :" + this.getUserAge()+"\t");
		sb.append("성별 :" + this.getUserGender()+"\t");
		sb.append("취미 :" + this.getUserHobby()+"\n");
		
		return sb.toString();
	}

}
