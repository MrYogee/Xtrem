package com.yog.simple;

public class Factorial {
	public static void main(String[] args) {
		int result = fact(4);
	System.out.println(result);
		}

		private static int fact(int i) {
		if(i==0)
			return 1;
		return i*fact(i-1);
	}


}
