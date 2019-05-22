package gunlee.scouter.demo.commondemo.interfaces.service;

import gunlee.scouter.demo.commondemo.domain.User;
import gunlee.scouter.demo.fw.util.SleepUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 30.
 */
@Component
@Slf4j
public class AsyncSampleService {
    @Autowired
    UserService userService;

    @Autowired
    TaskExecutor taskExecutor;

    @Async
    public void myAsyncService(String arg) {
        SleepUtil.sleep(800);
        System.out.println("[myAsyncService] async service called! " + arg);
    }

    @Async
    public Future<String> myAsyncWithReturnService(String arg) {
        SleepUtil.sleep(800);
        System.out.println("[myAsyncWithReturnService] async service called! " + arg);
        return new AsyncResult<>("OK");
    }

    public void cfAsyncTrace(String arg) {
        System.out.println("[cfAsyncTrace] cfAsyncTrace async service called! " + arg);

        CompletableFuture<User> f = CompletableFuture.supplyAsync(() -> {
            SleepUtil.sleep(1100);
            return userService.findUserById("10000");
        }, taskExecutor);

        try {
            User u = f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void cfAsyncTrace2(String arg) {
        List<CompletableFuture<User>> futureList = new ArrayList<>();
        futureList.add(CompletableFuture.supplyAsync(() -> {
            System.out.println("[cf-supplyAsync2-1]" + arg);
            SleepUtil.sleep(500);
            return userService.findUserById("10000");
        }, taskExecutor));

        futureList.add(CompletableFuture.supplyAsync(() -> {
            System.out.println("[cf-supplyAsync2-2]" + arg);
            SleepUtil.sleep(820);
            return userService.findUserById("10001");
        }, taskExecutor));

        CompletableFuture<User>[] futureArray = new CompletableFuture[futureList.size()];
        for (int i = 0; i < futureList.size(); i++) {
            futureArray[i] = futureList.get(i);
        }

        List<User> userList = Stream.of(futureArray).map(CompletableFuture::join).collect(Collectors.toList());
    }
}
