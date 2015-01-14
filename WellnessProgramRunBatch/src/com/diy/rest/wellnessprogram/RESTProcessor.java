package com.diy.rest.wellnessprogram;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/Submit")
public class RESTProcessor {

	public static void main(String[] args) {
	}
	
	// will return data as JSON
	@Produces(MediaType.APPLICATION_JSON)
	//@Consumes(MediaType.APPLICATION_JSON)
	// will respond to GET request
	@GET
	// will be called using url 
	//http://xpression:18080/WellnessProgramRunBatch/api/RESTProcessor/getInfo
    @Path("getInfo")
	public String getInfo() {
		return "INFO";
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	@Path("submitUserSet")
	public UserSetSubmitResponse submitUserSet(
	//public user_register_request createUser(
		final UserSet us,
		@Context UriInfo uriInfo
	) {
		int iUserCount = us.getUsers().length;
		String strUserSetName = us.getName();
		UserSetSubmitResponse ussr = new UserSetSubmitResponse();
		ussr.setStatus("COOL you submitted "+strUserSetName+" with "+iUserCount+" users");
		//return Response.status(201).entity(uriInfo.getAbsolutePath()+"/"+user.getId()).build();
		return ussr;
	}
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("getUsers")
	public UserSet getUsers() {
		UserSet us = new UserSet();
		us.setName("TEST");
		User u1 = new User();
		u1.setEmail("edvardas@aconnect.co");
		User u2 = new User();
		u2.setEmail("svinchon@gmail.com");
		User[] users = new User[2];
		users[0] =  u1;
		users[1] =  u2;
		us.setUsers(users);
		return us;
	}

}
