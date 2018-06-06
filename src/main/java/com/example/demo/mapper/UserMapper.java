package com.example.demo.mapper;

import com.example.demo.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by caoyixian on 2018/6/2.
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO t_user(username,nickname) VALUES (#{username}, #{nickname}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);

    @Delete("DELETE FROM t_user WHERE id= #{id}")
    int delete(@Param("id") Integer id);

    @Update("UPDATE t_user SET username = #{username},nickname = #{nickname} WHERE id = #{id}")
    int update(User user);

    @Select("SELECT id, username, nickname FROM t_user WHERE id = #{id}")
    User findById(Integer id);

    @Select("SELECT * FROM t_user")
    @Results(id = "userMap", value = { @Result(column = "id", property = "id", javaType = Integer.class),
            @Result(column = "nickname", property = "nickname", javaType = String.class),
            @Result(column = "username", property = "username", javaType = String.class) })
    List<User> fingAll();

    @Select("SELECT u.id, u.username, u.nickname,t.amount FROM t_user u left join rc_test t on t.user_id=u.id WHERE u.id = #{id}")
    @Results(id = "userMapJoinAmount", value = { @Result(column = "id", property = "Id", javaType = Integer.class),
            @Result(column = "nickname", property = "nickName", javaType = String.class),
            @Result(column = "username", property = "userName", javaType = String.class),
            @Result(column = "amount",property = "amount",javaType = Integer.class)
    })
    List<Map> findByIdJoinTest(Integer id);


}
