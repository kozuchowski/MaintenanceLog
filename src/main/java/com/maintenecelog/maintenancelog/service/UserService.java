package com.maintenecelog.maintenancelog.service;

import com.maintenecelog.maintenancelog.dto.CreateMainteinerDto;
import com.maintenecelog.maintenancelog.dto.LoginUserDto;
import com.maintenecelog.maintenancelog.dto.UpdateMainteinerDto;
import com.maintenecelog.maintenancelog.exception.ObjectAlreadyExistsException;
import com.maintenecelog.maintenancelog.exception.ObjectDoesNotExistException;
import com.maintenecelog.maintenancelog.exception.PasswordNotValidException;
import com.maintenecelog.maintenancelog.model.User;
import com.maintenecelog.maintenancelog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }



    public User create(CreateMainteinerDto dto){
        isPasswordsValid(dto.getPassword(), dto.getConfirmPassword(),"Passwords don't match");
        isUnique(dto.getLicenceNumber(), dto.getEmail(), dto.getLogin());
        User user = dtoIntoUser(dto);
        return userRepository.save(user);

    }


    public User loginUser(LoginUserDto dto) {
        User user = userRepository.findByLogin(dto.getLogin());
//        isPasswordsValid(dto.getPassword(), user.getPassword(),"Wrong login or password");
        return user;
    }


    public User findMainteinerByLogin(String login) {
        User user = userRepository.findByLogin(login);

        if(user == null){
            throw new ObjectDoesNotExistException("User does not exist in database");
        }
        return user;
    }

    @Transactional
    public void deleteUserByLogin(String login){
        findMainteinerByLogin(login);
        userRepository.deleteByLogin(login);
    }


    public User update(UpdateMainteinerDto dto) {
        User m = userRepository.findByLogin(dto.getLogin());
        m.setName(dto.getName());
        m.setSurname(dto.getSurname());
        m.setLogin(dto.getLogin());
        if(!(dto.getPassword().equals(dto.getConfirmPassword()))) {
            throw new PasswordNotValidException("Invalid password");
        }
        m.setPassword(dto.getPassword());
        m.setEmail(dto.getEmail());
        m.setPhoneNumber(dto.getPhoneNumber());
        m.setLicenceNumber(dto.getLicenceNumber());

        return userRepository.save(m);
    }


    public boolean isUnique(String licence, String email, String login) {
        if(userRepository.findByUnique(licence, email, login) != null){
            throw new ObjectAlreadyExistsException("User already exists in database");
        }
        return true;
    }


    public boolean isPasswordsValid(String password, String confirm, String message) {
        if(!password.equals(confirm)){
            throw new PasswordNotValidException(message);
        }
        return true;
    }


    public User dtoIntoUser(CreateMainteinerDto dto) {
        return new User(dto.getName(), dto.getSurname(), dto.getLogin(), encoder.encode(dto.getPassword()), dto.getEmail(),
                dto.getPhoneNumber(), dto.getLicenceNumber());
    }


    public User getMainteinerByIdI(Long id) {
        Optional<User> optionalMainteiner = userRepository.findById(id);

        if(optionalMainteiner.isEmpty()) {
            throw new ObjectDoesNotExistException("No such mainteiner");
        }

        return optionalMainteiner.get();

    }


}
