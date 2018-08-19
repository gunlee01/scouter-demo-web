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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 1.
 */
@Service
public class TraceabilityService {
    @Autowired
    Environment environment;

    @Autowired
    UserService userService;

    @Autowired
    AsyncSimulateDelayService asyncSimulateDelayService;

    @Autowired
    SimulateDelayService simulateDelayService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${scouter.demo.web.internal.host}")
    String remoteHost;

    @Value("${scouter.demo.web.internal.port:0}")
    String remotePort;

    public void justCallRemote() {
        String remoteHost = getRemoteHost();

        userService.findUserById("user0" + ThreadLocalRandom.current().nextInt(101, 499));
        restTemplate.getForObject(remoteHost + "/user/user0" + ThreadLocalRandom.current().nextInt(101, 499), String.class);
        restTemplate.getForObject("http://notice.scouterapm.com:6181/latest-notice", String.class);
    }

    public void callAsyncAndRemoteChain() {
        String remoteHost = getRemoteHost();

        userService.findUserById("user0" + ThreadLocalRandom.current().nextInt(101, 499));
        simulateDelayService.simulateDelayLongWithAsyncCall();
        restTemplate.getForObject(remoteHost + "/user/user0" + ThreadLocalRandom.current().nextInt(101, 499), String.class);
        restTemplate.getForObject(remoteHost + "/traceability/cross-service/simple", String.class);
        restTemplate.getForObject(remoteHost + "/traceability/cross-service/simple2", String.class);
        userService.findDeviceByUserId("user0210");
        restTemplate.getForObject("http://www.google.com/", String.class);
    }

    public void callAsyncSample1() {
        userService.findDeviceByUserId("user0" + ThreadLocalRandom.current().nextInt(101, 499));
        simulateDelayService.simulateDelayShortWithAsyncCall();
    }

    private String getRemoteHost() {
        String port = remotePort;
        if ("0".equals(port)) {
            port = environment.getProperty("local.server.port");
        }
        return "http://" + remoteHost + ":" + port;
    }
}
