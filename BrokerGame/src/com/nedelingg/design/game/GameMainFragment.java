package com.nedelingg.design.game;

import java.util.Map;

import com.nedelingg.backend.actions.Lowerer;
import com.nedelingg.backend.actions.Raiser;
import com.nedelingg.backend.cards.Card;
import com.nedelingg.backend.companies.Company;
import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.exceptions.NotEnoughMoney;
import com.nedelingg.backend.exceptions.NotEnoughShares;
import com.nedelingg.backend.exceptions.UnsupportedCompanyID;
import com.nedelingg.backend.model.Game;
import com.nedelingg.backend.model.Player;
import com.nedelingg.backend.utils.Console;
import com.nedelingg.design.BrokerMainActivity;
import com.nedelingg.design.R;

import android.app.AlertDialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class GameMainFragment extends Fragment {
		
		public static Map<CompanyID, Integer> currentMarkers;
		private MainThreadHandler mainThreadHandler;
		@Override
		public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
				Bundle savedInstanceState) {
			GameMainActivity.rootView = inflater.inflate(R.layout.fragment_game_main,
					container, false);
			final View popView = inflater.inflate(R.layout.player_count_window, null);
			final PopupWindow popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Button start = (Button) popView.findViewById(R.id.btnStart);
			start.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					EditText playersNumber = (EditText) popView.findViewById(R.id.playersNumber);
					String playersNumberText = playersNumber.getText().toString();
					int players = 0;
					try {
						players = Integer.parseInt(playersNumberText);
					} catch (NumberFormatException e){
						players = 2;
					}
					if (players < 2) {
						players = 2;
					} else if (players > 6) {
						players = 6;
					}
//					init(GameMainActivity.rootView, players);
					init(players);
					popupWindow.dismiss();
				}
			});
			Button cancel = (Button) popView.findViewById(R.id.btnCancel);
			cancel.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					goToMain(v);
				}
			});
			popupWindow.setFocusable(true);
			popupWindow.setTouchable(true); 
			popupWindow.setOutsideTouchable(false);
			GameMainActivity.rootView.post(new Runnable() {
		        public void run() {
		        	popupWindow.showAtLocation(GameMainActivity.rootView ,Gravity.CENTER, 0, 0);
		        }
		    });
			return GameMainActivity.rootView;
		}
		
		private void goToMain(View view) {
			Intent intent = new Intent(this.getActivity(), BrokerMainActivity.class);
			startActivity(intent);
			this.getActivity().finish();
		}
		
		
