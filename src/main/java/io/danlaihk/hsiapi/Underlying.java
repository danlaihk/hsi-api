package io.danlaihk.hsiapi;

public class Underlying {
    private String code, cname, name;
    private int id;
    //private int contributionChange;

    public Underlying(String code){
        this.code = code;
    }

    public Underlying(int id, String code, String name, String cname){
        this.id = id;
        this.code =code;
        this.name = name;
        this.cname = cname;
    }

    public String toString(){
        return "name: "+name;
    }
    //
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    //
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    //
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    //
    /*
    public int getContributionChange() {
        return contributionChange;
    }

    public void setContributionChange(int contributionChange) {
        this.contributionChange = contributionChange;
    }*/
    //

}
