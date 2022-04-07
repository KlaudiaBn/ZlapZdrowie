package pl.klaudiabaran.zlapzdrowie.menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * klasa odpowiedzialna za ustawienia przyciskow
 */
public abstract class Button {

    String name;
    Rectangle rectangle;

    /**
     * konstruktor przypisujacy zmienne
     * @param name argument
     * @param rectangle argument
     */
    public Button(String name, Rectangle rectangle) {
        this.name = name;
        this.rectangle = rectangle;
    }

    /**
     * konstruktor zwraca ksztalt przycisku w formie prostokata
     * @return zwraca zdarzenie
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * metoda opowiedzialna za wyglad przyciskow
     * @param renderer argument
     * @param mouse argument
     */
    public void render(ShapeRenderer renderer, Vector2 mouse){
        renderer.setColor(rectangle.contains(mouse)? Color.GRAY:Color.BLACK);
        renderer.rect(rectangle.x,rectangle.y,rectangle.width,rectangle.height);
    }

    /**
     *metoda odpowiedzialna za ustawienia napisu na przycisku
     * @param batch argument
     * @param font argument
     */
    public void renderFont(SpriteBatch batch,BitmapFont font){
        font.draw(batch,name,rectangle.x+5,rectangle.y+rectangle.height/2+5);
    }

    /**
     * metoda zapamietujaca zdarzenie i wykonujaca je w przyszlosci
     */
    public abstract void submit();
}
