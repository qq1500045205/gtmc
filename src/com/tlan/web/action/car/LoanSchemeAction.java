package com.tlan.web.action.car;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.car.WxModuleContentLoanScheme;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.ExportUtil;

public class LoanSchemeAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentLoanScheme> {

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentLoanScheme> loanSchemeService;

	private WxModuleContentLoanScheme loanScheme;
	private LoginBean loginBean = obtainLoginBean();

	private String[] items;

	private File inputfile;
	private String inputfileFileName;
	private String inputfileContentType;
	private ServletContext context1;
	
	private String filename;
	private ByteArrayInputStream inputStream;

	public String getAllLoanScheme() {
		try {
			List<WxModuleContentLoanScheme> loanSchemes = loanSchemeService
					.getAll(WxModuleContentLoanScheme.class, new String[] {},
							new Object[] {}, new String[] {}, "loanSchemeName asc, monthNum asc, firstPayPercent asc");

			setMap(true, "success", loanSchemes);
		} catch (Exception e) {
			setMap(true, "fail", null);
		}

		return this.SUCCESS;
	}

	public String deleteLoanScheme() {
		if (null != items) {
			for (int i = 0; i < items.length; i++) {
				// 删除关键字
				loanSchemeService.delete(WxModuleContentLoanScheme.class,
						"loan_scheme_guid", items[i]);
			}
			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}

		return this.SUCCESS;
	}

	public String exportLoan() {
		List<WxModuleContentLoanScheme> answers = loanSchemeService.getAll(
				WxModuleContentLoanScheme.class, new String[] {},
				new Object[] {}, new String[] {}, "loanSchemeName asc, monthNum asc, firstPayPercent asc");

		String[] headers = new String[] { "loanSchemeName", "firstPayPercent",
				"lastPayPercent", "monthNum", "payPerMonthPercent" };
		String[] headerNames = new String[] { "方案名", "首付比例", "尾款比例", "贷款期数",
				"万元月供系数" };
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ExportUtil<WxModuleContentLoanScheme> data = new ExportUtil<WxModuleContentLoanScheme>();
		data.export("贷款方案", headerNames, headers,null, answers, out, "yyyy-MM-dd");

		filename = "loan.xls";
		inputStream = new ByteArrayInputStream(out.toByteArray());

		return this.SUCCESS;
	}

	public String importLoan() {
		if (null == inputfile) {
			setMap(true, "无导入文件", null);
		}
		try {
			loanSchemeService.deleteAll(WxModuleContentLoanScheme.class);// 清空数据库
			InputStream read = new FileInputStream(inputfile);
			HSSFWorkbook workbook = new HSSFWorkbook(read); // 创建新的excel
			// 获取所有sheets
			int sheetLength = workbook.getNumberOfSheets();
			HSSFSheet sheet;
			for (int i = 0; i < sheetLength; i++) {// 遍历每一个sheet
				sheet = workbook.getSheetAt(i); // 创建sheet页
				int length = sheet.getLastRowNum(); // 获取最后一条数据的行数
				if (length <= 0) {
					continue;
				}
				for (int rowNum = 1; rowNum <= length; rowNum++) {
					WxModuleContentLoanScheme loan = new WxModuleContentLoanScheme();
					Row row = sheet.getRow(rowNum);
					loan.setLoanSchemeName(row.getCell(0).toString());
					loan.setFirstPayPercent(row.getCell(1).toString());
					loan.setLastPayPercent(row.getCell(2).toString());
					loan.setMonthNum((int)Double
							.parseDouble(row.getCell(3).toString()));
					loan.setPayPerMonthPercent(row.getCell(4).toString());
					loan.setLoanSchemeGuid(DataUtils.getUUID());

					loanSchemeService.save(loan);
				}
			}
			setMap(true, "导入成功", null);
		} catch (Exception e) {
			System.out.println(e.toString());
			setMap(true, "导入失败", null);
		}
		return this.SUCCESS;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getInputfile() {
		return inputfile;
	}

	public void setInputfile(File inputfile) {
		this.inputfile = inputfile;
	}

	public String getInputfileFileName() {
		return inputfileFileName;
	}

	public void setInputfileFileName(String inputfileFileName) {
		this.inputfileFileName = inputfileFileName;
	}

	public String getInputfileContentType() {
		return inputfileContentType;
	}

	public void setInputfileContentType(String inputfileContentType) {
		this.inputfileContentType = inputfileContentType;
	}

	public ServletContext getContext1() {
		return context1;
	}

	public void setContext1(ServletContext context1) {
		this.context1 = context1;
	}

	@Override
	public WxModuleContentLoanScheme getModel() {
		// TODO Auto-generated method stub
		return loanScheme;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (loanScheme == null) {
			loanScheme = new WxModuleContentLoanScheme();
		}
	}

}
