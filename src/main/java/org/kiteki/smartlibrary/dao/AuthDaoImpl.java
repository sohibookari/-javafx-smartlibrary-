package org.kiteki.smartlibrary.dao;

import org.apache.ibatis.session.SqlSession;
import org.kiteki.smartlibrary.domain.auth.Role;
import org.kiteki.smartlibrary.domain.auth.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class AuthDaoImpl implements AuthDao{

    private SqlSession sqlSession;

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public User selectUser(String id) {
        return sqlSession.selectOne("org.kiteki.smartlibrary.mappers.authMapper.selectUser", id);
    }

    @Override
    public Role selectRole(String id) {
        return sqlSession.selectOne("org.kiteki.smartlibrary.mappers.authMapper.selectRole", id);
    }

    @Override
    public User selectUserByUserNameAndPasswd(User userinfo) {
        return sqlSession.selectOne("org.kiteki.smartlibrary.mappers.authMapper.selectUserByUserNameAndPasswd", userinfo);
    }

    @Override
    public Role selectRoleByUser(User user) {
        return sqlSession.selectOne("org.kiteki.smartlibrary.mappers.authMapper.selectRoleByUser", user);
    }
}
