package com.example.qo;

import lombok.Data;

@Data
public class SearchQuery extends QueryObject{
    private String keyword;
    private Long deptId; // 若这个值为 null 情况，就不根据部门过滤数据
}
