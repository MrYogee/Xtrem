package com.yo.dp;

public class LongeshPalindrom {

	public static void main(String[] args) {


		String result = palindrom("abhinajaoqwertyuiopoiuytrewq");
		System.out.println(result);
	
	

	}

	private static String palindrom(String s) {
		
		int n = s.length();
		boolean table[][] = new boolean[n][n];
		
		//for 1;
		for(int i=0;i<n;i++) {
//			System.out.println(i);
			table[i][i] = true;
		}
		
		for (int i=0;i<n-1;i++) {
			if(s.charAt(i) == s.charAt(i+1)) {
				table[i][i+1] = true;
			}
		}
		
		int k =3;
		int maxLength = 2;  int start = 0;
		 for (k=3;k<=n;k++) {
			 
			 for (int i = 0; i < n - k + 1; ++i)  
	            { 
				// Get the ending index of substring from 
	                // starting index i and length k 
	                int j = i + k - 1; 
	  
	                
	             // checking for sub-string from ith index to 
	                // jth index iff str.charAt(i+1) to  
	                // str.charAt(j-1) is a palindrome 
	                if (table[i + 1][j - 1] && s.charAt(i) ==  
	                                          s.charAt(j)) { 
	                    table[i][j] = true; 
	  
	                    if (k > maxLength) { 
	                        start = i;
	                        maxLength = k; 
	                    } 
	            }
		 }
			 
		 }
		 return s.substring(start, start + maxLength );

		
		
	}

}
