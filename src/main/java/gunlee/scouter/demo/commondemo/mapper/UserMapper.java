package gunlee.scouter.demo.commondemo.mapper;

import gunlee.scouter.demo.commondemo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 23.
 */
@Mapper
public interface UserMapper {
    @Select("select user_id, user_name, created from user where user_id=#{userId}")
    User findById(@Param("userId") String userId);

}
