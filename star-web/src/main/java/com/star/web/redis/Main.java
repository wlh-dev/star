package com.star.web.redis;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.concurrent.CountDownLatch;

/**
 * @author : wlh
 * @date Date : 2022年06月28日 10:32
 */

public class Main {

	private static CountDownLatch cld = new CountDownLatch(10);
	public static void main(String[] args) throws InterruptedException {




		Runnable taskTemp = new Runnable() {

			private int iCounter;

			@Override
			public void run() {
				for(int i = 0; i < 10; i++) {
					// 发起请求
					extractedPost();
					iCounter++;
					// System.out.println(System.nanoTime() + " [" + Thread.currentThread().getName() + "] iCounter = " + iCounter);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		startTaskAllInOnce(10, taskTemp);

	}

	private static void extractedPost() {
		String url = "http://newstore.dev.cn/store/openapi/v2/resources/batch_save?apikey=6b2236f544fb49829b163573238a0848&source=platform_cloud";
		JSONArray jsonArray = new JSONArray();

		JSONObject entries = new JSONObject();
		entries.set("classCode", "Switch");
		entries.set("name", "tes07255121");
		entries.set("serial_number", "tes07255121");
		entries.set("is_active", "Y");
		jsonArray.add(entries);
		System.out.println(System.currentTimeMillis());
		HttpResponse execute = HttpRequest.post(url).body(jsonArray.toJSONString(0)).execute();
	}
	public static long startTaskAllInOnce(int threadNums, final Runnable task) throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(threadNums);
		for(int i = 0; i < threadNums; i++) {
			Thread t = new Thread(() -> {
				try {
					// 使线程在此等待，当开始门打开时，一起涌入门中
					startGate.await();
					try {
						task.run();
					} finally {
						// 将结束门减1，减到0时，就可以开启结束门了
						endGate.countDown();
					}
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}
			});
			t.start();
		}
		long startTime = System.nanoTime();
		System.out.println(startTime + " [" + Thread.currentThread() + "] All thread is ready, concurrent going...");
		// 因开启门只需一个开关，所以立马就开启开始门
		startGate.countDown();
		// 等等结束门开启
		endGate.await();
		long endTime = System.nanoTime();
		System.out.println(endTime + " [" + Thread.currentThread() + "] All thread is completed.");
		return endTime - startTime;
	}

}
