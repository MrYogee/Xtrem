package com.yog.WFDistributed;

import java.beans.FeatureDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

public class WordFrequency {
	public final static int BATCH_SIZE = 30;
	public final static int PROCESSOR_NUMBER = 30;
	private static ExecutorService executorService;
	private static BlockingQueue<Future<Map<String, Integer>>> queue = new LinkedBlockingQueue<>();
	private static MergeManager mergeManager;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startExecutor();
		startMergeJob();
		try {
			readfile();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

	



	private static void readfile() throws InterruptedException {
		Scanner sc1 = new Scanner("simple.txt");
		Scanner sc;
		String[] batch = null;
		try {
			sc = new Scanner(new FileInputStream("simple.txt"));
			int currentSize = 0;

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.isEmpty()) {
					continue;
				}
				// System.out.println(line);
				String[] splitArray = line.split("\\W+");
				for (String word : splitArray) {
					if (currentSize == 0) {
						batch = new String[BATCH_SIZE];
					}
					batch[currentSize++] = word;

					if (currentSize == BATCH_SIZE) {
						processBatch(batch);
						currentSize = 0;
					}
				}
//				processLine(line);

			}
			if (currentSize > 0) {
				processBatch(batch);
			}
			mergeManager.setDone(true);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void processBatch(String[] batch) throws InterruptedException {
		 
		//System.out.println("BATCH STARTED");
		//System.out.println(Arrays.toString(batch));
		Future<Map<String, Integer>> feature = executorService.submit(new FrequencyCountTask(batch));
		queue.put(feature);
		//System.out.println("BATCH ENDED");
		/*try {
		//	Map<String, Integer> map = feature.get();
			//System.out.println(map.keySet());
			//System.out.println(map.values());

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

	}
	private static void startExecutor() {
		  executorService = Executors.newFixedThreadPool(PROCESSOR_NUMBER);
	}
	
	private static void startMergeJob() {
		mergeManager = new MergeManager(queue,executorService);
		Thread m =  new Thread(mergeManager);
		m.start();
		/*Future<Map<String, Integer>> resultFuturer = executorService.submit(new MergeManager(queue,executorService));
		try {
			Map<String, Integer> result = resultFuturer.get();
			for (Entry<String, Integer> entry : result.entrySet()) {
				System.out.println(entry.getKey() +" ->"+entry.getValue());
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		}*/
		
	}
}
