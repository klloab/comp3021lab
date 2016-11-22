package lab11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumbersTraditional {
	
	public static List<Integer> isOdd(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		for (int n : numbers) {
			if (n % 2 != 0) results.add(n);
		}
		return results;
	}
	
	public static List<Integer> isPrime(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();

		for (int n : numbers) {
            boolean prime = true;
            
		    //check if n is a multiple of 2
		    if (n%2==0) {
		    	prime = false;
		    }
		    //if not, then just check the odds
		    for(int i=3;i*i<=n;i+=2) {
		        if (n%i==0) {
			    	prime = false;
		        	continue;
		        }
		    }
		    if (prime) {
			    results.add(n);
		    }
		}
		return results;
		
	}
	
	public static List<Integer> isPalindrome(List<Integer> numbers) {
		List<Integer> results = new ArrayList<Integer>();
		// TODO
		// Find out all the palindrome numbers, such as 484 and 121.

		for (int n : numbers) {
			
			int num = n;
            int rev = 0;
            while (num > 0)
            {
                 int dig = num % 10;
                 rev = rev * 10 + dig;
                 num = num / 10;
            }

		    if (n == rev) {
			    results.add(n);
		    }
		}
		  
		return results;
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(480,514,484,389,709,935,328,169,649,300,685,429,243,532,308,87,25,282,91,415);
		
		System.out.println("The odd numbers are : " + isOdd(numbers));
		System.out.println("The prime numbers are : " + isPrime(numbers));
		System.out.println("The palindrome numbers are : " + isPalindrome(numbers));
		
	}
}
