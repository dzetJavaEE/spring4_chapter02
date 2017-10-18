package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao implements ILoginLogDao {

    private static final String INSERT_LOGIN_LOG_SQL = " insert into t_login_log (user_id,ip,login_datetime) values(?,?,?) ";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertLoginLog(LoginLog loginLog) {
        this.jdbcTemplate.update(INSERT_LOGIN_LOG_SQL, loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDatetime());
    }

}
