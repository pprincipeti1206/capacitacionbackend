package com.incloud.hcp.bean;

public class JSON {

    private String json;

    public JSON() {
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "JSON{" +
                "json=[{'id':" + json +"}]"+
                '}';
    }
}
