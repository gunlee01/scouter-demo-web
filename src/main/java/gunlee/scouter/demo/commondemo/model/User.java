package gunlee.scouter.demo.commondemo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 23.
 */
@Getter
@Setter
@ToString
public class User implements Serializable {
    static final long serialVersionUID = 1L;

    String userId;
    String userName;
    Date created;
}
