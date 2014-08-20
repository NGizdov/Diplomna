package com.nedelingg.design.game;

import com.nedelingg.backend.companies.CompanyID;
import com.nedelingg.backend.model.Game;
import com.nedelingg.backend.model.Player;
import com.nedelingg.design.BrokerMainActivity;
import com.nedelingg.design.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;

public class GameMainActivity extends Activity {
	
	private static Game newGame;
	private static View rootView;
		
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
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	

	public void selectCard(View view){
		ImageView card = (ImageView) view;
		card.setVisibility(View.GONE);
		ImageView cardPlace = (ImageView) rootView.findViewById(R.id.cardPlace);
		cardPlace.setImageResource((Integer)card.getTag());
		cardPlace.setTag(card.getId());
		setCardsClickable(false);
		showPlayCardBtn(true);
	}
	
	public void returnCard(View view){
		ImageView cardPlace = (ImageView) rootView.findViewById(R.id.cardPlace);
		cardPlace.setImageResource(R.drawable.card_place);
		ImageView card = (ImageView) rootView.findViewById((Integer) cardPlace.getTag());
		card.setVisibility(View.VISIBLE);
		setCardsClickable(true);
		showPlayCardBtn(false);
	}

	private static void showPlayCardBtn(boolean showBtn){
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
			
			/*
			newGame = new Game(3);
			
			String firstCompanyName = newGame.getBoard().getCompanyName(CompanyID.FIRST); 
			String secondCompanyName = newGame.getBoard().getCompanyName(CompanyID.SECOND); 
			String thirdCompanyName = newGame.getBoard().getCompanyName(CompanyID.THIRD);
			String fourthCompanyName = newGame.getBoard().getCompanyName(CompanyID.FOURTH);
			
			// Board Text
			TextView firstCompanyText = (TextView) rootView.findViewById(R.id.firstCompanyName);
			firstCompanyText.setText(firstCompanyName);
			
			TextView secondCompanyText = (TextView) rootView.findViewById(R.id.secondCompanyName);
			secondCompanyText.setText(secondCompanyName);
			
			TextView thirdCompanyText = (TextView) rootView.findViewById(R.id.thirdCompanyName);
			thirdCompanyText.setText(thirdCompanyName);
			
			TextView fourthCompanyText = (TextView) rootView.findViewById(R.id.fourthCompanyName);
			fourthCompanyText.setText(fourthCompanyName);
			// Board Text
			
			//Info Text
			TextView companyANameInfo = (TextView) rootView.findViewById(R.id.companyAName);
			companyANameInfo.setText(firstCompanyName);
			TextView companyBNameInfo = (TextView) rootView.findViewById(R.id.companyBName);
			companyBNameInfo.setText(secondCompanyName);
			TextView companyCNameInfo = (TextView) rootView.findViewById(R.id.companyCName);
			companyCNameInfo.setText(thirdCompanyName);
			TextView companyDNameInfo = (TextView) rootView.findViewById(R.id.companyDName);
			companyDNameInfo.setText(fourthCompanyName);
			//Info Text
			*/
			
			return rootView;
		}
		
		private void goToMain(View view){
			Intent intent = new Intent(this.getActivity(), BrokerMainActivity.class);
			startActivity(intent);
			this.getActivity().finish();
		}
		
		
		private void init(View rootView, int playerNumber){
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
			TextView playersCash = (TextView) rootView.findViewById(R.id.playersCash);
			playersCash.setText(String.valueOf(humanPlayersCash));
			TextView playersTotalCash = (TextView) rootView.findViewById(R.id.playersTotalCash);
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
			Button btn = (Button) rootView.findViewById(R.id.btnPlayCard);
			btn.setOnClickListener(new OnClickListener() {				
				@Override
				public void onClick(View v) {
					playCard();
				}
			});
			// play card button
		}
		
		private void playCard(){
			ImageView cardPlace = (ImageView) rootView.findViewById(R.id.cardPlace);
			cardPlace.setImageResource(R.drawable.card_place);
//			ImageView card = (ImageView) rootView.findViewById((Integer) cardPlace.getTag());
//			((ViewGroup)card.getParent()).removeView(card);
			newGame.playPhase();
			setCardsClickable(false);
			showPlayCardBtn(false);
		}
	}

}
