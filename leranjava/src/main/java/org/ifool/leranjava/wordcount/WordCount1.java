package org.ifool.leranjava.wordcount;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class WordCount1 {

	public static String largeInput() {
		StringBuilder sb = new StringBuilder(5000000);
		String[] dict = {"hello ","world ","china ","apple ","windows "};
		Random rand = new Random();
		for(int i = 0; i < 1000000; i++) {
			int index = rand.nextInt(5);
			sb.append(dict[index]);
		}
		sb.append("test");
		return sb.toString();
	}
	public static void main(String[] args) {
		String input = "hello world hello china computer apple mac windows man woman";
		String largeInput = largeInput();
		
		long start = System.currentTimeMillis();
		System.out.println(wordcount(largeInput));
		long end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
		
		start = System.currentTimeMillis();
		System.out.println(wordcount_opt1(largeInput));
		end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
		
		start = System.currentTimeMillis();
		System.out.println(wordcount_opt2(largeInput));
		end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
		
		start = System.currentTimeMillis();
		printWordCount(largeInput);
		end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
		
		start = System.currentTimeMillis();
		printWordCount_opt1(largeInput);
		end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
		
		start = System.currentTimeMillis();
		printWordCount_opt2(largeInput);
		end = System.currentTimeMillis();
		System.out.println((end-start)+"ms");
	}
	
	public static int wordcount(String input) {
		Set<String> wordSet = new HashSet<String>();
		if(input == null || input.length() == 0) {
			return 0;
		}
		String[] words = input.split(" ");
		for(String word : words) {
			wordSet.add(word);
		}
		return wordSet.size();
		
	}
	public static int wordcount_opt1(String input) {
		Set<String> wordSet = new HashSet<String>();
		if(input == null || input.length() == 0) {
			return 0;
		}
		String[] words = input.split(" ");
		for(String word : words) {
			if(!wordSet.contains(word)) {
				wordSet.add(word);
			}
		}
		return wordSet.size();
	}
	public static int wordcount_opt2(String input) {
		Set<String> wordSet = new HashSet<String>();
		if(input == null || input.length() == 0) {
			return 0;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {			
			if(input.charAt(i) != ' ') {
				sb.append(input.charAt(i));
			} else if(input.charAt(i) == ' ' && sb.length() != 0){
				String word = sb.toString();
				if(!wordSet.contains(word)) {
					wordSet.add(word);
				}
				sb.setLength(0);					
				while(i+1 < input.length() && input.charAt(i+1) == ' ') {
						i++;
				}			
			}
		}	
		if(sb.length() != 0) {
			wordSet.add(sb.toString());
		}
		return wordSet.size();
	}
	
	public static void printWordCount(String input) {
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		if(input == null || input.length() == 0) {
			return ;
		}
		String[] words = input.split(" ");
		for(String word : words) {
			Integer count = countMap.get(word);
			if(count == null) {
				countMap.put(word, 1);
			} else {
				countMap.put(word, count+1);
			}
		}
		for(String word: countMap.keySet()) {
			System.out.println(word + ":" + countMap.get(word));
		}
	}
	
	public static void printWordCount_opt1(String input) {
		Map<String,Integer> countMap = new HashMap<String,Integer>();
		if(input == null || input.length() == 0) {
			return ;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {			
			if(input.charAt(i) != ' ') {
				sb.append(input.charAt(i));
			} else if(input.charAt(i) == ' ' && sb.length() != 0){
				String word = sb.toString();
				Integer count = countMap.get(word);
				if(count == null) {
					countMap.put(word, 1);
				} else {
					countMap.put(word, count+1);
				}
				sb.setLength(0);					
				while(i+1 < input.length() && input.charAt(i+1) == ' ') {
						i++;
				}			
			}
		}	
		if(sb.length() != 0) {
			String word = sb.toString();
			Integer count = countMap.get(word);
			if(count == null) {
				countMap.put(word, 1);
			} else {
				countMap.put(word, count+1);
			}
		}
		for(String word: countMap.keySet()) {
			System.out.println(word + ":" + countMap.get(word));
		}
	}
	
	public static void printWordCount_opt2(String input) {
		Map<String,BoxedInteger> countMap = new HashMap<String,BoxedInteger>();
		if(input == null || input.length() == 0) {
			return ;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {			
			if(input.charAt(i) != ' ') {
				sb.append(input.charAt(i));
			} else if(input.charAt(i) == ' ' && sb.length() != 0){
				String word = sb.toString();
				BoxedInteger count = countMap.get(word);
				if(count == null) {
					count = new BoxedInteger();
					countMap.put(word, count);
				} else {
					count.value++;
				}
				sb.setLength(0);					
				while(i+1 < input.length() && input.charAt(i+1) == ' ') {
						i++;
				}			
			}
		}	
		if(sb.length() != 0) {
			String word = sb.toString();
			BoxedInteger count = countMap.get(word);
			if(count == null) {
				count = new BoxedInteger();
				countMap.put(word, count);
			} else {
				count.value++;
			}
		}
		for(String word: countMap.keySet()) {
			System.out.println(word + ":" + countMap.get(word).value);
		}
	}
}
class BoxedInteger {
	public int value;
	public BoxedInteger() {
		this.value = 1;
	}
}
