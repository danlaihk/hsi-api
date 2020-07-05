package io.danlaihk.hsiapi.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Constituent {
    private String code, constituentName;
    private int contributionChange;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getConstituentName() {
        return constituentName;
    }

    public void setConstituentName(String constituentName) {
        this.constituentName = constituentName;
    }

    public int getContributionChange() {
        return contributionChange;
    }

    public void setContributionChange(int contributionChange) {
        this.contributionChange = contributionChange;
    }
}
