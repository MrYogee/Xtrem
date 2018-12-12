package com.yog.WFDistributed;

import java.util.Map;
import java.util.concurrent.Callable;

public class MergeTask implements Callable<Map<String, Integer>> {
	Map<String, Integer> map1;
	Map<String, Integer> map2;

	public MergeTask(Map<String, Integer> map1, Map<String, Integer> map2) {
		this.map1 = map1;
		this.map2 = map2;
	}

	@Override
	public Map<String, Integer> call() throws Exception {

		Map<String, Integer> smallMap;
		Map<String, Integer> largeMap;
		if (map1.keySet().size() < map2.keySet().size()) {
			smallMap = map1;
			largeMap = map2;
		} else {
			largeMap = map1;
			smallMap = map2;
		}

		for (String word : smallMap.keySet()) {
			Integer frequency = smallMap.get(word);
			if (largeMap.containsKey(word)) {
				largeMap.put(word, largeMap.get(word) + frequency);
			} else {
				largeMap.put(word, frequency);
			}

		}
		return largeMap;
	}

}
