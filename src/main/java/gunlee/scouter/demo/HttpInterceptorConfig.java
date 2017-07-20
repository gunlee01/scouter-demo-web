package gunlee.scouter.demo;

import gunlee.scouter.demo.fw.HttpInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2017. 7. 20.
 */
@Configuration
public class HttpInterceptorConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(httpInterceptor)
        .addPathPatterns("/**");
    }
}
