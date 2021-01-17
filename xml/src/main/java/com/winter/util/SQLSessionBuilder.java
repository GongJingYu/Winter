package com.winter.util;

import com.winter.xml.SQLXMLParse;

public class SQLSessionBuilder {

    private SQLSessionBuilder(){}

    private static SQLXMLParse parse = new SQLXMLParse();

    public static void build(String file){
        parse.parse(file);
    }
}
