package com.zl.bgec.basicapi.common;








/**
 * 基础测试类
 * 
 * @author ZhengWei(HY)
 * @create 2014-09-19
 */
public class BaseJunit
{
    private static boolean $IsInit = false;
    
    
    
    public BaseJunit()
    {
        synchronized ( this )
        {
            if ( !$IsInit )
            {
                $IsInit = true;
            }
        }
    }
    
}
