package com.jenbumapps.core.model;

import com.jenbumapps.core.model.codec.ApproveStatus;
import com.jenbumapps.core.model.codec.FormType;
import com.jenbumapps.core.model.time.LocalDate;
import com.jenbumapps.core.model.time.LocalDateTime;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class EPass {
    private int id;
    private ApplicantDetail applicantDetail;
    private List<Traveller> travellers;
    private LocalDateTime dateOfJourney;
    private String vehicleRcNumber;
    private String DriverName;
    private long driverContact;
    private City city;
    private String routeOfJourney;
    private FormType formType;
    private List<EPassTerm> termsAndConditions;
    private DocumentDetail documentDetail;
    private String qrCodeUrl;
    private String permissionDetail;
    private LocalDate permitApprovalDate;
    private String idPrefix;
    private AuthorityDetail authorityDetail;
    private ApproveStatus status;
    private String rejectReason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ApplicantDetail getApplicantDetail() {
        return applicantDetail;
    }

    public void setApplicantDetail(ApplicantDetail applicantDetail) {
        this.applicantDetail = applicantDetail;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public LocalDateTime getDateOfJourney() {
        return dateOfJourney;
    }

    public void setDateOfJourney(LocalDateTime dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public String getVehicleRcNumber() {
        return vehicleRcNumber;
    }

    public void setVehicleRcNumber(String vehicleRcNumber) {
        this.vehicleRcNumber = vehicleRcNumber;
    }

    public String getDriverName() {
        return DriverName;
    }

    public void setDriverName(String DriverName) {
        this.DriverName = DriverName;
    }

    public long getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(long driverContact) {
        this.driverContact = driverContact;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getRouteOfJourney() {
        return routeOfJourney;
    }

    public void setRouteOfJourney(String routeOfJourney) {
        this.routeOfJourney = routeOfJourney;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public List<EPassTerm> getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(List<EPassTerm> termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public DocumentDetail getDocumentDetail() {
        return documentDetail;
    }

    public void setDocumentDetail(DocumentDetail documentDetail) {
        this.documentDetail = documentDetail;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getPermissionDetail() {
        return permissionDetail;
    }

    public void setPermissionDetail(String permissionDetail) {
        this.permissionDetail = permissionDetail;
    }

    public LocalDate getPermitApprovalDate() {
        return permitApprovalDate;
    }

    public void setPermitApprovalDate(LocalDate permitApprovalDate) {
        this.permitApprovalDate = permitApprovalDate;
    }

    public String getIdPrefix() {
        return idPrefix;
    }

    public void setIdPrefix(String idPrefix) {
        this.idPrefix = idPrefix;
    }

    public AuthorityDetail getAuthorityDetail() {
        return authorityDetail;
    }

    public void setAuthorityDetail(AuthorityDetail authorityDetail) {
        this.authorityDetail = authorityDetail;
    }

    public ApproveStatus getStatus() {
        return status;
    }

    public void setStatus(ApproveStatus status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
