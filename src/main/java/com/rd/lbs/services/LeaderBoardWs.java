package com.rd.lbs.services;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.google.gson.Gson;
import com.rd.lbs.dao.LeaderBoard;

@Path("/lb")
public class LeaderBoardWs {

	// This method is called if XML is request
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getTop")
	public LeaderBoard[] getTop() {

		LeaderBoard[] lb = DataServices.getLeaderboardDesc();

		return lb;
	}

	// This method is called if XML is request
	@GET
//	@Produces(MediaType.APPLICATION_JSON)
	@Produces("application/json")  
	@Path("/getPage/{param}")
	public List<LeaderBoard> getPage(@PathParam("param") String page) {

		List<LeaderBoard> lb = DataServices.getPage(page);

		//DAD WAS HERE
		 
		return lb;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getHighscore/{param}")
	public String getHighscore(@PathParam("param") String userID) {

		String score = DataServices.getHighscore(userID);

		return score;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getUsername/{param}")
	public String getUsername(@PathParam("param") String userID) {
		String username = DataServices.getUsername(userID);

		return username;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getTotal")
	public String getTotal() {
		return DataServices.getTotal();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/postScore")
	public Response postScore(@FormParam("lb") String lbJson) {
		String output = "Recieved";
		// 2. Convert JSON to Java object
		ObjectMapper mapper = new ObjectMapper();
		LeaderBoard lb = null;
		try {
			System.out.println("GOT:" + lbJson);
			lb = mapper.readValue(lbJson, LeaderBoard.class);
			System.out.println("GOT CONVERT:" + lb.toString());
			// LeaderBoard lb = new LeaderBoard();
			
			
			DataServices.postData(lb.getUserid(), lb.getUsername(), lb.getScore());
		} catch (JsonParseException e) {

			e.printStackTrace();
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}catch(Exception e2){
			
		}
		return Response.status(200).entity(output).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/putScore")
	public Response putScore(@FormParam("score") String score, @FormParam("userid") String userid) {
		String output = "Recieved";
		try {
			System.out.println("GOT:" + score);
			
			DataServices.putData(userid, score);
		}catch(Exception e2){
			
		}
		return Response.status(200).entity(output).build();
	}
	
}
