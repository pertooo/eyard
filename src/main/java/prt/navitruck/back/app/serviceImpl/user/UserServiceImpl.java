package prt.navitruck.back.app.serviceImpl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.errors.EntityNotFoundException;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.repository.UserRepository;
import prt.navitruck.back.app.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
