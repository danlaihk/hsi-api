package io.danlaihk.hsiapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubIndex {
    private int constituentsCount;
    //private List<Constituent> constituentContent;

    public int getConstituentsCount() {
        return constituentsCount;
    }

    public void setConstituentsCount(int constituentsCount) {
        this.constituentsCount = constituentsCount;
    }

    /*
    public List<Constituent> getConstituentContent() {
        return constituentContent;
    }

    public void setConstituentContent(List<Constituent> constituentContent) {
        this.constituentContent = constituentContent;
    }*/
}
