/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package gunlee.scouter.demo.config;

import gunlee.scouter.demo.fw.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 6. 16.
 */
@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean getFilterRegistrationBean()
    {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LoggingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
