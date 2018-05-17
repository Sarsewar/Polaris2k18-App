package com.example.ankush.polaris2k18;

/**
 * Created by Ankush on 12/24/2017.
 */

public class NoticeClass {
public String title;
    public String message;
    NoticeClass()
    {}
NoticeClass(String title,String message)
{
    this.title=title;
    this.message=message;
}
public String getTitle()
{
    return this.title;

}
public String getMessage()
{
    return this.message;
}
}

