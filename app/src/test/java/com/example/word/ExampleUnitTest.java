package com.example.word;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String temp=GetJson.get("cat","en","zh-CHS");
        System.out.println(temp.lastIndexOf("http://openapi"));
        System.out.println(temp.length());
        System.out.println(temp.substring(temp.lastIndexOf("http://openapi"),temp.length()-2));
    }
}