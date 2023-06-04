package br.com.evasion.watch.models.embeddables;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable
public class TrainingMetrics {

	@NotNull
    private int accuracy;
	
	@NotNull
    private int f1Score;
	
	@NotNull
    private int recall;
	
	@NotNull
    private int precisionValue;
	
	@NotNull
    private int kappa;
	
	@NotNull
    private int modelScore;
	
	@NotNull
    private String featureImportances;

	public TrainingMetrics() {
		// Empty Constructor
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getF1Score() {
		return f1Score;
	}

	public void setF1Score(int f1Score) {
		this.f1Score = f1Score;
	}

	public int getRecall() {
		return recall;
	}

	public void setRecall(int recall) {
		this.recall = recall;
	}

	public int getPrecisionValue() {
		return precisionValue;
	}

	public void setPrecisionValue(int precisionValue) {
		this.precisionValue = precisionValue;
	}

	public int getKappa() {
		return kappa;
	}

	public void setKappa(int kappa) {
		this.kappa = kappa;
	}

	public int getModelScore() {
		return modelScore;
	}

	public void setModelScore(int modelScore) {
		this.modelScore = modelScore;
	}

	public String getFeatureImportances() {
		return featureImportances;
	}

	public void setFeatureImportances(String featureImportances) {
		this.featureImportances = featureImportances;
	}
	
}
