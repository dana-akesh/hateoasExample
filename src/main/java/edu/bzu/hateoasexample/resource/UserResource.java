package edu.bzu.hateoasexample.resource;

import edu.bzu.hateoasexample.model.Users;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserResource {

    @GetMapping("/all")
    public List<Users> getAll() {
        Users users = new Users("petar", 1000L);
        Users users1 = new Users("marko", 2000L);
        return List.of(users, users1);
    }

    @GetMapping(value = "/hateoas/all", produces = MediaTypes.HAL_JSON_VALUE)
    public List<Users> getHateoasAll() {
        Users users = new Users("petar", 1000L);
        users.add(org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo(UserResource.class).slash(users.getName()).withSelfRel());

        Users users1 = new Users("marko", 2000L);
        users1.add(org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo(UserResource.class).slash(users1.getName()).withSelfRel());
        return List.of(users, users1);
    }
}
