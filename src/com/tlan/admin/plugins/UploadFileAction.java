package com.tlan.admin.plugins;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.FileUtils;
import com.tlan.common.utils.PropertiesUtil;
import com.tlan.contrants.ParamString;

/**
 * 上传文件
 * 
 * @author magenm
 * 
 */
public class UploadFileAction extends ActionSupport {

	private static final long serialVersionUID = 633651513301635751L;
	private static Log log = LogFactory.getLog(UploadFileAction.class);

	public static final String VIDEO = "video";
	public static final String MUSIC = "music";
	public static final String IMAGE = "image";

	// 这个名字为了保持和om-fileupload.js 一致
	// 没有Filedata的原因是为了支持om框架升级
	private File Filedata;
	private String FiledataFileName;
	private String type;

	// 提示信息
	private String message;
	private String filepath;
	private String filetype;
	private int width;
	private int height;

	/**
	 * 获取文件后缀
	 * 
	 * @param filename
	 * @return
	 */
	private String getSuffix(String filename) {
		String suffix = "";
		int pos = filename.lastIndexOf('.');
		if (pos > 0 && pos < filename.length() - 1) {
			suffix = filename.substring(pos + 1);
		}
		return suffix;
	}

	/**
	 * 上传文件
	 * 
	 * @return
	 */
	public String uploadImage() {
		// 文件名处理
		String fileName = DataUtils.getUniqueName(FiledataFileName);
		String ext = getSuffix(FiledataFileName).toLowerCase();
		// 图片
		if (PropertiesUtil.getProperty(ParamString.IMAGE_TYPE).contains(ext)) {
			String uploadFilePath = PropertiesUtil
					.getProperty(ParamString.UPLOAD_FILE_PATH);
			filepath = uploadFilePath + fileName;
			// 判断文件大小
			String size = PropertiesUtil
					.getProperty(ParamString.IMAGE_MAX_SIZE);
			if (Filedata.length() > Integer.parseInt(size)) {
				message = "上传文件出错，文件过大。";
				log.error(message);
				return this.INPUT;
			}
			// 上传文件
			FileUtils fileUtils = new FileUtils();

			height = fileUtils.getImgHeight(Filedata);
			width = fileUtils.getImgWidth(Filedata);
			filetype = IMAGE;

			fileUtils.copyFile(Filedata.getPath(), ServletActionContext
					.getServletContext().getRealPath(uploadFilePath)
					+ File.separator + fileName);
			if (fileUtils.getMessage() != null) {
				message = "上传文件出错：" + fileUtils.getMessage();
				log.error(message);
				return this.INPUT;
			}
		} else if (PropertiesUtil.getProperty(ParamString.VIDEO_TYPE).contains(
				ext)) { // 视频
			String uploadVideoPath = PropertiesUtil
					.getProperty(ParamString.UPLOAD_VIDEO_PATH);
			filepath = uploadVideoPath + fileName;
			// 判断文件大小
			String size = PropertiesUtil
					.getProperty(ParamString.VIDEO_MAX_SIZE);
			if (Filedata.length() > Integer.parseInt(size)) {
				message = "上传文件出错，文件过大。";
				log.error(message);
				return this.INPUT;
			}
			filetype = VIDEO;

			// 上传文件
			FileUtils fileUtils = new FileUtils();
			fileUtils.copyFile(Filedata.getPath(), ServletActionContext
					.getServletContext().getRealPath(uploadVideoPath)
					+ File.separator + fileName);
			if (fileUtils.getMessage() != null) {
				message = "上传文件出错：" + fileUtils.getMessage();
				log.error(message);
				return this.INPUT;
			}
		} else if (PropertiesUtil.getProperty(ParamString.MUSIC_TYPE).contains(
				ext)) { // 视频
			String uploadMusicPath = PropertiesUtil
					.getProperty(ParamString.UPLOAD_MUSIC_PATH);
			filepath = uploadMusicPath + fileName;
			// 判断文件大小
			String size = PropertiesUtil
					.getProperty(ParamString.VIDEO_MAX_SIZE);
			if (Filedata.length() > Integer.parseInt(size)) {
				message = "上传文件出错，文件过大。";
				log.error(message);
				return this.INPUT;
			}
			filetype = MUSIC;

			// 上传文件
			FileUtils fileUtils = new FileUtils();
			fileUtils.copyFile(Filedata.getPath(), ServletActionContext
					.getServletContext().getRealPath(uploadMusicPath)
					+ File.separator + fileName);
			if (fileUtils.getMessage() != null) {
				message = "上传文件出错：" + fileUtils.getMessage();
				log.error(message);
				return this.INPUT;
			}
		}

		return this.SUCCESS;

	}

	public File getFiledata() {
		return Filedata;
	}

	public void setFiledata(File filedata) {
		Filedata = filedata;
	}

	public String getFiledataFileName() {
		return FiledataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		FiledataFileName = filedataFileName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

}
