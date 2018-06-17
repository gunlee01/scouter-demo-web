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

import static gunlee.scouter.demo.commondemo.domain.UserSql.SELECT_USER;

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
        return njt.queryForObject(SELECT_USER, param, BeanPropertyRowMapper.newInstance(User.class));
    }

}
