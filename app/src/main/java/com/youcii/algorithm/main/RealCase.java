package com.youcii.algorithm.main;

/**
 * 面试中遇到的真实题目
 */
public class RealCase {

    public static void main(String[] args) {
        System.out.print("\n是否包含重复字符: " + containRepeatChars("as啊dfg"));
    }

    /**
     * 字符串内是否存在重复字符
     * 注:
     * 1. ASCII码最大值为127, 0-9对应48-57, A-Z对应65-90, a-z对应97-122
     * 2. UTF-8等全球字符集的最大支持数目依赖于字体库的版本
     *
     * @param string 用ASCII码表示的字符串
     */
    private static boolean containRepeatChars(String string) {
        if (string == null || string.length() < 2) {
            return false;
        }
        char[] chars = string.toCharArray();
        short[] cache = new short[128];
        for (char c : chars) {
            if (c >= chars.length) {
                throw new IllegalArgumentException("不是ASCII码字符串");
            }

            if (cache[c] == 0) {
                cache[c]++;
            } else {
                return true;
            }
        }
        return false;
    }

}
