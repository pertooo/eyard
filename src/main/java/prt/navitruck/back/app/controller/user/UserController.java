package prt.navitruck.back.app.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.repository.UserRepository;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController<User, Long> {

    @Autowired
    public UserController(UserRepository userRepository) {
        super(userRepository);
    }


}
