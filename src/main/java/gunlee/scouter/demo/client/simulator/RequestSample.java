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

package gunlee.scouter.demo.client.simulator;

import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 10.
 */
@Getter
public enum RequestSample {
    login(HttpMethod.POST, "", "{\"userId\":\"user0001\"}", 3000, 8000),
    callApiGetUserId(HttpMethod.GET, "/user/user0010", null, 1000, 5000),
    simulateMillisDelay(HttpMethod.GET, "/simulate/type/millis-delay", null, 1000, 5000),
    simulateSecondsDelay(HttpMethod.GET, "/simulate/type/seconds-delay", null, 2000, 5000),
    simulateMillisDelayAsync(HttpMethod.GET, "/simulate/type/millis-delay-async-call", null, 500, 5000),
    simulateSecondsDelayAsync(HttpMethod.GET, "/simulate/type/seconds-delay-async-call", null, 3000, 5000),
    sqlError(HttpMethod.GET, "/case/sql-error", null, 5000, 15000),
    sqlGenerationError(HttpMethod.GET, "/case/sql-generation-error", null, 5000, 15000),
    sqlLockDelaySeconds(HttpMethod.GET, "/simulate/type/sql-locked-seconds-delay", null, 60000, 180000),
    sqlLockDelayMillis(HttpMethod.GET, "/simulate/type/sql-locked-millis-delay", null, 8000, 25000),
    crossTraceSimple(HttpMethod.GET, "/traceability/cross-service/simple", null, 1000, 5000),
    crossTraceComplex(HttpMethod.GET, "/traceability/cross-service/complex", null, 5000, 15000),
    ;

    private HttpMethod httpMethod;
    private String url;
    private String body;
    private int delayFrom;
    private int delayTo;

    RequestSample(HttpMethod httpMethod, String url, String body, int delayFrom, int delayTo) {
        this.httpMethod = httpMethod;
        this.url = url;
        this.body = body;
        this.delayFrom = delayFrom;
        this.delayTo = delayTo;
    }

    public int getSleepMillis() {
        return ThreadLocalRandom.current().nextInt(getDelayFrom(), getDelayTo());
    }
}
