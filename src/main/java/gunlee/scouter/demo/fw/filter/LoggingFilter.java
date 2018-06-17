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

package gunlee.scouter.demo.fw.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 6. 16.
 */
@Slf4j
public class LoggingFilter implements Filter {
    private static final String MDC_REQ_ID = "reqId";
    private AtomicLong al = new AtomicLong();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        MDC.put(MDC_REQ_ID, "vm0:" + String.valueOf(al.getAndIncrement()));
        log.info("[start] {}", ((HttpServletRequest)servletRequest).getRequestURI());

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            log.info("[end] {}", ((HttpServletRequest)servletRequest).getRequestURI());
            MDC.clear();
        }
    }

    @Override
    public void destroy() {

    }
}
