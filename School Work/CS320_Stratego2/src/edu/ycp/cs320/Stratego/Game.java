package edu.ycp.cs320.Stratego;

public class Game {

        public Game()
        {

        }
	
	public static void main(String[] args) {
		GameInstance gameInt = new GameInstance();
		SetupView gameSetupView = new SetupView(gameInt);
		gameSetupView.main(args);
	
	}
}
