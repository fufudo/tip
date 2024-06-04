package com.fufufu.service;

import com.fufufu.pojo.Account;
import com.fufufu.pojo.Admin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public interface AdminService {
    Admin findByUserName(String account);

    void register(Account account);

    void update(Account account);

    void updateAvatar(String avatarUrl);

    void updatePwd(final String newPwd);
}
