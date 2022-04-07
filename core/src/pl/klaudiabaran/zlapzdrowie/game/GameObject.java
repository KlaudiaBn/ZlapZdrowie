package pl.klaudiabaran.zlapzdrowie.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * klasa odpowiedzialna za struktury wprowadzonych obiektow
 */
public class GameObject extends Rectangle {
    Sprite sprite;

    /**
     *konstruktor przypisujacy zmienna sprite
     * @param sprite argument przedstawiajacy obraz
     */
    public GameObject(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * konstruktor manipulujacy zmienna sprite, laduje strukture
     * @param sprite argument
     * @param x argument
     * @param y argument
     */
    public GameObject(Sprite sprite, float x , float y) {
        this.sprite = sprite;
        this.x=x;
        this.y=y;
        this.width=sprite.getWidth();
        this.height=sprite.getHeight();
    }

    /**
     * konstruktor manipulujacy zmienna sprite, laduje strukture
     * @param sprite argument
     * @param x argument
     * @param y argument
     * @param width argument
     * @param height argument
     */
    public GameObject(Sprite sprite, float x , float y,float width, float height) {
        this.sprite = sprite;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    /**
     * metoda rysujaca odpowiednie obiekty w konkretnych miejscach
     * @param batch
     */
    public void render(SpriteBatch batch){
        if(sprite==null)return;
        sprite.setBounds(x,y,width,height);
        sprite.draw(batch);
    }
}
