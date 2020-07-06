package com.macpaw.utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

final public class HTMLParser {
    private String htmlText;

    public HTMLParser(String htmlText){
        this.htmlText = htmlText;
    }

    public String findLinkThatContains(String text) {
        Document doc = Jsoup.parse(htmlText);
        return doc.selectFirst("a[href *= " + text + "]").attr("href");
    }
}
