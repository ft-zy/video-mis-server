package com.zy.web.video_system.menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.menu.entity.MakeTree;
import com.zy.web.video_system.menu.entity.Menu;
import com.zy.web.video_system.menu.entity.MenuParm;
import com.zy.web.video_system.menu.service.MenuService;
import com.zy.web.video_system.menu.mapper.MenuMapper;
import com.zy.web.video_system.role_menu.entity.RoleMenu;
import com.zy.web.video_system.role_menu.service.RoleMenuService;
import javassist.bytecode.Mnemonic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
* @author Administrator
* @description 针对表【menu】的数据库操作Service实现
* @createDate 2022-10-03 16:00:07
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService{

    @Resource
    private MenuMapper menuMapper;

    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public ResultVo addMenu(Menu menu) {
        int save = menuMapper.insertAll(menu);
        if (save > 0) {
            return ResultUtils.success("新增成功", save);
        }
        return ResultUtils.error("新增失败");
    }

    @Override
    public ResultVo editMenu(Menu menu) {
        int save = menuMapper.updateSelective(menu);
        if (save > 0) {
            return ResultUtils.success("修改成功", save);
        }
        return ResultUtils.error("修改失败");
    }

    @Override
    @Transactional
    public ResultVo deleteByMid(Long mid) {
        int save = menuMapper.deleteByMid(mid);
        if (save > 0) {
            //删除菜单对应的关联
            QueryWrapper<RoleMenu> query = new QueryWrapper<>();
            query.lambda().eq(RoleMenu::getMid,mid);
            roleMenuService.remove(query);
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.error("删除失败");
    }

    @Override
    public List<Menu> getList() {
        Menu menu = new Menu();
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().orderByAsc(Menu::getOrderNum);

        List<Menu> menuList = this.baseMapper.selectList(query);
        //组装树数据
        return MakeTree.makeMenuTree(menuList, 0L);
    }

    @Override
    public List<Menu> parentList() {
        //只获取目录和菜单
        String[] types = {"0","1"};
        //查询条件
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().in(Menu::getType, Arrays.asList(types)).orderByAsc(Menu::getOrderNum);
        List<Menu> menus = this.baseMapper.selectList(query);
        //构造顶级菜单
        Menu menu = new Menu();
        menu.setMid(0L);
        menu.setParentId(-1L);
        menu.setTitle("顶级菜单");
        menus.add(menu);
        //构造树形数据
        List<Menu> menuTree = MakeTree.makeMenuTree(menus, -1L);
        return menuTree;
    }

    @Override
    public List<Menu> getMenuByAid(Long aid) {
        return  this.baseMapper.getMenuByAid(aid);
    }

    @Override
    public List<Menu> getMenuByRoleId(Long roId) {
        return this.baseMapper.getMenuByRoleId(roId);
    }


}




