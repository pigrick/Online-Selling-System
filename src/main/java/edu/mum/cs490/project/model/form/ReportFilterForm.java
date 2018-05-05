package edu.mum.cs490.project.model.form;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * Created by Tamir Ganbat 04/29/2018
 */
public class ReportFilterForm implements Serializable {

    private List<Integer> lstVendor_Id;

    private List<Integer> lstCategory_Id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @PastOrPresent(message = "Please choose a valid date.")
    private Date begin_Date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Please choose a valid date.")
    @Temporal(TemporalType.DATE)
    private Date end_Date;

//    private Boolean end_DateValid;

    public ReportFilterForm(){}

    public List<Integer> getLstVendor_Id() {
        return lstVendor_Id;
    }

    public void setLstVendor_Id(List<Integer> lstVendor_Id) {
        this.lstVendor_Id = lstVendor_Id;
    }

    public List<Integer> getLstCategory_Id() {
        return lstCategory_Id;
    }

    public void setLstCategory_Id(List<Integer> lstCategory_Id) {
        this.lstCategory_Id = lstCategory_Id;
    }

    public Date getBegin_Date() {
        return begin_Date;
    }

    public void setBegin_Date(Date begin_Date) {
        this.begin_Date = begin_Date;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }

//    @AssertTrue(message = "Please choose a date after From date." )
//    public boolean isEnd_DateValid() {
//        return this.begin_Date.before(this.end_Date) || this.begin_Date.equals(this.end_Date);
//    }

//    @AssertTrue(message = "Date is wrong" )
//    public Boolean getEnd_DateValid() {
//        return this.begin_Date.before(this.end_Date) || this.begin_Date.equals(this.end_Date);
//    }
//
//    public void setEnd_DateValid(Boolean end_DateValid) {
//        this.end_DateValid = end_DateValid;
//    }
}