package org.qodra.api.user;


import com.google.gson.Gson;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponses;
import org.qodra.dao.User;
import org.qodra.exception.NotAuthorizedException;
import org.qodra.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Api(value = "User", description = "Manage user data/profiles", protocols = "http")
@Path("/user")
public class UserRest {

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ApiOperation(value = "Authenticate an user", notes = "", response = User.class)
    @ApiResponses(value = {})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/login")
    public Response doAuthentication(@ApiParam(value = "user", required = true) @FormParam("param") String json) {

        User user = new Gson().fromJson(json, User.class);

        UserService service = new UserService();

        try {
            user = service.login(user);
        } catch (NotAuthorizedException uie) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.status(Response.Status.OK).entity(new Gson().toJson(user)).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ApiOperation(value = "Create an user", notes = "", response = User.class)
    @ApiResponses(value = {})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/update")
    public Response doUpdate(@ApiParam(value = "user", required = true) @FormParam("param") String json) {

        User user = new Gson().fromJson(json, User.class);

        UserService service = new UserService();

        try {
            user = service.login(user);
        } catch (NotAuthorizedException uie) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        return Response.status(Response.Status.OK).entity(new Gson().toJson(user)).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ApiOperation(value = "Create an user", notes = "", response = User.class)
    @ApiResponses(value = {})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/delete")
    public Response doDelete(@ApiParam(value = "user", required = true) @FormParam("param") String json) {

        User user = new Gson().fromJson(json, User.class);

        UserService service = new UserService();
        service.deleteUser(user);

        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @ApiOperation(value = "Create an user", notes = "", response = User.class)
    @ApiResponses(value = {})
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/create")
    public Response doCreate(@ApiParam(value = "user", required = true) @FormParam("param") String json) {

        User user = new Gson().fromJson(json, User.class);

        UserService service = new UserService();
        user = service.createUser(user);

        return Response.status(Response.Status.OK).entity(new Gson().toJson(user)).build();
    }

}
