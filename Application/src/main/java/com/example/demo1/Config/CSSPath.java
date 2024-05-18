package com.example.demo1.Config;

import java.net.URL;

import com.example.demo1.BinApplication;

//Change path to the stylesheets here in case of changes in the folder structure
public enum CSSPath {
    SCROLLBAR("stylesheets/scrollbar.css"),
    POP_UP("stylesheets/notificationPopUp.css"),
    CHART("stylesheets/chartStyle.css");

    private final String cssPath;

    CSSPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public URL getCssPath() {
        return BinApplication.class.getResource(cssPath);
    }
}
