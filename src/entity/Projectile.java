package entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import general.World;
import map.Bords;
import map.Case;
import map.Mur;
import map.Porte;

public class Projectile extends Entity{

	private boolean friendly;
	
	public Projectile(){
		World.projectiles.add(this);
		atk = 1;
	}
	
	public Projectile(float x, float y, boolean friendly){
		this.friendly = friendly;
		this.x = x;
		this.y = y;
		height = 16;
		width = 16;
		hitbox = new Rectangle(x,y,width,height);
		World.projectiles.add(this);
		atk = 1;
		if(friendly){
			try {
				sprite = new Image(World.DIRECTORY_IMAGES+"coeur.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}else{
			try {
				sprite = new Image(World.DIRECTORY_IMAGES+"carreau.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}

	public Projectile(int i, int j, boolean friendly){
		this.friendly = friendly;
		this.x = 36*i;
		this.y = 36*j;
		height = 16;
		width = 16;
		hitbox = new Rectangle(x,y,width,height);
		World.projectiles.add(this);
		atk = 1;
		if(friendly){
			try {
				sprite = new Image(World.DIRECTORY_IMAGES+"coeur.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}else{
			try {
				sprite = new Image(World.DIRECTORY_IMAGES+"carreau.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean getFriendly(){
		return friendly;
	}
	
	public void setFriendly(boolean friendly){
		this.friendly = friendly;
	}
	
	@Override
	public void die() {
		World.projectiles.remove(this);
	}

	@Override
	public void checkForCollision() {
		int tmpI,tmpJ;
		Case[][] c = World.map.getCases();
		tmpI = (int) x/36;
		tmpJ = (int) y/36;
		for(int deltaI = -1; deltaI < 2; deltaI++){
			for(int deltaJ = -1; deltaJ < 2; deltaJ++){
				if(!(deltaI == 0 && deltaJ == 0) && tmpI+deltaI >= 0 && tmpJ+deltaJ >= 0 && tmpI+deltaI < c.length && tmpJ+deltaJ < c[tmpI].length){
					if(c[tmpI+deltaI][tmpJ+deltaJ].getHitbox().intersects(hitbox) && (c[tmpI+deltaI][tmpJ+deltaJ] instanceof Mur || c[tmpI+deltaI][tmpJ+deltaJ] instanceof Bords || c[tmpI+deltaI][tmpJ+deltaJ] instanceof Porte)){
						die();
					}
				}
			}
		}
	}

}
