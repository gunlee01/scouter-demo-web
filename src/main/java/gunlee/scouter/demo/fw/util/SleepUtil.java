package gunlee.scouter.demo.fw.util;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 29.
 */
public class SleepUtil {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
