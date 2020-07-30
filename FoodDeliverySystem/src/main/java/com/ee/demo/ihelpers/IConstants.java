package com.ee.demo.ihelpers;

public enum IConstants {
    SUCEESS("success");
     
 	private final String text;
    IConstants(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text.toString();
    }
};
 