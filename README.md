# TestTextView
TextView判断文字是否超长显示省略号。实现微信朋友圈文字折叠和展示全部的效果。
##TextView 判断文字内容是否超出显示省略号

 [博客地址](http://blog.csdn.net/gentlemanyc/article/details/49967719)

 TextView有个方法 **getLayout();** 

```
 /**
     * Returns the number of characters to be ellipsized away, or 0 if
     * no ellipsis is to take place.
     */
    public abstract int getEllipsisCount(int line);
```
可以获取到省略的字符数。如果返回0表示没有省略。那么就可以通过这个返回值来判断文字有没有省略了。
 
![运行截图](http://img.blog.csdn.net/20171107125834743?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZ2VudGxlbWFueWM=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)
