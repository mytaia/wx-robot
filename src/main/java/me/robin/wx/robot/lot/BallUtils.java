/**create 2017-05-15**/

package me.robin.wx.robot.lot;

import java.util.List;
import java.util.Stack;

import com.google.common.collect.Lists;

/**
 * FIXME 类注释信息(此标记自动生成,注释填写完成后请删除)
 * 
 * <pre>
 * [
 * 调用关系:
 * 实现接口及父类:
 * 子类:
 * 内部类列表:
 * ]
 * </pre>
 * 
 * @author 作者
 * @since 1.0
 * @version 2017年5月11日 作者
 */
public class BallUtils {
    
    /**
     * 两个球号码是否相同
     * 
     * @param b1num x
     * @param b2num x
     * @return x
     */
    public static boolean ballEquals(String b1num, String b2num) {
        return Integer.parseInt(b1num) == Integer.parseInt(b2num);
    }
    
    /**
     * xx
     * 
     * @param game x
     * @param key x
     * @return x
     */
    public static String playedId(String game, String key) {
        return String.format("%s:%s", game, key);
    }
    
    /**
     * xx
     * 
     * @param num x
     * @param length x
     * @return x
     */
    public static String ballNum(int num, int length) {
        return String.format("%0" + length + "d", num);
    }
    
    /**
     * 两个球号码是否相同
     * 
     * @param index x
     * @return x
     */
    public static String ballIndexString(int index) {
        return "第" + index + "球";
    }
    
    /**
     * 复选
     *
     * @param arr 待选数组
     * @param count 选的个数
     * @return x
     */
    public static List<String[]> comboSelect(String[] arr, int count) {
        List<String[]> list = Lists.newArrayList();
        Stack<String> nums = new Stack<>();
        comboSelect(arr, nums, 0, count, list);
        return list;
    }
    
    /**
     * FIXME 方法注释信息(此标记由Eclipse自动生成,请填写注释信息删除此标记)
     *
     * @param arr 待选数组
     * @param nums 已经选的序号
     * @param start 选数开始的索引
     * @param count 还有几个数
     * @param list x
     */
    private static void comboSelect(String[] arr, Stack<String> nums, int start, int count, List<String[]> list) {
        for (int i = start, end = arr.length - count + 1; i < end; i++) {
            nums.push(arr[i]);
            if (count == 1) {
                list.add(nums.toArray(new String[nums.size()]));
            } else {
                comboSelect(arr, nums, i + 1, count - 1, list);
            }
            nums.pop();
        }
    }
}
