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

package gunlee.scouter.demo.commondemo.interfaces.controller;

import gunlee.scouter.demo.commondemo.domain.BooleanView;
import gunlee.scouter.demo.commondemo.interfaces.service.SimulateDelayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@RestController
public class SimulateController {
    @Autowired
    SimulateDelayService simulateDelayService;

    @GetMapping("/simulate/type/millis-delay")
    public BooleanView millisDelay() {
        simulateDelayService.simulateDelayShort();
        return new BooleanView(true);
    }

    @GetMapping("/simulate/type/seconds-delay")
    public BooleanView secondsDelay() {
        simulateDelayService.simulateDelayLong();
        return new BooleanView(true);
    }

    @GetMapping("/simulate/type/millis-delay-async-call")
    public BooleanView millisDelayWithAsyncCall() {
        simulateDelayService.simulateDelayShortWithAsyncCall();
        return new BooleanView(true);
    }

    @GetMapping("/simulate/type/seconds-delay-async-call")
    public BooleanView secondsDelayWithAsyncCall() {
        simulateDelayService.simulateDelayLongWithAsyncCall();
        return new BooleanView(true);
    }

    @GetMapping("/simulate/type/sql-locked-seconds-delay")
    public BooleanView secondsDelayWithDbLock() {
        simulateDelayService.simulateDelayLongWithDbLock();
        return new BooleanView(true);
    }

    @GetMapping("/simulate/type/sql-locked-millis-delay")
    public BooleanView millisDelayWithDbLock() {
        simulateDelayService.simulateDelayShortWithDbLock();
        return new BooleanView(true);
    }
}
