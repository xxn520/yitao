package com.yitao.core.service;

import com.yitao.core.dao.UserRepository;
import com.yitao.core.model.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * Created by m2mbob on 16/5/20.
 */
@Service
public class UserService extends AbstractService{

    @Inject
    private UserRepository repository;

    public User findOne(Long id) {
        User user = this.repository.findOne(id);
        if (user != null) {
            Hibernate.initialize(user.getProducts());
        }
        return user;
    }

}
