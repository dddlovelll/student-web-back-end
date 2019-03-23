package com.qmkl.service.impl;


import com.qmkl.dao.MyCollectDao;
import com.qmkl.entity.MyCollect;
import com.qmkl.entity.MyCollectResult;
import com.qmkl.service.MyCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("myCollectService")
public class MyCollectServiceImpl implements MyCollectService {

    @Resource
    private MyCollectDao myCollectDao;


    @Override
    public void add(MyCollect myCollect) {
        myCollectDao.add(myCollect);
    }

    @Override
    public MyCollect get(Integer userId, Integer postId) {
        return myCollectDao.get(userId,postId);
    }

    @Override
    public void update(Integer userId, Integer postId, boolean state) {
        myCollectDao.update(userId,postId,state);
    }

    @Override
    public List<MyCollectResult> listMyCollectWithPage(Integer start, Integer num, Integer userId) {
        return myCollectDao.listMyCollectWithPage(start,num,userId);
    }

}
