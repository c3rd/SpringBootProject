package dev.c3rd.app.model.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import dev.c3rd.app.controller.UserController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User entity) {
        return EntityModel.of(entity,
                    linkTo(methodOn(UserController.class).getUserById(entity.getId())).withSelfRel(),
                    linkTo(methodOn(UserController.class).getAllUsers()).withRel("users")
                );
    }
}
