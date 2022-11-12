package com.zy.email.service;

import java.util.Map;

public interface VerifyService {

    Map sendVerifyCode(String email);

}
