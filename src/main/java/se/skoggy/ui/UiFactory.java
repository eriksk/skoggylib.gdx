package se.skoggy.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import se.skoggy.atlases.TextureAtlas;
import se.skoggy.content.ContentManager;
import se.skoggy.tweens.TweenManager;
import se.skoggy.tweens.stock.BackAndForthInterpolation;
import se.skoggy.tweens.stock.PositionXYTween;
import se.skoggy.tweens.stock.RotationTween;
import se.skoggy.tweens.stock.ScaleXTween;
import se.skoggy.tweens.stock.ScaleXYTween;
import se.skoggy.tweens.stock.ScaleYTween;
import se.skoggy.utils.Rand;

public class UiFactory {

	TweenManager tweens;
	ContentManager content;
	BitmapFont font;
	
	TextureAtlas iconAtlas;
	
	public UiFactory(ContentManager content, TweenManager tweens) {
		this.content = content;
		this.tweens = tweens;
		font = content.loadFont("universal_fruitcake_64");
		iconAtlas = new TextureAtlas(content);
		iconAtlas.register("atlases/icon_toolset");
	}
	
	public BitmapFont getFont(){
		return font;
	}

	public TouchButton createRoundIconButton(String iconName, String buttonColor, float transitionDuration){		
		TouchButton button = new TouchButton(iconAtlas.getTexture("button_round_" + buttonColor), iconAtlas.getTexture("icon_" + iconName));	
		button.setScale(0f);
		tweens.add(new ScaleXYTween(button, Interpolation.bounceOut, transitionDuration, 0f, 1f).setWait(Rand.rand(100f, 400f)));
		button.addListener(new TouchButtonEventListener() {
			public void clicked(TouchButton button) {
				tweens.add(new ScaleXYTween(button, new BackAndForthInterpolation(Interpolation.pow2), 150f, 1f, 0.8f));
				tweens.add(new RotationTween(button.getIcon(), Interpolation.pow2, 500f, 0f, 360f * MathUtils.degreesToRadians));
			}
		});
		return button;
	}
	
	public MessageBox createMessageBox(Vector2 position, float transitionDuration){
		MessageBox mb = new MessageBox(iconAtlas);

		tweens.add(new ScaleXTween(mb, Interpolation.elasticOut, 600f, 0f, 0.8f).setWait(200f));
		tweens.add(new ScaleYTween(mb, Interpolation.elasticOut, 600f, 0f, 0.8f));
		tweens.add(new PositionXYTween(mb, Interpolation.pow2, transitionDuration, position.x, position.y - 800f, position.x, position.y));
		
		
		return mb;
	}
	
	public Label createLabel(String text, float minWait, float maxWait){
		se.skoggy.ui.Label label = new se.skoggy.ui.Label(text, TextAlign.center);

		tweens.add(new ScaleXTween(label, Interpolation.elasticOut, 1000f, 0f, 1f).setWait(Rand.rand(minWait, maxWait)));
		tweens.add(new ScaleYTween(label, Interpolation.elasticOut, 1000f, 0f, 1f).setWait(Rand.rand(minWait, maxWait)));
		
		return label;
	}
}
