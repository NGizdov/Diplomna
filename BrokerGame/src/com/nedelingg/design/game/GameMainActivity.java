package com.nedelingg.design.game;

import java.util.LinkedHashMap;
import java.util.Map;

import com.nedelingg.backend.actions.Lowerer;
import com.nedelingg.backend.actions.Raiser;
import com.nedelingg.backend.cards.Card;
import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.exceptions.UnsupportedCompanyID;
import com.nedelingg.backend.model.Game;
import com.nedelingg.backend.model.Player;
import com.nedelingg.design.BrokerMainActivity;
import com.nedelingg.design.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GameMainActivity extends Activity {
	
	private static Game newGame;
	private static View rootView;
		
	public static View getRootView() {
		return rootView;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    getActionBar().hide();
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_game_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	public void selectCard(View view) {
		ImageView card = (ImageView) view;
		card.setVisibility(View.GONE);
		ImageView cardPlace = (ImageView) rootView.findViewById(R.id.cardPlace);
		cardPlace.setImageResource((Integer)card.getTag());
		cardPlace.setTag(card.getId());
		setCardsClickable(false);
		showPlayCardBtn(true);
	}
	
	public void returnCard(View view) {
		ImageView cardPlace = (ImageView) rootView.findViewById(R.id.cardPlace);
		cardPlace.setImageResource(R.drawable.card_place);
		ImageView card = (ImageView) rootView.findViewById((Integer) cardPlace.getTag());
		card.setVisibility(View.VISIBLE);
		setCardsClickable(true);
		showPlayCardBtn(false);
	}

	private static void showPlayCardBtn(boolean showBtn) {
		Button btn = (Button) rootView.findViewById(R.id.btnPlayCard);
		if (showBtn) 
			btn.setVisibility(View.VISIBLE);
		else
			btn.setVisibility(View.INVISIBLE);
	}
	
	private static void setCardsClickable(boolean clickable) {
		ImageView card01 = (ImageView) rootView.findViewById(R.id.card01);
		card01.setClickable(clickable);
		
		ImageView card02 = (ImageView) rootView.findViewById(R.id.card02);
		card02.setClickable(clickable);
		
		ImageView card03 = (ImageView) rootView.findViewById(R.id.card03);
		card03.setClickable(clickable);
		
		ImageView card04 = (ImageView) rootView.findViewById(R.id.card04);
		card04.setClickable(clickable);
		
		ImageView card05 = (ImageView) rootView.findViewById(R.id.card05);
		card05.setClickable(clickable);
		
		ImageView card06 = (ImageView) rootView.findViewById(R.id.card06);
		card06.setClickable(clickable);
		
		ImageView card07 = (ImageView) rootView.findViewById(R.id.card07);
		card07.setClickable(clickable);
		
		ImageView card08 = (ImageView) rootView.findViewById(R.id.card08);
		card08.setClickable(clickable);
		
		ImageView card09 = (ImageView) rootView.findViewById(R.id.card09);
		card09.setClickable(clickable);
		
		ImageView card10 = (ImageView) rootView.findViewById(R.id.card10);
		card10.setClickable(clickable);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		public Map<CompanyID, Integer> currentMarkers;
		
		public PlaceholderFragment() {
		}
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
//			final View rootView = inflater.inflate(R.layout.fragment_game_main,
//					container, false);
			rootView = inflater.inflate(R.layout.fragment_game_main,
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
					popupWindow.dismiss();					
					init(rootView, players);
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
			rootView.post(new Runnable() {
		        public void run() {
		        	popupWindow.showAtLocation(rootView ,Gravity.CENTER, 0, 0);
		        }
		    });
			
			return rootView;
		}
		
		private void goToMain(View view) {
			Intent intent = new Intent(this.getActivity(), BrokerMainActivity.class);
			startActivity(intent);
			this.getActivity().finish();
		}
		
		
		private void init(View rootView, int playerNumber) {
			newGame = new Game(playerNumber);
			
			String firstCompanyName = newGame.getBoard().getCompanyName(CompanyID.FIRST); 
			String secondCompanyName = newGame.getBoard().getCompanyName(CompanyID.SECOND); 
			String thirdCompanyName = newGame.getBoard().getCompanyName(CompanyID.THIRD);
			String fourthCompanyName = newGame.getBoard().getCompanyName(CompanyID.FOURTH);
			
			// Board Text
			TextView firstCompanyText = (TextView) rootView.findViewById(R.id.firstCompanyName);
			firstCompanyText.setText(firstCompanyName);
			if(firstCompanyName.length() > 7) {
				firstCompanyText.setTextSize(14);
			} else if(firstCompanyName.length() > 11) {
				firstCompanyText.setTextSize(12);
			}
			
			TextView secondCompanyText = (TextView) rootView.findViewById(R.id.secondCompanyName);
			secondCompanyText.setText(secondCompanyName);
			if(secondCompanyName.length() > 7) {
				secondCompanyText.setTextSize(14);
			} else if(secondCompanyName.length() > 11) {
				secondCompanyText.setTextSize(12);
			}
			
			TextView thirdCompanyText = (TextView) rootView.findViewById(R.id.thirdCompanyName);
			thirdCompanyText.setText(thirdCompanyName);
			if(thirdCompanyName.length() > 7) {
				thirdCompanyText.setTextSize(14);
			} else if(thirdCompanyName.length() > 11) {
				thirdCompanyText.setTextSize(12);
			}
			
			TextView fourthCompanyText = (TextView) rootView.findViewById(R.id.fourthCompanyName);
			fourthCompanyText.setText(fourthCompanyName);
			if(fourthCompanyName.length() > 7) {
				fourthCompanyText.setTextSize(14);
			} else if(fourthCompanyName.length() > 11) {
				fourthCompanyText.setTextSize(12);
			}
			// Board Text
			
			//Info Text
			TextView companyANameInfo = (TextView) rootView.findViewById(R.id.companyAName);
			companyANameInfo.setText(firstCompanyName);
			TextView companyAShares = (TextView) rootView.findViewById(R.id.companyAShares);
			companyAShares.setText(R.string.zero_number);
			TextView companyASharesValue = (TextView) rootView.findViewById(R.id.companyASharesValue);
			companyASharesValue.setText(R.string.zero_number);
			
			TextView companyBNameInfo = (TextView) rootView.findViewById(R.id.companyBName);
			companyBNameInfo.setText(secondCompanyName);
			TextView companyBShares = (TextView) rootView.findViewById(R.id.companyBShares);
			companyBShares.setText(R.string.zero_number);
			TextView companyBSharesValue = (TextView) rootView.findViewById(R.id.companyBSharesValue);
			companyBSharesValue.setText(R.string.zero_number);
			
			TextView companyCNameInfo = (TextView) rootView.findViewById(R.id.companyCName);
			companyCNameInfo.setText(thirdCompanyName);
			TextView companyCShares = (TextView) rootView.findViewById(R.id.companyCShares);
			companyCShares.setText(R.string.zero_number);
			TextView companyCSharesValue = (TextView) rootView.findViewById(R.id.companyCSharesValue);
			companyCSharesValue.setText(R.string.zero_number);
			
			TextView companyDNameInfo = (TextView) rootView.findViewById(R.id.companyDName);
			companyDNameInfo.setText(fourthCompanyName);
			TextView companyDShares = (TextView) rootView.findViewById(R.id.companyDShares);
			companyDShares.setText(R.string.zero_number);
			TextView companyDSharesValue = (TextView) rootView.findViewById(R.id.companyDSharesValue);
			companyDSharesValue.setText(R.string.zero_number);				

			Player humanPlayer = newGame.getHumanPlayer();
			int humanPlayersCash = humanPlayer.getMoneys();
			TextView playersCash = (TextView) rootView.findViewById(R.id.playerHumanCash);
			playersCash.setText(String.valueOf(humanPlayersCash));
			TextView playersTotalCash = (TextView) rootView.findViewById(R.id.playerHumanTotalCash);
			playersTotalCash.setText(String.valueOf(humanPlayersCash));	
			//Info Text
			
			//set cards
			ImageView card01 = (ImageView) rootView.findViewById(R.id.card01);
			card01.setImageResource(humanPlayer.getCards().get(0).getImageID());
			card01.setTag(humanPlayer.getCards().get(0).getImageID());
			humanPlayer.setCardID(R.id.card01, humanPlayer.getCards().get(0));

			ImageView card02 = (ImageView) rootView.findViewById(R.id.card02);
			card02.setImageResource(humanPlayer.getCards().get(1).getImageID());
			card02.setTag(humanPlayer.getCards().get(1).getImageID());
			humanPlayer.setCardID(R.id.card02, humanPlayer.getCards().get(1));
			
			ImageView card03 = (ImageView) rootView.findViewById(R.id.card03);
			card03.setImageResource(humanPlayer.getCards().get(2).getImageID());
			card03.setTag(humanPlayer.getCards().get(2).getImageID());
			humanPlayer.setCardID(R.id.card03, humanPlayer.getCards().get(2));

			ImageView card04 = (ImageView) rootView.findViewById(R.id.card04);
			card04.setImageResource(humanPlayer.getCards().get(3).getImageID());
			card04.setTag(humanPlayer.getCards().get(3).getImageID());
			humanPlayer.setCardID(R.id.card04, humanPlayer.getCards().get(3));

			ImageView card05 = (ImageView) rootView.findViewById(R.id.card05);
			card05.setImageResource(humanPlayer.getCards().get(4).getImageID());
			card05.setTag(humanPlayer.getCards().get(4).getImageID());
			humanPlayer.setCardID(R.id.card05, humanPlayer.getCards().get(4));

			ImageView card06 = (ImageView) rootView.findViewById(R.id.card06);
			card06.setImageResource(humanPlayer.getCards().get(5).getImageID());
			card06.setTag(humanPlayer.getCards().get(5).getImageID());
			humanPlayer.setCardID(R.id.card06, humanPlayer.getCards().get(5));

			ImageView card07 = (ImageView) rootView.findViewById(R.id.card07);
			card07.setImageResource(humanPlayer.getCards().get(6).getImageID());
			card07.setTag(humanPlayer.getCards().get(6).getImageID());
			humanPlayer.setCardID(R.id.card07, humanPlayer.getCards().get(6));

			ImageView card08 = (ImageView) rootView.findViewById(R.id.card08);
			card08.setImageResource(humanPlayer.getCards().get(7).getImageID());
			card08.setTag(humanPlayer.getCards().get(7).getImageID());
			humanPlayer.setCardID(R.id.card08, humanPlayer.getCards().get(7));

			ImageView card09 = (ImageView) rootView.findViewById(R.id.card09);
			card09.setImageResource(humanPlayer.getCards().get(8).getImageID());
			card09.setTag(humanPlayer.getCards().get(8).getImageID());
			humanPlayer.setCardID(R.id.card09, humanPlayer.getCards().get(8));

			ImageView card10 = (ImageView) rootView.findViewById(R.id.card10);
			card10.setImageResource(humanPlayer.getCards().get(9).getImageID());
			card10.setTag(humanPlayer.getCards().get(9).getImageID());
			humanPlayer.setCardID(R.id.card10, humanPlayer.getCards().get(9));
			//set cards
			
			// play card button
			showPlayCardBtn(false);
			Button playCardBtn = ((Button) rootView.findViewById(R.id.btnPlayCard));
			playCardBtn.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					playCard();
				}
			});
			// play card button
			
			// Buttons
			((Button) rootView.findViewById(R.id.showInfoBtn)).setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					try {
						showInfo();
					} catch (UnsupportedCompanyID e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			((Button) rootView.findViewById(R.id.buySellBtn)).setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {					
				}
			});
			
			((Button) rootView.findViewById(R.id.nextPhaseBtn)).setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {					
				}
			});
			// Buttons
			
			currentMarkers = newGame.getBoard().getAllCompaniesCurrentMarkers();
		}
		
		private void showInfo() throws UnsupportedCompanyID{
			final View popView = getActivity().getLayoutInflater().inflate(R.layout.show_info_popup, null);
			
			String firstCompanyName = newGame.getBoard().getCompanyName(CompanyID.FIRST); 
			String secondCompanyName = newGame.getBoard().getCompanyName(CompanyID.SECOND); 
			String thirdCompanyName = newGame.getBoard().getCompanyName(CompanyID.THIRD);
			String fourthCompanyName = newGame.getBoard().getCompanyName(CompanyID.FOURTH);
			
			// Player One
			int[][] playerIds = new int[6][13];
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
			// Player Six
			
			
			int i = 0;
			for (Player player : newGame.getPlayers()) {
				TextView playerOneCompanyAName = (TextView) popView.findViewById(playerIds[i][0]);
				playerOneCompanyAName.setText(firstCompanyName);
				TextView playerOneCompanyAShares = (TextView) popView.findViewById(playerIds[i][1]);
				playerOneCompanyAShares.setText((player.getShares().get(CompanyID.FIRST) == null) ? 0 : player.getShares().get(CompanyID.FIRST));
				TextView playerOneCompanyASharesValue = (TextView) popView.findViewById(playerIds[i][2]);
				playerOneCompanyASharesValue.setText((player.getShares().get(CompanyID.FIRST) * newGame.getBoard().getCompanyCurrentValue(CompanyID.FIRST)));
				
				TextView playerOneCompanyBNameInfo = (TextView) popView.findViewById(playerIds[i][3]);
				playerOneCompanyBNameInfo.setText(secondCompanyName);
				TextView playerOneCompanyBShares = (TextView) popView.findViewById(playerIds[i][4]);
				playerOneCompanyBShares.setText(player.getShares().get(CompanyID.SECOND));
				TextView playerOneCompanyBSharesValue = (TextView) popView.findViewById(playerIds[i][5]);
				playerOneCompanyBSharesValue.setText((player.getShares().get(CompanyID.SECOND) * newGame.getBoard().getCompanyCurrentValue(CompanyID.SECOND)));
				
				TextView playerOneCompanyCNameInfo = (TextView) popView.findViewById(playerIds[i][6]);
				playerOneCompanyCNameInfo.setText(thirdCompanyName);
				TextView playerOneCompanyCShares = (TextView) popView.findViewById(playerIds[i][7]);
				playerOneCompanyCShares.setText(player.getShares().get(CompanyID.THIRD));
				TextView playerOneCompanyCSharesValue = (TextView) popView.findViewById(playerIds[i][8]);
				playerOneCompanyCSharesValue.setText((player.getShares().get(CompanyID.THIRD) * newGame.getBoard().getCompanyCurrentValue(CompanyID.THIRD)));
				
				TextView playerOneCompanyDNameInfo = (TextView) popView.findViewById(playerIds[i][9]);
				playerOneCompanyDNameInfo.setText(fourthCompanyName);
				TextView playerOneCompanyDShares = (TextView) popView.findViewById(playerIds[i][10]);
				playerOneCompanyDShares.setText(player.getShares().get(CompanyID.FOURTH));
				TextView playerOneCompanyDSharesValue = (TextView) popView.findViewById(playerIds[i][11]);
				playerOneCompanyDSharesValue.setText((player.getShares().get(CompanyID.FOURTH) * newGame.getBoard().getCompanyCurrentValue(CompanyID.FOURTH)));				

				int playerCashInt = player.getMoneys();
				TextView playersCash = (TextView) popView.findViewById(R.id.playerOneCash);
				playersCash.setText(String.valueOf(playerCashInt));
				TextView playersTotalCash = (TextView) popView.findViewById(R.id.playerOneTotalCash);
				playersTotalCash.setText(String.valueOf(playerCashInt));
				
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
			rootView.post(new Runnable() {
		        public void run() {
		        	popupWindow.showAtLocation(rootView ,Gravity.CENTER, 0, 0);
		        }
		    });
		}
		
		private void playCard(){
			final ImageView cardPlace = (ImageView) rootView.findViewById(R.id.cardPlace);
			int cardID = (Integer) cardPlace.getTag();
			final Card card = newGame.getHumanCardByID(cardID);

			Raiser raiser = card.getRaiser();
			Lowerer lower = card.getLowerer();
			CompanyID raiserCompanyID = raiser.getCompany();
			CompanyID lowerCompanyID = lower.getCompany();
//			boolean isRaiser = false;
			
			if (raiserCompanyID.equals(CompanyID.BY_CHOICE) || lowerCompanyID.equals(CompanyID.BY_CHOICE)) {
				String radioLabel = "Choose company to ";
				CompanyID illieagalID = null;
				if (raiserCompanyID.equals(CompanyID.BY_CHOICE)) {
					radioLabel += "raise current shares value";
//					isRaiser = true;
					illieagalID = lowerCompanyID;
				} else {
					radioLabel += "raise current shares value";				
					illieagalID = raiserCompanyID;
				}
				String firstCompanyName = newGame.getBoard().getCompanyName(CompanyID.FIRST); 
				String secondCompanyName = newGame.getBoard().getCompanyName(CompanyID.SECOND); 
				String thirdCompanyName = newGame.getBoard().getCompanyName(CompanyID.THIRD);
				String fourthCompanyName = newGame.getBoard().getCompanyName(CompanyID.FOURTH);
				
				final View popView = getActivity().getLayoutInflater().inflate(R.layout.choose_company, null);
//				View view = getActivity().getLayoutInflater().inflate(R.layout.choose_company, null);
				TextView radioLabelText = (TextView) popView.findViewById(R.id.compChooseLabel);
				radioLabelText.setText(radioLabel);
				
				RadioButton firstCompanyRadio = (RadioButton) popView.findViewById(R.id.compOneRadioBtn);
				firstCompanyRadio.setText(firstCompanyName);
				firstCompanyRadio.setTag(CompanyID.FIRST);
				if(illieagalID.equals(CompanyID.FIRST)) {
					firstCompanyRadio.setClickable(false);
					firstCompanyRadio.setChecked(false);
				}
				RadioButton secondCompanyRadio = (RadioButton) popView.findViewById(R.id.compTwoRadioBtn);
				secondCompanyRadio.setText(secondCompanyName);				
				secondCompanyRadio.setTag(CompanyID.SECOND);
				if(illieagalID.equals(CompanyID.SECOND))
					secondCompanyRadio.setClickable(false);
				RadioButton thirdCompanyRadio = (RadioButton) popView.findViewById(R.id.compTreeRadioBtn);
				thirdCompanyRadio.setText(thirdCompanyName);				
				thirdCompanyRadio.setTag(CompanyID.THIRD);
				if(illieagalID.equals(CompanyID.THIRD))
					thirdCompanyRadio.setClickable(false);
				RadioButton fourthCompanyRadio = (RadioButton) popView.findViewById(R.id.compFourRadioBtn);
				fourthCompanyRadio.setText(fourthCompanyName);
				fourthCompanyRadio.setTag(CompanyID.FOURTH);
				if(illieagalID.equals(CompanyID.FOURTH))
					fourthCompanyRadio.setClickable(false);
				
				final PopupWindow popupWindow = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				Button btnOK = (Button) popView.findViewById(R.id.compChooseBtn);
				btnOK.setOnClickListener(new OnClickListener() {				
					@Override
					public void onClick(View v) {
						RadioGroup radioButtonGroup = (RadioGroup) popView.findViewById(R.id.companyChooseRadio);
						int radioButtonID = radioButtonGroup.getCheckedRadioButtonId();
						View radioButton = radioButtonGroup.findViewById(radioButtonID);
						CompanyID id = (CompanyID) radioButton.getTag();
//						choosenId = (CompanyID) radioButton.getTag();
						newGame.playCardWithChoose(newGame.getHumanPlayer(), card, id);
						Map<CompanyID, Integer> markers = newGame.getBoard().getAllCompaniesCurrentMarkers();
						repaintBoard(markers);
						cardPlace.setImageResource(R.drawable.card_place);			
						setCardsClickable(false);
						showPlayCardBtn(false);
						popupWindow.dismiss();
					}
				});
				popupWindow.setFocusable(true);
				popupWindow.setTouchable(true); 
				popupWindow.setOutsideTouchable(false);
				rootView.post(new Runnable() {
			        public void run() {
			        	popupWindow.showAtLocation(rootView ,Gravity.CENTER, 0, 0);
			        }
			    });
			} else {
				newGame.playCard(newGame.getHumanPlayer(), cardID);	
				Map<CompanyID, Integer> markers = newGame.getBoard().getAllCompaniesCurrentMarkers();
				repaintBoard(markers);
				cardPlace.setImageResource(R.drawable.card_place);			
				setCardsClickable(false);
				showPlayCardBtn(false);
			}
		}
		
		private void repaintBoard(Map<CompanyID, Integer> newMarkers) {			
			ImageView firstCompMarker = (ImageView) rootView.findViewById(this.currentMarkers.get(CompanyID.FIRST));
			firstCompMarker.setVisibility(View.INVISIBLE);
			ImageView secondCompMarker = (ImageView) rootView.findViewById(this.currentMarkers.get(CompanyID.SECOND));
			secondCompMarker.setVisibility(View.INVISIBLE);
			ImageView thirdCompMarker = (ImageView) rootView.findViewById(this.currentMarkers.get(CompanyID.THIRD));
			thirdCompMarker.setVisibility(View.INVISIBLE);
			ImageView fourthCompMarker = (ImageView) rootView.findViewById(this.currentMarkers.get(CompanyID.FOURTH));
			fourthCompMarker.setVisibility(View.INVISIBLE);
			
			firstCompMarker = (ImageView) rootView.findViewById(newMarkers.get(CompanyID.FIRST));
			firstCompMarker.setVisibility(View.VISIBLE);
			secondCompMarker = (ImageView) rootView.findViewById(newMarkers.get(CompanyID.SECOND));
			secondCompMarker.setVisibility(View.VISIBLE);
			thirdCompMarker = (ImageView) rootView.findViewById(newMarkers.get(CompanyID.THIRD));
			thirdCompMarker.setVisibility(View.VISIBLE);
			fourthCompMarker = (ImageView) rootView.findViewById(newMarkers.get(CompanyID.FOURTH));
			fourthCompMarker.setVisibility(View.VISIBLE);			
		}
	}

}
