package com.kebaptycoon.model.entities;

import java.util.Date;

import com.kebaptycoon.model.entities.Customer.Type;

public class Advertisement {

    private final static float BASE_MULTIPLIER = 0.001f;
    private final static float FOCUS_MULTIPLIER = 0.01f;

	public static enum Quality {
		Low(1),
		Medium(2),
		High(3);

		private float value;
		
		private Quality(float value) {
			this.value = value;
		}
		
		public float getValue() {
			return value;
		}
	}
	
	public static enum Duration {
		Day(1),
		Week(7),
		Month(30);

		private int value;
		
		private Duration(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public static enum Platform {
		Newspaper(1),
		Internet(2),
		Billboard(3),
		Television(5);

		private float value;
		
		private Platform(float value) {
			this.value = value;
		}
		
		public float getValue() {
			return value;
		}
	}
	
	Quality quality;
	Duration duration;
	Platform platform;
	Customer.Type focus;
    int elapsedDuration;
	
	public Advertisement(Quality quality, Duration duration, Platform platform, Type focus) {
		this.quality = quality;
		this.duration = duration;
		this.platform = platform;
		this.focus = focus;
		this.elapsedDuration = 0;
	}

	public Quality getQuality() {
		return quality;
	}

	public Duration getDuration() {
		return duration;
	}

	public Platform getPlatform() {
		return platform;
	}

	public Customer.Type getFocus() {
		return focus;
	}

    public int getElapsedDuration() {
        return elapsedDuration;
    }

	public boolean isExpired() {
		return elapsedDuration >= duration.getValue();
	}

    public float getAbsoluteEffect(Customer.Type type) {
        float mul = (type == this.focus) ? FOCUS_MULTIPLIER : BASE_MULTIPLIER;

        return quality.getValue() * platform.getValue() * mul;
    }

    public void incrementElapsedDuration() {
        elapsedDuration++;
    }
}
