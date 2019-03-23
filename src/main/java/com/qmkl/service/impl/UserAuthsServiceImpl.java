package com.qmkl.service.impl;
import com.qmkl.dao.UserAuthsDao;
import com.qmkl.service.UserAuthsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userAuthsService")
public class UserAuthsServiceImpl implements UserAuthsService {
    @Resource
    private UserAuthsDao userAuthsDao;

}
