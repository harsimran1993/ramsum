package com.mygdx.ramsumgame.desktop;

import com.mygdx.ramsumgame.ActionResolver;

public class ActionResolverDesktop implements ActionResolver{
	public void showOrLoadInterstital() { 
		System.out.println("showOrLoadInterstital()"); 
	}
	public void setTrackerScreenName(String path) {	
		System.out.println("Screenset"+path); 
	}
	@Override
	public boolean getSignedInGPGS() {
		// TODO Auto-generated method stub
		System.out.println("check login"); 
		return false;
	}
	@Override
	public void loginGPGS() {
		// TODO Auto-generated method stub
		System.out.println("prompt login"); 
	}
	@Override
	public void submitScoreGPGS(int score) {
		// TODO Auto-generated method stub
		System.out.println("submit score"); 
	}
	@Override
	public void unlockAchievementGPGS(String achievementId) {
		// TODO Auto-generated method stub
		System.out.println("achievement unlocked"); 
	}
	@Override
	public void getLeaderboardGPGS() {
		// TODO Auto-generated method stub
		System.out.println("check ranking"); 
	}
	@Override
	public void getAchievementsGPGS() {
		// TODO Auto-generated method stub
		System.out.println("check achievement"); 
	}
	@Override
	public void logoutGPGS() {
		// TODO Auto-generated method stub
		System.out.println("logging out"); 
	}
	@Override
	public void sendmessage(String message) {
		// TODO Auto-generated method stub
		System.out.println("message"+message); 
	}
	@Override
	public String username() {
		// TODO Auto-generated method stub
		return "none";
	}
	@Override
	public void stargame() {
		// TODO Auto-generated method stub
		System.out.println("game started");
	}
	@Override
	public void leave() {
		// TODO Auto-generated method stub
	System.out.println("left");	
	}
}
