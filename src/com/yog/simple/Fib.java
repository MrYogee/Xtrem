package com.yog.simple;

public class Fib {

	public static void main(String[] args) {
	int result = fib(15);
System.out.println(result);
	}

	private static int fib(int i) {
	
		if (i==1 || i==2) {
			return 1;
		}
		return fib(i-1)+fib(i-2);
	}

}
