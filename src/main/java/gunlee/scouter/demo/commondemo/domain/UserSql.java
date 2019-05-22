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

/**
 * @author Gun Lee (gunlee01@gmail.com) on 30/11/2018
 */
public class UserSql {
    public static final String SELECT_USER = "\n        select user_id, \n               user_name, \n               created \n        from   user \n        where  user_id=:userId\n    ";

    public static final String SELECT_USER_BY_ID_PW = "\n        select user_id, \n               user_name, \n               created \n        from   user \n        where  user_id = :userId\n        and    password = :password\n    ";

    public static final String SELECT_USER_BY_NAME = "\n        select user_id, \n               user_name, \n               created \n        from   user \n        where  user_name like concat(:userName, '%')\n    ";

    public static final String SELECT_ERROR_SQL = "\n        select user_id, \n               user_name, \n               non_column,\n               created \n        from   user \n        where  user_name = 'any'\n    ";

    public static final String UPDATE_USER_NAME = "\n        update user \n        set    user_name = :userName\n        where  user_id = :userId\n    ";

    public static final String SELECT_DEVICE_BY_USER = "\n        select device_id, \n               device_token, \n               created \n        from   device \n        where  user_id=:userId\n    ";
}
