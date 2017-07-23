package gunlee.scouter.demo.commondemo.service;

import gunlee.scouter.demo.commondemo.mapper.UserMapper;
import gunlee.scouter.demo.commondemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 23.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User retrieveUserById(String userId) {
        return userMapper.findById(userId);
    }
}
