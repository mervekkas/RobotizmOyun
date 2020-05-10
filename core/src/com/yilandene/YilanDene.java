package com.yilandene;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class YilanDene extends ApplicationAdapter implements InputProcessor {

	SpriteBatch batch;



	int kare_birim = 140;
	int limit_kare = 50;
	int anlikKare = 0;
	int bos_kare = 0;
	int kare_duvar = 1;
	int puan_kare = 2;

	Texture robotimg;
	Texture gulenyuz;

	Robot1 robot1;
	Smile smile;
	ShapeRenderer shapeRenderer;

	//arka plandaki kareler dizisi
	int saha[][] = new int[12][10];

	@Override
	public void create () {

		shapeRenderer = new ShapeRenderer();

		smile = new Smile(0,0);

		batch = new SpriteBatch();
		robotimg = new Texture("rbt.png");
		gulenyuz = new Texture("smiley.png");

		Gdx.input.setInputProcessor(this);//aşağıdaki input metodları için

		//kareleri düzenleme
		for(int i=0; i<12; i++){
			for(int j=0; j<10; j++){
				if(i == 0 || i ==11){

					saha[i][j] = kare_duvar;
				}
				else if(j == 0 || j ==9){

					saha[i][j] = kare_duvar;
				}
				else{

					saha[i][j] = bos_kare;
				}
			}
		}
		robot1 = new Robot1(0,0, saha);
	}

	@Override
	public void render () {

		if(anlikKare == limit_kare){//karenin akışını yavaşlatmak için

			mantikGuncelle();
			anlikKare = 0;
		}
		else{
			anlikKare++;
		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mantikGuncelle();
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

		for(int i=0; i<12; i++){
			for(int j=0; j<10; j++){
				if(saha[i][j] == bos_kare){
					shapeRenderer.setColor(Color.LIME);//kareler için shaperenderer
					shapeRenderer.rect(i*kare_birim,j*kare_birim,
							kare_birim-1,kare_birim-1);
				}
				else if(saha[i][j] == kare_duvar){
					shapeRenderer.setColor(Color.ORANGE);//kareler için kenar duvarı
					shapeRenderer.rect(i*kare_birim,j*kare_birim,
							kare_birim-1,kare_birim-1);
				}
			}
		}

		shapeRenderer.setColor(Color.LIME);//smile için shaperenderer
		shapeRenderer.rect(smile.x+kare_birim+25, smile.y+kare_birim+25,
				kare_birim-50, kare_birim-50);
		shapeRenderer.setColor(Color.ORANGE);//robot için shaperenderer
		shapeRenderer.rect(robot1.x+kare_birim+140, robot1.y+kare_birim+140,
				kare_birim-1, kare_birim-1);


		shapeRenderer.end();

		batch.begin();
		//konumlar random üretim olarak değişecek
		batch.draw(gulenyuz, smile.x+kare_birim+25, smile.y+kare_birim+25,
				kare_birim-50,kare_birim-50);
		batch.draw(robotimg, robot1.x+kare_birim+140, robot1.y+kare_birim+140,
				kare_birim-1, kare_birim-1);

		batch.end();


	}

	private void mantikGuncelle() {

		robot1.yuru();
	}

	@Override
	public void dispose () {

		shapeRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) { //basılı tutsak bile bir kere basılmış sayar
		if(keycode == Input.Keys.UP){
			robot1.modDegistir(robot1.YUKARİ);
			return true;
		}
		else if(keycode == Input.Keys.DOWN){
			robot1.modDegistir(robot1.ASAGİ);
			return true;
		}
		else if(keycode == Input.Keys.LEFT){
			robot1.modDegistir(robot1.SOL);
			return true;
		}
		else if(keycode == Input.Keys.RIGHT){
			robot1.modDegistir(robot1.SAG);
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
