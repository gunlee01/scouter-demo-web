package gunlee.scouter.demo.commondemo.interfaces.service;

import gunlee.scouter.demo.commondemo.domain.Device;
import gunlee.scouter.demo.commondemo.domain.User;
import gunlee.scouter.demo.commondemo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 23.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

//    @NewSpan
    public User findUserById(String userId) {
        return userRepository.findById(userId);
    }

//    @NewSpan
    public User login(String userId, String password) {
        List<User> users = userRepository.findByIdAndPassword(userId, password);
        if (users != null && users.size() == 1) {
            return users.get(0);
        } else {
            return null;
        }
    }

//    @NewSpan
    public List<User> findUserByUserNameLike(String userName) {
        return userRepository.findByUserNameLike(userName);
    }

    public void modifyUserName(String userId, String userName) {
        userRepository.modifyUserName(userId, userName);
    }

//    @NewSpan
    public Device findDeviceByUserId(String userId) {
        return userRepository.findDeviceByUserId(userId);
    }
}

