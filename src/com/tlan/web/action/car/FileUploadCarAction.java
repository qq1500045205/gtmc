package com.tlan.web.action.car;

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
import com.tlan.common.model.car.WxModuleContentCar;
import com.tlan.common.model.car.WxModuleContentCarPara;
import com.tlan.common.model.car.WxModuleContentCarType;
import com.tlan.common.model.car.WxModuleContentGzh;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;

/**
 * 导入xlsx
 * 
 * @author magenm 2014-1-10
 * 
 */

@SuppressWarnings("serial")
public class FileUploadCarAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentCarType> {
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentGzh> ContentGzhService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarType> contentCartTypeService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCar> contentCarService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentCarPara> carParaService;

	private WxModuleContentCarType carType;
	public static WxModuleContentGzh gzh = new WxModuleContentGzh();
	public static WxModuleContentCar car = new WxModuleContentCar();
	public static WxModuleContentCarPara carPara = new WxModuleContentCarPara();

	private File inputfile;
	private String inputfileFileName;
	private String inputfileContentType;
	private ServletContext context1;

	public ServletContext getContext1() {
		return context1;
	}

	public void setContext1(ServletContext context1) {
		this.context1 = context1;
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
	
	private int length2;

	private LoginBean loginBean = obtainLoginBean();

	public String readXlsToInsert() throws Exception {
		String gzhHash = loginBean.getGzhHash();
		if (getInputfile() == null) {
			return INPUT;
		}
//		List<WxModuleContentGzh> gzhs = ContentGzhService.getAll(
//				WxModuleContentGzh.class, "gzhHash", gzhHash);
//		if (gzhs.size()>0) {
//			return INPUT;
//		}
		try {
			InputStream read = new FileInputStream(getInputfile());

			HSSFWorkbook workbook = new HSSFWorkbook(read); // 创建新的excel
			// 获取所有sheets
			int sheetLength = workbook.getNumberOfSheets();
			HSSFSheet sheet;
			for (int i = 0; i < sheetLength; i++) {// 遍历每一个sheet
				sheet = workbook.getSheetAt(i); // 创建sheet页
				int length = sheet.getLastRowNum(); // 获取最后一条数据的行数
				System.out.println(length);
				length2 = length;
				if (length <= 0) {
					continue;
				}
				String carTypeGuid = "";// 车型号
				String carTypename = "";
				// 第一行是标题,从第二行开始读取
				for (int rowNum = 1; rowNum <= 1; rowNum++) {
					Row row = sheet.getRow(rowNum);
					carTypename = row.getCell(0).toString();
					if (carTypename.equals("")) {
						continue;
					}
					carTypeGuid = DataUtils.getUUID();
					carType.setCarTypeGuid(carTypeGuid);
					carType.setCarTypeTitle(carTypename);
					carType.setCarTypeSortid(i*10);
					carType.setCreatedOn(DateUtils.currentDatetime());
					carType.setDeleteTag(0);
					gzh.setGzhGuid(DataUtils.getUUID());
					gzh.setGzhHash(gzhHash);
					gzh.setCarTypeGuid(carTypeGuid);
					gzh.setCreatedOn(DateUtils.currentDatetime());
					gzh.setDeleteTag(0);
					try {
						ContentGzhService.save(gzh);
						contentCartTypeService.save(carType);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				// 处理子型号
				String carSfxName = "";
				String carSfxGuid = "";
				String carNaviName = "";
				String carNaviGuid = "";
				for (int rowNum = 1; rowNum < length; rowNum++) {
					Row row = sheet.getRow(rowNum);
					String name = row.getCell(1).toString();// 获取型号数据;
					if (!name.equals(carSfxName)) {
						carSfxGuid = DataUtils.getUUID();
						car.setCarGuid(carSfxGuid);
						car.setCarName(carTypename + "-" + name);
						car.setCarSortid("0");
						car.setCarCartypeGuid(carTypeGuid);
						car.setCarPrice(row.getCell(2).toString());// 第三列是价格
						car.setCreatedOn(DateUtils.currentDatetime());
						car.setDeleteTag(0);
						try {
							contentCarService.save(car);
							carSfxName = name;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					String carNavi = row.getCell(3).toString();// 获取参数类型;
					if (!carNavi.equals(carNaviName)) {
						carNaviGuid = DataUtils.getUUID();
						carPara.setCarParaGuid(carNaviGuid);
						carPara.setCarParaName(carNavi);
						carPara.setCarParaParentId("0");
						carPara.setCarParaCarGuid(carSfxGuid);
						carPara.setCarParaSortid("0");
						carPara.setCreatedOn(DateUtils.currentDatetime());
						carPara.setDeleteTag(0);
						try {
							carParaService.save(carPara);
							carNaviName = carNavi;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					String paraName = row.getCell(4).toString();// 获取参数名称;
					String parValue = row.getCell(5).toString();// 获取参数值;
					carPara.setCarParaGuid(DataUtils.getUUID());
					carPara.setCarParaName(paraName);
					carPara.setCarParaValue(parValue);
					carPara.setCarParaCarGuid(carSfxGuid);
					carPara.setCarParaParentId(carNaviGuid);
					carPara.setCarParaSortid("0");
					carPara.setCreatedOn(DateUtils.currentDatetime());
					carPara.setDeleteTag(0);
					try {
						carParaService.save(carPara);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			// 处理子型号对应的参数类型
		} catch (Exception e) {
			System.out.println("Error Happened!this sheet data length = " + length2);
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {
		if (null == carType) {
			carType = new WxModuleContentCarType();
		}
	}

	@Override
	public WxModuleContentCarType getModel() {
		return carType;
	}

}
