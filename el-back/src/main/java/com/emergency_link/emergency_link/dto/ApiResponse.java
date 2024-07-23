package com.emergency_link.emergency_link.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.List;

public class ApiResponse {

    private Header header;
    private Body body;

    // Getters and setters

    public static class Header {
        private String resultCode;
        private String resultMsg;

        // Getters and setters
    }

    public static class Body {
        @JacksonXmlElementWrapper(useWrapping = false)
        @JsonProperty("item")
        private List<Item> items;
        private int numOfRows;
        private int pageNo;
        private int totalCount;

        // Getters and setters
    }

    public static class Item {
        private String dutyName;
        private String dutyTel3;
        private String hpid;
        private String hv10;
        private String hv11;
        private int hv28;
        private int hv29;
        private int hv30;
        private int hv34;
        private int hv41;
        private String hv42;
        private String hv5;
        private int hv6;
        private String hv7;
        private String hvamyn;
        private String hvangioayn;
        private String hvcrrtayn;
        private String hvctayn;
        private int hvec;
        private String hvecmoayn;
        private int hvgc;
        private String hvhypoayn;
        private int hvicc;
        private String hvidate;
        private String hvincuayn;
        private String hvmriayn;
        private int hvncc;
        private int hvoc;
        private String hvoxyayn;
        private int hvs01;
        private int hvs02;
        private int hvs03;
        private int hvs04;
        private int hvs08;
        private int hvs12;
        private int hvs15;
        private int hvs17;
        private int hvs22;
        private int hvs25;
        private int hvs26;
        private int hvs27;
        private int hvs28;
        private int hvs29;
        private int hvs30;
        private int hvs31;
        private int hvs32;
        private int hvs33;
        private int hvs34;
        private int hvs35;
        private int hvs38;
        private String hvventiayn;
        private String hvventisoayn;
        private String phpid;
        private int rnum;

        // Getters and setters
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
