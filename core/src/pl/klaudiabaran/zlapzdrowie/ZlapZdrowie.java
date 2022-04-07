package pl.klaudiabaran.zlapzdrowie;

import com.badlogic.gdx.Game;

/**
 * klasa opowiedzialna na uruchomienie gry
 */
public class ZlapZdrowie extends Game {
	
	@Override
	/**
	 * metoda tworzaca
	 */
	public void create () {
		this.setScreen(new MenuScreen(this));
	}
}
