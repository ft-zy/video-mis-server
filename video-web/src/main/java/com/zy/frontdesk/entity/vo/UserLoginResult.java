package com.zy.frontdesk.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResult {

    private Long uid;

    private String username;

    private String token;

    private String netName;

}
