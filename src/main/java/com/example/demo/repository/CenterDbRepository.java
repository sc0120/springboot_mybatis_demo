package com.example.demo.repository;

import com.example.demo.vo.UserJoinAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2018/1/1 0001.
 */
@Repository
public class CenterDbRepository {
    @Autowired
    @Qualifier(value = "centerJdbcTemplate")
    private JdbcTemplate centerJdbcTemplate;

    @Transactional(readOnly = true)
    public Map getUserById(int id) {
        String sql = "select * from rc_user where `id`=?";
        try {
            Map userInfo = centerJdbcTemplate.queryForMap(sql, new Object[]{id});
            return userInfo;
        } catch (Exception e) {
            return null;
        }
    }

    @Transactional(readOnly = true)
    public List<UserJoinAmount> getUserList(int offset, int limit) throws Exception {
        String sql = "select a.*,b.amount from rc_user as a left join rc_test as b on b.user_id = a.id limit ?,?";
        List<UserJoinAmount> userList = centerJdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1,offset);
                preparedStatement.setInt(2,limit);
            }
        }, new RowMapper<UserJoinAmount>() {
            @Override
            public UserJoinAmount mapRow(ResultSet rs, int rowNum) throws SQLException {
                UserJoinAmount user = new UserJoinAmount();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("user_name"));
                user.setNickname(rs.getString("user_nickname"));
                user.setAmount(rs.getString("amount"));
                return user;
            }
        });
        return userList;
    }

    @Transactional
    public int addNewUser(String username,String userNickname) throws Exception {
        int autoIncId = 0;
        String sqlstr = "insert into rc_user(`user_name`,`user_nickname`) values(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        centerJdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sqlstr,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,username);
                ps.setString(2,userNickname);
                return ps;
            }
        },keyHolder);
        autoIncId = keyHolder.getKey().intValue();
        return autoIncId;
    }

}
