package com.zy.web.video_system.role_menu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zy.web.video_system.role_menu.entity.RoleMenu;
import com.zy.web.video_system.role_menu.entity.SaveAssign;
import com.zy.web.video_system.role_menu.service.RoleMenuService;
import com.zy.web.video_system.role_menu.mapper.RoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author Administrator
* @description 针对表【role_menu】的数据库操作Service实现
* @createDate 2022-10-10 22:06:07
*/
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu>
    implements RoleMenuService{

    //@Override
    //public void assignSave(SaveAssign saveAssign) {
    //    // 先删除角色原来的权限
    //    QueryWrapper<RoleMenu> query = new QueryWrapper<>();
    //    query.lambda().eq(RoleMenu::getRoId, saveAssign.getRoId());
    //    int save = this.baseMapper.delete(query);
    //        this.baseMapper.assignSave(saveAssign.getRoId(), saveAssign.getList());
    //    //if ( save > 0 ) {
    //    //}
    //}

    @Transactional
    @Override
    public void assignSave(SaveAssign saveAssign) {
        //先删除角色原来的权限
        QueryWrapper<RoleMenu> query = new QueryWrapper<>();
        query.lambda().eq(RoleMenu::getRoId,saveAssign.getRoId());
        this.baseMapper.delete(query);
        //新的插入
        this.baseMapper.assignSave(saveAssign.getRoId(),saveAssign.getList());
    }
}




