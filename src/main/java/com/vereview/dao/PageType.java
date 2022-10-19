package com.vereview.dao;

/**
 * Created by mjmangan on 9/29/17.
 */
public enum PageType {
    ORIGINAL("ORIGINAL"),
    REDACTED("REDACTED"),
    REDACTED_TEXT("REDACTED_TEXT"),
    TEXT("TEXT");

    private String type;

    PageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static PageType getPageType(String value){
        for(PageType type : PageType.values()){
            if(type.getType().equalsIgnoreCase(value)){
                return type;
            }
        }
        return null;
    }
}
