package com.yog.WFDistributed;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class FrequencyCountTask implements Callable<Map <String,Integer>>{
	String[] batch;
	public FrequencyCountTask(String[] batch) {
		this.batch = batch;
	}
	
	@Override
	public Map<String, Integer> call() throws Exception {
		Map<String, Integer> map = new HashMap<>();
		for (String word : batch) {
			if(map.containsKey(word)) {
				map.put(word, map.get(word)+1);
			}else {
				map.put(word, 1);
			}
		}
		return map;
	}
}
