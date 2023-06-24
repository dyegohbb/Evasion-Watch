package br.com.evasion.watch.models.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import br.com.evasion.watch.models.enums.SituationEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "csv_import_history")
public class CsvImportHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@NotNull(message = "Situação não pode ser nula")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SituationEnum situation;
	
	@NotBlank(message = "Nome do arquivo não pode ser vazio")
	@Column(nullable = false)
	private String fileName;
	
	@NotNull(message = "Tamanho do arquivo não pode ser nulo")
	@Column(nullable = false)
	private double fileSize;
	
	@NotNull(message = "A quantidade de linhas não pode ser nula")
	@Column(nullable = false)
	private int rowCount = 0;
	
	@NotNull(message = "Os dados dos estudantes não pode ser nulo")
	@Column(nullable = false)
	@OneToMany(mappedBy = "csvImportHistory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<StudentData> studentDatas = new HashSet<>();
	
	public CsvImportHistory() {
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

	public SituationEnum getSituation() {
		return situation;
	}

	public void setSituation(SituationEnum situation) {
		this.situation = situation;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public Set<StudentData> getStudentDatas() {
		return studentDatas;
	}

	public void setStudentDatas(Set<StudentData> studentDatas) {
		this.studentDatas = studentDatas;
	}
	
}
