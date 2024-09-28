package com.example.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryObject {
    private Integer currentPage=1;
    private Integer pageSize=6;
    public Integer getStart() {
        return (currentPage-1)*pageSize;
    }
    //bootstrap-talbe
    private int offset;
    private int limit;
}
