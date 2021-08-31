package com.project.myApp;


import java.util.List;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;




@Path("aliens")
public class Alienres {
	
	alienRepo repo=new alienRepo();
	@GET
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public List<alien> getAliens()
	{
		System.out.println("show");
		return repo.getAliens();
	}
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public alien getAlien(@PathParam("id")int id)
	{
		return repo.getAlien(id);
	}
	
	@POST
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public alien createAlien(alien a1)
	{
		System.out.println(a1);
		repo.create(a1);
		
		
		return a1;
	}

	@PUT
	@Path("alien")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public alien updateAlien(alien a1)
	{
		System.out.println(a1);
		if(repo.getAlien(a1.getId()).getId()==0)
		{
			repo.create(a1);
		}
		else
		{
		repo.update(a1);
		}
		
		return a1;
	}
	
	
	@DELETE
	@Path("alien/{id}")
	public alien killAlien(@PathParam("id")int id)
	{
		alien a= repo.getAlien(id);
		if(a.getId()!=0)
			repo.delete(id);
		return a;
	}

}
