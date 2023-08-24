package project.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dao.impl.UserDao;
import project.dto.FormLoginDto;
import project.dto.FormRegisterDto;
import project.model.User;

import java.util.List;
@Service
public class UserService  {
    @Autowired
    private UserDao userDao;

    public List<User> findAll() {
        return null;
    }


    public User findById(Long id) {
        return null;
    }


    public void save(FormRegisterDto formRegisterDto) {
        // chuyen doi dto sang model
        User user = new User();
        user.setUsername(formRegisterDto.getUsername());
        user.setPassword(formRegisterDto.getPassword());
        user.setEmail(formRegisterDto.getEmail());
        userDao.save(user);
    }


    public void delete(Long id) {

    }
    public User login(FormLoginDto formLoginDto){
        return userDao.login(formLoginDto);
    }
    public User register(FormRegisterDto formRegisterDto){
        return userDao.register(formRegisterDto);
    }
}
