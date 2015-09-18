import java.util.Scanner;
import java.util.Arrays;

public class PizzaDelivery {

	public static void main(String[] args) {
		
		int width = 20; 
		int height = 20;
		
		int[][] deliveries = new int[width][height];
		
		Scanner input = new Scanner(System.in);
		
		System.out.print("Input # of test cases (n): ");
		int testCases = input.nextInt();
		
		for(int n = 0; n < testCases; n++) {
			
			System.out.print("Input # of blocks (x): ");
			width = input.nextInt();
			
			System.out.print("Input # of blocks (y): ");
			height = input.nextInt();
			
			for(int x = 0; x < width; x++) {
				for(int y = 0; y < height; y++) {
					System.out.println("Input deliveries to each crossing: ");
					deliveries[x][y] = input.nextInt();
				}
			}
		}
		
		System.out.println("Input: ");
		System.out.println(Arrays.toString(deliveries));
	}

}