//		private void init(final View GameMainActivity.rootView, int playerNumber) {
		private void init(int playerNumber) {
			GameMainActivity.newGame = new Game(playerNumber);
			mainThreadHandler = new MainThreadHandler();
			
			String firstCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FIRST); 
			String secondCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.SECOND); 
			String thirdCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.THIRD);
			String fourthCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FOURTH);
			
			// Board Text
			TextView firstCompanyText = (TextView) GameMainActivity.rootView.findViewById(R.id.firstCompanyName);
			firstCompanyText.setText(firstCompanyName);
			if(firstCompanyName.length() > 7) {
				firstCompanyText.setTextSize(14);
			} else if(firstCompanyName.length() > 11) {
				firstCompanyText.setTextSize(12);
			}
			
			TextView secondCompanyText = (TextView) GameMainActivity.rootView.findViewById(R.id.secondCompanyName);
			secondCompanyText.setText(secondCompanyName);
			if(secondCompanyName.length() > 7) {
				secondCompanyText.setTextSize(14);
			} else if(secondCompanyName.length() > 11) {
				secondCompanyText.setTextSize(12);
			}
			
			TextView thirdCompanyText = (TextView) GameMainActivity.rootView.findViewById(R.id.thirdCompanyName);
			thirdCompanyText.setText(thirdCompanyName);
			if(thirdCompanyName.length() > 7) {
				thirdCompanyText.setTextSize(14);
			} else if(thirdCompanyName.length() > 11) {
				thirdCompanyText.setTextSize(12);
			}
			
			TextView fourthCompanyText = (TextView) GameMainActivity.rootView.findViewById(R.id.fourthCompanyName);
			fourthCompanyText.setText(fourthCompanyName);
			if(fourthCompanyName.length() > 7) {
				fourthCompanyText.setTextSize(14);
			} else if(fourthCompanyName.length() > 11) {
				fourthCompanyText.setTextSize(12);
			}
			// Board Text
			
			//Info Text
			TextView companyANameInfo = (TextView) GameMainActivity.rootView.findViewById(R.id.companyAName);
			companyANameInfo.setText(firstCompanyName);
			TextView companyAShares = (TextView) GameMainActivity.rootView.findViewById(R.id.companyAShares);
			companyAShares.setText(R.string.zero_number);
			TextView companyASharesValue = (TextView) GameMainActivity.rootView.findViewById(R.id.companyASharesValue);
			companyASharesValue.setText(R.string.zero_number);
			
			TextView companyBNameInfo = (TextView) GameMainActivity.rootView.findViewById(R.id.companyBName);
			companyBNameInfo.setText(secondCompanyName);
			TextView companyBShares = (TextView) GameMainActivity.rootView.findViewById(R.id.companyBShares);
			companyBShares.setText(R.string.zero_number);
			TextView companyBSharesValue = (TextView) GameMainActivity.rootView.findViewById(R.id.companyBSharesValue);
			companyBSharesValue.setText(R.string.zero_number);
			
			TextView companyCNameInfo = (TextView) GameMainActivity.rootView.findViewById(R.id.companyCName);
			companyCNameInfo.setText(thirdCompanyName);
			TextView companyCShares = (TextView) GameMainActivity.rootView.findViewById(R.id.companyCShares);
			companyCShares.setText(R.string.zero_number);
			TextView companyCSharesValue = (TextView) GameMainActivity.rootView.findViewById(R.id.companyCSharesValue);
			companyCSharesValue.setText(R.string.zero_number);
			
			TextView companyDNameInfo = (TextView) GameMainActivity.rootView.findViewById(R.id.companyDName);
			companyDNameInfo.setText(fourthCompanyName);
			TextView companyDShares = (TextView) GameMainActivity.rootView.findViewById(R.id.companyDShares);
			companyDShares.setText(R.string.zero_number);
			TextView companyDSharesValue = (TextView) GameMainActivity.rootView.findViewById(R.id.companyDSharesValue);
			companyDSharesValue.setText(R.string.zero_number);				

			Player humanPlayer = GameMainActivity.newGame.getHumanPlayer();
			int humanPlayersCash = humanPlayer.getMoneys();
			TextView playersCash = (TextView) GameMainActivity.rootView.findViewById(R.id.playerHumanCash);
			playersCash.setText(String.valueOf(humanPlayersCash));
			TextView playersTotalCash = (TextView) GameMainActivity.rootView.findViewById(R.id.playerHumanTotalCash);
			playersTotalCash.setText(String.valueOf(humanPlayersCash));	
			//Info Text
			
			//set cards
			ImageView card01 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card01);
			card01.setImageResource(humanPlayer.getCards().get(0).getImageID());
			card01.setTag(humanPlayer.getCards().get(0).getImageID());
			humanPlayer.setCardID(R.id.card01, humanPlayer.getCards().get(0));

			ImageView card02 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card02);
			card02.setImageResource(humanPlayer.getCards().get(1).getImageID());
			card02.setTag(humanPlayer.getCards().get(1).getImageID());
			humanPlayer.setCardID(R.id.card02, humanPlayer.getCards().get(1));
			
			ImageView card03 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card03);
			card03.setImageResource(humanPlayer.getCards().get(2).getImageID());
			card03.setTag(humanPlayer.getCards().get(2).getImageID());
			humanPlayer.setCardID(R.id.card03, humanPlayer.getCards().get(2));

			ImageView card04 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card04);
			card04.setImageResource(humanPlayer.getCards().get(3).getImageID());
			card04.setTag(humanPlayer.getCards().get(3).getImageID());
			humanPlayer.setCardID(R.id.card04, humanPlayer.getCards().get(3));

			ImageView card05 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card05);
			card05.setImageResource(humanPlayer.getCards().get(4).getImageID());
			card05.setTag(humanPlayer.getCards().get(4).getImageID());
			humanPlayer.setCardID(R.id.card05, humanPlayer.getCards().get(4));

			ImageView card06 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card06);
			card06.setImageResource(humanPlayer.getCards().get(5).getImageID());
			card06.setTag(humanPlayer.getCards().get(5).getImageID());
			humanPlayer.setCardID(R.id.card06, humanPlayer.getCards().get(5));

			ImageView card07 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card07);
			card07.setImageResource(humanPlayer.getCards().get(6).getImageID());
			card07.setTag(humanPlayer.getCards().get(6).getImageID());
			humanPlayer.setCardID(R.id.card07, humanPlayer.getCards().get(6));

			ImageView card08 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card08);
			card08.setImageResource(humanPlayer.getCards().get(7).getImageID());
			card08.setTag(humanPlayer.getCards().get(7).getImageID());
			humanPlayer.setCardID(R.id.card08, humanPlayer.getCards().get(7));

			ImageView card09 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card09);
			card09.setImageResource(humanPlayer.getCards().get(8).getImageID());
			card09.setTag(humanPlayer.getCards().get(8).getImageID());
			humanPlayer.setCardID(R.id.card09, humanPlayer.getCards().get(8));

			ImageView card10 = (ImageView) GameMainActivity.rootView.findViewById(R.id.card10);
			card10.setImageResource(humanPlayer.getCards().get(9).getImageID());
			card10.setTag(humanPlayer.getCards().get(9).getImageID());
			humanPlayer.setCardID(R.id.card10, humanPlayer.getCards().get(9));
			//set cards
			
			// play card button
			GameMainActivity.showPlayCardBtn(false);
			Button playCardBtn = ((Button) GameMainActivity.rootView.findViewById(R.id.btnPlayCard));
			playCardBtn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					playCard();
				}
			});
			// play card button
			
			// Buttons
			((Button) GameMainActivity.rootView.findViewById(R.id.showInfoBtn)).setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					try {
						showInfo();
					} catch (UnsupportedCompanyID e) {
						e.printStackTrace();
					}
				}
			});
			
			((Button) GameMainActivity.rootView.findViewById(R.id.buyBtnNew)).setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Log.i("buyBtn", "click");
