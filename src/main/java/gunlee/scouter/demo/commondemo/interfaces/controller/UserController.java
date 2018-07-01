package gunlee.scouter.demo.commondemo.interfaces.controller;

import gunlee.scouter.demo.commondemo.domain.BooleanView;
import gunlee.scouter.demo.commondemo.domain.User;
import gunlee.scouter.demo.commondemo.interfaces.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    public BooleanView login(@RequestBody @Valid User user, HttpSession session) {
        User logined = userService.login(user.getUserId(), "test");
        if (user != null) {
            session.setAttribute("user", logined);
            return new BooleanView(true);
        } else {
            session.setAttribute("user", null);
            return new BooleanView(false);
        }
    }

    @GetMapping("/user/{userId}")
    public User systemInfo(@PathVariable("userId") String userId) {
        User user = userService.findUserById(userId);
        return user;
    }
}
