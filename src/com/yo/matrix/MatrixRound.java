package com.yo.matrix;

public class MatrixRound {

	public static void main(String[] args) {
		int row = 4;
		int coloumn = 4;
		int[][] mat = new int[row][coloumn];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < coloumn; j++) {
				mat[i][j] = (i * coloumn) + j;
			}
		}
		
		
		// Print M*N
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < coloumn; j++) {
				System.out.print("	" + mat[i][j]);
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("");

		
		
		for (int startRow = 0, startColoumn = 0; startRow <= row && startColoumn <= coloumn;) {
			
			
			for (int i = startRow, j = startColoumn; i >= 0 && j <= coloumn - 1; i--, j++) {
				System.out.print("	" + mat[i][j]);
			}
			System.out.println("");
			if (startRow < row - 1) {
				startRow++;
			} else {
				startColoumn++;
			}

		}
	}
}