//					if (currentHumanPhase == 1)
					showBuySharesWindow();					
				}
			});
			
			((Button) GameMainActivity.rootView.findViewById(R.id.sellBtnNew)).setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					Log.i("SellBtn", "click");
//					if (currentHumanPhase == 1) 
					showSellSharesWindow();
				}
			});
			
			((Button) GameMainActivity.rootView.findViewById(R.id.nextPhaseBtn)).setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					if (GameMainActivity.currentHumanPhase == 1){
						GameMainActivity.currentHumanPhase = 2;
						GameMainActivity.setCardsClickable(true);
						GameMainActivity.setNextPhaseClickable(false);
						GameMainActivity.setBuySellClickable(false);
						GameMainActivity.showPlayCardBtn(false);
					} else if (GameMainActivity.currentHumanPhase == 2){
						GameMainActivity.currentHumanPhase = 3;
						GameMainActivity.setCardsClickable(false);
						GameMainActivity.setNextPhaseClickable(true);
						GameMainActivity.setBuySellClickable(true);
						GameMainActivity.showPlayCardBtn(false);
					} else if (GameMainActivity.currentHumanPhase == 3){
						GameMainActivity.currentHumanPhase = 0;
						GameMainActivity.setCardsClickable(false);
						GameMainActivity.setNextPhaseClickable(false);
						GameMainActivity.setBuySellClickable(false);
						GameMainActivity.showPlayCardBtn(false);
						GameMainActivity.isHumanTurn = false;
					} /*else {
						currentHumanPhase = 1;
						setCardsClickable(false);
						setNextPhaseClickable(true);
						setBuySellClickable(true);
						showPlayCardBtn(false);
					}*/
				}
			});
			// Buttons
			
			currentMarkers = GameMainActivity.newGame.getBoard().getAllCompaniesCurrentMarkers();
				
			GameMainActivity.setCardsClickable(false);
			GameMainActivity.setNextPhaseClickable(false);
			GameMainActivity.setBuySellClickable(false);
			
			GameThread gameThread = new GameThread();
			
			gameThread.start();
		}
		
		private void showInfo() throws UnsupportedCompanyID{
			final View popView = getActivity().getLayoutInflater().inflate(R.layout.show_info_popup, null);
			
			String firstCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FIRST); 
			String secondCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.SECOND); 
			String thirdCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.THIRD);
			String fourthCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FOURTH);
			
			// Player One
			int[][] playerIds = new int[6][16];
			playerIds[0][0] = R.id.playerOneCompanyAName;
			playerIds[0][1] = R.id.playerOneCompanyAShares;
			playerIds[0][2] = R.id.playerOneCompanyASharesValue;
			playerIds[0][3] = R.id.playerOneCompanyBName;
			playerIds[0][4] = R.id.playerOneCompanyBShares;
			playerIds[0][5] = R.id.playerOneCompanyBSharesValue;
			playerIds[0][6] = R.id.playerOneCompanyCName;
			playerIds[0][7] = R.id.playerOneCompanyCShares;
			playerIds[0][8] = R.id.playerOneCompanyCSharesValue;
			playerIds[0][9] = R.id.playerOneCompanyDName;
			playerIds[0][10] = R.id.playerOneCompanyDShares;
			playerIds[0][11] = R.id.playerOneCompanyDSharesValue;	
			playerIds[0][12] = R.id.playerOneInfo;
			playerIds[0][13] = R.id.playerOneCash;
			playerIds[0][14] = R.id.playerOneTotalCash;
			playerIds[0][15] = R.id.playerOneGameName;
			// Player One
			
			// Player Two
			playerIds[1][0] = R.id.playerTwoCompanyAName;
			playerIds[1][1] = R.id.playerTwoCompanyAShares;
			playerIds[1][2] = R.id.playerTwoCompanyASharesValue;
			playerIds[1][3] = R.id.playerTwoCompanyBName;
			playerIds[1][4] = R.id.playerTwoCompanyBShares;
			playerIds[1][5] = R.id.playerTwoCompanyBSharesValue;
			playerIds[1][6] = R.id.playerTwoCompanyCName;
			playerIds[1][7] = R.id.playerTwoCompanyCShares;
			playerIds[1][8] = R.id.playerTwoCompanyCSharesValue;
			playerIds[1][9] = R.id.playerTwoCompanyDName;
			playerIds[1][10] = R.id.playerTwoCompanyDShares;
			playerIds[1][11] = R.id.playerTwoCompanyDSharesValue;
			playerIds[1][12] = R.id.playerTwoInfo;
			playerIds[1][13] = R.id.playerTwoCash;
			playerIds[1][14] = R.id.playerTwoTotalCash;
			playerIds[1][15] = R.id.playerTwoGameName;
			// Player Two
			
			// Player Tree
			playerIds[2][0] = R.id.playerTreeCompanyAName;
			playerIds[2][1] = R.id.playerTreeCompanyAShares;
			playerIds[2][2] = R.id.playerTreeCompanyASharesValue;
			playerIds[2][3] = R.id.playerTreeCompanyBName;
			playerIds[2][4] = R.id.playerTreeCompanyBShares;
			playerIds[2][5] = R.id.playerTreeCompanyBSharesValue;
			playerIds[2][6] = R.id.playerTreeCompanyCName;
			playerIds[2][7] = R.id.playerTreeCompanyCShares;
			playerIds[2][8] = R.id.playerTreeCompanyCSharesValue;
			playerIds[2][9] = R.id.playerTreeCompanyDName;
			playerIds[2][10] = R.id.playerTreeCompanyDShares;
			playerIds[2][11] = R.id.playerTreeCompanyDSharesValue;
			playerIds[2][12] = R.id.playerTreeInfo;
			playerIds[2][13] = R.id.playerTreeCash;
			playerIds[2][14] = R.id.playerTreeTotalCash;
			playerIds[2][15] = R.id.playerTreeGameName;
			// Player Tree

			// Player Four
			playerIds[3][0] = R.id.playerFourCompanyAName;
			playerIds[3][1] = R.id.playerFourCompanyAShares;
			playerIds[3][2] = R.id.playerFourCompanyASharesValue;
			playerIds[3][3] = R.id.playerFourCompanyBName;
			playerIds[3][4] = R.id.playerFourCompanyBShares;
			playerIds[3][5] = R.id.playerFourCompanyBSharesValue;
			playerIds[3][6] = R.id.playerFourCompanyCName;
			playerIds[3][7] = R.id.playerFourCompanyCShares;
			playerIds[3][8] = R.id.playerFourCompanyCSharesValue;
			playerIds[3][9] = R.id.playerFourCompanyDName;
			playerIds[3][10] = R.id.playerFourCompanyDShares;
			playerIds[3][11] = R.id.playerFourCompanyDSharesValue;
			playerIds[3][12] = R.id.playerFourInfo;
			playerIds[3][13] = R.id.playerFourCash;
			playerIds[3][14] = R.id.playerFourTotalCash;
			playerIds[3][15] = R.id.playerFourGameName;
			// Player Four
			
			// Player Five
			playerIds[4][0] = R.id.playerFiveCompanyAName;
			playerIds[4][1] = R.id.playerFiveCompanyAShares;
			playerIds[4][2] = R.id.playerFiveCompanyASharesValue;
			playerIds[4][3] = R.id.playerFiveCompanyBName;
			playerIds[4][4] = R.id.playerFiveCompanyBShares;
			playerIds[4][5] = R.id.playerFiveCompanyBSharesValue;
			playerIds[4][6] = R.id.playerFiveCompanyCName;
			playerIds[4][7] = R.id.playerFiveCompanyCShares;
			playerIds[4][8] = R.id.playerFiveCompanyCSharesValue;
			playerIds[4][9] = R.id.playerFiveCompanyDName;
			playerIds[4][10] = R.id.playerFiveCompanyDShares;
			playerIds[4][11] = R.id.playerFiveCompanyDSharesValue;
			playerIds[4][12] = R.id.playerFiveInfo;
			playerIds[4][13] = R.id.playerFiveCash;
			playerIds[4][14] = R.id.playerFiveTotalCash;
			playerIds[4][15] = R.id.playerFiveGameName;
			// Player Five
			
			// Player Six
			playerIds[5][0] = R.id.playerSixCompanyAName;
			playerIds[5][1] = R.id.playerSixCompanyAShares;
			playerIds[5][2] = R.id.playerSixCompanyASharesValue;
			playerIds[5][3] = R.id.playerSixCompanyBName;
			playerIds[5][4] = R.id.playerSixCompanyBShares;
			playerIds[5][5] = R.id.playerSixCompanyBSharesValue;
			playerIds[5][6] = R.id.playerSixCompanyCName;
			playerIds[5][7] = R.id.playerSixCompanyCShares;
			playerIds[5][8] = R.id.playerSixCompanyCSharesValue;
			playerIds[5][9] = R.id.playerSixCompanyDName;
			playerIds[5][10] = R.id.playerSixCompanyDShares;
			playerIds[5][11] = R.id.playerSixCompanyDSharesValue;
			playerIds[5][12] = R.id.playerSixInfo;
			playerIds[5][13] = R.id.playerSixCash;
			playerIds[5][14] = R.id.playerSixTotalCash;
			playerIds[5][15] = R.id.playerSixGameName;
			// Player Six
			
			
			int i = 0;
			for (Player player : GameMainActivity.newGame.getPlayers()) {
				if (!player.isHuman()) {
					TextView playerName = (TextView) popView.findViewById(playerIds[i][15]);
					playerName.setText(player.getName());
				}
				
				TextView playerCompanyAName = (TextView) popView.findViewById(playerIds[i][0]);
				playerCompanyAName.setText(firstCompanyName);
				TextView playerCompanyAShares = (TextView) popView.findViewById(playerIds[i][1]);
				playerCompanyAShares.setText((player.getShares().get(CompanyID.FIRST) == null) ? "0" : String.valueOf(player.getShares().get(CompanyID.FIRST)));
				TextView playerCompanyASharesValue = (TextView) popView.findViewById(playerIds[i][2]);
				playerCompanyASharesValue.setText(String.valueOf(Integer.parseInt(playerCompanyAShares.getText().toString()) * GameMainActivity.newGame.getBoard().getCompanyCurrentValue(CompanyID.FIRST)));
				
				TextView playerCompanyBNameInfo = (TextView) popView.findViewById(playerIds[i][3]);
				playerCompanyBNameInfo.setText(secondCompanyName);
				TextView playerCompanyBShares = (TextView) popView.findViewById(playerIds[i][4]);
				playerCompanyBShares.setText((player.getShares().get(CompanyID.SECOND) == null) ? "0" : String.valueOf(player.getShares().get(CompanyID.SECOND)));
				TextView playerCompanyBSharesValue = (TextView) popView.findViewById(playerIds[i][5]);
				playerCompanyBSharesValue.setText(String.valueOf(Integer.parseInt(playerCompanyBShares.getText().toString()) * GameMainActivity.newGame.getBoard().getCompanyCurrentValue(CompanyID.SECOND)));
				
				TextView playerCompanyCNameInfo = (TextView) popView.findViewById(playerIds[i][6]);
				playerCompanyCNameInfo.setText(thirdCompanyName);
				TextView playerCompanyCShares = (TextView) popView.findViewById(playerIds[i][7]);
				playerCompanyCShares.setText((player.getShares().get(CompanyID.THIRD) == null) ? "0" : String.valueOf(player.getShares().get(CompanyID.THIRD)));
				TextView playerCompanyCSharesValue = (TextView) popView.findViewById(playerIds[i][8]);
				playerCompanyCSharesValue.setText(String.valueOf(Integer.parseInt(playerCompanyCShares.getText().toString()) * GameMainActivity.newGame.getBoard().getCompanyCurrentValue(CompanyID.THIRD)));
				
				TextView playerCompanyDNameInfo = (TextView) popView.findViewById(playerIds[i][9]);
				playerCompanyDNameInfo.setText(fourthCompanyName);
				TextView playerCompanyDShares = (TextView) popView.findViewById(playerIds[i][10]);
				playerCompanyDShares.setText((player.getShares().get(CompanyID.FOURTH) == null) ? "0" : String.valueOf(player.getShares().get(CompanyID.FOURTH)));
				TextView playerCompanyDSharesValue = (TextView) popView.findViewById(playerIds[i][11]);
				playerCompanyDSharesValue.setText(String.valueOf(Integer.parseInt(playerCompanyDShares.getText().toString()) * GameMainActivity.newGame.getBoard().getCompanyCurrentValue(CompanyID.FOURTH)));				

				int playerCashInt = player.getMoneys();
				TextView playersCash = (TextView) popView.findViewById(playerIds[i][13]);
				playersCash.setText(String.valueOf(playerCashInt));
				TextView playersTotalCash = (TextView) popView.findViewById(playerIds[i][14]);
				int totalCash = Integer.parseInt(playerCompanyASharesValue.getText().toString()) + Integer.parseInt(playerCompanyBSharesValue.getText().toString()) + Integer.parseInt(playerCompanyCSharesValue.getText().toString()) + Integer.parseInt(playerCompanyDSharesValue.getText().toString()); 
				playersTotalCash.setText(String.valueOf(totalCash));
				
				(popView.findViewById(playerIds[i][12])).setVisibility(View.VISIBLE);
				i++;
			}
			
			
			final PopupWindow popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Button btnOK = (Button) popView.findViewById(R.id.closeInfoBtn);
			btnOK.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
				}
			});
			popupWindow.setFocusable(true);
			popupWindow.setTouchable(true); 
			popupWindow.setOutsideTouchable(false);
			GameMainActivity.rootView.post(new Runnable() {
		        public void run() {
		        	popupWindow.showAtLocation(GameMainActivity.rootView ,Gravity.CENTER, 0, 0);
		        }
		    });
		}
		
		private void playCard(){
			final ImageView cardPlace = (ImageView) GameMainActivity.rootView.findViewById(R.id.cardPlace);
			int cardID = (Integer) cardPlace.getTag();
			final Card card = GameMainActivity.newGame.getHumanCardByID(cardID);

			Raiser raiser = card.getRaiser();
			Lowerer lower = card.getLowerer();
			CompanyID raiserCompanyID = raiser.getCompany();
			CompanyID lowerCompanyID = lower.getCompany();
			
			if (raiserCompanyID.equals(CompanyID.BY_CHOICE) || lowerCompanyID.equals(CompanyID.BY_CHOICE)) {
				String radioLabel = "Choose company to ";
				CompanyID illieagalID = null;
				if (raiserCompanyID.equals(CompanyID.BY_CHOICE)) {
					radioLabel += "raise current shares value";
					illieagalID = lowerCompanyID;
				} else {
					radioLabel += "raise current shares value";				
					illieagalID = raiserCompanyID;
				}
				String firstCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FIRST); 
				String secondCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.SECOND); 
				String thirdCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.THIRD);
				String fourthCompanyName = GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FOURTH);
				
				final View popView = getActivity().getLayoutInflater().inflate(R.layout.choose_company, null);
				TextView radioLabelText = (TextView) popView.findViewById(R.id.compChooseLabel);
				radioLabelText.setText(radioLabel);
				
				RadioButton firstCompanyRadio = (RadioButton) popView.findViewById(R.id.compOneRadioBtn);
				firstCompanyRadio.setText(firstCompanyName);
				firstCompanyRadio.setTag(CompanyID.FIRST);
				RadioButton secondCompanyRadio = (RadioButton) popView.findViewById(R.id.compTwoRadioBtn);
				secondCompanyRadio.setText(secondCompanyName);				
				secondCompanyRadio.setTag(CompanyID.SECOND);
				RadioButton thirdCompanyRadio = (RadioButton) popView.findViewById(R.id.compTreeRadioBtn);
				thirdCompanyRadio.setText(thirdCompanyName);				
				thirdCompanyRadio.setTag(CompanyID.THIRD);
				RadioButton fourthCompanyRadio = (RadioButton) popView.findViewById(R.id.compFourRadioBtn);
				fourthCompanyRadio.setText(fourthCompanyName);
				fourthCompanyRadio.setTag(CompanyID.FOURTH);
				
				if(illieagalID.equals(CompanyID.FIRST)) {
					firstCompanyRadio.setClickable(false);
					firstCompanyRadio.setChecked(false);
					secondCompanyRadio.setChecked(true);
				} else if(illieagalID.equals(CompanyID.SECOND)) {
					secondCompanyRadio.setClickable(false);
				} else if(illieagalID.equals(CompanyID.THIRD)) {
					thirdCompanyRadio.setClickable(false);
				} else if(illieagalID.equals(CompanyID.FOURTH)) {
					fourthCompanyRadio.setClickable(false);
				}
				
				final PopupWindow popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				Button btnOK = (Button) popView.findViewById(R.id.compChooseBtn);
				btnOK.setOnClickListener(new OnClickListener() {				
					@Override
					public void onClick(View v) {
						RadioGroup radioButtonGroup = (RadioGroup) popView.findViewById(R.id.companyChooseRadio);
						int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
						View radioButton = radioButtonGroup.findViewById(radioButtonID);
						CompanyID id = (CompanyID) radioButton.getTag();
						GameMainActivity.newGame.playCardWithChoose(GameMainActivity.newGame.getHumanPlayer(), card, id);
						Map<CompanyID, Integer> markers = GameMainActivity.newGame.getBoard().getAllCompaniesCurrentMarkers();
						repaintBoard(markers);
						cardPlace.setImageResource(R.drawable.card_place);			
						GameMainActivity.setCardsClickable(false);
						GameMainActivity.showPlayCardBtn(false);
						GameMainActivity.setNextPhaseClickable(true);
						popupWindow.dismiss();
					}
				});
				popupWindow.setFocusable(true);
				popupWindow.setTouchable(true); 
				popupWindow.setOutsideTouchable(false);
				GameMainActivity.rootView.post(new Runnable() {
			        public void run() {
			        	popupWindow.showAtLocation(GameMainActivity.rootView ,Gravity.CENTER, 0, 0);
			        }
			    });
			} else {
				GameMainActivity.newGame.playCard(GameMainActivity.newGame.getHumanPlayer(), cardID);
				Map<CompanyID, Integer> markers = GameMainActivity.newGame.getBoard().getAllCompaniesCurrentMarkers();
				repaintBoard(markers);
				cardPlace.setImageResource(R.drawable.card_place);
				GameMainActivity.setNextPhaseClickable(true);
				GameMainActivity.setCardsClickable(false);
				GameMainActivity.showPlayCardBtn(false);
			}
		}
		
		private void showBuySharesWindow() {
			final View popView = getActivity().getLayoutInflater().inflate(R.layout.fragment_shares_buy_sell_window, null);
			final PopupWindow popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Button okBtn = (Button) popView.findViewById(R.id.btnBuySharesOK);
			
			final NumberPicker sharesNumber = (NumberPicker) popView.findViewById(R.id.pickerNumberOfShares);
			sharesNumber.setMaxValue(20);
			sharesNumber.setMinValue(1);
						
			final Company[] companyArray = GameMainActivity.newGame.getBoard().getCompaniesArray();
			
			final Spinner companyName = (Spinner)	popView.findViewById(R.id.spinnerBuySharesCompany);
			
			ArrayAdapter<Company> adapter = new ArrayAdapter<Company>(this.getActivity().getWindow().getContext() , android.R.layout.simple_spinner_dropdown_item, companyArray);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			companyName.setAdapter(adapter);
			companyName.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
			        InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					Log.i("Spinner", (String) parent.getSelectedItem());
				}			
			});
			
			okBtn.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					try {
						GameMainActivity.newGame.getHumanPlayer().buyCompanyShares(sharesNumber.getValue(), ((Company) companyName.getSelectedItem()).getId());
						popupWindow.dismiss();
					} catch (NotEnoughMoney | NotEnoughShares
							| UnsupportedCompanyID e) {
						String message = "Not such company";
						if (e instanceof NotEnoughMoney) {
							message = "You don't have enough money";
						} else if (e instanceof NotEnoughShares) {
							message = "There are not enough shares for that company";
						}
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								getActivity().getWindow().getContext());				 
						// set title
						alertDialogBuilder.setTitle("Sorry");			 
						// set dialog message
						alertDialogBuilder
							.setMessage(message)
							.setCancelable(false)
							.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									dialog.dismiss();
								}
							 });			 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();		 
						// show it
						alertDialog.show();							
					}
				}
			});
			Button cancel = (Button) popView.findViewById(R.id.btnBuyCancel);
			cancel.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
				}
			});
			popupWindow.setFocusable(true);
			popupWindow.setTouchable(true); 
			popupWindow.setOutsideTouchable(false);
			GameMainActivity.rootView.post(new Runnable() {
		        public void run() {
		        	popupWindow.showAtLocation(GameMainActivity.rootView ,Gravity.CENTER, 0, 0);
		        }
		    });
		}
		
		private void showSellSharesWindow() {
			final View popView = getActivity().getLayoutInflater().inflate(R.layout.fragment_shares_buy_sell_window, null);
			final PopupWindow popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Button okBtn = (Button) popView.findViewById(R.id.btnBuySharesOK);
			
			final NumberPicker sharesNumber = (NumberPicker) popView.findViewById(R.id.pickerNumberOfShares);
			sharesNumber.setMaxValue(20);
			sharesNumber.setMinValue(1);
			
			final String[] companyArray = new String[]{
				GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FIRST),
				GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.SECOND),
				GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.THIRD),
				GameMainActivity.newGame.getBoard().getCompanyName(CompanyID.FOURTH)					
			};			
						
			final Spinner companyName = (Spinner)	popView.findViewById(R.id.spinnerBuySharesCompany);
			
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity().getWindow().getContext() , android.R.layout.simple_spinner_dropdown_item, companyArray);
			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
