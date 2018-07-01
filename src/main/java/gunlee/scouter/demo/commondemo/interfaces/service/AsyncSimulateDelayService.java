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

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 1.
 */
@Service
public class AsyncSimulateDelayService {

    @Async
    public Future<Boolean> sleepHundreds() {
        sleep(ThreadLocalRandom.current().nextInt(120, 800));
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> sleepThousands() {
        sleep(ThreadLocalRandom.current().nextInt(1200, 3800));
        return new AsyncResult<>(true);
    }

    private void sleep(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
