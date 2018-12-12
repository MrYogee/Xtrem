package com.cap.t;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OddEven {

	private static final int MAX = 20;
	static Thread t1;
	static Thread t2;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		OddEven oe = new OddEven();
		ExecutorService executorService = Executors.newCachedThreadPool();
		 t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				oe.printEven();
			}

		});

		 t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				oe.pringOdd();
			}

		});
		t1.start();
		t2.start();
		//t1.join();

	}

	private void pringOdd() {
			System.out.println("before SYSC T1-"+Thread.currentThread().holdsLock(this));
			synchronized (this) {
				try {
					for (int i = 1; i < MAX; i= i+2) {

//					System.out.println("Inside SYNC T1-"+Thread.currentThread().holdsLock(this));
					System.out.println(i);
					Thread.sleep(3000);
					notifyAll();
//					System.out.println("BEFORE WAIT T1-"+Thread.currentThread().holdsLock(this));
					wait();
//					System.out.println("AFTER WAIT T1-"+Thread.currentThread().holdsLock(this));

				} }catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("AFTER SYNC T1-"+Thread.currentThread().holdsLock(this));

			//System.out.println("Out of SYNC Odd");

	}

	private void printEven() {
			System.out.println("before SYSC T2-"+Thread.currentThread().holdsLock(this));
			synchronized (this) {
				try {
					for (int i = 0; i < MAX; i=i+2) {

//					System.out.println("Inside SYNC T2-"+Thread.currentThread().holdsLock(this));
					wait();
					System.out.println("e-"+i);
					Thread.sleep(3000);
					this.notifyAll();
//					System.out.println("BEFORE WAIT T2-"+Thread.currentThread().holdsLock(this));
//					System.out.println("AFTER WAIT T2-"+Thread.currentThread().holdsLock(this));

				} }catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//System.out.println("Out of SYNC Even");
	}

}
