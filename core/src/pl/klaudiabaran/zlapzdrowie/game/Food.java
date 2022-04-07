package pl.klaudiabaran.zlapzdrowie.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * klasa odpowiedzialna za ustawienia obiektow-jedzenia
 */
public class Food extends GameObject{
    boolean healthyfood;
    float speed=2;

    /**
     *konstruktor sprawdza czy obiekt jest healthyfood
     * @return zwraca zmienna healthyfood
     */
    public boolean isHealthy() {
        return healthyfood;
    }

    /**
     *konstruktor przypisujay zmienna healthyfood
     * @param healthyfood
     * @return
     */
    public Food setHealthyfood(boolean healthyfood) {
        this.healthyfood = healthyfood;
        return this;
    }

    @Override
    /**
     *metoda wyświetla wyswietlajaca obiekty jedzenia
     */
    public void render(SpriteBatch batch) {
        this.y-=speed;
        super.render(batch);
    }

    /**
     *konstruktor
     * @param sprite argument
     */
    public Food(Sprite sprite) {
        super(sprite);
    }

    /**
     *konstruktor manipulujacy zmienna sprite, laduje strukture
     * @param sprite qrgument
     * @param x argument
     * @param y argument
     */
    public Food(Sprite sprite, float x, float y) {
        super(sprite, x, y);
    }

    /**
     *konstruktor manipulujacy zmienna sprite, laduje strukture
     * @param sprite argument
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Food(Sprite sprite, float x, float y, float width, float height) {
        super(sprite, x, y, width, height);
    }
}
