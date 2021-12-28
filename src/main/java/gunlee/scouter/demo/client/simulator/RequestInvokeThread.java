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

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 10.
 */
@Slf4j
public class RequestInvokeThread implements Pausable {
    ExecutorService es;
    private RequestSample requestSample;
    private boolean pause = true;
    private long sleepDefault = 1000L;

    private RequestInvokeThread(RequestSample requestSample, ExecutorService es) {
        this.es = es;
        this.requestSample = requestSample;
        Thread thread = new Thread(runnable);
        thread.setName("RequestInvokeThread-" + requestSample.name());
        thread.setDaemon(true);
        thread.start();
    }

    public static void invokeNewRequestSampleRun(RequestSample requestSample, PauseStatusPublisher publisher) {
        ExecutorService es = Executors.newFixedThreadPool(10, new CustomizableThreadFactory("RequestSampler-" + requestSample.name()));
        publisher.addSubscriber(new RequestInvokeThread(requestSample, es));
    }

    @Override
    public void pause() {
        pause = true;
    }

    @Override
    public void resume() {
        pause = false;
    }

    private Runnable runnable = () -> {
        while (true) {
            try {
                if (!pause) {
                    Thread.sleep(requestSample.getSleepMillis());
                    es.submit(() -> {
                        try {
                            HttpResponse<String> response;
                            String url = "http://localhost:9090" + requestSample.getUrl();
                            if (requestSample.getHttpMethod() == HttpMethod.POST) {
                                response = Unirest.post(url).body(requestSample.getBody()).asString();
                            } else { // All get now
                                response = Unirest.get(url).asString();
                            }
                            log.debug("url: {}, response :{}", requestSample.getUrl(), response);
                        } catch (Exception e) {
                            log.error("Error: {}, url: {}", e.getMessage(), requestSample.getUrl(), e);
                        }
                    });
                } else {
                    Thread.sleep(sleepDefault);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
}