//			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
//					R.array.player_type, android.R.layout.simple_spinner_item);			
//			adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			companyName.setAdapter(adapter);
			companyName.setOnItemSelectedListener(new OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view,
						int pos, long id) {
					companyName.setTag(pos);
			        InputMethodManager imm =  (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					Log.i("Spinner", (String) parent.getSelectedItem());
				}			
			});
			
			okBtn.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					CompanyID id;
					switch ((Integer) companyName.getTag()) {
					case 0:
						id = CompanyID.FIRST;
						break;
					case 1:
						id = CompanyID.SECOND;
						break;
					case 2:
						id = CompanyID.THIRD;
						break;
					default:
						id = CompanyID.FOURTH;
						break;
					}
					try {
						GameMainActivity.newGame.getHumanPlayer().sellCompanyShares(sharesNumber.getValue(), id);
						popupWindow.dismiss();
					} catch (NotEnoughShares
							| UnsupportedCompanyID e) {
						String message = "Not such company";
						if (e instanceof NotEnoughShares) {
							message = "You don't have enough shares from that company";
						}
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
								getActivity().getWindow().getContext());				 
						// set title
						alertDialogBuilder.setTitle("Sorry");			 
						// set dialog message
						alertDialogBuilder
							.setMessage(message)
							.setCancelable(false)
							.setPositiveButton("OK",new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,int id) {
									dialog.dismiss();
								}
							 });			 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();		 
						// show it
						alertDialog.show();							
					}
				}
			});
			Button cancel = (Button) popView.findViewById(R.id.btnBuyCancel);
			cancel.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					popupWindow.dismiss();
				}
			});
			popupWindow.setFocusable(true);
			popupWindow.setTouchable(true); 
			popupWindow.setOutsideTouchable(false);
			GameMainActivity.rootView.post(new Runnable() {
		        public void run() {
		        	popupWindow.showAtLocation(GameMainActivity.rootView ,Gravity.CENTER, 0, 0);
		        }
		    });
		}
		
		private static void repaintBoard(Map<CompanyID, Integer> newMarkers) {
			ImageView firstCompMarker = (ImageView) GameMainActivity.rootView.findViewById(currentMarkers.get(CompanyID.FIRST));
			firstCompMarker.setVisibility(View.INVISIBLE);
			ImageView secondCompMarker = (ImageView) GameMainActivity.rootView.findViewById(currentMarkers.get(CompanyID.SECOND));
			secondCompMarker.setVisibility(View.INVISIBLE);
			ImageView thirdCompMarker = (ImageView) GameMainActivity.rootView.findViewById(currentMarkers.get(CompanyID.THIRD));
			thirdCompMarker.setVisibility(View.INVISIBLE);
			ImageView fourthCompMarker = (ImageView) GameMainActivity.rootView.findViewById(currentMarkers.get(CompanyID.FOURTH));
			fourthCompMarker.setVisibility(View.INVISIBLE);
			
			currentMarkers = newMarkers;
			
			firstCompMarker = (ImageView) GameMainActivity.rootView.findViewById(newMarkers.get(CompanyID.FIRST));
			firstCompMarker.setVisibility(View.VISIBLE);
			secondCompMarker = (ImageView) GameMainActivity.rootView.findViewById(newMarkers.get(CompanyID.SECOND));
			secondCompMarker.setVisibility(View.VISIBLE);
			thirdCompMarker = (ImageView) GameMainActivity.rootView.findViewById(newMarkers.get(CompanyID.THIRD));
			thirdCompMarker.setVisibility(View.VISIBLE);
			fourthCompMarker = (ImageView) GameMainActivity.rootView.findViewById(newMarkers.get(CompanyID.FOURTH));
			fourthCompMarker.setVisibility(View.VISIBLE);			
		}
				
		private class GameThread extends Thread {
			@Override
			public void run() {
				try {
					Looper.prepare();
//					Message msg = Message.obtain();
					for (int i = 0; i < 10; i++) {
						for (Player player : GameMainActivity.newGame.getPlayers()) {
							
							if (player.isHuman()) {
								GameMainActivity.isHumanTurn = true;
								GameMainActivity.currentHumanPhase = 1;
								Message msg = Message.obtain();
								msg.arg1 = GameCodes.HUMAN_PLAY.value();
								mainThreadHandler.sendMessage(msg);
								try {
									while(GameMainActivity.isHumanTurn) {
										Thread.sleep(1000);
									}
								} catch (InterruptedException e1) {
									e1.printStackTrace();
								}							
							} else {							
								try {
									GameMainActivity.newGame.playPhaseCPU(player);
								} catch (Exception e) {
									Console.log(e.getMessage());
									Log.e("play Error", e.getMessage(), e);
									System.out.println(e.getStackTrace());
								}
							}
						}
					}
					
					Looper.loop();
				} catch (Exception e) {
					System.out.println(e.getStackTrace());
				}
			}
		}
		
		private static class MainThreadHandler extends Handler {
			@Override
			public void handleMessage(Message msg) {
				this.obtainMessage();
				if (msg.arg1 == GameCodes.HUMAN_PLAY.value()) {
					repaintBoard(GameMainActivity.newGame.getBoard().getAllCompaniesCurrentMarkers());
					GameMainActivity.setNextPhaseClickable(true);
					GameMainActivity.setBuySellClickable(true);
				}
			}
		}

		private enum GameCodes {
			HUMAN_PLAY(1), CPU_PLAY(2), END_HUMAN_TURN(3);
			
			private int value;
			 
			GameCodes(int id){
				this.value = id;
			}
			public int value() {
				return value;
			}
		}
}
