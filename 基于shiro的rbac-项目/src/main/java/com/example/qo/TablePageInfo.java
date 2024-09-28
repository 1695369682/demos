package com.example.qo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 类似于PageResult，给bootstrap用
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TablePageInfo {
    /** 总记录数 */
    private long total;
    /** 列表数据 */
    private List<?> rows;
}
