package com.example.nene.movie20.data;

public class Exam {
    private int exam_logo;
    private String exam_name;
    private String test_number;
    private String test_finish_number;

    public Exam(int exam_logo, String exam_name, String test_number, String test_finish_number) {
        this.exam_logo = exam_logo;
        this.exam_name = exam_name;
        this.test_number = test_number;
        this.test_finish_number = test_finish_number;
    }

    public int getExam_logo() {
        return exam_logo;
    }

    public void setExam_logo(int exam_logo) {
        this.exam_logo = exam_logo;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }

    public String getTest_number() {
        return test_number;
    }

    public void setTest_number(String test_number) {
        this.test_number = test_number;
    }

    public String getTest_finish_number() {
        return test_finish_number;
    }

    public void setTest_finish_number(String test_finish_number) {
        this.test_finish_number = test_finish_number;
    }
}
