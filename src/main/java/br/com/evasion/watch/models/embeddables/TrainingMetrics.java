package br.com.evasion.watch.models.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TrainingMetrics {

    private double accuracy;
	
    private double f1Score;
	
    private double recall;
	
    private double kappa;
	
    private double modelScore;
	
    @Column(columnDefinition = "TEXT")
    private String featureImportances;

	public TrainingMetrics() {
		// Empty Constructor
	}

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}

	public double getF1Score() {
		return f1Score;
	}

	public void setF1Score(double f1Score) {
		this.f1Score = f1Score;
	}

	public double getRecall() {
		return recall;
	}

	public void setRecall(double recall) {
		this.recall = recall;
	}

	public double getKappa() {
		return kappa;
	}

	public void setKappa(double kappa) {
		this.kappa = kappa;
	}

	public double getModelScore() {
		return modelScore;
	}

	public void setModelScore(double modelScore) {
		this.modelScore = modelScore;
	}

	public String getFeatureImportances() {
		return featureImportances;
	}

	public void setFeatureImportances(String featureImportances) {
		this.featureImportances = featureImportances;
	}
	
}
