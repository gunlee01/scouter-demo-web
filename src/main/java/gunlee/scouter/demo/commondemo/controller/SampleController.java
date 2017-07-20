package gunlee.scouter.demo.commondemo.controller;

import gunlee.scouter.demo.commondemo.model.SystemInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@RestController
public class SampleController {

    @GetMapping("/systemInfo")
    public SystemInfo systemInfo() {
        return new SystemInfo(1, "demo", "normal");
    }
}
