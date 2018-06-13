package com.example.nene.movie20.data;

public class Doc {
    private String docName;
    private String docType;
    private String docClass;

    public Doc(String docName, String docType, String docClass) {
        this.docName = docName;
        this.docType = docType;
        this.docClass = docClass;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    //doc
    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    //plant
    public String getDocClass() {
        return docClass;
    }

    public void setDocClass(String docClass) {
        this.docClass = docClass;
    }
}
