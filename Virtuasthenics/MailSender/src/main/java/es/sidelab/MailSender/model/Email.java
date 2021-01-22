package es.sidelab.MailSender.model;

public class Email {
	private String from;
	private String to;
	private String msg;

	@Override
	public String toString() {
		return "Email [from=" + from + ", to=" + to + ", msg=" + msg + "]";
	}

	public Email() {
	}

	public Email(String from, String to, String msg) {
		super();
		this.from = from;
		this.to = to;
		this.msg = msg;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
