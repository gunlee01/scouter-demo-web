package gunlee.scouter.demo.fw;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@Component
@Slf4j
@Getter
@Setter
public class HttpInterceptor extends HandlerInterceptorAdapter {
    private static final String INTERCEPTOR_KEY = "__interceptor__key";
    private AtomicInteger ai = new AtomicInteger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(INTERCEPTOR_KEY, ai.getAndIncrement());
        log.info("[preHandle]{} {}", request.getAttribute(INTERCEPTOR_KEY), request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("[postHandle]{} {}", request.getAttribute(INTERCEPTOR_KEY), request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("[afterCompletion]{} {}", request.getAttribute(INTERCEPTOR_KEY), request.getRequestURI());
    }
}
