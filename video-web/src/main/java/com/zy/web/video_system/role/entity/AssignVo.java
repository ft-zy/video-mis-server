package com.zy.web.video_system.role.entity;

import com.zy.web.video_system.menu.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AssignVo {
    // 当前用户拥有的菜单
    private List<Menu> menuList = new ArrayList<>();
    // 被分配的角色拥有的菜单 id
    private Object[] checkList;

}
