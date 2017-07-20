package gunlee.scouter.demo.commondemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@Getter
@Setter
@AllArgsConstructor
public class SystemInfo {
    int systemId;
    String systemName;
    String systemStatus;
}
