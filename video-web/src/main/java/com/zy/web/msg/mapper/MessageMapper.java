package com.zy.web.msg.mapper;

import com.zy.web.msg.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【message】的数据库操作Mapper
* @createDate 2022-11-05 14:12:44
* @Entity com.zy.web.message.entity.Message
*/
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}




