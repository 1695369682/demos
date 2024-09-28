package com.example.entity;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    private Long id;
    private String name;
    private String sn;

    public String getJson(){
        Map<String,Object> map = new HashMap<>();
        map.put("id",this.id);
        map.put("name",this.name);
        map.put("sn",this.sn);
        System.out.println(map);
        return JSON.toJSONString(map);
    }

}