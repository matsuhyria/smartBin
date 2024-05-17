package com.example.demo1.Config;

import java.net.URL;

import com.example.demo1.BinApplication;

public enum CSSPath {
    SCROLLBAR("scrollbar.css"),
    POP_UP("notificationPopUp.css");

    private final String cssPath;

    CSSPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public URL getCssPath() {
        System.out.println(BinApplication.class.getResource(cssPath));
        return BinApplication.class.getResource(cssPath);
    }
}
