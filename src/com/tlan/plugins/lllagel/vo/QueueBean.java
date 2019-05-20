package com.tlan.plugins.lllagel.vo;

/**
 * 
 * @author magenm 2014-1-8
 * 
 */
public class QueueBean {
	/**
	 * 查询任务状态。目前的取值有 <br/>
	 * queued。任务已添加到任务队列 <br/>
	 * started。任务已开始执行 <br/>
	 * finished。任务执行结束 <br/>
	 * failed。任务执行失败 <br/>
	 */
	private String status;
	/**
	 * 预计等待任务执行时间，单位为秒
	 */
	private int sleep;
	/**
	 * 查询任务id，目前有效期为5分钟
	 */
	private String id;
	/**
	 * 车辆违章查询结果
	 */
	private ResultList result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResultList getResult() {
		return result;
	}

	public void setResult(ResultList result) {
		this.result = result;
	}

}
