package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.gcstudios.main.Game;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;


public class Player extends Entity{
	
	
	public boolean right,up,left,down,stop_left;
	public int lastDir =1;
	public BufferedImage[] sprite_right1;
	public BufferedImage[] sprite_left1;
	public BufferedImage[] sprite_up1;
	public BufferedImage[] sprite_down1;
	public BufferedImage stopPlayer,stopRight;
	private int frames = 0, maxFrames = 5, index =0,maxIndex = 3; 
	private boolean moved = false;
	
	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		stopPlayer = Game.spritesheet.getSprite(64, 16, 16, 16);
		stopRight = Game.spritesheet.getSprite(32, 0, 16, 16);
		sprite_left1 = new BufferedImage[3];
		sprite_up1 = new BufferedImage[3];
		sprite_down1 = new BufferedImage[3];
		sprite_right1 = new BufferedImage[3];
		for(int i=0; i<3; i++) {
			sprite_left1[i] = Game.spritesheet.getSprite(32+(i *16), 16, 16, 16);
			sprite_right1[i] = Game.spritesheet.getSprite(32+(i *16), 0, 16, 16);
			sprite_up1[i] = Game.spritesheet.getSprite(0+(i *16), 48, 16, 16);
			sprite_down1[i] = Game.spritesheet.getSprite(48+(i *16), 48, 16, 16);
			}
	}
	
	public void tick(){
		depth = 1;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			x+=speed;
			lastDir =1;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			x-=speed;
			lastDir =-1;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			moved = true;
			y-=speed;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			moved = true;
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index == maxIndex)
					index = 0;
			}
		}
		
		checkCollisionfruit();
		
	}
	
	public void checkCollisionfruit() {
		for (int i=0; i< Game.entities.size(); i++) {
			Entity current = Game.entities.get(i);
			if(current instanceof Fruta) {
				if(Entity.isColidding(this, current)) {
					Game.entities.remove(i);
					Game.fruit_atual++;
					return;
				}
			}
		}
	}
	public void render(Graphics g) {

		if(right) {
			g.drawImage(sprite_right1[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(left) {
			g.drawImage(sprite_left1[index],this.getX() - Camera.x,this.getY() - Camera.y,null);	
		}else if(up) {
			g.drawImage(sprite_up1[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}else if(down) {
			g.drawImage(sprite_down1[index],this.getX() - Camera.x,this.getY() - Camera.y,null);
		}		
		else {
			if(!stop_left) {
			g.drawImage(stopRight, this.getX()-Camera.x, this.getY()-Camera.y, null);
			}else {
			g.drawImage(stopPlayer, this.getX()-Camera.x, this.getY()-Camera.y, null);	
			}
		}
		
		
	}

	


}
