package io.danlaihk.hsiapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Index {
    private String indexName;
    //private List<SubIndex> subIndexList;
    private List<Constituent> constituentContent;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    /*
    public List<SubIndex> getSubIndexList() {
        return subIndexList;
    }

    public void setSubIndexList(List<SubIndex> subIndexList) {
        this.subIndexList = subIndexList;
    }
    */
    public List<Constituent> getConstituentContent() {
        return constituentContent;
    }

    public void setConstituentContent(List<Constituent> constituentContent) {
        this.constituentContent = constituentContent;
    }
}
