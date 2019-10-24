package com.deskbill.domain;

/**
 * 找回密码
 * 
 * @author fwl admin
 *
 */
public class BillResetPwd {
	private int id;// 主键
	private String question;// 问题
	private String answer;// 答案
	private int userId;// 用户主键
	private String hint;// 提示

	public BillResetPwd() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	@Override
	public String toString() {
		return "BillResetPwd [id=" + id + ", question=" + question + ", answer=" + answer + ", userId=" + userId
				+ ", hint=" + hint + "]";
	}

}
