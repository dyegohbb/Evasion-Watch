package br.com.evasion.watch.models.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.evasion.watch.models.entities.AnalysisResultHistory;

public class AnalysisResultHistoryObject implements Serializable {

	private static final long serialVersionUID = -2388359477332453767L;
	
	private String studentId;
	
	private String name;
	
	private LocalDateTime analysisDate;
	
	private boolean situation;
	
	public AnalysisResultHistoryObject() {
		//Empty Constructor
	}
	
	public AnalysisResultHistoryObject(AnalysisResultHistory history, String studentName) {
		this.studentId = history.getStudentID();
		this.name = studentName;
		this.analysisDate = history.getCreatedAt();
		this.situation = history.isEvaded();
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getAnalysisDate() {
		return analysisDate;
	}

	public void setAnalysisDate(LocalDateTime analysisDate) {
		this.analysisDate = analysisDate;
	}

	public boolean isSituation() {
		return situation;
	}

	public void setSituation(boolean situation) {
		this.situation = situation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
