package com.benchplayer.domain.service.impl;

import java.util.List;
import static java.util.Optional.ofNullable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.benchplayer.domain.model.User;
import com.benchplayer.domain.repository.UserRepository;
import com.benchplayer.domain.service.UserService;
import com.benchplayer.domain.service.exception.ExceptionGlobal;
import com.benchplayer.domain.service.exception.NotFoundException;


@Service
public class UserServiceImpl implements UserService {

    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    @Transactional
    public User create(User user) {
        ofNullable(user).orElseThrow(() -> new ExceptionGlobal("User to create must not be null."));
        
        validateChangeableId(user.getId(), "created");
        if(userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new ExceptionGlobal("This account number already existis.");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        validateChangeableId(id, "updated");
        User dbUser = this.findById(id);
        if(!dbUser.getId().equals(user.getId())) {
            throw new ExceptionGlobal("Update IDs must be the same.");
        }

        dbUser.setName(user.getName());
        dbUser.setAccount(user.getAccount());
        dbUser.setGames(user.getGames());

        return userRepository.save(dbUser);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        validateChangeableId(id, "deleted");
        User dbUser = this.findById(id);
        userRepository.delete(dbUser);
    }
    
    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new ExceptionGlobal("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
    
}
