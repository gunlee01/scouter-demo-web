/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package gunlee.scouter.demo.commondemo.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

import static gunlee.scouter.demo.commondemo.domain.UserSql.SELECT_DEVICE_BY_USER;
import static gunlee.scouter.demo.commondemo.domain.UserSql.SELECT_ERROR_SQL;
import static gunlee.scouter.demo.commondemo.domain.UserSql.SELECT_USER;
import static gunlee.scouter.demo.commondemo.domain.UserSql.SELECT_USER_BY_ID_PW;
import static gunlee.scouter.demo.commondemo.domain.UserSql.SELECT_USER_BY_NAME;
import static gunlee.scouter.demo.commondemo.domain.UserSql.UPDATE_USER_NAME;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 6. 16.
 */
@Repository
public class UserRepository {
    final NamedParameterJdbcTemplate njt;

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.njt = jdbcTemplate;
    }

    public User findById(String userId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        List<User> users = njt.query(SELECT_USER, param, BeanPropertyRowMapper.newInstance(User.class));
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    public Device findDeviceByUserId(String userId) {
        SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
        List<Device> devices = njt.query(SELECT_DEVICE_BY_USER, param, BeanPropertyRowMapper.newInstance(Device.class));
        if (devices.size() > 0) {
            return devices.get(0);
        } else {
            return null;
        }
    }

    public List<User> findByIdAndPassword(String userId, String password) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("password", password);
        return njt.query(SELECT_USER_BY_ID_PW, param, BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<User> findByUserNameLike(String userName) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userName", userName);
        return njt.query(SELECT_USER_BY_NAME, param, BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<User> sqlGenerationError() {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("nonfield", "none");
        return njt.query(SELECT_USER_BY_NAME, param, BeanPropertyRowMapper.newInstance(User.class));
    }

    public List<User> sqlError() {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("nonfield", "none");
        return njt.query(SELECT_ERROR_SQL, param, BeanPropertyRowMapper.newInstance(User.class));
    }

    public void modifyUserName(String userId, String userName) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("userName", userName);
        njt.update(UPDATE_USER_NAME, param);
    }
}
