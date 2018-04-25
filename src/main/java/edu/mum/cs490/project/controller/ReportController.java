package edu.mum.cs490.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View.*;

import edu.mum.cs490.project.service.*;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping("/report")
public class ReportController {
	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping(value = "adminReport", method = RequestMethod.GET)
	public ModelAndView adminReport() {
//		JasperReportsPdfView view = new JasperReportsPdfView();
//		view.setUrl("classpath:jpreport/AdminReport.jrxml");
//		view.setApplicationContext(applicationContext);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("datasource", orderDetailService.report());

		return new ModelAndView("", params);

	}
}
