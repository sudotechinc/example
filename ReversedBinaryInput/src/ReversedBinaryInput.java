//import java.util.Scanner;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

class ReversedBinaryNumber {
	
	public static String reverseIntToBinary(int num){
		int number = num;
		StringBuilder bldr = new StringBuilder();
		
		while (number != 0) {
			int remainder = number % 2;
			number /= 2;
			bldr.append(remainder);
		}
		return bldr.toString();
	}
	
	public static int toDecimal(String bin) {
		char[] binString = bin.toCharArray();
		
		int starting = 0;
		
		for (int i = 0; i < binString.length; i++) {
			int tempoNum = starting * 2 + Character.getNumericValue(binString[i]);
			starting = tempoNum;
		}
		return starting;
	}
	
	public static int reversedBinary(int num) {
		String bin = reverseIntToBinary(num);
		int result = toDecimal(bin);
		return result;
	}
	
    public static void main(String[] args) {
		
		int number1 = 13;
		int number2 = 47;
		
		//Scanner input = new Scanner(System.in);
		//System.out.print("Enter an integer (0 is exit): ");
		
		//File input1 = new File("C:\\Users\\Evan\\Downloads\\reversebinary-sample-data\\binary-sample-1.in");
		//File input2 = new File("C:\\Users\\Evan\\Downloads\\reversebinary-sample-data\\binary-sample-2.in");
		
		reversedBinary(number1);
		
		System.out.println("Reversal of integer " + number1 + " is: " + reversedBinary(number1));
		
		/*
		while((number = input.nextInt()) != 0) {
			
			System.out.println("Reversal of integer " + number + " is: " + reversedBinary(number));
			
			System.out.print("Enter a decimal number (0 is exit): ");
		}*/	
    }
}
