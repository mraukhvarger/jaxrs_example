package com.ystu.jaxrs.server;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Map;

@Path("/hello")
public class HelloWorldService {

    @GET
    @Path("/all")
    public Response all() {
        return Response.status(200).entity("Hello, all!").build();
    }


    @GET
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response test() throws JSONException {
        return Response.status(200).entity(Arrays.asList(1, 2, 3)).build();
    }

    @GET
    @Path("/")
    public Response hello(
            @DefaultValue("user") @QueryParam("user") String user,
            @DefaultValue("") @QueryParam("x") String x) {
        return Response.status(200).entity("Hello, " + user + "<br>" + x).build();
    }
}
