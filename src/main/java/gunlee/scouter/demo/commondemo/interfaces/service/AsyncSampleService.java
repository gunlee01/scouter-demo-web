package gunlee.scouter.demo.commondemo.interfaces.service;

import gunlee.scouter.demo.fw.util.SleepUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 30.
 */
@Component
@Slf4j
public class AsyncSampleService {
    @Async
    public void myAsyncService(String arg) {
        SleepUtil.sleep(800);
        System.out.println("[myAsyncService] async service called! " + arg);
    }
}
