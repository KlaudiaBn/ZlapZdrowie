package pl.klaudiabaran.zlapzdrowie;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import pl.klaudiabaran.zlapzdrowie.game.Food;
import pl.klaudiabaran.zlapzdrowie.game.GameObject;
import pl.klaudiabaran.zlapzdrowie.game.Player;
import java.util.ArrayList;

/**
 * klasa wyswietla tresc gry
 */
public class GameScreen implements Screen {

    ZlapZdrowie zdrowie;
    Texture texture;
    Sprite playerSprite,goodFoodSprite,badFoodSprite;
    SpriteBatch batch;
    BitmapFont font;
    ArrayList<GameObject> objects;
    ArrayList<GameObject> toRemove;
    Player player;
    ShapeRenderer renderer;
    boolean end=false;
    float time;
    float spawnTime;
    float speed=3;
    float healthypoints=0;
    float unhealthypoints=0;
    float maxhealthypoints=20;
    float maxunhealthypoints=10;

    /**
     * konstruktor klasy GameScreen, przypisuje zmienną zdrowie do argumentu zdrowie
     * @param zdrowie
     */
    public GameScreen(ZlapZdrowie zdrowie) {
        this.zdrowie = zdrowie;
    }

    @Override
    /**
     * metoda wyświetlająca elementy na ekranie
     */
    public void show() {
        batch=new SpriteBatch();
        font=new BitmapFont();
        renderer=new ShapeRenderer();
        texture=new Texture(Gdx.files.absolute("assets/tlo.jpg"));
        playerSprite=new Sprite(new Texture(Gdx.files.absolute("assets/gracz.png")));
        goodFoodSprite=new Sprite(new Texture(Gdx.files.absolute("assets/jablko.png")));
        badFoodSprite=new Sprite(new Texture(Gdx.files.absolute("assets/slodycz.png")));
        objects=new ArrayList<>();
        toRemove=new ArrayList<>();
        player=new Player(playerSprite,Gdx.graphics.getWidth()/2-30,60, 60,80);
        objects.add(player);
        spawn();

    }

    @Override
    /**
     * metoda renderująca zdarzenia, wywolywana przy kazdym odswiezeniu ekranu
     * @param delta argument, który zawiera informacje o minionym czasie
     */
    public void render(float delta) {
        if(!end) {
            time += delta;
            spawnTime += delta;

            if (spawnTime > speed) {
                spawnTime = 0;
                spawn();
            }
            if (Math.floor(time) % 10 == 0 && Math.floor(time) > 1) {
                speed *= 7.0 / 8.0;
                time = 0;
            }
            player.update();
        }else{
            if(Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))zdrowie.setScreen(new MenuScreen(zdrowie));
        }
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        for (GameObject object : objects) {
            object.render(batch);
            if(object instanceof Food){
                if(object.overlaps(player)){
                    toRemove.add(object);
                    if(((Food) object).isHealthy()){
                        healthypoints++;
                    }else {
                        unhealthypoints++;
                    }
                }
                if(object.y<0)toRemove.add(object);
            }
        }
        if(toRemove.size()>0){
            objects.removeAll(toRemove);
            toRemove.clear();
        }
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.BLACK);
        renderer.rect(0,0,Gdx.graphics.getWidth(),50);
        drawBars(renderer,100,540);
        renderer.setColor(Color.GRAY);
        renderer.rect(Gdx.graphics.getWidth()-100,0,100,50);
        if(end){
            renderer.rect(Gdx.graphics.getWidth()/2-100,Gdx.graphics.getHeight()/2-50,200,100);
        }
        renderer.end();
        batch.begin();
        font.draw(batch,"Miesnie:", 30,27);
        font.draw(batch,"Tycie:", 490,27);
        font.draw(batch,"Menu", Gdx.graphics.getWidth()-70,27);
        if(end){
            font.draw(batch,"Koniec gry", Gdx.graphics.getWidth()/2-32,Gdx.graphics.getHeight()/2+45);
            font.draw(batch,unhealthypoints>=maxunhealthypoints?"Przegrales":"Wygrales", Gdx.graphics.getWidth()/2-32,Gdx.graphics.getHeight()/2+10);
            font.draw(batch,"Nacisnij dowolny klawisz", Gdx.graphics.getWidth()/2-70,Gdx.graphics.getHeight()/2-20);
        }
        batch.end();
        if(Gdx.input.justTouched() && Gdx.input.getX()>Gdx.graphics.getWidth()-100 && Gdx.graphics.getHeight()-Gdx.input.getY()<50){
            zdrowie.setScreen(new MenuScreen(zdrowie));
        }
        if(unhealthypoints>=maxunhealthypoints || healthypoints>=maxhealthypoints){
            end=true;
            objects.clear();
            objects.add(player);
        }
    }

    /**
     * metoda wyświetlająca paski postepu
     * @param renderer
     * @param hx zwiększenie paska postępu miesnie
     * @param unhx zwiększenie paska postępu tycia
     */
    private void drawBars(ShapeRenderer renderer,float hx, float unhx) {
        renderer.setColor(Color.WHITE);
        renderer.rect(unhx-5,5,210,30);
        renderer.rect(hx-5,5,210,30);
        renderer.setColor(Color.GREEN);
        renderer.rect(hx,10,200*healthypoints/maxhealthypoints,20);
        renderer.setColor(Color.RED);
        renderer.rect(unhx,10,200*unhealthypoints/maxunhealthypoints,20);
    }

    /**
     * metoda, dzięki której pojawiają się obiekty na ekranie
     */
    public void spawn(){
       boolean isGood=MathUtils.randomBoolean();
        objects.add(new Food(isGood?goodFoodSprite:badFoodSprite,MathUtils.random(50,Gdx.graphics.getWidth()-100),Gdx.graphics.getHeight()+100,50,50).setHealthyfood(isGood));
    }

    @Override
    /**
     * metoda zmiany rozmiaru
     */
    public void resize(int width, int height) {

    }

    @Override
    /**
     * metoda odpowiadająca za pauzę w grze
     */
    public void pause() {

    }

    @Override
    /**
     * metoda wznawiająca
     */
    public void resume() {

    }

    @Override
    /**
     * metoda odpowiedzialna za ukrywanie
     */
    public void hide() {

    }

    @Override
    /**
     * metoda wywolywana przy zamknieciu ekranu, zwalnia zasoby systemowe
     */
    public void dispose() {

    }
}
