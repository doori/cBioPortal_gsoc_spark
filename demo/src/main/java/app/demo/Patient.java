package app.demo;

// resources/data/data_clinical_patient.txt
public class Patient {
	private String patientId;
	private String sex;
	private String vitalStatus;
	private String smokingHistory;
	private String osMonths;
	private String osStatus;

	public Patient(String patientId, String sex, String vitalStatus, String smokingHistory,
		String osMonths, String osStatus) {
		this.setPatientId(patientId);
		this.setSex(sex);
		this.setVitalStatus(vitalStatus);
		this.setSmokingHistory(smokingHistory);
		this.setOsMonths(osMonths);
		this.setOsStatus(osStatus);
	}

	public String getPatientId() {
		return this.patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getVitalStatus() {
		return this.vitalStatus;
	}

	public void setVitalStatus(String vitalStatus) {
		this.vitalStatus = vitalStatus;
	}

	public String getSmokingHistory() {
		return this.smokingHistory;
	}

	public void setSmokingHistory(String smokingHistory) {
		this.smokingHistory = smokingHistory;
	}

	public String getOsMonths() {
		return this.osMonths;
	}

	public void setOsMonths(String osMonths) {
		this.osMonths = osMonths;
	}

	public String getOsStatus() {
		return this.osStatus;
	}

	public void setOsStatus(String osStatus) {
		this.osStatus = osStatus;
	}

	public String toString() {
		return this.patientId + "," + this.sex + "," + this.vitalStatus + "," + this.smokingHistory
			+ "," + this.osMonths + "," + this.osStatus;
	}
}