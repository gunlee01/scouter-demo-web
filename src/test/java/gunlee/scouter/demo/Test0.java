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

package gunlee.scouter.demo;

import org.junit.Test;

import java.util.regex.Matcher;

/**
 * @author Gun Lee (gunlee01@gmail.com) on 2018. 7. 30.
 */
public class Test0 {
    @Test
    public void test() {
        String contents = "enabled=false\ninput_telegraf_$cpu$_debug_enabled=false\ninput_debug=false\n";
        String exp = "(?m)^input_telegraf_\\$cpu\\$_debug_enabled\\s*=.*\\n?";
        String replacement = "input_telegraf_$cpu$_debug_enabled=true\\n";

        String result = contents.replaceAll(exp, Matcher.quoteReplacement(replacement));
        System.out.println(result);
    }

    @Test
    public void test1() {
        String contents = "enabled=false\ninput_telegraf_$cpu$_debug_enabled=false\ninput_debug=false\n";
        String exp = "(?m)^input_telegraf_\\$cpu\\$_debug_enabled\\s*=.*\\n?";
        String replacement = "input_telegraf_$cpu$_debug_enabled=true\\n";

        String result = contents.replaceAll(exp, replacement);
        System.out.println(result);
    }
}
