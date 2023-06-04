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

	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull
	@Enumerated(EnumType.STRING)
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
	
}
