package gunlee.scouter.demo.commondemo.interfaces.controller;

import gunlee.scouter.demo.fw.exception.SampleRuntimeException;
import gunlee.scouter.demo.commondemo.domain.SystemInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@RestController
public class SampleController {

    @GetMapping("/systemInfo")
    public SystemInfo systemInfo() {
        System.out.println("##@!#$!$!asdfasfasf!@$!@");
        return new SystemInfo(1, "demo", "normal");
    }

    @GetMapping("/sleep10000")
    public String sleep10000() throws InterruptedException {
        Thread.sleep(10000);
        return "Sleep-10,000";
    }

    @GetMapping("/sleep100000")
    public String sleep100000() throws InterruptedException {
        Thread.sleep(100001);
        return "Sleep-100,000";
    }

    @GetMapping("/error1")
    public String error1() {
        if (true) {
            throw new RuntimeException("my test exception!");
        }
        return "error1";
    }

    @GetMapping("/error2")
    public String error2() throws Throwable {
        if (true) {
            throw new SampleRuntimeException("my test exception!-2");
        }
        return "error1";
    }
}
