package se.skoggy.utils;

public abstract class TimelineEvent implements ITimelineEvent{

	private boolean isTrigged;
	private float time;
	
	public abstract void onEvent();
	
	public void trig(){
		onEvent();
		isTrigged = true;
	}
	
	public void reset() {
		isTrigged = false;
	}

	public float time(float time) {
		this.time = time;
		return time;
	}
	
	public float time() {
		return time;
	}

	public boolean isTrigged() {
		return isTrigged;
	}

}
