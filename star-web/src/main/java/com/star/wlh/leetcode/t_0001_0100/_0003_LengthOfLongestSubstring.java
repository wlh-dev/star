package com.star.wlh.leetcode.t_0001_0100;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class _0003_LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "asdgladfjkmhsfg";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }
    public static int lengthOfLongestSubstring(String s){
        if (s.length() == 0){
            return 0;
        }
        int maxLength = 1;
        int startIndex = 1;
        for (int i = 0; i < s.length(); i++) {
           int index=  s.indexOf(s.charAt(i),i-startIndex);
           if (index<i){
               startIndex = i-index;
           }else {
               startIndex++;
           }
            maxLength = Math.max(maxLength,startIndex);
            System.out.println(index);
        }

        return maxLength;

    }
}
