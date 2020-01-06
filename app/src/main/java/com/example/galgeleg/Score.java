package com.example.galgeleg;

public class Score {

    private String tid;
    private String fejl;
    private String navn;
    private String difficult;

    public Score(String tid, String fejl, String navn, String difficult){
        this.tid = tid;
        this.fejl = fejl;
        this.navn = navn;
        this.difficult = difficult;
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

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getDifficult() {
        return difficult;
    }

    public void setDifficult(String difficult) {
        this.difficult = difficult;
    }
}
