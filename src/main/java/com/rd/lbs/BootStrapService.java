package com.rd.lbs;

import com.rd.lbs.dao.LeaderBoard;
import com.rd.lbs.services.DataServices;

public class BootStrapService {

	
	
	public static void main(String[] args) {
		
		
		LeaderBoard[] lb = DataServices.getLeaderboardDesc();
		
		for(LeaderBoard row: lb){
			if(row!= null)
			System.out.println(row.toString());
		}
		

	}

}
