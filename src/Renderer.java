import java.awt.Font;
import java.io.InputStream;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Renderer {

	public static TrueTypeFont font;
	
	public static void init() {
		Font awtFont = new Font("Times New Roman", Font.BOLD, 24);
		//font =  new TrueTypeFont(awtFont, true);
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("dejavu.ttf");
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(24f);
			font = new TrueTypeFont(awtFont, false);
	 
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public static void text(float x, float y, String str) {
		Color.white.bind();
		font.drawString(x, y, str, Color.white);
	}
}
