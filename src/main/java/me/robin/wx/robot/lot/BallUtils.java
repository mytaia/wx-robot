/**create 2017-05-15**/

package me.robin.wx.robot.lot;

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
    
}
