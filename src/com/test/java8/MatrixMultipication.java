package com.test.java8;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

public class MatrixMultipication {
	public static <T, R> void main(String[] args) {
		
		 int m =2;
		int n =3;
		 int p = 3;
		int q =2;
		int [][] E = new int[m][n];
		int [][] F = new int[p][q];
		
		String [][] C  = { {"John", "Smith"}, {"Javin", "Paul"}, {"James", "Gosling"}, };

		int [][] A  = {{1,2}, {3,4} , {5,6}, };
		int [][] B  = {{7,8,9}, {10,11,1} };
		
		/*Function mapper = new Function<T, R>() {

			@Override
			public double[] apply(double[] t) {
				 
		       double[] re = IntStream.range(0, B[0].length).mapToDouble(i -> 
		            IntStream.range(0, B.length).mapToDouble(j -> t[j] * B[j][i]).sum()
		    ).toArray();
		       return re;
			}
		};
		Arrays.stream(A).map(mapper).toArray(int[][]::new);*/
		

		
		
		/*double[][] result = Arrays.stream(m1).map(r -> 
        IntStream.range(0, m2[0].length).mapToDouble(i -> 
            IntStream.range(0, m2.length).mapToDouble(j -> r[j] * m2[j][i]).sum()
    ).toArray()).toArray(double[][]::new);*/
//		
		/*Arrays.stream(A).map(row -> 
		IntStream.range(0, 2).mapToDouble(
					inner ->IntStream.range(0, B.length).
						mapToDouble(k -> row[row]* m2[k][inner]).sum()).toArray())toArray(double[][]::new); 
		long r = Arrays.stream(A).count();
		System.out.println(Arrays.deepToString(A));
		System.out.println(""+r);
		*/
		
		
		
	}

}
