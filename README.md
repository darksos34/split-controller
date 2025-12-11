This demo project, SplitController, 
shows how to refactor a monolithic 
controller into an API interface plus 
separate request/response DTOs.

*** Before Refactoring: UserOldController.java ***
````java

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


````

*** After Splitting: UserController.java ***
````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<User> getUser(UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
````
*** UserApi.java - The separated API interface & RequestPath added ***

````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.requestmapping.RequestPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RequestMapping(RequestPath.API)
public interface UserApi {

    @GetMapping(RequestPath.USER_UUID)
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping(RequestPath.USERS)
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping(RequestPath.USER_ID_PATH)
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


````

*** Before Refactoring: UserOldController.java ***
````java

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


````

*** After Splitting: UserController.java ***
````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<User> getUser(UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
````
*** UserApi.java - The separated API interface & RequestPath added ***

````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.requestmapping.RequestPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RequestMapping(RequestPath.API)
public interface UserApi {

    @GetMapping(RequestPath.USER_UUID)
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping(RequestPath.USERS)
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping(RequestPath.USER_ID_PATH)
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


````

*** Before Refactoring: UserOldController.java ***
````java

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


````

*** After Splitting: UserController.java ***
````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<User> getUser(UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
````
*** UserApi.java - The separated API interface & RequestPath added ***

````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.requestmapping.RequestPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RequestMapping(RequestPath.API)
public interface UserApi {

    @GetMapping(RequestPath.USER_UUID)
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping(RequestPath.USERS)
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping(RequestPath.USER_ID_PATH)
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


````

*** Before Refactoring: UserOldController.java ***
````java

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


````

*** After Splitting: UserController.java ***
````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<User> getUser(UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
````
*** UserApi.java - The separated API interface & RequestPath added ***

````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.requestmapping.RequestPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RequestMapping(RequestPath.API)
public interface UserApi {

    @GetMapping(RequestPath.USER_UUID)
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping(RequestPath.USERS)
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping(RequestPath.USER_ID_PATH)
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


````

*** Before Refactoring: UserOldController.java ***
````java

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


````

*** After Splitting: UserController.java ***
````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<User> getUser(UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
````
*** UserApi.java - The separated API interface & RequestPath added ***

````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.requestmapping.RequestPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RequestMapping(RequestPath.API)
public interface UserApi {

    @GetMapping(RequestPath.USER_UUID)
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping(RequestPath.USERS)
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping(RequestPath.USER_ID_PATH)
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


````

*** Before Refactoring: UserOldController.java ***
````java

import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


````

*** After Splitting: UserController.java ***
````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;


    @Override
    public ResponseEntity<User> getUser(UUID uuid) {
        return ResponseEntity.ok(userService.findById(uuid));    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        User saved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Override
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        return ResponseEntity.noContent().build();
    }
}
````
*** UserApi.java - The separated API interface & RequestPath added ***

````java
import com.jda.coder.splitcontroller.model.User;
import com.jda.coder.splitcontroller.requestmapping.RequestPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;
import java.util.UUID;

@RequestMapping(RequestPath.API)
public interface UserApi {

    @GetMapping(RequestPath.USER_UUID)
    ResponseEntity<User> getUser(@PathVariable("uuid") UUID uuid);

    @PostMapping(RequestPath.USERS)
    ResponseEntity<User> createUser(@RequestBody User user);

    @GetMapping
    ResponseEntity<List<User>> getAll();

    @DeleteMapping(RequestPath.USER_ID_PATH)
    ResponseEntity<Void> delete(@PathVariable UUID id);
}


````
