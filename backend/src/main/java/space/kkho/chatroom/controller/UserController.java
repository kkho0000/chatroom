package space.kkho.chatroom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import space.kkho.chatroom.domain.ResponseMessage;
import space.kkho.chatroom.domain.User;
import space.kkho.chatroom.service.IUserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseMessage<User> addUser(@RequestBody User user) {
        // add user
        if ( userService.add(user) == false ) {
            return ResponseMessage.error("Username already exists");
        }
        else {
            return ResponseMessage.success(user);
        }
    }

}