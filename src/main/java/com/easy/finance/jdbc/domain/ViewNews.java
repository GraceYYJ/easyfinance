package com.easy.finance.jdbc.domain;

@SuppressWarnings("serial")
public class ViewNews extends News{

	private float score;

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
}
