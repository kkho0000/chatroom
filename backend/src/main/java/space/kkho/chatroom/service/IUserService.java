package space.kkho.chatroom.service;

import space.kkho.chatroom.domain.User;

public interface IUserService {

    boolean add (User user);

    boolean login (User user);
    
}
