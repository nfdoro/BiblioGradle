package edu.itplus.bibliospring.backend.service.impl;

import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserDAO;
import edu.itplus.bibliospring.backend.service.LoginService;
import edu.itplus.bibliospring.backend.utils.impl.PasswordEncrypterSha256;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncrypterSha256 passwordEncrypter;

    @Override
    public boolean login(User user) {

        User dbUser = userDAO.findByUsername(user.getUsername());
        if (dbUser == null){
            return false;
        } else if (dbUser.getPassword().equals(passwordEncrypter.hashPassword(user.getPassword(),dbUser.getUuid()))){
            return true;
        }

        return false;
    }

    @Override
    public void register(User user) {

        user.setPassword(passwordEncrypter.hashPassword(user.getPassword(),user.getUuid()));
        userDAO.create(user);
    }
}
