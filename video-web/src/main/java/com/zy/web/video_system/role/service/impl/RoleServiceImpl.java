package com.zy.web.video_system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.utils.ResultUtils;
import com.zy.utils.ResultVo;
import com.zy.web.video_system.admin.entity.Admin;
import com.zy.web.video_system.admin.service.AdminService;
import com.zy.web.video_system.menu.entity.MakeTree;
import com.zy.web.video_system.menu.entity.Menu;
import com.zy.web.video_system.menu.service.MenuService;
import com.zy.web.video_system.role.entity.AssignParm;
import com.zy.web.video_system.role.entity.AssignVo;
import com.zy.web.video_system.role.entity.Role;
import com.zy.web.video_system.role.entity.RoleParm;
import com.zy.web.video_system.role.mapper.RoleMapper;
import com.zy.web.video_system.role.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
* @author Administrator
* @description 针对表【role】的数据库操作Service实现
* @createDate 2022-09-18 22:16:32
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private AdminService adminService;

    @Resource
    private MenuService menuService;

    @Override
    public IPage<Role> getList(RoleParm roleParm) {
        IPage<Role> page = new Page<>();
        page.setSize(roleParm.getPageSize());
        page.setCurrent(roleParm.getCurrentPage());
        // 构造查询条件
        QueryWrapper<Role> query = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(roleParm.getRoleName())) {
            query.lambda().like(Role::getRoleName,roleParm.getRoleName());
        }
        return this.baseMapper.selectPage(page, query);
    }

    @Override
    public ResultVo addRole(Role role) {
        int save = roleMapper.insertAll(role);
        if (save > 0) {
            return ResultUtils.success("新增角色成功!");
        }
        return ResultUtils.error("新增角色失败");
    }

    @Override
    public ResultVo deleteRoleById(Long roId) {
        int save = roleMapper.delByRoid(roId);
        if (save > 0) {
            return ResultUtils.success("删除角色成功!");
        }
        return ResultUtils.error("删除角色失败");
    }

    @Override
    public ResultVo updateRole(Role role) {
        int save = roleMapper.updateSelective(role);
        if (save > 0) {
            return ResultUtils.success("修改角色成功!");
        }
        return ResultUtils.error("修改角色失败");
    }

    @Override
    public AssignVo getAssignShow(AssignParm parm) {
        //查询当前用户信息
        Admin admin = adminService.getById(parm.getAid());
        //查询用户的菜单
        List<Menu> list = null;
        //if(admin.getIsadmin().equals("1")){ //超级管理员，直接查询所有的菜单
        //    QueryWrapper<Menu> query = new QueryWrapper<>();
        //    query.lambda().orderByAsc(Menu::getOrderNum);
        //    list = menuService.list(query);
        //}else{
        //    list = menuService.getMenuByAid(parm.getAid());
        //}
        QueryWrapper<Menu> query = new QueryWrapper<>();
        query.lambda().orderByAsc(Menu::getOrderNum);
        list = menuService.list(query);
        //组装菜单数据
        List<Menu> menuList = MakeTree.makeMenuTree(list, 0L);
        //查询角色对应的菜单
        List<Long> ids = new ArrayList<>();
        List<Menu> roleList = menuService.getMenuByRoleId(parm.getRoId());
        Optional.ofNullable(roleList).orElse(new ArrayList<>())
                .stream()
                .filter(item ->item != null)
                .forEach(item ->{
                    ids.add(item.getMid());
                });
        AssignVo vo = new AssignVo();
        vo.setCheckList(ids.toArray());
        vo.setMenuList(menuList);
        return vo;
    }
}




