package com.cap.t;

public class OEPrinter {

	public static void main(String[] args) throws InterruptedException {
		OEPrinter oePrinter = new OEPrinter();
		oePrinter.prinOE();
	}

	private void prinOE() throws InterruptedException {
		Printer printer = new Printer();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				printer.printEven();
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				printer.printOdd();

			}
		});
		t2.start();
		t1.start();
		t1.join();
		t2.join();
	}

	public class TaskEvenOdd implements Runnable {

		boolean isEven;

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}

	}

	class Printer {
		boolean isOdd = false;
		int i = 0;

		public Printer() {
			// TODO Auto-generated constructor stub
		}

		public void printOdd() {

			while (true) {
				synchronized (this) {
					while (!isOdd) {
						try {
							this.wait();
							Thread.sleep(1000);
						}
						 catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(i);
						isOdd = false;
						i++;
						notifyAll();
					}
			}
			}
		}

		public void printEven() {

			synchronized (this) {
				while (true) {
					while (isOdd) {
						try {
							this.wait();
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					System.out.println(i);
					isOdd = true;
					i++;
					notifyAll();
				}
			}

		}

	}

}
