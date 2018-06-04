package com.example.service1;

public class UUSData {
    private String data;
    private String uid;
    private String service;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String toString(){
        return "uid=" + uid + ",data=" + data + ",service=" + service;
    }
}
