package com.zy.web.msg.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.web.msg.entity.Message;
import com.zy.web.msg.entity.MessageParm;
import com.zy.web.msg.service.MessageService;
import com.zy.web.msg.mapper.MessageMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Administrator
* @description 针对表【message】的数据库操作Service实现
* @createDate 2022-11-05 14:12:44
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService{

    @Override
    public Page<Message> getList(MessageParm msg) {
        Page<Message> page = new Page<>(msg.getCurrentPage(), msg.getPageSize());
        QueryWrapper<Message> query = new QueryWrapper<>();
        query.lambda().like(Message::getTitle, msg.getTitle());

        if (StringUtils.isNotEmpty(msg.getTitle())) {
            query.lambda().like(Message::getTitle, msg.getTitle());
        }
        return this.baseMapper.selectPage(page, query);
    }

    @Override
    public List<Message> getAll(Message message) {
        return this.baseMapper.selectList(null);
    }
}




