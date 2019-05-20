package com.tlan.admin.plugins;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.tlan.common.action.BaseAction;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.FileUtils;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;

/**
 * 上传图片
 * 
 * @author magenm
 * 
 */
public class EditUploadAction extends BaseAction {

	private static final long serialVersionUID = 633651513301635751L;
	private static Log log = LogFactory.getLog(EditUploadAction.class);

	private File imgFile;
	private String imgFileFileName;
	private Map<String, Object> dataMap = new HashMap<>();

	/**
	 * 富文本编辑器上传图片
	 * 
	 * @return
	 */
	public String uploadImage() {
		log.info("富文本编辑器图片上传");
		String prefix = DataUtils.getSimpleUUID();

		// 得到文件的扩展名(无扩展名时将得到全名)
		String ext = imgFileFileName
				.substring(imgFileFileName.lastIndexOf(".") + 1);
		String fileName = prefix + "." + ext.toLowerCase();
		// 图片
		if (PropertiesUtil.getProperty(ParamString.IMAGE_TYPE).contains(ext)) {
			// 图片处理
			String size = PropertiesUtil
					.getProperty(ParamString.IMAGE_MAX_SIZE);
			// 判定文件类型
			if (imgFile.length() > Long.parseLong(size)) { // 判断文件大小
				dataMap.put("error", 1);
				dataMap.put("message", "上传文件出错，文件最大支持：" + size + "K");
			} else {
				String uploadFilePath = PropertiesUtil
						.getProperty(ParamString.UPLOAD_FILE_PATH);
				String filePath = uploadFilePath + fileName;
				// 上传文件
				FileUtils fileUtils = new FileUtils();
				fileUtils.copyFile(imgFile.getPath(), ServletActionContext
						.getServletContext().getRealPath(uploadFilePath)
						+ File.separator + fileName);
				if (fileUtils.getMessage() != null) {
					dataMap.put("error", 1);
					dataMap.put("message", "上传文件失败。");
				} else {
					dataMap.put("error", 0);
					dataMap.put("url",
							PropertiesUtil.getProperty(ParamString.BSEE_PATH)
									+ filePath.trim());
				}
			}
		} else if (PropertiesUtil.getProperty(ParamString.VIDEO_TYPE).contains(
				ext)) { // 视频
			String uploadVideoPath = PropertiesUtil
					.getProperty(ParamString.UPLOAD_VIDEO_PATH);
			String filepath = uploadVideoPath + fileName;
			// 判断文件大小
			String size = PropertiesUtil
					.getProperty(ParamString.VIDEO_MAX_SIZE);
			if (imgFile.length() > Integer.parseInt(size)) {
				dataMap.put("error", 1);
				dataMap.put("message", "上传文件出错，文件最大支持：" + size + "K");
			} else {
				// 上传文件
				FileUtils fileUtils = new FileUtils();
				fileUtils.copyFile(imgFile.getPath(), ServletActionContext
						.getServletContext().getRealPath(uploadVideoPath)
						+ File.separator + fileName);
				if (fileUtils.getMessage() != null) {
					dataMap.put("error", 1);
					dataMap.put("message", "上传文件出错：" + fileUtils.getMessage());
				} else {
					dataMap.put("error", 0);
					dataMap.put("url",
							PropertiesUtil.getProperty(ParamString.BSEE_PATH)
									+ filepath.trim());
				}
			}
		} else if (PropertiesUtil.getProperty(ParamString.MUSIC_TYPE).contains(
				ext)) { // 音频
			String uploadMusicPath = PropertiesUtil
					.getProperty(ParamString.UPLOAD_MUSIC_PATH);
			String filepath = uploadMusicPath + fileName;
			// 判断文件大小
			String size = PropertiesUtil
					.getProperty(ParamString.VIDEO_MAX_SIZE);
			if (imgFile.length() > Integer.parseInt(size)) {
				dataMap.put("error", 1);
				dataMap.put("message", "上传文件出错，文件最大支持：" + size + "K");
			}

			// 上传文件
			FileUtils fileUtils = new FileUtils();
			fileUtils.copyFile(imgFile.getPath(), ServletActionContext
					.getServletContext().getRealPath(uploadMusicPath)
					+ File.separator + fileName);
			if (fileUtils.getMessage() != null) {
				dataMap.put("error", 1);
				dataMap.put("message", "上传文件出错：" + fileUtils.getMessage());
			}else{
				dataMap.put("error", 0);
				dataMap.put("url",
						PropertiesUtil.getProperty(ParamString.BSEE_PATH)
								+ filepath.trim());
			}
		}

		return this.SUCCESS;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

}
