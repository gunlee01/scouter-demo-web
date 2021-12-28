package gunlee.scouter.demo.commondemo.interfaces.controller.lab;
/*
 *  Copyright 2015 the original author or authors.
 *  @https://github.com/scouter-project/scouter
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import gunlee.scouter.demo.fw.util.SleepUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.*;

/**
 * Created by Gun Lee(gunlee01@gmail.com) on 12/11/2019
 */
@RestController
public class AsyncExecutorTraceController {

	@Autowired
	@Qualifier("taskExecutor")
	protected Executor executor;

	@GetMapping("/lab/async/executor")
	public List<Integer> testAsyncExecutor() throws IOException {
		List<CompletableFuture<Integer>> futureList = new ArrayList<>();

		futureList.add(CompletableFuture.supplyAsync(() -> {
			SleepUtil.sleep(1000);
			return ThreadLocalRandom.current().nextInt(0, 100000);
		},executor));

		futureList.add(CompletableFuture.supplyAsync(() -> {
			SleepUtil.sleep(3000);
			return ThreadLocalRandom.current().nextInt(0, 100000);
		},executor));

		List<Integer> ints = futureList.stream().map(CompletableFuture::join).collect(toList());

		return ints;
	}

	@GetMapping("/lab/async/executor1")
	public List<Integer> testAsyncExecutor1() throws IOException {
		List<CompletableFuture<Integer>> futureList = new ArrayList<>();

		futureList.add(CompletableFuture.supplyAsync(() -> {
			SleepUtil.sleep(1000);
			return ThreadLocalRandom.current().nextInt(0, 100000);
		},executor));

		List<Integer> ints = futureList.stream().map(CompletableFuture::join).collect(toList());

		return ints;
	}

	@Autowired
	Service1 service1;

	@GetMapping("/lab/async/run-after-run")
	public boolean runAfterRun() throws IOException {
		service1.sleep3000();

		return true;
	}

	@GetMapping("/lab/async/run-after-expired-delay")
	public boolean runAfterExpiredDelay() throws IOException {
		service1.sleep500();
		SleepUtil.sleep(20000);

		return true;
	}

	@Service
	public static class Service1 {

		@Async
		public void sleep100() {
			SleepUtil.sleep(100);
		}

		@Async
		public void sleep500() {
			SleepUtil.sleep(500);
		}

		@Async
		public void sleep3000() {
			SleepUtil.sleep(3000);
		}

		@Async
		public void sleep11000() {
			SleepUtil.sleep(11000);
		}
	}
}
