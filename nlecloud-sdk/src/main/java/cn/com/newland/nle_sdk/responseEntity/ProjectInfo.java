package cn.com.newland.nle_sdk.responseEntity;

public class ProjectInfo {
    private String  Name;
    private String  Industry;
    private String  NetWorkKind;
    private String  ProjectTag;
    private String  CreateDate;
    private String  Remark;
    private int  ProjectID;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIndustry() {
        return Industry;
    }

    public void setIndustry(String industry) {
        Industry = industry;
    }

    public String getNetWorkKind() {
        return NetWorkKind;
    }

    public void setNetWorkKind(String netWorkKind) {
        NetWorkKind = netWorkKind;
    }

    public String getProjectTag() {
        return ProjectTag;
    }

    public void setProjectTag(String projectTag) {
        ProjectTag = projectTag;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public int getProjectID() {
        return ProjectID;
    }

    public void setProjectID(int projectID) {
        ProjectID = projectID;
    }
}
