package gunlee.scouter.demo.commondemo.controller;

import gunlee.scouter.demo.commondemo.model.User;
import gunlee.scouter.demo.commondemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user/{userId}")
    public User systemInfo(@PathVariable("userId") String userId) {
        User user = userService.retrieveUserById(userId);
        return user;
    }
}
