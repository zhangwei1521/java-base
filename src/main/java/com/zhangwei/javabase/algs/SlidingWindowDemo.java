package com.zhangwei.javabase.algs;

import java.util.HashSet;
import java.util.Set;

/**
 * 滑动窗口算法
 */
public class SlidingWindowDemo {
    public static void main(String[] args) {
        testLengthOfLongestSubstring();
        testMaxSubArraySum();
    }

    private static void testLengthOfLongestSubstring(){
        String str = "abcbad";
        System.out.println(lengthOfLongestSubstring(str));
    }

    private static void testMaxSubArraySum(){
        int[] array = new int[]{1, 4, 2, 10, 2, 3, 1, 0, 20};
        int k = 3;
        System.out.println(maxSubArraySum(array,k));
    }

    //返回连续k个元素构成的子数组的最大和
    public static int maxSubArraySum(int[] array,int k){
        if(k > array.length){
            return -1;
        }
        int l = 0;
        int r = 0;
        int maxSum = 0;
        while (r < k){
            maxSum += array[r++];
        }

        int temp = maxSum;
        while (r < array.length){
            temp = temp-array[l++]+array[r++];
            if(temp > maxSum){
                maxSum = temp;
            }
        }
        return maxSum;
    }

    //返回无重复字符最长子串的长度
    public static int lengthOfLongestSubstring(String s) {
        //使用HashSet作为滑动窗口,找出无重复字符的最长子串。
        Set<Character> set=new HashSet<>();
        //i为滑动窗口的左边，j为右边
        int ans=0,i=0,j=0;
        while(i<s.length()&&j<s.length()){
            if(!set.contains(s.charAt(j))){
                //如果没有重复
                set.add(s.charAt(j++));
                ans=Math.max(ans,j-i);
            }
            else{
                //如果出现重复
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
}
