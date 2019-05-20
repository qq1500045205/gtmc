package com.tlan.admin.gtmc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.tlan.common.action.BaseAction;
import com.tlan.common.model.WxProject;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;

/**
 * 
 * 导入救援电话
 * 
 * @PackageName:com.tlan.admin.gtmc
 * @ClassName:ImportHelpTelAction
 * @Description:TODO
 * @author magenming@tlan.com.cn
 * @date 2014年3月20日 下午1:57:34
 * 
 */
public class ImportHelpTelAction extends BaseAction {
	private static final long serialVersionUID = 7971378868522961101L;

	private static Log log = LogFactory.getLog(ImportHelpTelAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxProject> projService;

	private File xlsFile;
	private String message;

	/**
	 * 导入救援电话
	 * 
	 * @Title: importData
	 * @Description: TODO
	 * @param @return
	 * @return String
	 * @throws
	 * @Date 2014年3月20日 下午1:48:20
	 */
	public String importData() {
		String path = xlsFile.getAbsolutePath();
		try {
			projService.executeSql(readXls(createWorkbook(path)));
			log.info("import help_tel success");
			message = "{success:true,message:\"上传成功\"}";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("import help_tel error:" + e.getMessage());
			message = "{success:false,message:\"上传失败\"}";
		}
		return SUCCESS;
	}

	/**
	 * 创建Workbook
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public Workbook createWorkbook(String path) throws Exception {
		InputStream ins = new FileInputStream(path);
		Workbook wkbook = WorkbookFactory.create(ins);
		return wkbook;
	}

	public List<String> readXls(Workbook wkbook) throws Exception {
		Sheet sheet = wkbook.getSheetAt(0);
		List<String> rowList = new ArrayList<>();
		// 第一行是标题,从第二行开始读取
		for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			String sql = "update wx_project set help_tel='{1}',as_tel='{3}',mobile='{4}' where project_guid='{2}'";
			// 获取第三列，经销店编码
			sql = sql.replace("{2}", getCellFormatValue(row.getCell(2)));
			// 获取第七列，区号
			String code = getCellFormatValue(row.getCell(6));
			
			// 获取第八列，服务热线（售后电话）
			String asTel = getCellFormatValue(row.getCell(7)).trim();
			if (asTel.indexOf("/") > -1) {
				asTel = asTel.split("/")[0].trim();
			}
			if (!DataUtils.checkTelephone(asTel) && !asTel.startsWith("400-")) {
				asTel = code + "-" + asTel;
			}
			sql = sql.replace("{3}", asTel);
			
			// 获取第十一列，销售热线
			String mobile = getCellFormatValue(row.getCell(10)).trim();
			if (mobile.indexOf("/") > -1) {
				mobile = mobile.split("/")[0].trim();
			}
			if (!DataUtils.checkTelephone(mobile) && !asTel.startsWith("400-")) {
				mobile = code + "-" + mobile;
			}
			sql = sql.replace("{4}", mobile);
			
			// 获取第九列，救援电话
			String tel = getCellFormatValue(row.getCell(8)).trim();
			if (tel.indexOf("/") > -1) {
				tel = tel.split("/")[0].trim();
			}
			if (!DataUtils.checkTelephone(tel) && !tel.startsWith("400-")) {
				tel = code + "-" + tel;
			}
			sql = sql.replace("{1}", tel);
			rowList.add(sql);
		}
		return rowList;
	}

	private String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，取得该Cell的Date值
					Date date = cell.getDateCellValue();
					// 把Date转换成本地格式的字符串
					cellvalue = cell.getDateCellValue().toLocaleString();
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cell.setCellType(Cell.CELL_TYPE_STRING);
					cellvalue = cell.getStringCellValue();
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getStringCellValue().replaceAll("'", "''");
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	public File getXlsFile() {
		return xlsFile;
	}

	public void setXlsFile(File xlsFile) {
		this.xlsFile = xlsFile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
