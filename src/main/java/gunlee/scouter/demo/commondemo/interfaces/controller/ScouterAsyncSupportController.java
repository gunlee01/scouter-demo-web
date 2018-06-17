package gunlee.scouter.demo.commondemo.interfaces.controller;

import gunlee.scouter.demo.commondemo.interfaces.service.AsyncSampleService;
import gunlee.scouter.demo.fw.util.SleepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 29.
 */
@RestController
public class ScouterAsyncSupportController {
    ExecutorService es = Executors.newFixedThreadPool(3);

    @Autowired
    AsyncSampleService asyncSampleService;

    @GetMapping("/async/runnable")
    public String asyncRunnable() {
        new Thread(new Runnable() {
            public void run() {
                SleepUtil.sleep(120);
            }
        }).start();
        return "asyncRunnable";
    }

    @GetMapping("/async/lambda")
    public String asyncLambda() {
        String value = "closer";

        new Thread(() -> {
            System.out.println("[Scouter demo test] In lambda thread. closer : " + value);
            SleepUtil.sleep(120);
        }).start();
        return "asyncLambda";
    }

    @GetMapping("/async/executorService")
    public String asyncExecutorService() {
        String value = "closer";
        es.submit(() -> {
            System.out.println("[Scouter demo test] In executorservice submit thread. closer : " + value);
            SleepUtil.sleep(500);
        });
        return "asyncExecutorService";
    }

    @GetMapping("/async/executorServiceRunnable")
    public String asyncExecutorServiceRunnable() {
        String value = "closer";
        es.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("[Scouter demo test] In executorservice submit thread runnable. closer : " + value);
                SleepUtil.sleep(500);
            }
        });
        return "asyncExecutorServiceRunnable";
    }

    @GetMapping("/async/executorServiceExecute")
    public String asyncExecutorServiceExecute() {
        String value = String.valueOf(Math.random());
        es.execute(() -> {
            System.out.println("[Scouter demo test] In executorservice execute thread. closer : " + value);
            SleepUtil.sleep(500);
        });
        return "asyncExecutorServiceExecute";
    }

    @GetMapping("async/asyncMethod")
    public String asyncMethodCall() {
        String value = String.valueOf(Math.random());
        asyncSampleService.myAsyncService(value);
        return "async/asyncMethod";
    }
}
