package com.yog.WFDistributed;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class MergeManager implements Runnable {

	private BlockingQueue<Future<Map<String, Integer>>> queue;
	private boolean isDone = false;

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	private ExecutorService executorService;
	Map<String, Integer> result;

	public Map<String, Integer> getResult() {
		return result;
	}

	public MergeManager(BlockingQueue<Future<Map<String, Integer>>> queue, ExecutorService executorService) {
		this.queue = queue;
		this.executorService = executorService;
	}

	@Override
	public void run() {
		try {
			while (!(queue.isEmpty() && isDone)) {
				if (queue.size() == 1 && isDone) {
					result = queue.poll().get();
					break;
				}
				Map<String, Integer> map1 = queue.take().get();
				Map<String, Integer> map2 = queue.take().get();
				Future<Map<String, Integer>> mergeResult = executorService.submit(new MergeTask(map1, map2));

				queue.put(mergeResult);
			}
			
			Map<String, Integer> sortedByCount = result.entrySet()
	                .stream()
	                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

			
			for (Entry<String, Integer> entry : sortedByCount.entrySet()) {
				System.out.println(entry.getKey() + " ->" + entry.getValue());
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally

		{
			executorService.shutdown();
		}

	}

}
