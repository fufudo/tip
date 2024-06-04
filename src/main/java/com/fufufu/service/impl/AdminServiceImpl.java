/**
 * @packageName: com.fufufu.service.impl
 * @className: AdminServiceImpl
 * @createTime: 2024-04-02 14:44
 * @description:
 **/

package com.fufufu.service.impl;

import com.fufufu.mapper.AdminMapper;
import com.fufufu.pojo.Account;
import com.fufufu.pojo.Admin;
import com.fufufu.service.AdminService;
import com.fufufu.utils.Md5Util;
import com.fufufu.utils.RoleEnum;
import com.fufufu.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Transactional
    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        adminMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(final String newPwd) {
        final String md5String = Md5Util.getMD5String(newPwd);
        final Map<String, Object> map = ThreadLocalUtil.get();
        final Integer id = (Integer) map.get("id");
        adminMapper.updatePwd(md5String, id);
    }


    @Transactional
    @Override
    public Admin findByUserName(String username) {
        Admin admin = adminMapper.findByUserName(username);
        return admin;
    }

    @Transactional
    @Override
    public void register(Account account) {
        account.setPassword(Md5Util.getMD5String(account.getPassword()));
        account.setRole(RoleEnum.ADMIN.name());
        adminMapper.add(account);
    }

/*    @Transactional
    @Override
    public Account findByAdminName(String username) {
        Account account = adminMapper.findByAdminName(username);
        return account;
    }*/

    @Transactional
    @Override
    public void update(Account account) {

        adminMapper.update(account);
    }


}
 
