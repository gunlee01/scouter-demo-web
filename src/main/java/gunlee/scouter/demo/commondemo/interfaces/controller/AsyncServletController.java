package gunlee.scouter.demo.commondemo.interfaces.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 29.
 */
@RestController
public class AsyncServletController {
    @GetMapping("/asyncServlet")
    public Callable<String> asyncServlet() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(100);
                return "It's async servlet., callable return.";
            }
        };
        return callable;
    }

    @GetMapping("/asyncServletSleep3000")
    public Callable<String> asyncServletSleep3000() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "It's async servlet., sleep3000, callable return.";
            }
        };
        return callable;
    }

    @GetMapping("/asyncServletSleep10000")
    public Callable<String> asyncServletSleep10000() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(10000);
                return "It's async servlet., sleep10000, callable return.";
            }
        };
        return callable;
    }

}
