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

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 1.
 */
@Service
public class SimulateDelayService {

    @Autowired
    UserService userService;

    @Autowired
    AsyncSimulateDelayService asyncSimulateDelayService;

    public void simulateDelayShort() {
        userService.findUserById("user0010");
        sleepHundreds();
        userService.findUserByUserNameLike("name001");
    }

    public void simulateDelayLong() {
        userService.findUserById("user0020");
        sleepThousands();
        userService.findUserByUserNameLike("name010");
        userService.findUserByUserNameLike("name011");
        userService.findUserByUserNameLike("name012");
    }

    public void simulateDelayShortWithAsyncCall() {
        userService.findUserById("user0010");
        Future<Boolean> future = asyncSimulateDelayService.sleepHundreds();
        block(future);
        userService.findUserByUserNameLike("name001");
    }

    public void simulateDelayLongWithAsyncCall() {
        userService.findUserById("user0020");
        Future<Boolean> future = asyncSimulateDelayService.sleepThousands();
        userService.findUserByUserNameLike("name010");
        userService.findUserByUserNameLike("name011");
        block(future);
        userService.findUserByUserNameLike("name012");
    }

    @Transactional
    public void simulateDelayLongWithDbLock() {
        userService.findUserById("user0020");
        String userId = "user" + StringUtils.leftPad(String.valueOf(ThreadLocalRandom.current().nextInt(101, 200)), 4, '0');
        String userName = "nameX" + String.valueOf(ThreadLocalRandom.current().nextInt(101, 200));
        asyncSimulateDelayService.modifyUserNameInAnotherTx(userId);
        sleep(500);
        userService.modifyUserName(userId, userName);
        userService.findUserByUserNameLike(userName);
    }

    @Transactional
    public void simulateDelayShortWithDbLock() {
        userService.findUserById("user0020");
        String userId = "user" + StringUtils.leftPad(String.valueOf(ThreadLocalRandom.current().nextInt(101, 200)), 4, '0');
        String userName = "nameX" + String.valueOf(ThreadLocalRandom.current().nextInt(101, 200));
        asyncSimulateDelayService.modifyUserNameInAnotherTxShort(userId);
        sleep(100);
        userService.modifyUserName(userId, userName);
        userService.findUserByUserNameLike(userName);
    }

    public void sleepHundreds() {
        sleep(ThreadLocalRandom.current().nextInt(30, 800));
    }

    public void sleepThousands() {
        sleep(ThreadLocalRandom.current().nextInt(1200, 3800));
    }

    private void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void block(Future<?> future) {
        try {
            future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
