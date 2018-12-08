package com.ystu.jaxrs.server;


import com.sun.jersey.api.view.Viewable;
import org.codehaus.jettison.json.JSONException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response query() throws SQLException {

        DB db = new DB();
        Connection con = db.getDBConnection();
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("select * from PERSON");

        List res_map = new ArrayList();
        while (res.next()) {
            res_map.add(res.getObject(2));
        }

        return Response.status(200).entity(res_map).build();

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

    @GET
    @Path("/view")
    public Viewable view(@Context HttpServletRequest req, @Context HttpServletResponse resp) {
        return new Viewable("/index.jsp");
    }
}
