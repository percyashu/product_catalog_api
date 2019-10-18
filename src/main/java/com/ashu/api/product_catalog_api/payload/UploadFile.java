package com.ashu.api.product_catalog_api.payload;


public class UploadFile {
    private String FileURLConnection;

    public String getFileURLConnection() {
        return FileURLConnection;
    }

    public void setFileURLConnection(String fileURLConnection) {
        FileURLConnection = fileURLConnection;
    }

    public UploadFile(String fileURLConnection) {
        FileURLConnection = fileURLConnection;
    }
}
