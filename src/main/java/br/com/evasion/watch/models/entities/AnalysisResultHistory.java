package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name="analysis_result_history")
public class AnalysisResultHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private String studentID;
	
	@Column(nullable = false, columnDefinition = "TINYINT")
	private boolean evaded;
	
	public AnalysisResultHistory() {
		// Empty Constructor
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = LocalDateTime.now();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public boolean isEvaded() {
		return evaded;
	}

	public void setEvaded(boolean evaded) {
		this.evaded = evaded;
	}
	
}
