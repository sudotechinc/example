package tomography;

import java.util.Scanner;
import java.util.Arrays;

public class Tomography {

	public static void main(String[] args) {
		int rowSize, colSize = 0;
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Input rows (m): ");
		int row = input.nextInt();
		rowSize = row;
		int[] ri = new int[rowSize];
		
		System.out.print("Input columns (n): ");
		int col = input.nextInt();
		colSize = col;
		int[] cj = new int[colSize];
		
		for(int m = 0; m < row; m++) {
			System.out.print("Enter row total: ");
			int rowTotal = input.nextInt();
			
			// store in data buffer
			Arrays.fill(ri, m, rowSize, rowTotal);
		}
		
		for(int n = 0; n < col; n++) {
			System.out.print("Enter column total: ");
			int colTotal = input.nextInt();
			
			// store in data buffer
			Arrays.fill(cj, n, colSize, colTotal);
		}
		
		System.out.println("Input:");
		System.out.print(row);
		System.out.println(", " + col);
		System.out.println(Arrays.toString(ri));
		System.out.println(Arrays.toString(cj));
	}
	
	public static boolean tomography(int[] ri, int[] cj) {
		
		boolean result = false;
		
		while(!(ri.length == 0)) {
			
			Arrays.sort(ri);
			//int k = 
		}
		
		
		return result;
		
		/* Convert into java
		 * while (!a.empty()) {
    		std::sort(b.begin(), b.end(), std::greater<int>());
    		int k = a.back();
    		a.pop_back();
    		if (k > b.size()) return false;
    		if (k == 0) continue;
    		if (b[k - 1] == 0) return false;
    		for (int i = 0; i < k; i++)
      		b[i]--;
  		}
  		for (std::vector<int>::iterator i = b.begin(); i != b.end(); i++)
    		if (*i != 0)
      			return false;
  		return true;
		 */
	}

}
