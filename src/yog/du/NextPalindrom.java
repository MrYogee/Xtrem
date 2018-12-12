package yog.du;

import java.util.Arrays;
import java.util.stream.Stream;

public class NextPalindrom {

	public static void main(String[] args) {
		String numberStr = "1";
		
		findNextPalindrom(numberStr);

	}

	private static void findNextPalindrom(String numberStr) {

		final int[] ints1 = numberStr.chars()
		    .map(x -> x - '0')
		    .toArray();

		System.out.println(Arrays.toString(ints1));

		final int[] charArray = Stream.of(numberStr.split(""))
		    .mapToInt(Integer::parseInt)
		    .toArray();
		
		int mid = charArray.length/2;
		boolean isOddLength = charArray.length%2 ==0 ? false:true;
		System.out.println("is Odd Length = "+ isOddLength);

		System.out.println("mid is = "+ mid);
		int left = mid;
		int right = isOddLength ? left+2:left+1;
//		left < 2 ,right <numberStr.lenght() ,left--,right++
		for( ; left>=0 ||right<=numberStr.length(); left--,right++) {
			 if(charArray[left] == charArray[right]) {
				 continue;
			 }
			 if(charArray[left] > charArray[right]) {
				 
			 }
		}

		
	}

}
