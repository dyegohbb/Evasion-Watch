package br.com.evasion.watch.models.transfer;

import java.io.Serializable;
import java.time.LocalDateTime;

import br.com.evasion.watch.models.entities.TrainingHistory;

public class TrainingHistoryObject implements Serializable {

	private static final long serialVersionUID = 3268375903899824674L;

	private LocalDateTime createdAt;

	private double accuracy;

	private double f1Score;

	private double recall;

	private double kappa;

	private double modelScore;

	public TrainingHistoryObject() {
//		Empty Constructor
	}

	public TrainingHistoryObject(TrainingHistory history) {
		this.createdAt = history.getCreatedAt();
		this.accuracy = Math.round(history.getMetrics().getAccuracy() * 100.0) / 100.0;
		this.f1Score = Math.round(history.getMetrics().getF1Score() * 100.0) / 100.0;
		this.recall = Math.round(history.getMetrics().getRecall() * 100.0) / 100.0;
		this.kappa = Math.round(history.getMetrics().getKappa() * 100.0) / 100.0;
		this.modelScore = Math.round(history.getMetrics().getModelScore() * 100.0) / 100.0;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
