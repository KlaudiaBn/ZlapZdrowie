package pl.klaudiabaran.zlapzdrowie.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;

/**
 *klasa opdowiadajaca za ustwienia postaci gracza
 */
public class Player extends GameObject{
    /**
     * konstuktor
     * @param sprite argument odpowiedzialny za obraz
     */
    public Player(Sprite sprite) {
        super(sprite);
    }

    /**
     * konstruktor
     * @param sprite argument
     * @param x argument przedstawiajacy pozycje
     * @param y argument przedstawiajacy pozycje
     */
    public Player(Sprite sprite, float x, float y) {
        super(sprite, x, y);
    }

    /**
     * konstruktor
     * @param sprite argument
     * @param x argument przedstawiajacy pozycje
     * @param y argument przedstawiajacy pozycje
     * @param width argument
     * @param height argument
     */
    public Player(Sprite sprite, float x, float y, float width, float height) {
        super(sprite, x, y, width, height);
    }

    /**
     * metoda odpowiedzialna za poruszanie sie postaci
     */
    public void update(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            this.x-=10;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            this.x+=10;
        }
        this.x= MathUtils.clamp(this.x,0,Gdx.graphics.getWidth()-width);
    }
}
