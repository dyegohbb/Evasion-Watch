package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.evasion.watch.models.enums.AnalysisTypeEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="analysis_history")
public class AnalysisHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull(message = "O tipo de analise não pode ser nulo")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private AnalysisTypeEnum type;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "analysis_student_relation",
               joinColumns = @JoinColumn(name = "analysis_history_id"),
               inverseJoinColumns = @JoinColumn(name = "student_data_id"))
    private Set<StudentData> studentDatas = new HashSet<>();
	
	public AnalysisHistory() {
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

	public AnalysisTypeEnum getType() {
		return type;
	}

	public void setType(AnalysisTypeEnum type) {
		this.type = type;
	}

	public Set<StudentData> getStudentDatas() {
		return studentDatas;
	}

	public void setStudentDatas(Set<StudentData> studentDatas) {
		this.studentDatas = studentDatas;
	}
	
}
