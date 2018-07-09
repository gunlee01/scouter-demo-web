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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 1.
 */
@Service
public class AsyncSimulateDelayServiceStep2 {
    @Autowired
    UserService userService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyUserNameTx(String userId) {
        String userName = "nameZ" + String.valueOf(ThreadLocalRandom.current().nextInt(201, 300));
        userService.modifyUserName(userId, userName);
        sleepThousands();
        userService.findUserById(userId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void modifyUserNameTxShort(String userId) {
        String userName = "nameZ" + String.valueOf(ThreadLocalRandom.current().nextInt(201, 300));
        userService.modifyUserName(userId, userName);
        sleepShort();
        userService.findUserById(userId);
    }

    public void sleepShort() {
        sleep(ThreadLocalRandom.current().nextInt(30, 70));
    }

    public void sleepHundreds() {
        sleep(ThreadLocalRandom.current().nextInt(30, 800));
    }

    public void sleepThousands() {
        sleep(ThreadLocalRandom.current().nextInt(1800, 5600));
    }

    private void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
