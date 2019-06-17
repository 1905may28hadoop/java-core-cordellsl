package com.revature.eval.java.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EvaluationService {

	/**
	 * 1. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		String ac = ""; // create a string that will become answer
		String[] splited = phrase.split("[ -]"); // create array - split string based on symbols in brackets - both " " and "-"
		for(int i=0; i<splited.length; i++) {     // must loop through new array
			ac += splited[i].substring(0,1).toUpperCase(); // add first letter to answer substringing and making uppercase
		}
		return ac;
	}

	/**
	 * 2. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
        Map<String, Integer> scrabbleKey = new HashMap<String, Integer>()
        		{{
        			put("a",1);
        			put("e",1);
        			put("i",1);
        			put("o",1);
        			put("u",1);
        			put("l",1);
        			put("n",1);
        			put("r",1);
        			put("s",1);
        			put("t",1);
        			put("d",2);
        			put("g",2);
        			put("b",3);
        			put("c",3);
        			put("m",3);
        			put("p",3);
        			put("f",4);
        			put("h",4);
        			put("v",4);
        			put("w",4);
        			put("y",4);
        			put("k",5);
        			put("j",8);
        			put("x",8);
        			put("q",10);
        			put("z",10);
        		}};
        int points = 0; 
        for(int i=0; i<string.length(); i++) {
            for(Map.Entry<String, Integer> entry : scrabbleKey.entrySet()) {
            	if(string.substring(i, i+1).toLowerCase().equals(entry.getKey())) {
            		points += entry.getValue();
            	}
            }
        } 
		return points;
	}

	/**
	 * 3. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		String pnum = ""; 
		for(int i=0; i<string.length(); i++) {
			if(string.substring(i, i+1).matches("0|1|2|3|4|5|6|7|8|9")) {
				pnum += string.substring(i, i+1); 
			}
		}
		if(pnum.length() != 10) {
			throw new IllegalArgumentException(); 
		}
		
		// Check (NXX)NXXXXXX
		return pnum;
	}

	/**
	 * 4. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		Map<String, Integer> finalCount = new HashMap<>(); // map that will be return statement 
		String[] divided = string.replaceAll("[^A-Za-z]", " ").split("\\s+"); // Replace non-letter with a space, then split by all the whitespace
		for(String i : divided) {
			finalCount.put(i, 0); // Populating map with all words beginning with a count of 0 
		}
			// iterating through words and map, incrementing key's values everything the key appears
		for(String i : divided) { 
			for(Map.Entry<String, Integer> entry : finalCount.entrySet()) {
				if(i.equals(entry.getKey())) { finalCount.put(i, entry.getValue()+1); }
			}
		}
		return finalCount;
	}

	/**
	 * 5. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T> { // 
		private List<T> sortedList; // Private List<T> that can only change by getter/setter 
		//public static <T extends Comparable<? super T>> void sort(List<T> list) {} 

		public int indexOf(T t) { // We don't know what type the List will be full of 
			// Divide list length by 2 (round) for first comparison
			// Collections.sort(sortedList);
			
			//System.out.println("Searching for.. " + t + " ..in List " + sortedList);
			
			int indexToCompare = sortedList.size()/2; // 'middle' index of List, first spot to compare 't' to
			int index = 0; // This will be the final answer to return 
			//System.out.println(indexToCompare);
			
			// compare t to index 'while' index is reasonable
			// fist line is if statement to throw exception/return null if index gets too small 
			
//			while(indexToCompare >= 0) {
//			}
			
			
			
			return index;
		}

		public BinarySearch(List<T> sortedList) { // Simple constructor 
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() { // Getter for the List<T>
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) { // Setter for the List<T>
			this.sortedList = sortedList;
		}

	}

	/**
	 * 6. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		int numOfDigits = String.valueOf(input).length(); // finds how many digits are in the int input 
		double[] toSum = new double[numOfDigits]; // create array of doubles that will be filled by powers 
		for(int i=0; i<numOfDigits; i++) { // for loop for how many digits there are 
			double x = Integer.parseInt(Integer.toString(input).substring(i,i+1)); // gets single digit from the int input 
			toSum[i] = Math.pow(x, numOfDigits); // raises the single int to power of digits and puts it in array
		}
		double sum = 0; // sum that will be final check 
		for(int i=0; i<toSum.length; i++) { // loop through array
			sum += toSum[i]; // sum up elements of array 
		}
		if(sum == input) { // if final sum equals original int input -> pass 
			return true;
		} else {
			return false; // or else fail 
		}
	}

	/**
	 * 7. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	public List<Long> calculatePrimeFactorsOf(long l) {
		
		long toFactor = l; 
		List<Long> factors = new ArrayList<>(); // create List that will be answer
		for(long i=2; i<=toFactor; i++) { // Start at loop at 2 because 0 & 1 cannot be factors, <= to make sure actual value is used as well
			while(toFactor%i == 0) {// check that it is an even factor
				factors.add(i); // add it to List 
				toFactor /= i; // replace number with the divided version to continue dividing 
			}
		}
		return factors;
	}


	/**
	 * 8-9. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 8
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			String toCode = string;
			String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
			String[] letters = toCode.toLowerCase().replaceAll("[^a-z\\d]", "").split("");
			String[] encoded = new String[letters.length];
			for(int i=0; i<letters.length; i++) {
				for(int j=0; j<alphabet.length; j++) {
					if(letters[i].equals(alphabet[j])) { encoded[i] = alphabet[25-j]; }
				}
				if( letters[i].matches("[0-9]") ) { encoded[i] = letters[i]; }
			}
			String code = ""; 
			for(int i=0;i <encoded.length; i++) {
				if( i%5 == 0 && i != 0) { code += " "; }
				code += encoded[i];
			}
			if(code.charAt(code.length()-1) == ' ') {
				code = code.substring(0, code.length()-2); 
			}
			return code;
		}

		/**
		 * Question 9
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			String toDecode = string; 
			String[] alphabet = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
			String[] letters = toDecode.replaceAll("[^a-z\\d]", "").split("");
			String[] decoded = new String[letters.length];
			for(int i=0; i<letters.length; i++) {
				for(int j=0; j<alphabet.length; j++) {
					if(letters[i].equals(alphabet[j])) { decoded[i] = alphabet[25-j]; } 
				}
				if( letters[i].matches("[0-9]") ) { decoded[i] = letters[i]; }
			}
			String ans = ""; 
			for(int i=0; i<decoded.length; i++) { ans += decoded[i]; }
			return ans;
		}
	}

	/**
	 * 10. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		String[] sentence = string.replaceAll("[^A-Za-z-\\d]", " ").split(" "); // string to array
		int num1 = Integer.parseInt(sentence[2]); // first number always 3 word in sentence 
		int num2 = Integer.parseInt(sentence[sentence.length-1]); // second number always last word in sentence 
		switch(sentence[3]) {
		case "plus":
			return num1+num2;
		case "minus":
			return num1-num2;
		case "multiplied":
			return num1*num2;
		case "divided":
			return num1/num2;
		default:
			return 0;
		}
	}

}
