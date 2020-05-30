package com.community.dao;

import com.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @ClassName LoginTicketMapper
 * @Description
 */

@Mapper
public interface LoginTicketMapper {

    //插入用户凭证
    @Insert({"insert into login_ticket(user_id,ticket,status,expired) ", "values(#{userId},#{ticket},#{status},#{expired})"})
    //自动生成主键
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertLoginTicket(LoginTicket loginTicket);

    //查询用户的凭证
    @Select({"select id,user_id,ticket,status,expired",
    "from login_ticket where ticket=#{ticket}"})
    LoginTicket selectByTicket(String ticket);

    //更新凭证状态
    @Update({"<script>", "update login_ticket set status=#{status} where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ", "and 1=1 ", "</if>", "</script>"})
    int updateStatus(String ticket, int status);
}
