package com.example.galgeleg;

import java.util.ArrayList;

public class Score {

    private String tid;
    private String fejl;

    public Score(String tid, String fejl){
        this.tid = tid;
        this.fejl = fejl;
    }

    public ArrayList<Score> rankedList(ArrayList<Score> unsortedAList){
        ArrayList<Score> sortedAList = new ArrayList<>();


        return sortedAList;
    }


    //----------------------------
    public String getFejl() {
        return fejl;
    }

    public void setFejl(String fejl) {
        this.fejl = fejl;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
