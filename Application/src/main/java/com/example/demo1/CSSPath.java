package com.example.demo1;

import java.net.URL;

public enum CSSPath {
    SCROLLBAR("scrollbar.css"),
    POP_UP("1.css");

    private final String cssPath;

    CSSPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public URL getCssPath() {
        return getClass().getResource(cssPath);
    }
}
