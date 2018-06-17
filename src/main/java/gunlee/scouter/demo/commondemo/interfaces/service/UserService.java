package gunlee.scouter.demo.commondemo.interfaces.service;

import gunlee.scouter.demo.commondemo.domain.User;
import gunlee.scouter.demo.commondemo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 23.
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User retrieveUserById(String userId) {
        return userRepository.findById(userId);
    }
}
