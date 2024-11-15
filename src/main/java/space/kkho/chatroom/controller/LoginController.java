package space.kkho.chatroom.controller;

import org.apache.ibatis.jdbc.Null;
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
@CrossOrigin(origins="http://localhost:5173")
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    IUserService userService;

    @PostMapping
    public ResponseMessage<Null> login(@RequestBody User user) {
        // login
        if ( userService.login(user) == false ) {
            // return ResponseMessage.error("Username or password is incorrect");
            return ResponseMessage.error("Username or password is incorrect");
        }
        else {
            // return ResponseMessage.success(user);
            return ResponseMessage.success(null);
        }
    }

}
