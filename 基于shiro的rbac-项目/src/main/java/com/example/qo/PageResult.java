package com.example.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T>{
    private List<T> data;
    private Integer currentPage;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPage;
    private Integer nextPage;
    private Integer prevPage;
    public PageResult(Integer currentPage, Integer pageSize,Integer totalCount,List<T> data)
    {
        this.currentPage=currentPage;
        this.pageSize=pageSize;
        this.totalCount=totalCount;
        this.data=data;
        //总条数对每页数量取余，不为零则加一页----=总页数
//        totalPages=((Count%pageSize)==0)?totalPages/pageSize:totalPages/pageSize+1;
        totalPage=(totalCount + pageSize - 1) / pageSize;
        //当前页大于等于2，减一，否则保持第一页
        prevPage = currentPage-1>=1?currentPage-1:1;
        //同理
        nextPage=currentPage+1<=totalPage?currentPage+1:totalPage;
    }
}
