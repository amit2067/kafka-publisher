package uk.sk.kafka.messaging.kafkapublisher.pojos;

public class Application {
	private Long id;
	private Long customerId;
	private String heiCode;
	private String courseCode;
	private String courseYear;
	private Integer tflAmount;
	private Integer mlAmount;
	private String confirmedAtnInd;
	private String createdBy;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getHeiCode() {
		return heiCode;
	}
	public void setHeiCode(String heiCode) {
		this.heiCode = heiCode;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getCourseYear() {
		return courseYear;
	}
	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}
	public Integer getTflAmount() {
		return tflAmount;
	}
	public void setTflAmount(Integer tflAmount) {
		this.tflAmount = tflAmount;
	}
	public Integer getMlAmount() {
		return mlAmount;
	}
	public void setMlAmount(Integer mlAmount) {
		this.mlAmount = mlAmount;
	}
	public String getConfirmedAtnInd() {
		return confirmedAtnInd;
	}
	public void setConfirmedAtnInd(String confirmedAtnInd) {
		this.confirmedAtnInd = confirmedAtnInd;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "Application [id=" + id + ", customerId=" + customerId + ", heiCode=" + heiCode + ", courseCode="
				+ courseCode + ", courseYear=" + courseYear + ", tflAmount=" + tflAmount + ", mlAmount=" + mlAmount
				+ ", confirmedAtnInd=" + confirmedAtnInd + ", createdBy=" + createdBy + "]";
	}
}