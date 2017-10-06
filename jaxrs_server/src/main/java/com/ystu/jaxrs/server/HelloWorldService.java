package com.ystu.jaxrs.server;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorldService {

    @GET
    @Path("/{hello}")
    public Response hello(@PathParam("hello") String hello) {
        return Response.status(200).entity("Hello, " + (hello != null ? hello : "user")).build();
    }
}
