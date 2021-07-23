package org.kiteki.smartlibrary.dao;

import org.apache.ibatis.session.SqlSession;
import org.kiteki.smartlibrary.domain.AuthRole;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthRoleDaoImpl implements AuthRoleDao{

    private SqlSession sqlSession;

    @Autowired
    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public AuthRole selectAuthRole(String id) {
        return sqlSession.selectOne("org.kiteki.smartlibrary.mappers.AuthRoleMapper.selectAuthRole", id);
    }
}
