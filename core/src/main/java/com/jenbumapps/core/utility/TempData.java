package com.jenbumapps.core.utility;

import com.jenbumapps.core.model.ApplicantDetail;
import com.jenbumapps.core.model.AuthorityDetail;
import com.jenbumapps.core.model.EPass;
import com.jenbumapps.core.model.Traveller;
import com.jenbumapps.core.model.codec.FormType;
import com.jenbumapps.core.model.codec.TravellerType;
import com.jenbumapps.core.model.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class TempData {

    public static EPass getFromData(){

        EPass form = new EPass();
        form.setId(1);
        form.setFormType(FormType.LABOUR_STUDENT);
        ApplicantDetail applicantDetail = new ApplicantDetail();
        applicantDetail.setName("Mainong");
        applicantDetail.setContact(7973824311L);
        applicantDetail.setAddress("Bordumsa");
        form.setApplicantDetail(applicantDetail);

        AuthorityDetail authorityDetail = new AuthorityDetail();
        authorityDetail.setAuthorityName("Minga Sherpa");
        authorityDetail.setAuthorityDesignation("Additional Deputy Commissioner");
        authorityDetail.setAuthoritySign("https://upload.wikimedia.org/wikipedia/en/5/56/Signature_of_Clarence_King.jpg");
        authorityDetail.setAuthorityAddress("Bordumsa");

        form.setAuthorityDetail(authorityDetail);

        form.setPermissionDetail("");
        form.setIdPrefix("NO.B/JUD-18(COVID-19)/2020/12345");
        form.setPermitApprovalDate(LocalDate.now());
        form.setQrCodeUrl("https://boofcv.org/images/thumb/3/35/Example_rendered_qrcode.png/400px-Example_rendered_qrcode.png");

//        form.setTermsAndConditions(terms);

        List<Traveller> travellers = new ArrayList<>();
        Traveller t1 = new Traveller();
        t1.setName("Mainong");
        t1.setType(TravellerType.PATIENT);

        Traveller t2 = new Traveller();
        t2.setName("Some Driver");
        t2.setType(TravellerType.DRIVER);

        travellers.add(t1);
        travellers.add(t2);

        form.setTravellers(travellers);
//        form.setDateOfJourney(LocalDate.now());
        form.setVehicleRcNumber("AS23 5600");
        form.setDriverName("Some Driver");
        form.setDriverContact(9999999999L);
        form.setRouteOfJourney("From Bordumsa to Tinsukia via Namchik Gate");

        return form;

    }
}
