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

package gunlee.scouter.demo.commondemo.domain

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 6. 16.
 */
class UserSql {

    public static final String SELECT_USER = """
        select user_id, 
               user_name, 
               created 
        from   user 
        where  user_id=:userId
    """

    public static final String SELECT_USER_BY_ID_PW = """
        select user_id, 
               user_name, 
               created 
        from   user 
        where  user_id = :userId
        and    password = :password
    """

    public static final String SELECT_USER_BY_NAME = """
        select user_id, 
               user_name, 
               created 
        from   user 
        where  user_name like concat(:userName, '%')
    """

    public static final String SELECT_ERROR_SQL = """
        select user_id, 
               user_name, 
               non_column,
               created 
        from   user 
        where  user_name = 'any'
    """

    public static final String UPDATE_USER_NAME = """
        update user 
        set    user_name = :userName
        where  user_id = :userId
    """

    public static final String SELECT_DEVICE_BY_USER = """
        select device_id, 
               device_token, 
               created 
        from   device 
        where  user_id=:userId
    """
}
