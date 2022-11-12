package com.zy.frontdesk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zy.frontdesk.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.frontdesk.entity.vo.LoginVo;
import com.zy.frontdesk.entity.vo.UserParm;
import com.zy.utils.ResultVo;
import org.springframework.web.bind.annotation.RequestBody;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service
* @createDate 2022-10-25 14:23:58
*/
public interface UserService extends IService<User> {

    ResultVo Registered(User user);

    ResultVo FindPwd(User user);

    ResultVo getEmailUser(String email);

    IPage<User> userPage(UserParm userParm);

    /**
     * 用户登陆
     */
    ResultVo UserLogin(LoginVo loginVo);

    /**
     * 找回密码
     */
    ResultVo editPassword(User user);

    /**
     * 修改信息
     */
    ResultVo Modify(User user);

}
