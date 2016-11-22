package lab11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import sun.net.www.content.audio.x_aiff;

public class NumbersLambda {
	
	// Find numbers with certain properties in a unified form
	// The property is specified in Predicate
	
	public static List<Integer> findNumbers(List<Integer> list, Predicate<Integer> predicate) {
		List<Integer> results = new ArrayList<Integer>();
		for (int n : list) {
			if (predicate.test(n)) results.add(n);
		}
		return results;
	}
	
	public static List<Integer> calculateScore(List<Integer> list, Function<Integer, Integer> function) {
		
		List<Integer> results = new ArrayList<Integer>();
		
		for (int n : list) {
			results.add(function.apply(n));
		}
		
		return results;
	}
	
	public static Function<Integer, Integer> policy() {
		return x -> (isOdd().test(x)? 1: 0) + (isPrime().test(x)? 2: 0) + (isPalindrome().test(x)? 4: 0);
	}
	
	public static Predicate<Integer> isOdd() {
		return x -> x % 2 != 0;
	}
	
	public static Predicate<Integer> isPrime() {
		return x -> { 
		    if (x%2==0) {
		    	return false;
		    }
		    for(int i=3;i*i<=x;i+=2) {
		        if (x%i==0) {
		        	return false;
		        }
		    }
		    return true;
		};
	}
	
	public static Predicate<Integer> isPalindrome() {
		return x -> { 
			int num = x;
			int rev = 0;
			while (num > 0) {
				int dig = num % 10;
				rev = rev * 10 + dig;
				num = num / 10;
			}
			return x == rev;
		};
	}
	
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(480,514,484,389,709,935,328,169,649,300,685,429,243,532,308,87,25,282,91,415);
		
		System.out.println("The odd numbers are : " + findNumbers(numbers,isOdd()));
		System.out.println("The prime numbers are : " + findNumbers(numbers,isPrime()));
		System.out.println("The palindrome numbers are : " + findNumbers(numbers,isPalindrome()));

		System.out.println("The score of the all numbers are :" + calculateScore(numbers, policy()));
	}
}
