package com.diy.wellnessprogram;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/users")
public class UserResource {

	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response createUser(
	//public user_register_request createUser(
		final user_register_request ur,
		@Context UriInfo uriInfo
	) {
		User user = new User();
		user.setId("ab56f8");
		user.setUsername("john_doe1");
		user.setExternalId("5656866-12");
		user.setIndex(1234.5668);
		user.setGender("male");
		user.setEmail("john_doe1@xp.com");
		user.setPassword("Pa$$word");
		user.setWeight(80);
		user.setIndex(1234.5668);
		user_register_request ur_out = new user_register_request();
		ur_out.setUser_register(user);
		return Response.status(201).entity(uriInfo.getAbsolutePath()+"/"+user.getId()).build();
		//return ur_out;
	}
	
	@Produces({ MediaType.APPLICATION_JSON })
    @GET
    @Path("{id}")
    public user_register_request getUserData(@PathParam("id") String id) {
		User user = new User();
		user.setId(id);
		user.setUsername("john_doe2");
		user.setExternalId("5656866-12");
		user.setIndex(1234.5668);
		user.setGender("male");
		user.setEmail("john_doe1@xp.com");
		user.setPassword("Pa$$word");
		user.setWeight(80);
		user.setIndex(1234.5668);
		user_register_request ur_out = new user_register_request();
		//Log(user.getId());
		ur_out.setUser_register(user);
		return ur_out;
	}
	
	public void Log(String str) {
		System.out.println(str);
	}

}

/*
@GET
@Produces({ MediaType.TEXT_XML })
public User getHTML() {
	User user = new User();
	user.setUsername("Toto");
	return user;
}
*/