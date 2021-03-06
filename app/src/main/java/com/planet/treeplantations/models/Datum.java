
package com.planet.treeplantations.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("PO_MOUNumberID")
    @Expose
    private String pOMOUNumberID;
    @SerializedName("PO_MOUNumber")
    @Expose
    private String pOMOUNumber;
    @SerializedName("ProjectID")
    @Expose
    private String projectID;
    @SerializedName("ProjectName")
    @Expose
    private String projectName;
    @SerializedName("ProjectCost")
    @Expose
    private String projectCost;
    @SerializedName("Agency")
    @Expose
    private String agency;
    @SerializedName("FinancialYearID")
    @Expose
    private String financialYearID;
    @SerializedName("FinancialYear")
    @Expose
    private String financialYear;
    @SerializedName("RegionName")
    @Expose
    private String regionName;
    @SerializedName("RegionID")
    @Expose
    private String regionID;
    @SerializedName("StateName")
    @Expose
    private String stateName;
    @SerializedName("StateID")
    @Expose
    private String stateID;
    @SerializedName("DivisionName")
    @Expose
    private String divisionName;
    @SerializedName("DivisionID")
    @Expose
    private String divisionID;
    @SerializedName("RangeName")
    @Expose
    private String rangeName;
    @SerializedName("RangeID")
    @Expose
    private String rangeID;
    @SerializedName("LandArea")
    @Expose
    private String landArea;
    @SerializedName("LandAreaID")
    @Expose
    private String landAreaID;
    @SerializedName("TreeSpecies")
    @Expose
    private String treeSpecies;
    @SerializedName("TreeSpeciesID")
    @Expose
    private String treeSpeciesID;
    @SerializedName("Datefrom")
    @Expose
    private String datefrom;
    @SerializedName("DateTo")
    @Expose
    private String dateTo;
    @SerializedName("NoOfPlant")
    @Expose
    private String noOfPlant;
    @SerializedName("Latitude")
    @Expose
    private String latitude;
    @SerializedName("Longitude")
    @Expose
    private String longitude;
    @SerializedName("UploadingDate")
    @Expose
    private String uploadingDate;
    @SerializedName("PolygonDetai")
    @Expose
    private String polygonDetai;
    @SerializedName("Image1")
    @Expose
    private String image1;
    @SerializedName("Image2")
    @Expose
    private String image2;
    @SerializedName("Image3")
    @Expose
    private String image3;
    @SerializedName("Image4")
    @Expose
    private String image4;
    @SerializedName("Image5")
    @Expose
    private String image5;
    @SerializedName("Image6")
    @Expose
    private String image6;
    @SerializedName("Image7")
    @Expose
    private String image7;
    @SerializedName("Image8")
    @Expose
    private String image8;
    @SerializedName("Image9")
    @Expose
    private String image9;
    @SerializedName("Image10")
    @Expose
    private String image10;
    @SerializedName("Remarks")
    @Expose
    private String remarks;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;
    @SerializedName("CreatedBy")
    @Expose
    private String createdBy;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("UpdatedBy")
    @Expose
    private String updatedBy;
    @SerializedName("Updateddate")
    @Expose
    private String updateddate;
    @SerializedName("TPUID")
    @Expose
    private Boolean tPUID;
    @SerializedName("IsRequested")
    @Expose
    private Boolean isRequested;
    @SerializedName("ReviewUpdated")
    @Expose
    private Object reviewUpdated;
    @SerializedName("ReviewDate")
    @Expose
    private Object reviewDate;
    @SerializedName("LandTypeName")
    @Expose
    private String landTypeName;
    @SerializedName("LandTypeID")
    @Expose
    private String landTypeID;
    @SerializedName("ProjectCodeName")
    @Expose
    private String projectCodeName;
    @SerializedName("ProjectCodeId")
    @Expose
    private String projectCodeId;
    @SerializedName("BudgetHead_Name")
    @Expose
    private String budgetHeadName;
    @SerializedName("BudgetHead_Id")
    @Expose
    private String budgetHeadId;
    @SerializedName("YearOfMaintence")
    @Expose
    private String yearOfMaintence;
    @SerializedName("YearOfMaintenceId")
    @Expose
    private String yearOfMaintenceId;
    @SerializedName("FundsApproved")
    @Expose
    private String fundsApproved;
    @SerializedName("YearofPlantation")
    @Expose
    private String yearofPlantation;
    @SerializedName("UtilizationCertificateFirstYearRupees")
    @Expose
    private String utilizationCertificateFirstYearRupees;
    @SerializedName("UtilizationCertificateSecondYear")
    @Expose
    private String utilizationCertificateSecondYear;
    @SerializedName("UtilizationCertificatethird")
    @Expose
    private String utilizationCertificatethird;
    @SerializedName("UtilizationCertificateFourthYear")
    @Expose
    private String utilizationCertificateFourthYear;
    @SerializedName("UtilizationCertificateFifthYear")
    @Expose
    private String utilizationCertificateFifthYear;
    @SerializedName("UtilizationCertificateSixthYear")
    @Expose
    private String utilizationCertificateSixthYear;
    @SerializedName("FirstYearFund")
    @Expose
    private String firstYearFund;
    @SerializedName("FirstYearFundReleaseDate")
    @Expose
    private String firstYearFundReleaseDate;
    @SerializedName("SecondYearFund")
    @Expose
    private String secondYearFund;
    @SerializedName("SecondYearFundReleaseDate")
    @Expose
    private String secondYearFundReleaseDate;
    @SerializedName("ThirdYearFund")
    @Expose
    private String thirdYearFund;
    @SerializedName("ThirdYearFundReleaseDate")
    @Expose
    private String thirdYearFundReleaseDate;
    @SerializedName("FourthYearFund")
    @Expose
    private String fourthYearFund;
    @SerializedName("FourthYearFundReleaseDate")
    @Expose
    private String fourthYearFundReleaseDate;
    @SerializedName("FifthYearFund")
    @Expose
    private String fifthYearFund;
    @SerializedName("FifthYearFundReleaseDate")
    @Expose
    private String fifthYearFundReleaseDate;
    @SerializedName("SixthYearFund")
    @Expose
    private String sixthYearFund;
    @SerializedName("SixthYearFundReleaseDate")
    @Expose
    private String sixthYearFundReleaseDate;
    @SerializedName("SurvivalPercentageFirstYear")
    @Expose
    private String survivalPercentageFirstYear;
    @SerializedName("SurvivalPercentageSecondYear")
    @Expose
    private String survivalPercentageSecondYear;
    @SerializedName("SurvivalPercentageThirdYear")
    @Expose
    private String survivalPercentageThirdYear;
    @SerializedName("SurvivalPercentageFourthYear")
    @Expose
    private String survivalPercentageFourthYear;
    @SerializedName("SurvivalPercentageFifthYear")
    @Expose
    private String survivalPercentageFifthYear;
    @SerializedName("SurvivalPercentageSixthYear")
    @Expose
    private String survivalPercentageSixthYear;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param fourthYearFundReleaseDate
     * @param projectCodeName
     * @param pOMOUNumberID
     * @param fundsApproved
     * @param landTypeID
     * @param utilizationCertificateFifthYear
     * @param yearOfMaintence
     * @param image10
     * @param fifthYearFundReleaseDate
     * @param thirdYearFundReleaseDate
     * @param projectID
     * @param uploadingDate
     * @param datefrom
     * @param rangeID
     * @param sixthYearFund
     * @param budgetHeadName
     * @param stateID
     * @param yearofPlantation
     * @param longitude
     * @param utilizationCertificateFourthYear
     * @param createdDate
     * @param survivalPercentageThirdYear
     * @param secondYearFund
     * @param budgetHeadId
     * @param dateTo
     * @param financialYearID
     * @param regionName
     * @param secondYearFundReleaseDate
     * @param fifthYearFund
     * @param isRequested
     * @param iD
     * @param isActive
     * @param landTypeName
     * @param survivalPercentageFirstYear
     * @param createdBy
     * @param tPUID
     * @param firstYearFundReleaseDate
     * @param latitude
     * @param reviewUpdated
     * @param survivalPercentageFourthYear
     * @param projectName
     * @param regionID
     * @param landArea
     * @param survivalPercentageSecondYear
     * @param projectCost
     * @param thirdYearFund
     * @param remarks
     * @param survivalPercentageFifthYear
     * @param updateddate
     * @param updatedBy
     * @param utilizationCertificateSecondYear
     * @param treeSpecies
     * @param pOMOUNumber
     * @param rangeName
     * @param polygonDetai
     * @param survivalPercentageSixthYear
     * @param financialYear
     * @param utilizationCertificateFirstYearRupees
     * @param sixthYearFundReleaseDate
     * @param yearOfMaintenceId
     * @param utilizationCertificatethird
     * @param agency
     * @param reviewDate
     * @param treeSpeciesID
     * @param firstYearFund
     * @param projectCodeId
     * @param landAreaID
     * @param image2
     * @param image1
     * @param divisionName
     * @param fourthYearFund
     * @param divisionID
     * @param stateName
     * @param noOfPlant
     * @param image8
     * @param image7
     * @param utilizationCertificateSixthYear
     * @param image9
     * @param image4
     * @param image3
     * @param image6
     * @param image5
     */
    public Datum(Integer iD, String pOMOUNumberID, String pOMOUNumber, String projectID, String projectName, String projectCost, String agency, String financialYearID, String financialYear, String regionName, String regionID, String stateName, String stateID, String divisionName, String divisionID, String rangeName, String rangeID, String landArea, String landAreaID, String treeSpecies, String treeSpeciesID, String datefrom, String dateTo, String noOfPlant, String latitude, String longitude, String uploadingDate, String polygonDetai, String image1, String image2, String image3, String image4, String image5, String image6, String image7, String image8, String image9, String image10, String remarks, Boolean isActive, String createdBy, String createdDate, String updatedBy, String updateddate, Boolean tPUID, Boolean isRequested, Object reviewUpdated, Object reviewDate, String landTypeName, String landTypeID, String projectCodeName, String projectCodeId, String budgetHeadName, String budgetHeadId, String yearOfMaintence, String yearOfMaintenceId, String fundsApproved, String yearofPlantation, String utilizationCertificateFirstYearRupees, String utilizationCertificateSecondYear, String utilizationCertificatethird, String utilizationCertificateFourthYear, String utilizationCertificateFifthYear, String utilizationCertificateSixthYear, String firstYearFund, String firstYearFundReleaseDate, String secondYearFund, String secondYearFundReleaseDate, String thirdYearFund, String thirdYearFundReleaseDate, String fourthYearFund, String fourthYearFundReleaseDate, String fifthYearFund, String fifthYearFundReleaseDate, String sixthYearFund, String sixthYearFundReleaseDate, String survivalPercentageFirstYear, String survivalPercentageSecondYear, String survivalPercentageThirdYear, String survivalPercentageFourthYear, String survivalPercentageFifthYear, String survivalPercentageSixthYear) {
        super();
        this.iD = iD;
        this.pOMOUNumberID = pOMOUNumberID;
        this.pOMOUNumber = pOMOUNumber;
        this.projectID = projectID;
        this.projectName = projectName;
        this.projectCost = projectCost;
        this.agency = agency;
        this.financialYearID = financialYearID;
        this.financialYear = financialYear;
        this.regionName = regionName;
        this.regionID = regionID;
        this.stateName = stateName;
        this.stateID = stateID;
        this.divisionName = divisionName;
        this.divisionID = divisionID;
        this.rangeName = rangeName;
        this.rangeID = rangeID;
        this.landArea = landArea;
        this.landAreaID = landAreaID;
        this.treeSpecies = treeSpecies;
        this.treeSpeciesID = treeSpeciesID;
        this.datefrom = datefrom;
        this.dateTo = dateTo;
        this.noOfPlant = noOfPlant;
        this.latitude = latitude;
        this.longitude = longitude;
        this.uploadingDate = uploadingDate;
        this.polygonDetai = polygonDetai;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.image6 = image6;
        this.image7 = image7;
        this.image8 = image8;
        this.image9 = image9;
        this.image10 = image10;
        this.remarks = remarks;
        this.isActive = isActive;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.updatedBy = updatedBy;
        this.updateddate = updateddate;
        this.tPUID = tPUID;
        this.isRequested = isRequested;
        this.reviewUpdated = reviewUpdated;
        this.reviewDate = reviewDate;
        this.landTypeName = landTypeName;
        this.landTypeID = landTypeID;
        this.projectCodeName = projectCodeName;
        this.projectCodeId = projectCodeId;
        this.budgetHeadName = budgetHeadName;
        this.budgetHeadId = budgetHeadId;
        this.yearOfMaintence = yearOfMaintence;
        this.yearOfMaintenceId = yearOfMaintenceId;
        this.fundsApproved = fundsApproved;
        this.yearofPlantation = yearofPlantation;
        this.utilizationCertificateFirstYearRupees = utilizationCertificateFirstYearRupees;
        this.utilizationCertificateSecondYear = utilizationCertificateSecondYear;
        this.utilizationCertificatethird = utilizationCertificatethird;
        this.utilizationCertificateFourthYear = utilizationCertificateFourthYear;
        this.utilizationCertificateFifthYear = utilizationCertificateFifthYear;
        this.utilizationCertificateSixthYear = utilizationCertificateSixthYear;
        this.firstYearFund = firstYearFund;
        this.firstYearFundReleaseDate = firstYearFundReleaseDate;
        this.secondYearFund = secondYearFund;
        this.secondYearFundReleaseDate = secondYearFundReleaseDate;
        this.thirdYearFund = thirdYearFund;
        this.thirdYearFundReleaseDate = thirdYearFundReleaseDate;
        this.fourthYearFund = fourthYearFund;
        this.fourthYearFundReleaseDate = fourthYearFundReleaseDate;
        this.fifthYearFund = fifthYearFund;
        this.fifthYearFundReleaseDate = fifthYearFundReleaseDate;
        this.sixthYearFund = sixthYearFund;
        this.sixthYearFundReleaseDate = sixthYearFundReleaseDate;
        this.survivalPercentageFirstYear = survivalPercentageFirstYear;
        this.survivalPercentageSecondYear = survivalPercentageSecondYear;
        this.survivalPercentageThirdYear = survivalPercentageThirdYear;
        this.survivalPercentageFourthYear = survivalPercentageFourthYear;
        this.survivalPercentageFifthYear = survivalPercentageFifthYear;
        this.survivalPercentageSixthYear = survivalPercentageSixthYear;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getPOMOUNumberID() {
        return pOMOUNumberID;
    }

    public void setPOMOUNumberID(String pOMOUNumberID) {
        this.pOMOUNumberID = pOMOUNumberID;
    }

    public String getPOMOUNumber() {
        return pOMOUNumber;
    }

    public void setPOMOUNumber(String pOMOUNumber) {
        this.pOMOUNumber = pOMOUNumber;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCost() {
        return projectCost;
    }

    public void setProjectCost(String projectCost) {
        this.projectCost = projectCost;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getFinancialYearID() {
        return financialYearID;
    }

    public void setFinancialYearID(String financialYearID) {
        this.financialYearID = financialYearID;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRegionID() {
        return regionID;
    }

    public void setRegionID(String regionID) {
        this.regionID = regionID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }

    public String getRangeName() {
        return rangeName;
    }

    public void setRangeName(String rangeName) {
        this.rangeName = rangeName;
    }

    public String getRangeID() {
        return rangeID;
    }

    public void setRangeID(String rangeID) {
        this.rangeID = rangeID;
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public String getLandAreaID() {
        return landAreaID;
    }

    public void setLandAreaID(String landAreaID) {
        this.landAreaID = landAreaID;
    }

    public String getTreeSpecies() {
        return treeSpecies;
    }

    public void setTreeSpecies(String treeSpecies) {
        this.treeSpecies = treeSpecies;
    }

    public String getTreeSpeciesID() {
        return treeSpeciesID;
    }

    public void setTreeSpeciesID(String treeSpeciesID) {
        this.treeSpeciesID = treeSpeciesID;
    }

    public String getDatefrom() {
        return datefrom;
    }

    public void setDatefrom(String datefrom) {
        this.datefrom = datefrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getNoOfPlant() {
        return noOfPlant;
    }

    public void setNoOfPlant(String noOfPlant) {
        this.noOfPlant = noOfPlant;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUploadingDate() {
        return uploadingDate;
    }

    public void setUploadingDate(String uploadingDate) {
        this.uploadingDate = uploadingDate;
    }

    public String getPolygonDetai() {
        return polygonDetai;
    }

    public void setPolygonDetai(String polygonDetai) {
        this.polygonDetai = polygonDetai;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }

    public String getImage6() {
        return image6;
    }

    public void setImage6(String image6) {
        this.image6 = image6;
    }

    public String getImage7() {
        return image7;
    }

    public void setImage7(String image7) {
        this.image7 = image7;
    }

    public String getImage8() {
        return image8;
    }

    public void setImage8(String image8) {
        this.image8 = image8;
    }

    public String getImage9() {
        return image9;
    }

    public void setImage9(String image9) {
        this.image9 = image9;
    }

    public String getImage10() {
        return image10;
    }

    public void setImage10(String image10) {
        this.image10 = image10;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(String updateddate) {
        this.updateddate = updateddate;
    }

    public Boolean getTPUID() {
        return tPUID;
    }

    public void setTPUID(Boolean tPUID) {
        this.tPUID = tPUID;
    }

    public Boolean getIsRequested() {
        return isRequested;
    }

    public void setIsRequested(Boolean isRequested) {
        this.isRequested = isRequested;
    }

    public Object getReviewUpdated() {
        return reviewUpdated;
    }

    public void setReviewUpdated(Object reviewUpdated) {
        this.reviewUpdated = reviewUpdated;
    }

    public Object getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Object reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getLandTypeName() {
        return landTypeName;
    }

    public void setLandTypeName(String landTypeName) {
        this.landTypeName = landTypeName;
    }

    public String getLandTypeID() {
        return landTypeID;
    }

    public void setLandTypeID(String landTypeID) {
        this.landTypeID = landTypeID;
    }

    public String getProjectCodeName() {
        return projectCodeName;
    }

    public void setProjectCodeName(String projectCodeName) {
        this.projectCodeName = projectCodeName;
    }

    public String getProjectCodeId() {
        return projectCodeId;
    }

    public void setProjectCodeId(String projectCodeId) {
        this.projectCodeId = projectCodeId;
    }

    public String getBudgetHeadName() {
        return budgetHeadName;
    }

    public void setBudgetHeadName(String budgetHeadName) {
        this.budgetHeadName = budgetHeadName;
    }

    public String getBudgetHeadId() {
        return budgetHeadId;
    }

    public void setBudgetHeadId(String budgetHeadId) {
        this.budgetHeadId = budgetHeadId;
    }

    public String getYearOfMaintence() {
        return yearOfMaintence;
    }

    public void setYearOfMaintence(String yearOfMaintence) {
        this.yearOfMaintence = yearOfMaintence;
    }

    public String getYearOfMaintenceId() {
        return yearOfMaintenceId;
    }

    public void setYearOfMaintenceId(String yearOfMaintenceId) {
        this.yearOfMaintenceId = yearOfMaintenceId;
    }

    public String getFundsApproved() {
        return fundsApproved;
    }

    public void setFundsApproved(String fundsApproved) {
        this.fundsApproved = fundsApproved;
    }

    public String getYearofPlantation() {
        return yearofPlantation;
    }

    public void setYearofPlantation(String yearofPlantation) {
        this.yearofPlantation = yearofPlantation;
    }

    public String getUtilizationCertificateFirstYearRupees() {
        return utilizationCertificateFirstYearRupees;
    }

    public void setUtilizationCertificateFirstYearRupees(String utilizationCertificateFirstYearRupees) {
        this.utilizationCertificateFirstYearRupees = utilizationCertificateFirstYearRupees;
    }

    public String getUtilizationCertificateSecondYear() {
        return utilizationCertificateSecondYear;
    }

    public void setUtilizationCertificateSecondYear(String utilizationCertificateSecondYear) {
        this.utilizationCertificateSecondYear = utilizationCertificateSecondYear;
    }

    public String getUtilizationCertificatethird() {
        return utilizationCertificatethird;
    }

    public void setUtilizationCertificatethird(String utilizationCertificatethird) {
        this.utilizationCertificatethird = utilizationCertificatethird;
    }

    public String getUtilizationCertificateFourthYear() {
        return utilizationCertificateFourthYear;
    }

    public void setUtilizationCertificateFourthYear(String utilizationCertificateFourthYear) {
        this.utilizationCertificateFourthYear = utilizationCertificateFourthYear;
    }

    public String getUtilizationCertificateFifthYear() {
        return utilizationCertificateFifthYear;
    }

    public void setUtilizationCertificateFifthYear(String utilizationCertificateFifthYear) {
        this.utilizationCertificateFifthYear = utilizationCertificateFifthYear;
    }

    public String getUtilizationCertificateSixthYear() {
        return utilizationCertificateSixthYear;
    }

    public void setUtilizationCertificateSixthYear(String utilizationCertificateSixthYear) {
        this.utilizationCertificateSixthYear = utilizationCertificateSixthYear;
    }

    public String getFirstYearFund() {
        return firstYearFund;
    }

    public void setFirstYearFund(String firstYearFund) {
        this.firstYearFund = firstYearFund;
    }

    public String getFirstYearFundReleaseDate() {
        return firstYearFundReleaseDate;
    }

    public void setFirstYearFundReleaseDate(String firstYearFundReleaseDate) {
        this.firstYearFundReleaseDate = firstYearFundReleaseDate;
    }

    public String getSecondYearFund() {
        return secondYearFund;
    }

    public void setSecondYearFund(String secondYearFund) {
        this.secondYearFund = secondYearFund;
    }

    public String getSecondYearFundReleaseDate() {
        return secondYearFundReleaseDate;
    }

    public void setSecondYearFundReleaseDate(String secondYearFundReleaseDate) {
        this.secondYearFundReleaseDate = secondYearFundReleaseDate;
    }

    public String getThirdYearFund() {
        return thirdYearFund;
    }

    public void setThirdYearFund(String thirdYearFund) {
        this.thirdYearFund = thirdYearFund;
    }

    public String getThirdYearFundReleaseDate() {
        return thirdYearFundReleaseDate;
    }

    public void setThirdYearFundReleaseDate(String thirdYearFundReleaseDate) {
        this.thirdYearFundReleaseDate = thirdYearFundReleaseDate;
    }

    public String getFourthYearFund() {
        return fourthYearFund;
    }

    public void setFourthYearFund(String fourthYearFund) {
        this.fourthYearFund = fourthYearFund;
    }

    public String getFourthYearFundReleaseDate() {
        return fourthYearFundReleaseDate;
    }

    public void setFourthYearFundReleaseDate(String fourthYearFundReleaseDate) {
        this.fourthYearFundReleaseDate = fourthYearFundReleaseDate;
    }

    public String getFifthYearFund() {
        return fifthYearFund;
    }

    public void setFifthYearFund(String fifthYearFund) {
        this.fifthYearFund = fifthYearFund;
    }

    public String getFifthYearFundReleaseDate() {
        return fifthYearFundReleaseDate;
    }

    public void setFifthYearFundReleaseDate(String fifthYearFundReleaseDate) {
        this.fifthYearFundReleaseDate = fifthYearFundReleaseDate;
    }

    public String getSixthYearFund() {
        return sixthYearFund;
    }

    public void setSixthYearFund(String sixthYearFund) {
        this.sixthYearFund = sixthYearFund;
    }

    public String getSixthYearFundReleaseDate() {
        return sixthYearFundReleaseDate;
    }

    public void setSixthYearFundReleaseDate(String sixthYearFundReleaseDate) {
        this.sixthYearFundReleaseDate = sixthYearFundReleaseDate;
    }

    public String getSurvivalPercentageFirstYear() {
        return survivalPercentageFirstYear;
    }

    public void setSurvivalPercentageFirstYear(String survivalPercentageFirstYear) {
        this.survivalPercentageFirstYear = survivalPercentageFirstYear;
    }

    public String getSurvivalPercentageSecondYear() {
        return survivalPercentageSecondYear;
    }

    public void setSurvivalPercentageSecondYear(String survivalPercentageSecondYear) {
        this.survivalPercentageSecondYear = survivalPercentageSecondYear;
    }

    public String getSurvivalPercentageThirdYear() {
        return survivalPercentageThirdYear;
    }

    public void setSurvivalPercentageThirdYear(String survivalPercentageThirdYear) {
        this.survivalPercentageThirdYear = survivalPercentageThirdYear;
    }

    public String getSurvivalPercentageFourthYear() {
        return survivalPercentageFourthYear;
    }

    public void setSurvivalPercentageFourthYear(String survivalPercentageFourthYear) {
        this.survivalPercentageFourthYear = survivalPercentageFourthYear;
    }

    public String getSurvivalPercentageFifthYear() {
        return survivalPercentageFifthYear;
    }

    public void setSurvivalPercentageFifthYear(String survivalPercentageFifthYear) {
        this.survivalPercentageFifthYear = survivalPercentageFifthYear;
    }

    public String getSurvivalPercentageSixthYear() {
        return survivalPercentageSixthYear;
    }

    public void setSurvivalPercentageSixthYear(String survivalPercentageSixthYear) {
        this.survivalPercentageSixthYear = survivalPercentageSixthYear;
    }

    /*@Override
    public String toString() {
        return new ToStringBuilder(this).append("iD", iD).append("pOMOUNumberID", pOMOUNumberID).append("pOMOUNumber", pOMOUNumber).append("projectID", projectID).append("projectName", projectName).append("projectCost", projectCost).append("agency", agency).append("financialYearID", financialYearID).append("financialYear", financialYear).append("regionName", regionName).append("regionID", regionID).append("stateName", stateName).append("stateID", stateID).append("divisionName", divisionName).append("divisionID", divisionID).append("rangeName", rangeName).append("rangeID", rangeID).append("landArea", landArea).append("landAreaID", landAreaID).append("treeSpecies", treeSpecies).append("treeSpeciesID", treeSpeciesID).append("datefrom", datefrom).append("dateTo", dateTo).append("noOfPlant", noOfPlant).append("latitude", latitude).append("longitude", longitude).append("uploadingDate", uploadingDate).append("polygonDetai", polygonDetai).append("image1", image1).append("image2", image2).append("image3", image3).append("image4", image4).append("image5", image5).append("image6", image6).append("image7", image7).append("image8", image8).append("image9", image9).append("image10", image10).append("remarks", remarks).append("isActive", isActive).append("createdBy", createdBy).append("createdDate", createdDate).append("updatedBy", updatedBy).append("updateddate", updateddate).append("tPUID", tPUID).append("isRequested", isRequested).append("reviewUpdated", reviewUpdated).append("reviewDate", reviewDate).append("landTypeName", landTypeName).append("landTypeID", landTypeID).append("projectCodeName", projectCodeName).append("projectCodeId", projectCodeId).append("budgetHeadName", budgetHeadName).append("budgetHeadId", budgetHeadId).append("yearOfMaintence", yearOfMaintence).append("yearOfMaintenceId", yearOfMaintenceId).append("fundsApproved", fundsApproved).append("yearofPlantation", yearofPlantation).append("utilizationCertificateFirstYearRupees", utilizationCertificateFirstYearRupees).append("utilizationCertificateSecondYear", utilizationCertificateSecondYear).append("utilizationCertificatethird", utilizationCertificatethird).append("utilizationCertificateFourthYear", utilizationCertificateFourthYear).append("utilizationCertificateFifthYear", utilizationCertificateFifthYear).append("utilizationCertificateSixthYear", utilizationCertificateSixthYear).append("firstYearFund", firstYearFund).append("firstYearFundReleaseDate", firstYearFundReleaseDate).append("secondYearFund", secondYearFund).append("secondYearFundReleaseDate", secondYearFundReleaseDate).append("thirdYearFund", thirdYearFund).append("thirdYearFundReleaseDate", thirdYearFundReleaseDate).append("fourthYearFund", fourthYearFund).append("fourthYearFundReleaseDate", fourthYearFundReleaseDate).append("fifthYearFund", fifthYearFund).append("fifthYearFundReleaseDate", fifthYearFundReleaseDate).append("sixthYearFund", sixthYearFund).append("sixthYearFundReleaseDate", sixthYearFundReleaseDate).append("survivalPercentageFirstYear", survivalPercentageFirstYear).append("survivalPercentageSecondYear", survivalPercentageSecondYear).append("survivalPercentageThirdYear", survivalPercentageThirdYear).append("survivalPercentageFourthYear", survivalPercentageFourthYear).append("survivalPercentageFifthYear", survivalPercentageFifthYear).append("survivalPercentageSixthYear", survivalPercentageSixthYear).toString();
    }*/

}
