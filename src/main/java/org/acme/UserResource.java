package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.acme.models.AgeInput;
import org.acme.models.NameInput;
import org.acme.models.User;
import org.acme.models.UserInput;
import org.acme.services.UserService;

@Path("/user")
@ApplicationScoped
public class UserResource {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createUser(UserInput userInput) {
        return userService.createUser(userInput);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") String id) {
        return userService.getUser(id);
    }

    @PATCH
    @Path("{id}/name")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUserName(@PathParam("id") String id, NameInput input) {
        userService.updateUserName(id, input);
    }

    @PATCH
    @Path("{id}/age")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUserAge(@PathParam("id") String id, AgeInput input) {
        userService.updateUserAge(id, input);
    }

}
