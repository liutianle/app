package com.example.nene.movie20.models;

import java.util.List;

public class DocInf {
    private String count;
    private String next;
    private String previous;
    private List<DocResultBean> results;

    public List<DocResultBean> getResults() {
        return results;
    }

    public void setResults(List<DocResultBean> results) {
        this.results = results;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public class DocResultBean {
        private String id;
        private String text;
        private String text_type;
        //plant
        private String file_type;
        //doc
        private String file_size;
        private String file_name;
        private String pdf_path;

        public String getFile_type() {
            return file_type;
        }

        public void setFile_type(String file_type) {
            this.file_type = file_type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText_type() {
            return text_type;
        }

        public void setText_type(String text_type) {
            this.text_type = text_type;
        }

        public String getFile_size() {
            return file_size;
        }

        public void setFile_size(String file_size) {
            this.file_size = file_size;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getPdf_path() {
            return pdf_path;
        }

        public void setPdf_path(String pdf_path) {
            this.pdf_path = pdf_path;
        }
    }
}
