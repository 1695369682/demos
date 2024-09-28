package com.example.until;

import org.springframework.stereotype.Component;

@Component
public class StringNull {
    public static boolean isNull(String s)
    {
        if (s.equals("")||s==null)
        {
            return true;
        }
        return false;
    }
    public static boolean isNotNull(String s)
    {
        if (!s.equals("")&&s!=null)
        {
            return true;
        }
        return false;
    }

}
