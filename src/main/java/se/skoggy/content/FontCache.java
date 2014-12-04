package se.skoggy.content;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FontCache {
	
	private BitmapFontData data;
	private Texture texture;

	public FontCache(BitmapFontData data, Texture texture) {
		this.data = data;
		this.texture = texture;
	}

	public BitmapFont createFont(){
		return new BitmapFont(data, new TextureRegion(texture), false);
	}
	
	public void dispose(){
		data = null;
		texture.dispose();
	}

}
