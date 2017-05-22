package com.mygdx.ramsumgame.android;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.plus.Plus;
import com.google.example.games.basegameutils.BaseGameUtils;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.ramsumgame.ActionResolver;
import com.mygdx.ramsumgame.RamSumGame;

public class AndroidLauncher extends AndroidApplication implements
GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener,
RoomUpdateListener, 
RoomStatusUpdateListener, 
RealTimeMessageReceivedListener,
ActionResolver{
	
	private GoogleApiClient mGoogleApiClient;
	private static int RC_SIGN_IN = 9001;
	final static int RC_WAITING_ROOM = 10002;
	boolean mPlaying = false;
	final static int MIN_PLAYERS = 2;


	private boolean mResolvingConnectionFailure = false;
	private boolean mAutoStartSignInflow = true;
	private boolean mSignInClicked = false;
	private String mRoomId = "xyz001";
    ArrayList<Participant> mParticipants = null;
    boolean mMultiplayer = false;
    byte[] mMsgBuf = new byte[2];
    String mMyId = null;
	private RamSumGame game;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		game = new RamSumGame(this);
		initialize(game, config);
		mGoogleApiClient = new GoogleApiClient.Builder(this)
        .addConnectionCallbacks(this)
        .addOnConnectionFailedListener(this)
        .addApi(Plus.API).addScope(Plus.SCOPE_PLUS_LOGIN)
        .addApi(Games.API).addScope(Games.SCOPE_GAMES)
        // add other APIs and scopes here as needed
        .build();
		mGoogleApiClient.connect();
	}


	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
		 Log.d("harsim", "onConnectionFailed() called, result: " + connectionResult);
		// TODO Auto-generated method stub
		 if (mResolvingConnectionFailure) {
			 Log.d("harsim", "onConnectionFailed() ignoring connection failure; already resolving.");
		        return;
		    }

		    // if the sign-in button was clicked or if auto sign-in is enabled,
		    // launch the sign-in flow
		    if (mSignInClicked || mAutoStartSignInflow) {
		        mAutoStartSignInflow = false;
		        mSignInClicked = false;
		        mResolvingConnectionFailure = true;

		        // Attempt to resolve the connection failure using BaseGameUtils.
		        // The R.string.signin_other_error value should reference a generic
		        // error string in your strings.xml file, such as "There was
		        // an issue with sign-in, please try again later."
		        if (!BaseGameUtils.resolveConnectionFailure(this,
		                mGoogleApiClient, connectionResult,
		                RC_SIGN_IN, Integer.toString(R.string.signin_other_error))) {
		            mResolvingConnectionFailure = false;
		        }
		    }

		    // Put code here to display the sign-in button

	}

	@Override
	public void onConnected(Bundle arg0) {
		// TODO Auto-generated method stub
		Log.d("harsim","connecbted");
	}

	@Override
	public void onConnectionSuspended(int arg0) {
		// TODO Auto-generated method stub
		mGoogleApiClient.connect();
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == RC_SIGN_IN) {
            Log.d("harsim", "onActivityResult with requestCode == RC_SIGN_IN, responseCode="
                + resultCode + ", intent=" + data);
	        mSignInClicked = false;
	        mResolvingConnectionFailure = false;
	        if (resultCode == RESULT_OK) {
	            mGoogleApiClient.connect();
	        } else {
	            // Bring up an error dialog to alert the user that sign-in
	            // failed. The R.string.signin_failure should reference an error
	            // string in your strings.xml file that tells the user they
	            // could not be signed in, such as "Unable to sign in."
	            BaseGameUtils.showActivityResultError(this,
	                requestCode, resultCode,R.string.signin_other_error);
	        }
	    }
		if (requestCode == RC_WAITING_ROOM) {
	        if (resultCode == Activity.RESULT_OK) {
	            game.isstart=true;
	        }
	        else if (resultCode == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                // player indicated that they want to leave the room
                leave();
 	           	game.iscanceled=true;
	            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
	        else if (resultCode == Activity.RESULT_CANCELED) {
	            // Waiting room was dismissed with the back button. The meaning of this
	            // action is up to the game. You may choose to leave the room and cancel the
	            // match, or do something else like minimize the waiting room and
	            // continue to connect in the background.

	            // in this example, we take the simple approach and just leave the room:
	           leave();
	          // game.iscanceled=true;
	            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	        }
	    }
	}
	private void startQuickGame() {
	    // auto-match criteria to invite one random automatch opponent.
	    Bundle am = RoomConfig.createAutoMatchCriteria(1, 1, 0);
	    // build the room config:
	    RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
	    roomConfigBuilder.setAutoMatchCriteria(am);
	    RoomConfig roomConfig = roomConfigBuilder.build();
	    // create room:
	    Games.RealTimeMultiplayer.create(mGoogleApiClient, roomConfig);
	    // go to game screen
	    // prevent screen from sleeping during handshake
	    //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	private RoomConfig.Builder makeBasicRoomConfigBuilder() {
	    RoomConfig.Builder builder = RoomConfig.builder(this);
	    builder.setMessageReceivedListener(this);
	    builder.setRoomStatusUpdateListener(this);
	    return builder;
	}
	@Override
	public void onJoinedRoom(int statusCode, Room room) {
		// TODO Auto-generated method stub
		if (statusCode != GamesStatusCodes.STATUS_OK) {
	        // let screen go to sleep
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	        // show error message, return to main screen.
	    }
		Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(mGoogleApiClient, room, Integer.MAX_VALUE);
		startActivityForResult(i, RC_WAITING_ROOM);

	}
	@Override
	public void onLeftRoom(int statusCode, String roomId) {
		// TODO Auto-generated method stub
		game.isstart=false;
        game.iscanceled=true;
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	@Override
	public void onRoomConnected(int statusCode, Room room) {
		// TODO Auto-generated method stub
	    log("harsim", room.getRoomId());
		 if (statusCode != GamesStatusCodes.STATUS_OK) {
		        // let screen go to sleep
		        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		        // show error message, return to main screen.
		    }
	        updateRoom(room);
	}
	@Override
	public void onRoomCreated(int statusCode, Room room) {
		// TODO Auto-generated method stub
		if (statusCode != GamesStatusCodes.STATUS_OK) {
	        // let screen go to sleep
	        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

	        // show error message, return to main screen.
	    }
        mRoomId = room.getRoomId();
		Intent i = Games.RealTimeMultiplayer.getWaitingRoomIntent(mGoogleApiClient, room, Integer.MAX_VALUE);
	    startActivityForResult(i, RC_WAITING_ROOM);
	}
	@Override
	public void onConnectedToRoom(Room room) {
		// TODO Auto-generated method stub
        mParticipants = room.getParticipants();
        mMyId = room.getParticipantId(Games.Players.getCurrentPlayerId(mGoogleApiClient));
        // print out the list of participants (for debug purposes)
        Log.d("harsim", "Room ID: " + mRoomId);
        Log.d("harsim", "My ID " + mMyId);
	}
	@Override
	public void onDisconnectedFromRoom(Room room) {
		// TODO Auto-generated method stub
		leave();
        showGameError();
	}
    void showGameError() {
        BaseGameUtils.makeSimpleDialog(this, "game connection lost");
    }
	boolean shouldStartGame(Room room) {
	    int connectedPlayers = 0;
	    for (Participant p : room.getParticipants()) {
	        if (p.isConnectedToRoom()) ++connectedPlayers;
	    }
	    return connectedPlayers >= MIN_PLAYERS;
	}

	// Returns whether the room is in a state where the game should be canceled.
	boolean shouldCancelGame(Room room) {
		   int connectedPlayers = 0;
		    for (Participant p : room.getParticipants()) {
		        if (p.isConnectedToRoom()) ++connectedPlayers;
		    }
		    return connectedPlayers <= MIN_PLAYERS;
	}
	 void updateRoom(Room room) {
		    log("harsim", room.getRoomId());
	        if (room != null) {
	            mParticipants = room.getParticipants();
	        }
	        if (mParticipants != null) {
	            game.isstart=true;
	        }
	    }


	@Override
	public void onP2PConnected(String participantId) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onP2PDisconnected(String participantId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPeerDeclined(Room room, List<String> arg1) {
		// TODO Auto-generated method stub
		updateRoom(room);
	}
	@Override
	public void onPeerInvitedToRoom(Room room, List<String> arg1) {
		// TODO Auto-generated method stub
		updateRoom(room);
	}
	@Override
	public void onPeerJoined(Room arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		updateRoom(arg0);
	}
	@Override
	public void onPeerLeft(Room arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		updateRoom(arg0);
	}
	@Override
	public void onPeersConnected(Room arg0, List<String> arg1) {
		// TODO Auto-generated method stub
		updateRoom(arg0);
		if (shouldStartGame(arg0)) {
	        // start game!
			game.isstart=true;
	    }

	}
	@Override
	public void onPeersDisconnected(Room arg0, List<String> arg1) {
		// TODO Auto-generated method stub
	        // cancel the game
		updateRoom(arg0);
		if(shouldCancelGame(arg0))
			leave();
	}
	@Override
	public void onRoomAutoMatching(Room room) {
		// TODO Auto-generated method stub
		updateRoom(room);
	}
	@Override
	public void onRoomConnecting(Room room) {
		// TODO Auto-generated method stub
		updateRoom(room);
	}
	@Override
	public void onRealTimeMessageReceived(RealTimeMessage rtm) {
		// TODO Auto-generated method stub
		byte[] buf = rtm.getMessageData();
	    String sender = rtm.getSenderParticipantId();
	    Log.d("harsim",new String(buf));
		if(!buf.equals(game.update))
		{
			game.update=new String(buf);
			game.full=true;
		}
	}
	public void sendmessage(String message)
	{
	try{	if(mGoogleApiClient.isConnected())
		for (Participant p : mParticipants) {
			if (p.getParticipantId().equals(mMyId))
                continue;
            if (p.getStatus() != Participant.STATUS_JOINED)
                continue;
            else
				Games.RealTimeMultiplayer.sendReliableMessage(mGoogleApiClient, null, message.getBytes(),
							mRoomId, p.getParticipantId());
		}
		}
	catch(Exception e){e.printStackTrace();}
	}
	@Override
	public boolean getSignedInGPGS() {
		// TODO Auto-generated method stub
		if(mGoogleApiClient.isConnected())
			return true;
		return false;
	}
	@Override
	public void loginGPGS() {
		// TODO Auto-generated method stub
		    mSignInClicked = true;
		    mGoogleApiClient.connect();
	}
	@Override
	public void logoutGPGS() {
		// TODO Auto-generated method stub
	    mSignInClicked = false;
	    Games.signOut(mGoogleApiClient);
	}
	@Override
	public void submitScoreGPGS(int score) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void unlockAchievementGPGS(String achievementId) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getLeaderboardGPGS() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void getAchievementsGPGS() {
		// TODO Auto-generated method stub
		
	}
	public String username()
	{
		return Games.Players.getCurrentPlayer(mGoogleApiClient).getDisplayName();
	}


	@Override
	public void stargame() {
		// TODO Auto-generated method stub
		try 
		{	
			startQuickGame();	
		}
		catch(Exception e)
		{
			log("harsim", "start game failed");
			e.printStackTrace();
		}
	}


	@Override
	public void leave() {
		// TODO Auto-generated method stub
        Games.RealTimeMultiplayer.leave(mGoogleApiClient, this, mRoomId);
       // game.isstart=false;
	}

}
