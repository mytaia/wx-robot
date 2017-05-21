
package me.robin.wx.robot.web.velocity;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.tools.config.DefaultKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.google.common.collect.Lists;

/**
 * 分页工具类
 * 
 * @author macong
 * @since 1.0
 * @version 2014-01-03 macong
 */
@DefaultKey("page")
public class PageTool {
    
    /****
     * 默认页面数据条数大小
     */
    public static final int PAGEDEFAULTSIZE = 16;
    
    /**
     * 生成page 使用方式： $!page.pagination($users)
     * 
     * @param page page
     * @param pageFun 页面改变js函数
     * @return 当前用户肤色样式
     */
    public String pagination(Page<?> page, String pageFun) {
        pageFun = StringUtils.isEmpty(pageFun) ? "pageFun" : pageFun;
        StringBuffer sb = new StringBuffer();
        int paginationSize = 7;
        if (page == null) {
            page = new PageImpl<>(Lists.newArrayList());
        }
        int current = page.getNumber();
        int begin = Math.max(0, current - paginationSize / 2);
        int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());
        int size = page.getSize();
        
        sb.append("");
        sb.append("<div><ul class=\"pagination\">");
        sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">" + (current + 1) + "/" + page.getTotalPages() + "</a></li>");
        if (page.hasPrevious()) {
            sb.append("<li><a page=\"0\" href=\"" + getPageFunStr(0, size, pageFun) + "\">&lt;&lt;</a></li>");
            sb.append("<li><a href=\"" + getPageFunStr(current - 1, size, pageFun) + "\">&lt;</a></li>");
        } else {
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&lt;&lt;</a></li>");
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&lt;</a></li>");
        }
        for (int i = begin; i < (end); i++) {
            if (i == current) {
                sb.append("<li class=\"active\"><a page=\"" + i + "\" href=\"javascript:void(0);\">" + (i + 1) + "</a></li>");
            } else {
                sb.append("<li><a href=\"" + getPageFunStr(i, size, pageFun) + "\">" + (i + 1) + "</a></li>");
            }
        }
        if (page.hasNext()) {
            sb.append("<li><a href=\"" + getPageFunStr(current + 1, size, pageFun) + "\">&gt;</a></li>");
            sb.append("<li><a href=\"" + getPageFunStr(page.getTotalPages(), size, pageFun) + "\">&gt;&gt;</a></li>");
        } else {
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&gt;</a></li>");
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&gt;&gt;</a></li>");
        }
        sb.append("</div><ul>");
        
        return sb.toString();
    }
    
    /**
     * 调用的页面方法
     * 
     * @param no 第几页
     * @param size 每页显示多少条
     * @param pageFun pageFun
     * @return 字符串
     */
    private String getPageFunStr(int no, int size, String pageFun) {
        return "javascript:" + pageFun + "(" + no + "," + size + ");";
    }
    
    /**
     * 生成page 使用方式： $!page.pagination($users)
     * 
     * @param pageNum 页数
     * @param total 总数
     * @param page page
     * @param pageFun 页面改变js函数
     * @return 当前用户肤色样式
     */
    public String pagination(int pageNum, int total, String pageFun) {
        return pagination(pageNum, PAGEDEFAULTSIZE, total, pageFun);
    }
    
    /**
     * 生成page 使用方式： $!page.pagination($users)
     * 
     * @param pageNum 页数
     * @param pageSize 条数
     * @param total 总数
     * @param page page
     * @param pageFun 页面改变js函数
     * @return 当前用户肤色样式
     */
    public String pagination(int pageNum, int pageSize, int total, String pageFun) {
        pageFun = StringUtils.isEmpty(pageFun) ? "pageFun" : pageFun;
        StringBuffer sb = new StringBuffer();
        int paginationSize = 7;
        int current = pageNum;// 当前页数
        int begin = Math.max(1, current - paginationSize / 2);// 显示页面数，开始
        int size = pageSize < 1 ? PAGEDEFAULTSIZE : pageSize;// 每页显示条数
        int totalPages = (total / size) + ((total % size == 0) ? 0 : 1); // 总页数
        int end = Math.min(begin + (paginationSize - 1), totalPages);// 显示页面数，结束
        
        sb.append("");
        sb.append("<div><ul class=\"pagination\">");
        sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">" + current + "/" + totalPages + "</a></li>");
        if (current > 1) {
            sb.append("<li><a page=\"1\" href=\"" + getPageFunStr(1, size, pageFun) + "\">&lt;&lt;</a></li>");
            sb.append("<li><a href=\"" + getPageFunStr(current - 1, size, pageFun) + "\">&lt;</a></li>");
        } else {
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&lt;&lt;</a></li>");
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&lt;</a></li>");
        }
        for (int i = begin; i < (end + 1); i++) {
            if (i == current) {
                sb.append("<li class=\"active\"><a page=\"" + i + "\" href=\"javascript:void(0);\">" + i + "</a></li>");
            } else {
                sb.append("<li><a href=\"" + getPageFunStr(i, size, pageFun) + "\">" + i + "</a></li>");
            }
        }
        if (current < totalPages) {
            sb.append("<li><a href=\"" + getPageFunStr(current + 1, size, pageFun) + "\">&gt;</a></li>");
            sb.append("<li><a href=\"" + getPageFunStr(totalPages, size, pageFun) + "\">&gt;&gt;</a></li>");
        } else {
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&gt;</a></li>");
            sb.append("<li class=\"disabled\"><a href=\"javascript:void(0);\">&gt;&gt;</a></li>");
        }
        sb.append("</div><ul>");
        
        return sb.toString();
    }
    
}
