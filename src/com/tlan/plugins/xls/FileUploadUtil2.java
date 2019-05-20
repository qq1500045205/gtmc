package com.tlan.plugins.xls;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.FileUtils;
import com.tlan.common.utils.Pinyin4jUtil;

/**
 * 导入xlsx
 * 
 * @author magenm 2014-1-10
 * 
 */
public class FileUploadUtil2 {
	private static Log log = LogFactory.getLog(FileUploadUtil2.class);

	private String tableName; // = "wx_import_user";
	private String gzhHash;
	private Workbook wkbook;
	private int index = 0;
	public final static String IGNORE = "insert ignore into ";
	public final static String REPALCE = "replace into ";
	public final static String NORMAL = "insert into ";
	private static String car;

	public static void main(String[] args) throws Exception {
		FileUploadUtil2 uitl = new FileUploadUtil2();
		Workbook wkbook = uitl.createWorkbook("d:\\1234.xls");
		// 获取第一个表格!
		Sheet sheet = wkbook.getSheetAt(0);
		StringBuffer sb = new StringBuffer();
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			System.out.println(rowNum);
			Row row = sheet.getRow(rowNum);
			String type = row.getCell(0).getStringCellValue();
			if(rowNum ==1){
				
			}
			getSql(row);
		}
		
		FileUtils fileUtils = new FileUtils();
		fileUtils.createFile("d:\\sql.txt", sb.toString());
		
		// 获取建表语句
		// String sql = getCreateSql(sheet);
		// System.out.println(sql);
		// 获取标题
		// List<String> titlelist = getTitle(sheet);
		// readXlsToInsert(sheet);

	}
	
	public static void getSql(Row row){
		
		car = row.getCell(1).getStringCellValue();
		String price = row.getCell(2).getStringCellValue();
		String guid = DataUtils.getUUID();
		//second sql  guid
		String paramType = row.getCell(3).getStringCellValue();
		String paramName = row.getCell(4).getStringCellValue();
		String param = row.getCell(5).getStringCellValue();
		//third sql 
		
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

	public FileUploadUtil2() {
	}

	public FileUploadUtil2(String path, String tableName, String gzhHash) {
		this.gzhHash = gzhHash;
		try {
			wkbook = createWorkbook(path, tableName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
	}

	/**
	 * 创建Workbook
	 * 
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public Workbook createWorkbook(String path, String tableName)
			throws Exception {
		this.tableName = tableName;
		InputStream ins = new FileInputStream(path);
		Workbook wkbook = WorkbookFactory.create(ins);
		return wkbook;
	}

	/**
	 * 获取建表语句
	 * 
	 * @param sheet
	 * @return
	 */
	public String getCreateSql() {
		Sheet sheet = wkbook.getSheetAt(index);
		// 获取标题
		Row row = sheet.getRow(0);
		StringBuffer sb = new StringBuffer();
		sb.append("create table ");
		sb.append(tableName);
		sb.append(" (");
		for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
			String col = getCellFormatValue(row.getCell(i));
			sb.append(Pinyin4jUtil.getPinYin(col));
			sb.append("  varchar(255) COMMENT '" + col + "'");
			if (i == 0) {
				sb.append("  not null primary key ");
			}
			if (i < row.getPhysicalNumberOfCells() - 1) {
				sb.append(",");
			}
		}
		sb.append(");");
		return sb.toString();
	}

	/**
	 * 获取标题
	 * 
	 * @param sheet
	 * @return
	 */
	public List<String> getTitle() {
		// 获取标题
		Sheet sheet = wkbook.getSheetAt(index);
		Row row = sheet.getRow(0);
		List<String> titleList = new ArrayList<>();
		for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
			// System.out.println(Pinyin4jUtil.getPinYin(getCellFormatValue(row.getCell(i))));
			titleList.add(row.getCell(i).getStringCellValue());
		}
		return titleList;
	}

	public List<List<String>> readXls() throws Exception {
		Sheet sheet = wkbook.getSheetAt(index);
		List<List<String>> list = new ArrayList<>();
		// 第一行是标题,从第二行开始读取
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			List<String> rowList = new ArrayList<>();
			for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
				rowList.add(getCellFormatValue(row.getCell(i)));
			}
			list.add(rowList);
		}
		return list;
	}

	public List<String> getInsertSql(String insertType) throws Exception {
		Sheet sheet = wkbook.getSheetAt(index);
		List<String> list = new ArrayList<>();
		// 第一行是标题,从第二行开始读取
		for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
			Row row = sheet.getRow(rowNum);
			if (row == null) {
				continue;
			}
			StringBuffer sbValue = new StringBuffer();
			StringBuffer sbName = new StringBuffer();
			for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
				sbName.append(Pinyin4jUtil.getPinYin(sheet.getRow(0).getCell(i)
						.getStringCellValue()));
				sbValue.append("'");
				sbValue.append(getCellFormatValue(row.getCell(i)));
				sbValue.append("'");
				if (i < row.getPhysicalNumberOfCells() - 1) {
					sbValue.append(",");
					sbName.append(",");
				}
			}
			StringBuffer result = new StringBuffer();
			result.append(insertType);
			result.append(tableName);
			result.append("(");
			result.append("gzh_hash");
			result.append(",");
			result.append(sbName);
			result.append(") values (");
			result.append("'");
			result.append(gzhHash);
			result.append("',");
			result.append(sbValue);
			result.append(");");
			System.out.println(result.toString());
			list.add(result.toString());
		}
		return list;
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

}
