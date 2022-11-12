package com.zy.web.video_system.menu.controller;

import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.menu.entity.MakeTree;
import com.zy.web.video_system.menu.entity.Menu;
import com.zy.web.video_system.menu.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "菜单管理模块")
@RestController
@RequestMapping("/api/menu/")
public class MenuController {
    @Resource
    private MenuService menuService;

    @ApiOperation("新增菜单")
    @RequestMapping(value = "addMenu", method = RequestMethod.POST)
    public ResultVo addMenu(@RequestBody Menu menu) {
        return menuService.addMenu(menu);
    }

    @ApiOperation("删除菜单")
    @RequestMapping(value = "{mid}", method = RequestMethod.DELETE)
    public ResultVo deleteMenu(@PathVariable("mid") Long mid) {
        return menuService.deleteByMid(mid);
    }

    @ApiOperation("更改菜单信息")
    @RequestMapping(value = "editMenu", method = RequestMethod.PUT)
    public ResultVo editMenu(@RequestBody Menu menu) {
        return menuService.editMenu(menu);
    }

    @ApiOperation(value = "菜单列表")
    @RequestMapping(value = "getMenuList", method = RequestMethod.GET)
    public ResultVo getMenuList() {
        List<Menu> list = menuService.getList();
        return ResultUtils.success("查询成功", list);
    }

    @ApiOperation(value = "菜单上级菜单树数据")
    @RequestMapping(value = "parent", method = RequestMethod.GET)
    public ResultVo getMparentList() {
        List<Menu> list = menuService.parentList();
        return ResultUtils.success("查询成功", list);
    }


}
