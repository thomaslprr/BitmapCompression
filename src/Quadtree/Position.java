package Quadtree;

public class Position {
	
	private int xDepart;
	private int xArrive;
	private int yDepart;
	private int yArrive;
	
	
	
	
	public Position(int xDepart, int xArrive, int yDepart, int yArrive) {
		super();
		this.xDepart = xDepart;
		this.xArrive = xArrive;
		this.yDepart = yDepart;
		this.yArrive = yArrive;
	}
	
	
	public int getxDepart() {
		return xDepart;
	}
	public void setxDepart(int xDepart) {
		this.xDepart = xDepart;
	}
	public int getxArrive() {
		return xArrive;
	}
	public void setxArrive(int xArrive) {
		this.xArrive = xArrive;
	}
	public int getyDepart() {
		return yDepart;
	}
	public void setyDepart(int yDepart) {
		this.yDepart = yDepart;
	}
	public int getyArrive() {
		return yArrive;
	}
	public void setyArrive(int yArrive) {
		this.yArrive = yArrive;
	}
	
	
	

}
