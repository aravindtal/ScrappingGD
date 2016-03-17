package com.scrapper.gd;

/**
 * Created by aravindp on 17/3/16.
 */
public class JobObject {

    private String id;
    private String name;
    private String company;
    private String companyRating;
    private String companyReview;
    private String jobDesc;

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyRating() {
        return companyRating;
    }

    public void setCompanyRating(String companyRating) {
        this.companyRating = companyRating;
    }

    public String getCompanyReview() {
        return companyReview;
    }

    public void setCompanyReview(String companyReview) {
        this.companyReview = companyReview;
    }
}
