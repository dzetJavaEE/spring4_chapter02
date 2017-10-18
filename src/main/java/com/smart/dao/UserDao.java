package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * spring jdbc 技术
 * such as "Data Access Object"
 */
@Repository
public class UserDao implements IUserDao {

    private static final String MATCH_COUNT_SQL = " select count(*) from t_user "
            + " where user_name =? and password =? ";
    private static final String FIND_USER_BY_USERNAME_SQL = " select * from t_user "
            + " where user_name =? ";
    private static final String UPDATE_LOGIN_INFO_SQL = " update t_user set last_visit=?, last_ip=?, credits=? "
            + " where user_id =? ";
    private JdbcTemplate jdbcTemplate;

    /**
     * 自动注入
     *
     * @param jdbcTemplate
     */
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password) {
        // ctrl alt c ==> 转换当前变量为常量
        return this.jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[]{userName, password}, Integer.class);
    }

    public User findUserByUserName(final String userName) {
        final User user = new User();
        this.jdbcTemplate.query(FIND_USER_BY_USERNAME_SQL, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(userName);
                user.setCredits(resultSet.getInt("credits"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        this.jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId());
    }
}
