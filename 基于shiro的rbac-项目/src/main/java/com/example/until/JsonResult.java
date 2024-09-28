package com.example.until;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult {
    private Boolean success;
    private String message;
    public JsonResult(Boolean success){
        if (success)
        {
            this.success = true;
            this.message = "保存成功";
        }else {
            this.success=false;
            this.message="保存失败";
        }
    }
}
