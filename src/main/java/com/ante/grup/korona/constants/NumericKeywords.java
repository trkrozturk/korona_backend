package com.ante.grup.korona.constants;

public enum NumericKeywords {
    VEFAT("vefat"),
    VAKA("vaka"),
    TABURCU("taburcu");

    private String keyword;


    NumericKeywords(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

}
