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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 10.
 */
public class PauseStatusPublisher {
    private List<Pausable> subscribers = new ArrayList<>();
    private static PauseStatusPublisher publisher = new PauseStatusPublisher();

    private PauseStatusPublisher() { }

    public static PauseStatusPublisher getInstance() {
        return publisher;
    }

    public void addSubscriber(Pausable pausable) {
        subscribers.add(pausable);
    }

    public void pause() {
        subscribers.forEach(Pausable::pause);
    }

    public void resume() {
        subscribers.forEach(Pausable::resume);
    }
}
