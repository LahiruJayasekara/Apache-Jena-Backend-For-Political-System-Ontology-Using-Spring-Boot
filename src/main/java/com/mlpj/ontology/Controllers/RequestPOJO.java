package com.mlpj.ontology.Controllers;

public class RequestPOJO {
    private String district;
    private String province;
    private String party;
    private String politicalInstituteInstance;

    public RequestPOJO(String district, String province, String party, String politicalInstituteInstance) {
        this.district = district;
        this.province = province;
        this.party = party;
        this.politicalInstituteInstance = politicalInstituteInstance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPoliticalInstituteInstance() {
        return politicalInstituteInstance;
    }

    public void setPoliticalInstituteInstance(String politicalInstituteInstance) {
        this.politicalInstituteInstance = politicalInstituteInstance;
    }
}
