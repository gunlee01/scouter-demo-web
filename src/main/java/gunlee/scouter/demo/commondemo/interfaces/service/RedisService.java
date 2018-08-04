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

package gunlee.scouter.demo.commondemo.interfaces.service;

import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 8. 4.
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class RedisService {

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public Long increaseCounter() {
        Long count = 0L;
        count = valueOperations.increment("sc:demo:main-counter", 1);
        return count;
    }

    public Long increaseLoginCounter() {
        Long count = 0L;
        count = valueOperations.increment("sc:demo:login-counter", 1);

        return count;
    }


    public void cacheUserId(String userId, String userName) {
        valueOperations.set("sc:demo:user:" + userId, userName, ThreadLocalRandom.current().nextLong(1, 300), TimeUnit.SECONDS);
        valueOperations.get("sc:demo:user:" + userId);
    }
}
