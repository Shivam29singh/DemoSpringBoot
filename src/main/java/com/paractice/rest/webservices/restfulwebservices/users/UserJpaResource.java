package com.paractice.rest.webservices.restfulwebservices.users;

import com.paractice.rest.webservices.restfulwebservices.repository.PostRepository;
import com.paractice.rest.webservices.restfulwebservices.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @GetMapping("/jpa/users")
    public List<User> retrieveAllUser() {
        return userRepository.findAll();

    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new userNotFoundException("id : " + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User saveedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveedUser.getId())
                .toUri();
       /* URI location = ControllerLinkBuilder.linkTo(UserController.class)
                .slash(saveedUser.getId())
                .toUri();*/
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);

    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new userNotFoundException("id : " + id);
        }

        return user.get().getPosts();
    }

    @GetMapping("/jpa/users/{userId}/posts/{postId}")
    public ResponseEntity<Post> retrieveParticularPostsForUser(@PathVariable int userId, @PathVariable Integer postId) {

        Optional<User> userOptional  = userRepository.findById(userId);
/*
        User user = userOptional.get();
        List<Post> posts = user.getPosts();
        Post foundPost = null;

        for (Post post : posts) {
            if (post.getId() == postId) {
                foundPost = post;
                break;
            }
        }

        if (foundPost == null) {
            throw new postNotFoundException("id : " + postId);
        }

        return ResponseEntity.ok(foundPost);*/

        if (userOptional.isEmpty()) {
            throw new userNotFoundException("id : " + userId);
        }

        User user = userOptional.get();
        Optional<Post> postOptional = user.getPosts().stream()
                .filter(post -> post.getId() == postId)
                .findFirst();

        if (postOptional.isEmpty()) {
            throw new PostNotFoundException("id : " + postId);
        }

        return ResponseEntity.ok(postOptional.get());
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new userNotFoundException("id : " + id);
        }
        post.setUser(user.get());
        Post savePost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savePost.getId())
                .toUri();
        return ResponseEntity.created(location).build();

    }

}
