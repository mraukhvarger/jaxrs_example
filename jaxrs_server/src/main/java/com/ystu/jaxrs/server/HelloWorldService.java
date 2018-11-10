package com.ystu.jaxrs.server;


import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Path("/hello")
public class HelloWorldService {


    @POST
    @Path("/msg")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response msg(Message msg) {
        Map result = new HashMap();
        result.put("message", msg.getMessage());
        return Response.ok().entity(result).build();
    }


    @GET
    @Path("/all")
    public Response all() {

        return Response.status(200).entity("Hello, all!").build();
    }

    @GET
    @Path("/query")
    public Response query() throws SQLException {

        DB db = new DB();
        Connection con = db.getDBConnection();
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("select * from PERSON");

        return Response.status(200).entity(res.toString()).build();

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
