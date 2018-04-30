package edu.mum.cs490.project.model.form;

import org.springframework.format.annotation.DateTimeFormat;

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
    private Date begin_Date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_Date;

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
}
