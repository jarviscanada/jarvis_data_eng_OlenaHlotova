package ca.jrvs.practice.codingChallenge;

import java.util.Arrays;

public class ValidAnagram {

  public boolean isAnagram (String s, String t) {
    if(s==null || t==null)
      return false;
    if(s.length()!=t.length())
      return false;

    int[] arr = new int[26];
    for(int i=0; i<s.length(); i++){
      arr[s.charAt(i)-'a']++;
      arr[t.charAt(i)-'a']--;
    }

    for(int i: arr){
      if(i!=0)
        return false;
    }

    return true;
  }

  public boolean isAnagramSort(String s, String t) {
    if(s==null || t==null)
      return false;
    if(s.length()!=t.length())
      return false;
    char[] sortedS = s.toCharArray();
    Arrays.sort(sortedS);
    String sS = new String(sortedS);
    char[] sortedT = t.toCharArray();
    Arrays.sort(sortedT);
    String sT = new String(sortedT);
    if (!sS.equals(sT)){
      return false;
    }
    return true;

  }

}
