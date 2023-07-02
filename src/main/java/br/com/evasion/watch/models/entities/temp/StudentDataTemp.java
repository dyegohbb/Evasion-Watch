package br.com.evasion.watch.models.entities.temp;

import java.time.LocalDateTime;

import br.com.evasion.watch.models.embeddables.StudentDataFeatures;
import br.com.evasion.watch.models.entities.CsvImportHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "student_data_temp")
public class StudentDataTemp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotBlank(message = "Nome deve ser preenchido")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Matricula deve ser preenchida")
	@Column(nullable = false, unique = true)
	private String studentID;
	
	@NotNull(message = "Features não pode ser nula")
	@Column(nullable = false)
	@Embedded
	private StudentDataFeatures features;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "csv_import_history_id")
    private CsvImportHistory csvImportHistory;
	
	public StudentDataTemp() {
		// Empty constructor
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public StudentDataFeatures getFeatures() {
		return features;
	}

	public void setFeatures(StudentDataFeatures features) {
		this.features = features;
	}
	
}
