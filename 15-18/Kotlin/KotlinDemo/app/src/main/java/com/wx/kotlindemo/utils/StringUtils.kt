package com.wx.kotlindemo.utils

class StringUtils {

    companion object {
        fun isEmpty(str : String?) :Boolean{
            if (str==null || str.equals(""))
                return true;
            else
                return false
        }
    }

    object StringUtils1 {

        val name = "wang"
        //@JvmStatic
        fun isEmpty(str : String?) :Boolean{
            if (str==null || str.equals(""))
                return true;
            else
                return false
        }
    }

}
