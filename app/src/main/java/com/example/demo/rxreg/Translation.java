package com.example.demo.rxreg;

import com.example.demo.common.LogUtils;

/**
 * // URL模板
 http://fy.iciba.com/ajax.php

 // URL实例
 http://fy.iciba.com/ajax.php?a=fy&f=auto&t=auto&w=hello%20world

 // 参数说明：
 // a：固定值 fy
 // f：原文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
 // t：译文内容类型，日语取 ja，中文取 zh，英语取 en，韩语取 ko，德语取 de，西班牙语取 es，法语取 fr，自动则取 auto
 // w：查询内容

 返回的GSON格式
 {
   "status": 1
   "content"：{
      “from": "en-EU"
      "to": "zh-CN"
      "vendor": "baidu"
      "out": "你好世界<br/>"
      "err_no": 0
    }
 }
 */
public class Translation {
    private int status;
    private Content content;

    public void show(){
        LogUtils.d("from= " + content.from + "  to= " + content.to
                                     + "  out= " + content.out);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private static class Content{
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;
    }

}
