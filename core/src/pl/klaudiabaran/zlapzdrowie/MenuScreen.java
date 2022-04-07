package pl.klaudiabaran.zlapzdrowie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.klaudiabaran.zlapzdrowie.menu.Button;
import java.util.ArrayList;

/**
 * klasa odpowiadająca za wygląd i działanie menu
 */
public class MenuScreen implements Screen {
    ZlapZdrowie zdrowie;
    ArrayList<Button> buttons;
    Texture texture;
    ShapeRenderer renderer;
    SpriteBatch batch;
    BitmapFont font, font1;
    Vector2 mouse;

    /**
     * konstruktor klasy MenuScreen, przypisuje zmienną zdrowie do argumentu zdrowie
     * @param zdrowie
     */
    public MenuScreen(ZlapZdrowie zdrowie) {
        this.zdrowie = zdrowie;
    }

    @Override
    /**
     * metoda odpowiedzialna za ustawienia przysickow, tesktu , tla menu
     */
    public void show() {
        batch=new SpriteBatch();
        renderer=new ShapeRenderer();
        buttons=new ArrayList<>();
        font=new BitmapFont();
        font1=new BitmapFont(Gdx.files.absolute("assets/czcionka.fnt"));
        texture=new Texture(Gdx.files.absolute("assets/tlo.jpg"));
        mouse=new Vector2();
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        float buttonWidth = 200, buttonHeight=50;
        buttons.add(new Button("Uruchom", new Rectangle(w / 2 - buttonWidth / 2, h / 2-50, buttonWidth, buttonHeight)) {
            @Override
            /**
             * metoda zapamietujaca zdarzenie i wykonujaca je w przyszlosci
             */
            public void submit() {
                zdrowie.setScreen(new GameScreen(zdrowie));
            }
        });
        buttons.add(new Button("Wyjdz z gry", new Rectangle(w / 2 - buttonWidth / 2, h / 2-120, buttonWidth, buttonHeight)) {
            @Override
            public void submit() {
                Gdx.app.exit();
            }
        });
    }

    @Override
    /**
     *metoda odpowiedzialna za ustawienie tekstu i funkcje przycisków
     */
    public void render(float delta) {
        mouse.set(Gdx.input.getX(),Gdx.graphics.getHeight()-Gdx.input.getY());
        if(Gdx.input.justTouched()) {
            for (Button button : buttons) {
                if(button.getRectangle().contains(mouse)){
                    button.submit();
                    break;
                }
            }
        }
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        font1.draw(batch,"Chwytaj Zdrowie", Gdx.graphics.getWidth()/2-220,Gdx.graphics.getHeight()/2+140);
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        for (Button button : buttons) {
            button.render(renderer,mouse);
        }
        renderer.end();
        batch.begin();
        for (Button button : buttons) {
            button.renderFont(batch,font);
        }
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
