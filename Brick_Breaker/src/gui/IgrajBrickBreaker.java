package gui;

import java.util.Scanner;

public class IgrajBrickBreaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BrickBreakerFrame igra = new BrickBreakerFrame();
		Thread gameThread = new Thread(igra.getPanel());
        gameThread.start();
	}
}