package com.tlan.web.action.question;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.tlan.beans.LoginBean;
import com.tlan.beans.UserAnswerBean;
import com.tlan.common.action.BaseAction;
import com.tlan.common.model.car.WxModuleContentCar;
import com.tlan.common.model.car.WxModuleContentCarPara;
import com.tlan.common.model.car.WxModuleContentGzh;
import com.tlan.common.model.questionnaire.WxAnswerQuestionUserView;
import com.tlan.common.model.questionnaire.WxModuleContentAnswer;
import com.tlan.common.model.questionnaire.WxModuleContentQuestion;
import com.tlan.common.service.IBaseService;
import com.tlan.common.utils.DataUtils;
import com.tlan.common.utils.DateUtils;
import com.tlan.common.utils.ExportUtil;

public class QuestionAction extends BaseAction implements Preparable,
		ModelDriven<WxModuleContentQuestion> {

	private static Logger log = Logger.getLogger(QuestionAction.class);

	@Resource(name = "baseService")
	private IBaseService<WxModuleContentQuestion> questionService;
	@Resource(name = "baseService")
	private IBaseService<WxModuleContentAnswer> answerService;
	@Resource(name = "baseService")
	private IBaseService<WxAnswerQuestionUserView> answerQuestionUserService;

	private WxModuleContentQuestion question;

	private String items;// 删除id集合
	private String openId;
	private String answer;

	private String filename;
	private ByteArrayInputStream inputStream;
	public static WxModuleContentGzh gzh = new WxModuleContentGzh();

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	private LoginBean loginBean = obtainLoginBean();

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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * 获取随机题目
	 * 
	 * @return
	 */
	public String getRandomQA() {
		List<WxModuleContentQuestion> questions = questionService
				.getAll(WxModuleContentQuestion.class, "gzhHash",
						question.getGzhHash());
		List<WxModuleContentQuestion> QA = new ArrayList<WxModuleContentQuestion>();
		// 生成3到随机 是否题
		for (int i = 0; i < 3; i++) {
			Random rand = new Random();
			int r = rand.nextInt(questions.size());
			while (questions.get(r).getType() != 1) {
				r = rand.nextInt(questions.size());
			}
			QA.add(questions.get(r));
			questions.remove(r);
		}
		// 一道10分制题目
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getType() == 2) {
				QA.add(questions.get(i));
				questions.remove(i);
				break;
			}
		}
		// 一道10分制题目
		for (int i = 0; i < questions.size(); i++) {
			if (questions.get(i).getType() == 3) {
				QA.add(questions.get(i));
				questions.remove(i);
				break;
			}
		}
		setMap(true, "获取成功", QA);

		return this.SUCCESS;
	}

	// 答题
	public String answerQA() {
		try {
			JSONArray ja = JSONArray.fromObject(answer);
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				WxModuleContentAnswer answer = new WxModuleContentAnswer();
				answer.setAnswerGuid(DataUtils.getUUID());
				answer.setOpenId(openId);
				answer.setAnswer(jo.getString("answer"));
				answer.setQuestionGuid(Integer.parseInt(jo
						.getString("questionGuid")));
				answer.setAnswerTime(DateUtils.currentDatetime());

				answerService.save(answer);
			}
			setMap(true, "提交成功", null);
		} catch (Exception e) {
			setMap(true, "提交失败", null);
		}
		return this.SUCCESS;
	}

	// 增加题目
	public String addQuestion() {
		if (DataUtils.isNotNullOrEmpty(question)) {
			question.setCreatedBy(loginBean.getUserName());
			question.setCreatedOn(DateUtils.currentDatetime());
			question.setDeleteFlag(0);
			question.setGzhHash(loginBean.getGzhHash());
			try {
				questionService.save(question);
				setMap(true, "添加成功", null);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				log.error("添加车款异常：" + e.getMessage());
				setMap(false, "添加失败", null);
			}
		} else {
			setMap(false, "保存失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	// 增加题目
	public String editQuestion() {
		try {
			List<WxModuleContentQuestion> questions = questionService.getAll(
					WxModuleContentQuestion.class, "questionGuid",
					question.getQuestionGuid());
			WxModuleContentQuestion q = questions.get(0);
			q.setQuestionValue(question.getQuestionValue());
			q.setType(question.getType());

			questionService.update(q);
			setMap(true, "修改成功", null);
		} catch (Exception e) {
			setMap(false, "修改失败", null);
		}

		return this.SUCCESS;
	}

	// 真删除一条记录
	public String deleteQuestion() {
		if (null != getItems()) {
			System.out.println("items=" + getItems());
			String items[] = getItems().split(",");
			String guids = "";
			for (int i = 0; i < items.length; i++) {
				System.out.println(items[i]);
				// 通过关键字，删除一条车型
				guids += "'" + items[i].trim() + "',";
			}
			guids = guids.substring(0, guids.length() - 1);
			List<WxModuleContentQuestion> qlist = questionService.getAll(
					WxModuleContentQuestion.class,
					new String[] { "questionGuid" }, new Object[] { guids },
					new String[] { "in" });
			for (WxModuleContentQuestion q : qlist) {
				q.setDeleteFlag(1);
				questionService.update(q);
			}

			setMap(true, "删除成功", null);
		} else {
			setMap(false, "删除失败（没有数据）", null);
		}
		return this.SUCCESS;
	}

	public String exportAnswer() {
		List<WxAnswerQuestionUserView> answers = answerQuestionUserService
				.getAll(WxAnswerQuestionUserView.class,
						new String[] { "questionGuid" },
						new Object[] { question.getQuestionGuid() },
						"answerTime", "desc");

		String[] headers = new String[] { "UserName", "UserTel",
				"QuestionValue", "Answer", "AnswerTime", };
		String[] headerNames = new String[] { "答题人", "答题人电话", "题目", "答案",
				"答题时间", };
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ExportUtil<WxAnswerQuestionUserView> data = new ExportUtil<WxAnswerQuestionUserView>();
		data.export("答题情况", headerNames, headers, null, answers, out,
				"yyyy-MM-dd");

		filename = "answer.xls";
		inputStream = new ByteArrayInputStream(out.toByteArray());

		return this.SUCCESS;
	}

	public String exportUserAnswer() {
		List<WxAnswerQuestionUserView> userAnswer = answerQuestionUserService
				.findPage(WxAnswerQuestionUserView.class, getStart() * 5,
						getLimit() * 5,
						"answerTime desc, openId asc, questionGuid asc");
		List<UserAnswerBean> userAnswerBeans = new ArrayList<UserAnswerBean>();
		for (int i = 0; i < userAnswer.size(); i += 5) {
			UserAnswerBean userAnswerBean = new UserAnswerBean();
			userAnswerBean.setUserName(userAnswer.get(i).getUserName());
			userAnswerBean.setUserTel(userAnswer.get(i).getUserTel());
			userAnswerBean.setAnswerTime(userAnswer.get(i).getAnswerTime());
			int index = 0;
			for (int j = i; j < i + 5; j++) {
				index++;
				switch (index) {
				case 1:
					userAnswerBean.setQ1(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA1(userAnswer.get(j).getAnswer());
					break;
				case 2:
					userAnswerBean.setQ2(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA2(userAnswer.get(j).getAnswer());
					break;
				case 3:
					userAnswerBean.setQ3(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA3(userAnswer.get(j).getAnswer());
					break;
				case 4:
					userAnswerBean.setQ4(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA4(userAnswer.get(j).getAnswer());
					break;
				case 5:
					userAnswerBean.setQ5(userAnswer.get(j).getQuestionGuid());
					userAnswerBean.setA5(userAnswer.get(j).getAnswer());
					break;
				}
			}
			userAnswerBeans.add(userAnswerBean);
		}

		String[] headers = new String[] { "UserName", "UserTel", "AnswerTime",
				"Q1", "A1", "Q2", "A2", "Q3", "A3", "Q4", "A4", "Q5", "A5" };
		String[] headerNames = new String[] { "答题人", "答题人电话", "答题时间","Q1", "A1", "Q2", "A2", "Q3", "A3", "Q4", "A4", "Q5", "A5"};
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ExportUtil<UserAnswerBean> data = new ExportUtil<UserAnswerBean>();
		data.export("答题情况", headerNames, headers, null, userAnswerBeans, out,
				"yyyy-MM-dd");

		filename = "userAnswer.xls";
		inputStream = new ByteArrayInputStream(out.toByteArray());

		return this.SUCCESS;
	}

	@Override
	public WxModuleContentQuestion getModel() {
		// TODO Auto-generated method stub
		return question;
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		if (null == question) {
			question = new WxModuleContentQuestion();
		}
	}
}
