package paket1;

import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class OpcijePaneli {
	static Zvuci zvuci = Zvuci.getInstance();
	static URL soundURL;	
	static String slovo;
	
	public static void porukaNakonTacnogOdg() {
		if (Frame.rBrTrenutnogPitanja <= 15) {
			String[] opcije = { "Nastavi sa kvizom", "Uzmi novac" };		
			String[] opcija = {"OK"};
			ImageIcon icon = new ImageIcon(Frame.class.getResource("/paket1/resursi/money.png"));
			Frame.holderOsvSume.setText(Pomocna.sume[Frame.rBrTrenutnogPitanja - 1] + "");
			if (Frame.rBrTrenutnogPitanja > 5 && Frame.rBrTrenutnogPitanja <= 10) {
			Frame.holderZagSume.setText("3 000");

			} else if (Frame.rBrTrenutnogPitanja > 10 && Frame.rBrTrenutnogPitanja <= 15) {
			Frame.holderZagSume.setText("96 000");
			};
			int izabrano = JOptionPane.showOptionDialog(Main.getFrame(),
					"Da li želite da nastavite sa kvizom?\nKliknite na 'Nastavi sa kvizom' ili X da biste nastavili sa kvizom,\nKliknite na 'Uzmi novac' da napustite kviz.",
					"Nastavak kviza?", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcije, opcije[0]);
			
			if (izabrano == 1) {
				zvuci.stop();
				soundURL = OpcijePaneli.class.getResource("/paket1/resursi/Zvuci/pogresanOdgovor.wav");
				zvuci.pustiMuzikuRepeat(soundURL);
				JOptionPane.showOptionDialog(Main.getFrame(), "Osvojili ste sumu od: " + Pomocna.sume[Frame.rBrTrenutnogPitanja - 1] + " RSD", 
				"Čestitam!!!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, opcija,  opcije[0]);
				Frame.kvizPanel.setVisible(false);
				Frame.menuPanel.setVisible(true);
				Frame.menuPanel.setFocusable(true);
				Frame.rBrTrenutnogPitanja = 16;
				Frame.btn1.setText("");
				Frame.btn2.setText("");
				Frame.btn3.setText("");
				Frame.btn4.setText("");
				Pomocna.odgovori[0] = "";
				Pomocna.odgovori[1] = "";
				Pomocna.odgovori[2] = "";
				Pomocna.odgovori[3] = "";
				Frame.iskPitanja1.clear();
				Frame.iskPitanja2.clear();
				Frame.iskPitanja3.clear();
				zvuci.stop();
				soundURL = OpcijePaneli.class.getResource("/paket1/resursi/Zvuci/glavnaTema.wav");
				zvuci.pustiMuzikuRepeat(soundURL);
				
			}

		} else {
			porukaNakonPrelaska();
		}

	}

	public static void porukaNakonNetacnogOdg() {
		ImageIcon icon = new ImageIcon(Frame.class.getResource("/paket1/resursi/tuzniSmajli.png"));
		JOptionPane.showMessageDialog(null, "Vaš odgovor je netačan!\nOsvojena suma: " + Frame.holderZagSume.getText() 
		+ " RSD", "Netačno!!!", JOptionPane.INFORMATION_MESSAGE, icon);
		Frame.kvizPanel.setVisible(false);
		Frame.menuPanel.setVisible(true);
		Frame.menuPanel.setFocusable(true);
		Frame.iskPitanja1.clear();
		Frame.iskPitanja2.clear();
		Frame.iskPitanja3.clear();
		Frame.btn1.setBackground(new Color(0, 0, 64));
		Frame.btn2.setBackground(new Color(0, 0, 64));
		Frame.btn3.setBackground(new Color(0, 0, 64));
		Frame.btn4.setBackground(new Color(0, 0, 64));
		Frame.btn1.setBorder(Frame.borderDefault);
		Frame.btn2.setBorder(Frame.borderDefault);
		Frame.btn3.setBorder(Frame.borderDefault);
		Frame.btn4.setBorder(Frame.borderDefault);
		Frame.rBrTrenutnogPitanja = 16;
		zvuci.stop();
		soundURL = OpcijePaneli.class.getResource("/paket1/resursi/Zvuci/glavnaTema.wav");
		zvuci.pustiMuzikuRepeat(soundURL);
	}

	public static void porukaNakonPrelaska() {
		Frame.rBrTrenutnogPitanja = 16;
		zvuci.stop();
		soundURL = OpcijePaneli.class.getResource("/paket1/resursi/Zvuci/pobeda.wav");
		zvuci.pustiMuzikuRepeat(soundURL);
	    String opcija[] = { "Vrati se na glavni meni" };
	    ImageIcon icon = new ImageIcon(Frame.class.getResource("/paket1/resursi/confetti.png"));
	    Frame.holderOsvSume.setText("3 MILIONA");
	    Frame.holderZagSume.setText("3 MILIONA");
		JOptionPane.showOptionDialog(null, "Osvojili ste maksimalnu sumu od 3 000 000 RSD", "Čestitam!!!",
		JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, opcija, opcija[0]);
	    Frame.kvizPanel.setVisible(false);
	    Frame.menuPanel.setVisible(true);
	    Frame.menuPanel.setFocusable(true);
	    Frame.iskPitanja1.clear();
	    Frame.iskPitanja2.clear();
	    Frame.iskPitanja3.clear();
	    Frame.btn1.setBackground(new Color(0, 0, 64));
	    Frame.btn2.setBackground(new Color(0, 0, 64));
	    Frame.btn3.setBackground(new Color(0, 0, 64));
	    Frame.btn4.setBackground(new Color(0, 0, 64));
	    Frame.btn1.setBorder(Frame.borderDefault);
	    Frame.btn2.setBorder(Frame.borderDefault);
	    Frame.btn3.setBorder(Frame.borderDefault);
	    Frame.btn4.setBorder(Frame.borderDefault);
	    zvuci.stop();
		Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/glavnaTema.wav");
		zvuci.pustiMuzikuRepeat(soundURL);
	    
	 
	}
	public static void odustajanje() {
		String opcije[] = {"Da", "Ne želim"};
		int izabrano = JOptionPane.showOptionDialog(Main.getFrame(),
				"Da li ste sigutni da želite da odustanete i uzmete zarađeni novac?",
				"Odustajete???", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcije, opcije[1]);		
				if (izabrano == 0) {
					zvuci.stop();
					soundURL = OpcijePaneli.class.getResource("/paket1/resursi/Zvuci/pogresanOdgovor.wav");
					zvuci.pustiMuzikuRepeat(soundURL);
					ImageIcon icon = new ImageIcon(Frame.class.getResource("/paket1/resursi/money.png"));
					String[] opcija = {"OK"};
					JOptionPane.showOptionDialog(Main.getFrame(), "Osvojili ste sumu od: " + Pomocna.sume[Frame.rBrTrenutnogPitanja - 1] + " RSD", 
							"Čestitam!!!", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, opcija,  opcije[0]);
							Frame.rBrTrenutnogPitanja = 16;
							Frame.kvizPanel.setVisible(false);
							Frame.menuPanel.setVisible(true);
							Frame.menuPanel.setFocusable(true);
							Frame.rBrTrenutnogPitanja = 16;
							Frame.btn1.setText("");
							Frame.btn2.setText("");
							Frame.btn3.setText("");
							Frame.btn4.setText("");
							Pomocna.odgovori[0] = "";
							Pomocna.odgovori[1] = "";
							Pomocna.odgovori[2] = "";
							Pomocna.odgovori[3] = "";
							Frame.iskPitanja1.clear();
							Frame.iskPitanja2.clear();
							Frame.iskPitanja3.clear();
							zvuci.stop();
							soundURL = OpcijePaneli.class.getResource("/paket1/resursi/Zvuci/glavnaTema.wav");
							zvuci.pustiMuzikuRepeat(soundURL);
							
				}
				
	}
	public static void unesiteOdgovor() {
		JOptionPane.showMessageDialog(null, "Molimo Vas da označite neki odgovor!" ,
		"Niste uneli nijedan odgovor.", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void predloziDobarOdgovor() {	
		ImageIcon customIcon = new ImageIcon(Frame.class.getResource("/paket1/resursi/prijateljIcon.png"));
		if (Frame.getCorrectButton() == Frame.btn1) {
			slovo = "A) ";
		} else if (Frame.getCorrectButton() == Frame.btn2) {
			slovo = "B) ";
		} else if (Frame.getCorrectButton() == Frame.btn3) {
			slovo = "C) "; 
		} else if (Frame.getCorrectButton() == Frame.btn4) {
			slovo = "D) "; 
		}
		JOptionPane.showMessageDialog(null, "Mislim da je tačan odgovor pod: " + slovo + 
		Frame.getCorrectButton().getText(),"Pomoć prijatelja", JOptionPane.INFORMATION_MESSAGE, customIcon);
	}
	
	public static void predloziPogresanOdgovor() {
		ImageIcon customIcon = new ImageIcon(Frame.class.getResource("/paket1/resursi/prijateljIcon.png"));
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		JButton b = new JButton();
		buttons.add(Frame.btn1);
		buttons.add(Frame.btn2);
		buttons.add(Frame.btn3);
		buttons.add(Frame.btn4);
		buttons.remove(Frame.getCorrectButton());
		Collections.shuffle(buttons);
		if (buttons.get(0) == Frame.btn1) {
			slovo = "A) ";
			b = Frame.btn1;
		} else if (buttons.get(0) == Frame.btn2) {
			slovo = "B) ";
			b = Frame.btn2;
		} else if (buttons.get(0) == Frame.btn3) {
			slovo = "C) ";
			b = Frame.btn3;
		} else if (buttons.get(0) == Frame.btn4) {
			slovo = "D) ";
			b = Frame.btn4;
		}		
		JOptionPane.showMessageDialog(null, "Mislim da je tačan odgovor pod " + slovo + 
		b.getText(),"Pomoć prijatelja", JOptionPane.INFORMATION_MESSAGE, customIcon);
	}

}