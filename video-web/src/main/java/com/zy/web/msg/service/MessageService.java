package com.zy.web.msg.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.web.msg.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zy.web.msg.entity.MessageParm;

import java.util.List;

/**
* @author Administrator
* @description 针对表【message】的数据库操作Service
* @createDate 2022-11-05 14:12:44
*/
public interface MessageService extends IService<Message> {

    Page<Message> getList(MessageParm msg);

    List<Message> getAll(Message message);

}
