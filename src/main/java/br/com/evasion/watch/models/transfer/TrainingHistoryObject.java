package br.com.evasion.watch.models.transfer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
		this.accuracy = roundToFourDecimalPlaces(history.getMetrics().getAccuracy());
		this.f1Score = roundToFourDecimalPlaces(history.getMetrics().getF1Score());
		this.recall = roundToFourDecimalPlaces(history.getMetrics().getRecall());
		this.kappa = roundToFourDecimalPlaces(history.getMetrics().getKappa());
		this.modelScore = roundToFourDecimalPlaces(history.getMetrics().getModelScore());
	}
	
	private double roundToFourDecimalPlaces(double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
