package dev.c3rd.app.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import dev.c3rd.app.model.user.User;
import dev.c3rd.app.model.user.UserModelAssembler;
import dev.c3rd.app.service.user.IUserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final IUserService userService;
    private final UserModelAssembler assembler;

    public UserController(IUserService userService, UserModelAssembler assembler) {
        this.userService = userService;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<User>> getAllUsers() {
        List<EntityModel<User>> users = userService.getAllUsers().stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    @GetMapping("{id}")
    public EntityModel<User> getUserById(@PathVariable Long id) {

        User user = userService.getUserById(id);
        return assembler.toModel(user);

    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        EntityModel<User> newUser = assembler.toModel(userService.saveUser(user));

        return ResponseEntity.created(newUser.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(newUser);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = userService.updateUser(user, id);

        EntityModel<User> updatedUserModel = assembler.toModel(updatedUser);

        return ResponseEntity.created(updatedUserModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(updatedUserModel);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
