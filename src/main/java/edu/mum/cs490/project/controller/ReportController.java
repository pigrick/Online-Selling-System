package edu.mum.cs490.project.controller;

import edu.mum.cs490.project.domain.*;
import edu.mum.cs490.project.model.form.ReportFilterForm;
import edu.mum.cs490.project.service.CategoryService;
import edu.mum.cs490.project.service.OrderDetailService;
import edu.mum.cs490.project.service.VendorService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

	private final OrderDetailService orderDetailService;
	private final VendorService vendorService;
	private final CategoryService categoryService;

	@Autowired
	public ReportController(OrderDetailService orderDetailService, VendorService vendorService, CategoryService categoryService){
		this.orderDetailService = orderDetailService;
		this.vendorService = vendorService;
		this.categoryService = categoryService;
	}

	@GetMapping(value = "/reportFilter")
	public String adminReportLoading(Model model, @ModelAttribute("reportFilterForm") ReportFilterForm reportFilterForm) {
		List<Vendor> vendorList = vendorService.find("", "", Status.ENABLED);
		List<Category> categoryList = categoryService.find(null, null, Status.ENABLED);
		model.addAttribute("vendors", vendorList);
		model.addAttribute("categories", categoryList);
		return "report/reportFilter";
	}

	@PostMapping(value = "/reportFilter")
	public void adminReportExport(Model model, @ModelAttribute("reportFilterForm") ReportFilterForm reportFilterForm, BindingResult bindingResult, @AuthenticationPrincipal User user, HttpServletResponse response) {
		if (bindingResult.hasErrors()) {
			//return "report/errorPage";
		}

		report(reportFilterForm, user, response);

		//return "report/reportFilter";
	}

	public void report(ReportFilterForm reportFilterForm, @AuthenticationPrincipal User user, HttpServletResponse response) {

		if(reportFilterForm.getLstVendor_Id() == null)
			reportFilterForm.setLstVendor_Id(new ArrayList<>());
		if(reportFilterForm.getLstCategory_Id() == null)
			reportFilterForm.setLstCategory_Id(new ArrayList<>());

		String reportName = "";
		if(user instanceof Vendor) {
			reportName = "reportForVendor";
			if(!reportFilterForm.getLstVendor_Id().isEmpty())
				reportFilterForm.setLstVendor_Id(new ArrayList<>());

			reportFilterForm.getLstVendor_Id().add(user.getId());
		} else if(user instanceof Admin) {
			reportName = "report";
		}
		List<OrderDetail> list = collectData(reportFilterForm.getLstVendor_Id(), reportFilterForm.getLstCategory_Id(), reportFilterForm.getBegin_Date(), reportFilterForm.getEnd_Date());

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		// Initialize response
		response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand.
		// set pdf content
		response.setContentType("application/pdf");
		// if you want to download instead of opening inline
		// response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
		try {
			byte[] bytes=generateJasperReportPDF(reportName, outputStream, list);
			// write the content to the output stream
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			bos.write(bytes);
			bos.flush();
			bos.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("<<<<File Not Fount Exception>>>>");
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("<<<<Input Output Stream Exception>>>>");
		} catch (JRException e) {
			e.printStackTrace();
			System.out.println("<<<<JRException Stream Exception>>>>");
		} finally{		}
	}

	private List<OrderDetail> collectData(List<Integer> lstVendorId, List<Integer> lstCategoryId, Date beging_Date, Date end_Date) {
		List<OrderDetail> lstOrderDetail = new ArrayList<>();

		if(lstVendorId.isEmpty() && lstCategoryId.isEmpty())
			lstOrderDetail.addAll(orderDetailService.findByDate(beging_Date, end_Date));
		else if(lstVendorId.isEmpty())
			lstOrderDetail.addAll(orderDetailService.findByCategory_Id(lstCategoryId, beging_Date, end_Date));
		else if(lstCategoryId.isEmpty())
			lstOrderDetail.addAll(orderDetailService.findByVendor_Id(lstVendorId, beging_Date, end_Date));
		else
			lstOrderDetail.addAll(orderDetailService.findByVendor_IdAndCategory_Id(lstVendorId, lstCategoryId, beging_Date, end_Date));

		if(!lstOrderDetail.isEmpty()) {
			lstOrderDetail.get(0).setBeginDate(beging_Date);
			lstOrderDetail.get(0).setEndDate(end_Date);
		}
		return lstOrderDetail;
	}

	private byte[] generateJasperReportPDF(String jasperReportName, ByteArrayOutputStream outputStream, List<OrderDetail> data) throws IOException, JRException {
		JRPdfExporter exporter = new JRPdfExporter();
		JasperReport jasperReport = null;
		InputStream jrxmlInput = JRLoader.getResourceInputStream("jpreport/"+jasperReportName+".jrxml");

		try
		{
			if (jrxmlInput != null)
			{
				JasperDesign design = JRXmlLoader.load(jrxmlInput);
				jasperReport = JasperCompileManager.compileReport(design);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRBeanCollectionDataSource(data));

				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
				exporter.exportReport();
			}
		} catch (JRException e) {
			e.printStackTrace();
		} finally {
			jrxmlInput.close();
		}
		return outputStream.toByteArray();
	}

	private byte[] generateJasperReportXLS(String jasperReportName, ByteArrayOutputStream outputStream, List<OrderDetail> data) throws IOException, JRException {
		JRXlsExporter exporter = new JRXlsExporter();
		JasperReport jasperReport = null;
		InputStream jrxmlInput = JRLoader.getResourceInputStream("jpreport/"+jasperReportName+".jrxml");

		try
		{
			if (jrxmlInput != null)
			{
				JasperDesign design = JRXmlLoader.load(jrxmlInput);
				jasperReport = JasperCompileManager.compileReport(design);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, new JRBeanCollectionDataSource(data));

				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
				exporter.exportReport();
			}
		} catch (JRException e) {
			e.printStackTrace();
		} finally {
			jrxmlInput.close();
		}
		return outputStream.toByteArray();
	}
}
