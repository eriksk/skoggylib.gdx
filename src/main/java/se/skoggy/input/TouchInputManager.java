package se.skoggy.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TouchInputManager implements ITouchInput{

	TouchState state, oldState;
	
	public TouchInputManager() {
		state= new TouchState();
		oldState = new TouchState();
	}

	public float x() {
		return state.x;
	}

	public float y() {
		return state.y;
	}
	
	public boolean tapped(){
		return !state.touched && oldState.touched;
	}

	public boolean pressed(){
		return state.touched && !oldState.touched;
	}
	
	public void update(Vector2 virtualResolution){
		oldState.set(state);
		state.touched = Gdx.input.isTouched();
		
		float x = Gdx.input.getX();
		float y = Gdx.input.getY();
		
		// this is resolution independent as long as cam used is centered
		state.x = ((x / Gdx.graphics.getWidth()) * (float)virtualResolution.x);
		state.y = ((y / Gdx.graphics.getHeight()) * (float)virtualResolution.y);		
	}
}
