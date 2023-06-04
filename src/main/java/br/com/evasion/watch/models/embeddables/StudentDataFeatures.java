package br.com.evasion.watch.models.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class StudentDataFeatures {
	
	@NotNull
	private String descricaoModalidadeCurso;

	@NotNull
	private String descricaoCota;

	@NotNull
	private String nivelEnsino;

	@NotNull
	private String descricaoSituacaoMatricula;

	@NotNull
	private String descricaoEstadoCivil;

	@NotNull
	private String descricaoTurno;

	@NotNull
	private String descricaoNaturalidade;

	@NotNull
	private String coeficienteRendimento;

	@NotNull
	private String descricaoCor;

	@NotNull
	private String sexo;

	@NotNull
	private String descricaoCurso;

	@NotNull
	private String percentualFrequencia;

	@NotNull
	private String descricaoTipoEscolaOrigem;

	@NotNull
	private String descricaoRendaPerCapita;
	
	public StudentDataFeatures() {
		// Empty constructor
	}

	public String getDescricaoModalidadeCurso() {
		return descricaoModalidadeCurso;
	}

	public void setDescricaoModalidadeCurso(String descricaoModalidadeCurso) {
		this.descricaoModalidadeCurso = descricaoModalidadeCurso;
	}

	public String getDescricaoCota() {
		return descricaoCota;
	}

	public void setDescricaoCota(String descricaoCota) {
		this.descricaoCota = descricaoCota;
	}

	public String getNivelEnsino() {
		return nivelEnsino;
	}

	public void setNivelEnsino(String nivelEnsino) {
		this.nivelEnsino = nivelEnsino;
	}

	public String getDescricaoSituacaoMatricula() {
		return descricaoSituacaoMatricula;
	}

	public void setDescricaoSituacaoMatricula(String descricaoSituacaoMatricula) {
		this.descricaoSituacaoMatricula = descricaoSituacaoMatricula;
	}

	public String getDescricaoEstadoCivil() {
		return descricaoEstadoCivil;
	}

	public void setDescricaoEstadoCivil(String descricaoEstadoCivil) {
		this.descricaoEstadoCivil = descricaoEstadoCivil;
	}

	public String getDescricaoTurno() {
		return descricaoTurno;
	}

	public void setDescricaoTurno(String descricaoTurno) {
		this.descricaoTurno = descricaoTurno;
	}

	public String getDescricaoNaturalidade() {
		return descricaoNaturalidade;
	}

	public void setDescricaoNaturalidade(String descricaoNaturalidade) {
		this.descricaoNaturalidade = descricaoNaturalidade;
	}

	public String getCoeficienteRendimento() {
		return coeficienteRendimento;
	}

	public void setCoeficienteRendimento(String coeficienteRendimento) {
		this.coeficienteRendimento = coeficienteRendimento;
	}

	public String getDescricaoCor() {
		return descricaoCor;
	}

	public void setDescricaoCor(String descricaoCor) {
		this.descricaoCor = descricaoCor;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDescricaoCurso() {
		return descricaoCurso;
	}

	public void setDescricaoCurso(String descricaoCurso) {
		this.descricaoCurso = descricaoCurso;
	}

	public String getPercentualFrequencia() {
		return percentualFrequencia;
	}

	public void setPercentualFrequencia(String percentualFrequencia) {
		this.percentualFrequencia = percentualFrequencia;
	}

	public String getDescricaoTipoEscolaOrigem() {
		return descricaoTipoEscolaOrigem;
	}

	public void setDescricaoTipoEscolaOrigem(String descricaoTipoEscolaOrigem) {
		this.descricaoTipoEscolaOrigem = descricaoTipoEscolaOrigem;
	}

	public String getDescricaoRendaPerCapita() {
		return descricaoRendaPerCapita;
	}

	public void setDescricaoRendaPerCapita(String descricaoRendaPerCapita) {
		this.descricaoRendaPerCapita = descricaoRendaPerCapita;
	}
	
}
