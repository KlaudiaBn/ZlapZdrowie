package pl.klaudiabaran.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.klaudiabaran.zlapzdrowie.ZlapZdrowie;

/**
 * klasa DesktopLuncher odpowiada za stworzenie okna gry
 */
public class DesktopLauncher {
	/**
	 * metoda main ustawia rozmiar okna gry
	 * @param arg argument metody
	 */
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=1024;
		config.height=768;
		new LwjglApplication(new ZlapZdrowie(), config);
	}
}
