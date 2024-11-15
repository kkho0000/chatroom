package space.kkho.chatroom.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import space.kkho.chatroom.domain.User;
import space.kkho.chatroom.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean add(User user) {
        // add user
        User user_ = new User();
        BeanUtils.copyProperties(user, user_);
        if ( userRepository.findByUsername(user.getUsername()) != null ) {
            return false;
        }
        else {
            userRepository.save(user_);
            return true;
        }
    }

    @Override
    public boolean login(User user) {
        // login
        User user_ = userRepository.findByUsername(user.getUsername());
        if ( user_ == null ) {
            return false;
        }
        else {
            return user_.getPassword().equals(user.getPassword());
        }
    }
}