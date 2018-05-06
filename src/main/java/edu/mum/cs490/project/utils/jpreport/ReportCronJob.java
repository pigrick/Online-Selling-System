package edu.mum.cs490.project.utils.jpreport;

import edu.mum.cs490.project.controller.ReportController;
import edu.mum.cs490.project.service.MailService;
import edu.mum.cs490.project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Calendar;
import java.util.Date;

public class ReportCronJob {

    @Autowired
    ReportService reportService;

    @Autowired
    MailService mailService;

    @Autowired
    ReportController reportController;

    public Date beginDateOfMonth() {
        Date endDate = new Date();
        Date beginDate = new Date(endDate.getYear(), endDate.getMonth() == 0 ? 11 : endDate.getMonth() - 1, endDate.getDay());
        return beginDate;
    }

    public Date beginDateOfWeek() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date beginDate = calendar.getTime();
        return beginDate;
    }

    @Scheduled(cron = "0 0/1 * * 0-11 * ?")
    public void monthlyReport() {
//        reportController.sendReportToVendor(beginDateOfMonth());
        System.out.println("Test cron job");
    }

    @Scheduled(cron = "0 0 0 * * Sun ?")
    public void weeklyReport() {
        reportController.sendReportToVendor(beginDateOfWeek());
    }


}
