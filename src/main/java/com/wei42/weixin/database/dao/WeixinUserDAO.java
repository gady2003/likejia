package com.wei42.weixin.database.dao;

import com.wei42.weixin.database.domain.UserDO;

/**
 * Created by xuanxiao on 2016-03-21.
 */
public class WeixinUserDAO extends  BaseDao {

    public WeixinUserDAO(){
        sqlSession = sqlSessionFactory.openSession(true);
    }

    public void insertWeixinUser(UserDO user) {
        sqlSession.insert("insertuser",user);
    }

    public UserDO queryWeixinUserbyid(int id){
        return sqlSession.selectOne("queryuserbyid",id);
    }

    public void updateWeixinUserStatus(UserDO userDO){
        sqlSession.update("updateuserstatus",userDO);
    }

    public void updateWeixinUserCurAddress(UserDO userDO){
        sqlSession.update("updateusercuraddress",userDO);
    }

    public static  void main(String[] args){
        WeixinUserDAO dao = new WeixinUserDAO();
//        WeixinUser user = new WeixinUser();
//        user.setWxid("o7elat4UcAdxfQFf9ndT6P9h_YOw");
//        user.setWxnick("树枝");
//        user.setTelphone("13958053485");
//        user.setAddress("浙江省杭州市");
//        user.setStatus("offline");
//        user.setCreatetime(new Date());
//        dao.insertWeixinUser(user);
        UserDO user = dao.queryWeixinUserbyid(2);
        System.out.println(user.getWxnick());
//        UserDO userDO = new UserDO();
//        userDO.setStatus(WeixinConstants.UNSUBSCRIBE_STATUS);
//        userDO.setWxid("o7elat4UcAdxfQFf9ndT6P9h_YOw");
//        dao.updateWeixinUserStatus(userDO);
    }
}
