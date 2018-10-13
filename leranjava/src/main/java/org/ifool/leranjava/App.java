package org.ifool.leranjava;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        String line = "jjjjj aaa bbb ccc ccc ddd aaa";
        
        Stream stream = Stream.of(line.split(" "));
        
        Map<String,List<String>> words = (Map<String, List<String>>) stream.collect(Collectors.groupingBy(String::toString));
        for(String key : words.keySet()) {
        	System.out.println(key + ":" + words.get(key).size());
        }
        
        
    }
}
