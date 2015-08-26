package com.rd.lbs.dao;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LeaderBoard {
	
	private String entrytime;
	private String userid;
	private String username;
	private Integer score;
	
	public LeaderBoard(){}
	
	public String getEntrytime() {
		return entrytime;
	}
	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime.toString();
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
//	@Override
//	public String toString(){
//		return entrytime + ":" + userid + ": " + username + "\t" + score ;
//		
//	}

}
