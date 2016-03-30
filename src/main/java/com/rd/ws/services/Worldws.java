package com.rd.ws.services;

import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;


@Path("/ws")
public class Worldws {
	private final String userid = "id";
	protected @Context
	HttpServletRequest request;

	@GET
		 @Produces("text/plain")
		@Path("/getWorld/{param}")
		 public String getWorld(@PathParam("param") String id) {

		String world = DataServices.getWorld(id);
		System.out.println(world);
		return world;
	}

	@GET
	@Produces("text/plain")
	@Path("/getLastWorld")
	public String getLastWorld() {

		String lastWorld = DataServices.getLastWorld((String) request.getAttribute(userid));
		System.out.println(request.getAttribute(userid));
		return lastWorld;
	}

	@GET
	@Produces("text/plain")
	@Path("/getWorldName/{param}")
	public String getWorldName(@PathParam("param") String id) {

		String worldName = DataServices.getWorldName(id);

		return worldName;
	}


	@GET
	@Produces("text/plain")
	@Path("/checkID/{param}")
	public String checkID(@PathParam("param") String id) {

		String ID = DataServices.checkID(id);
		DataServices.getWorldList();
		return ID;
	}

	@GET
	@Produces("application/json")
	@Path("/getWorldList")
	public HashMap<String, String> getWorldList() {

		HashMap<String, String> ID = DataServices.getWorldList();
		return ID;
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/putWorld")
	public Response putWorld(@FormParam("world") String world, @FormParam("id") String id) {
		String output = "Recieved";
		try {
			System.out.println("GOT:" + world);

			DataServices.putWorld( id, world);
		}catch(Exception e2){

		}
		return Response.status(200).entity(output).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/putLastWorld")
	public Response putLastWorld(@FormParam("lastWorld") String lastWorld) {
		String output = "Recieved";
		try {
			System.out.println("GOT:" + lastWorld);

			DataServices.putLastWorld((String) request.getAttribute(userid), lastWorld);
		}catch(Exception e2){

		}
		return Response.status(200).entity(output).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/putWorldName")
	public Response putWorldName(@FormParam("worldName") String worldName) {
		String output = "Recieved";
		try {
			System.out.println("GOT:" + worldName);

			DataServices.putWorldName((String) request.getAttribute(userid), worldName);
		}catch(Exception e2){

		}
		return Response.status(200).entity(output).build();
	}

		@POST
		 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		 @Path("/putID")
		 public Response putID(@FormParam("id") String id) {
		String output = "Recieved";
		try {
			System.out.println("GOT:" + id);

			DataServices.putID(id);
		}catch(Exception e2){

		}
		return Response.status(200).entity(output).build();
	}
}
