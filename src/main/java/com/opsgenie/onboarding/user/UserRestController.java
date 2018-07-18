package com.opsgenie.onboarding.user;

import com.opsgenie.onboarding.exception.EntityNotFoundException;
import com.opsgenie.onboarding.user.dto.UserDTO;
import com.opsgenie.onboarding.user.support.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDTO> listUsers() {
        return userService.listUsers().stream()
                .map(UserDTO::from)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        final User user = userDTO.toUser();

        final User addedUser = userService.addUser(user);

        return UserDTO.from(addedUser);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public UserDTO updateUser(@PathVariable String userId, @RequestBody UserDTO userDTO) {
        final User user = userService.getUser(userId);

        if (user == null) {
            throw new EntityNotFoundException("User not found with identifier [" + userId + "]");
        }

        user.setName(userDTO.getName());

        final User updatedUser = userService.updateUser(user);

        return UserDTO.from(updatedUser);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserDTO getUser(@PathVariable String userId) {
        final User user = userService.getUser(userId);

        if (user == null) {
            throw new EntityNotFoundException("User not found with identifier [" + userId + "]");
        }

        return UserDTO.from(user);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String userId) {
        final User user = userService.getUser(userId);

        if (user == null) {
            throw new EntityNotFoundException("User not found with identifier [" + userId + "]");
        }

        userService.deleteUser(user);

        return ResponseEntity.ok().build();
    }
}
