package com.zy.web.msg.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zy.frontdesk.service.UserService;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.msg.entity.Message;
import com.zy.web.msg.entity.MessageParm;
import com.zy.web.msg.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "发布公告信息")
@RestController
@RequestMapping("/api/msg/")
public class MessageController {

    @Resource
    private MessageService messageService;

    @ApiOperation("发布公告信息")
    @PostMapping("addMsg")
    public ResultVo addMsg(@RequestBody Message message) {
        boolean save = messageService.save(message);
        if (save) {
            return ResultUtils.success("发布成功");
        }
        return ResultUtils.error("发布失败");
    }

    @ApiOperation("删除公告信息")
    @GetMapping("msg/{id}")
    public ResultVo deleteMsg(@PathVariable("id") Long id) {
        boolean save = messageService.removeById(id);
        if (save) {
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @ApiOperation("修改公告信息")
    @PutMapping("editMsg")
    public ResultVo editMsg(@RequestBody Message message) {
        boolean save = messageService.updateById(message);
        if (save) {
            return ResultUtils.success("修改成功");
        }
        return ResultUtils.error("修改失败");
    }

    @ApiOperation("分页")
    @GetMapping("MsgPage")
    public ResultVo MsgPage(MessageParm msg) {
        IPage<Message> list = messageService.getList(msg);
        return ResultUtils.success("查询成功", list);
    }

    @ApiOperation("获取所有的信息")
    @GetMapping("MsgInfo")
    public ResultVo MsgInfo(Message message) {
        List<Message> all = messageService.getAll(message);
        return ResultUtils.success("查询成功", all);
    }


}
