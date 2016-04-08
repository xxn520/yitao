package com.yitao.core.service;

import com.yitao.core.config.AppProperties;
import com.yitao.core.dao.AccountRepository;
import com.yitao.core.dao.SystemVariableRepository;
import com.yitao.core.dao.UserGroupRepository;
import com.yitao.core.dao.UserRepository;
import com.yitao.core.model.Account;
import com.yitao.core.model.SystemVariable;
import com.yitao.core.model.User;
import com.yitao.core.model.UserGroup;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by m2mbob on 16/4/6.
 */
@Service
public class SystemInitializer extends YitaoInitializer {

    @Inject
    private UserGroupRepository userGroupRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private SystemVariablesHolder variablesHolder;

    @Inject
    private SystemVariableRepository variableRepository;

    @Inject
    private AppProperties appProperties;

    @Override
    @Transactional
    protected void doInit() {
        // 权限初始化
        if (accountRepository.count()==0) {
            Account account = new Account("admin", "123456");
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            UserGroup group = new UserGroup("管理员", "ROLE_ADMIN", "ROLE_USER");
            this.userGroupRepository.save(group);
            User user = new User(account.getUsername());
            user.setGroup(group);
            this.userRepository.save(user);
            account.setUser(user);
            this.accountRepository.save(account);
        }
        // 系统参数初始化
        if(variableRepository.count()==0){
            List<SystemVariable> list = new ArrayList<>();
            Map<String, String> map = appProperties.getProperties();
            for (String key : map.keySet()) {
                SystemVariable variable = new SystemVariable(key, map.get(key));
                list.add(variable);
            }
            list = this.variableRepository.save(list);
            this.variablesHolder.post();
        }
    }

}
