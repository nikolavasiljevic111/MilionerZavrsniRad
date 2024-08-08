package paket1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.border.Border;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Pomocna extends Frame {

	private static String q;
	static String dobarOdg;
	static boolean flagDugme = false;
	private static boolean flag = true;
	static int counter = 0;
	static Timer t2;
	static Timer t1;
	static String[] odgovori;
	static JButton dobarButton;
	static int sume[] = { 0, 300, 600, 900, 1500, 3000, 6000, 12000, 24000, 48000, 96000, 192000, 375000, 750000,
			1500000, 3000000 };
	static boolean fiftyFiftyIsUsed = false;
	static boolean pomocPrijateljaIsUsed = false;
	static boolean zamenaAktivirana = false;
	static boolean zamenaMoguca = false;
	static boolean zamenaIsUsed = false;

	public static void ispisiSume(JPanel panelHolderSuma, String[] sumeDinari, JPanel rBrPitanjaSidePanel) {
		int i = 15;
		for (String text : sumeDinari) {
			JLabel label = new JLabel(" " + text);
			label.setFont(new Font("Tahoma", Font.BOLD, 18));
			if (label.getText().trim().equals("3 000") || label.getText().trim().equals("96 000")
					|| label.getText().trim().equals("3 MILIONA")) {
				label.setForeground(new Color(255, 102, 178));
			} else {
				label.setForeground(Color.WHITE);
			}
			if (Frame.rBrTrenutnogPitanja == i) {
				label.setOpaque(true);
				label.setBackground(Color.ORANGE);
			}
			JLabel label2 = new JLabel(" " + i + ".");
			label2.setFont(new Font("Tahoma", Font.BOLD, 18));
			label2.setForeground(Color.WHITE);
			if (Frame.rBrTrenutnogPitanja == i) {
				label2.setBackground(Color.ORANGE);
				label2.setOpaque(true);
			}
			panelHolderSuma.add(label);
			rBrPitanjaSidePanel.add(label2);
			i--;
		}
	}

	public static void gameLoop(int rBrTrenutnogPitanja, ArrayList<Integer> lvl1, ArrayList<Integer> lvl2,
			ArrayList<Integer> lvl3, JTextArea pitanje, JButton b1, JButton b2, JButton b3, JButton b4)
			throws IOException, ParseException {

		if (Frame.rBrTrenutnogPitanja <= 15) {
			JSONParser jsonParser = new JSONParser();
			InputStream inputStream = Pomocna.class.getResourceAsStream("/bazaZaKviz/bazaPitanja.json");
			InputStreamReader reader = new InputStreamReader(inputStream);
			JSONObject obj = (JSONObject) jsonParser.parse(reader);
			JSONArray nivo1 = (JSONArray) obj.get("pitanjaNivo1");
			JSONArray nivo2 = (JSONArray) obj.get("pitanjaNivo2");
			JSONArray nivo3 = (JSONArray) obj.get("pitanjaNivo3");

			int min = 0;
			int max1 = nivo1.size() - 1;
			int max2 = nivo2.size() - 1;
			int max3 = nivo3.size() - 1;

			Random random = new Random();

			Frame.btn1.setBorder(borderDefault);
			Frame.btn2.setBorder(borderDefault);
			Frame.btn3.setBorder(borderDefault);
			Frame.btn4.setBorder(borderDefault);
			Frame.btn1.setText("");
			Frame.btn2.setText("");
			Frame.btn3.setText("");
			Frame.btn4.setText("");
			removeLabels(Frame.panelHolderSuma, Frame.rBrPitanjaSidePanel);

			JSONObject randomPitanje = null;
			int randomNumber = 0;

			if (rBrTrenutnogPitanja >= 1 && rBrTrenutnogPitanja <= 5) {
				do {
					randomNumber = random.nextInt(max1 - min + 1) + min;
				} while (lvl1.contains(randomNumber));
				randomPitanje = (JSONObject) nivo1.get(randomNumber);
				lvl1.add(randomNumber);

			} else if (rBrTrenutnogPitanja > 5 && rBrTrenutnogPitanja <= 10) {
				do {
					randomNumber = random.nextInt(max2 - min + 1) + min;
				} while (lvl2.contains(randomNumber));
				randomPitanje = (JSONObject) nivo2.get(randomNumber);
				lvl2.add(randomNumber);

			} else if (rBrTrenutnogPitanja > 10 && rBrTrenutnogPitanja <= 15) {
				do {
					randomNumber = random.nextInt(max3 - min + 1) + min;
				} while (lvl3.contains(randomNumber));
				randomPitanje = (JSONObject) nivo3.get(randomNumber);
				lvl3.add(randomNumber);
			}

			if (randomPitanje != null) {
				daKliknuto = false;
				Frame.btn1.setEnabled(true);
				Frame.btn2.setEnabled(true);
				Frame.btn3.setEnabled(true);
				Frame.btn4.setEnabled(true);
				Frame.resignHolder.setEnabled(false);
				getLabel().setText("Pitanje broj " + Frame.rBrTrenutnogPitanja);
				if (Frame.rBrTrenutnogPitanja <= 5) {
					returnHolderZag().setText("0");
				} else if (Frame.rBrTrenutnogPitanja > 5 && rBrTrenutnogPitanja <= 10) {
					returnHolderZag().setText("3 000");

				} else if (Frame.rBrTrenutnogPitanja > 10 && rBrTrenutnogPitanja <= 15) {
					returnHolderZag().setText("96 000");
				}
				returnHolderOsv().setText(sume[Frame.rBrTrenutnogPitanja - 1] + "");
				ispisiSume(Frame.panelHolderSuma, sumeDinari, rBrPitanjaSidePanel);
				dobarOdg = (String) randomPitanje.get("dobarOdgovor");
				q = (String) randomPitanje.get("pitanje");
				JSONArray odgovoriArray = (JSONArray) randomPitanje.get("potencijalniOdgovori");
				odgovori = new String[odgovoriArray.size()];
				for (int i = 0; i < odgovoriArray.size(); i++) {
					odgovori[i] = (String) odgovoriArray.get(i);
				}

				Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/letsPlay.wav");
				zvuci.pustiMuziku(soundURL);

				if (rBrTrenutnogPitanja == 1) {
					if (!zamenaAktivirana) {
						pitanje.setText("Dobrodošli u kviz \"želite li da postanete milioner?\"!!!");
					}
					Timer timer1 = new Timer(6000, e -> {
						if (!zamenaAktivirana) {
							pitanje.setText("Prvo pitanje glasi");
						} else {
							pitanje.setText("Zamena pitanja glasi...");
							zamenaAktivirana = false;
						}
						Timer timer2 = new Timer(2500, e2 -> {
							zvuci.stop();
							Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/pitanjeZvuk.wav");
							zvuci.pustiMuzikuRepeat(soundURL);
							setPitanjaIPonudjeno(pitanje, b1, b2, b3, b4, q, odgovori);
						});
						timer2.setRepeats(false);
						timer2.start();
					});
					timer1.setRepeats(false);
					timer1.start();
				} else {
					if (!zamenaAktivirana) {
						pitanje.setText("Sledeće pitanje glasi...");
					} else {
						Frame.zvuci.stop();
						zvuci.pustiMuziku(soundURL);
						pitanje.setText("Zamena pitanja glasi...");
						zamenaAktivirana = false;
					}
					Timer timer1 = new Timer(6000, e -> {
						zvuci.stop();
						Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/pitanjeZvuk.wav");
						zvuci.pustiMuzikuRepeat(soundURL);
						setPitanjaIPonudjeno(pitanje, b1, b2, b3, b4, q, odgovori);
					});
					timer1.setRepeats(false);
					timer1.start();
				}
			}
		} else {
			Frame.kvizPanel.setVisible(false);
			Frame.menuPanel.setVisible(true);
			Frame.menuPanel.setFocusable(true);
			zvuci.stop();
			soundURL = OpcijePaneli.class.getResource("/paket1/resursi/Zvuci/glavnaTema.wav");
			zvuci.pustiMuzikuRepeat(soundURL);
		}

	}

	public static void setPitanjaIPonudjeno(JTextArea pitanje, JButton b1, JButton b2, JButton b3, JButton b4, String q,
			String[] odgovori) {
		pitanje.setText(q + "");
		Timer timer1 = new Timer(3000, e -> {
			b1.setText(odgovori[0] + "");
			Timer timer2 = new Timer(2000, e1 -> {
				b2.setText(odgovori[1] + "");
				Timer timer3 = new Timer(2000, e2 -> {
					b3.setText(odgovori[2] + "");
					Timer timer4 = new Timer(2000, e3 -> {
						b4.setText(odgovori[3] + "");
						resignHolder.setEnabled(true);
						if (!fiftyFiftyIsUsed) {
							fiftyFiftyHolder.setEnabled(true);
							Frame.fiftyFiftyMoguc = true;
						}
						if (!pomocPrijateljaIsUsed) {
							pomocPrijateljaHolder.setEnabled(true);
						}
						Frame.predajaMoguca = true;
						if (!zamenaIsUsed) {
							zamenaMoguca = true;
							Frame.zameniPitanjeHolder.setEnabled(true);
						}

					});
					timer4.setRepeats(false);
					timer4.start();
				});
				timer3.setRepeats(false);
				timer3.start();
			});
			timer2.setRepeats(false);
			timer2.start();
		});
		timer1.setRepeats(false);
		timer1.start();

	}

	public static void nakonPoslatogOdgovora(JPanel potvrdaOdgovora, JButton btn1, JButton btn2, JButton btn3,
			JButton btn4, Border borderDefault, Border borderAdvanced)
			throws IOException, ParseException {
		potvrdaOdgovora.setVisible(false);

		if (btn1.getBorder().equals(borderAdvanced)) {
			buttonAnimation(btn1);

		}
		if (btn2.getBorder().equals(borderAdvanced)) {
			buttonAnimation(btn2);

		}
		if (btn3.getBorder().equals(borderAdvanced)) {
			buttonAnimation(btn3);

		}
		if (btn4.getBorder().equals(borderAdvanced)) {
			buttonAnimation(btn4);

		}
	}

	public static void buttonAnimation(JButton btn) {
		dobarButton = getCorrectButton();
		if (btn.equals(dobarButton)) {
			btn.setBackground(Color.YELLOW);
			btn.setForeground(Color.BLACK);
			zvuci.stop();
			Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/finalniOdgovor.wav");
			zvuci.pustiMuziku(soundURL);
			t1 = new Timer(3500, e -> {
				zvuci.stop();
				Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/dobarOdgovor.wav");
				zvuci.pustiMuziku(soundURL);
				t2 = new Timer(350, e2 -> {
					if (counter >= 15) {
						btn.setBackground(new Color(0, 0, 64));
						btn.setForeground(Color.WHITE);
						t2.stop();
						OpcijePaneli.porukaNakonTacnogOdg();
						if (Frame.rBrTrenutnogPitanja <= 15) {
							try {
								gameLoop(rBrTrenutnogPitanja, getLvl1(), getLvl2(), getLvl3(), getTxtArea(), getBtn1(),
										getBtn2(), getBtn3(), getBtn4());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else {
							Frame.kvizPanel.setVisible(false);
							Frame.menuPanel.setVisible(true);
							Frame.menuPanel.setFocusable(true);
							zvuci.stop();
							Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/glavnaTema.wav");
							zvuci.pustiMuziku(soundURL);
						}
					}

					else {

						if (flag) {
							btn.setBackground(Color.GREEN);
							btn.setForeground(Color.WHITE);
						} else {
							btn.setBackground(Color.YELLOW);
							btn.setForeground(Color.BLACK);

						}
						flag = !flag;
						counter++;

					}

				});
				t2.start();

			});
			t1.setRepeats(false);
			t1.start();
			counter = 0;

		} else {
			btn.setBackground(Color.YELLOW);
			btn.setForeground(Color.BLACK);
			zvuci.stop();
			Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/finalniOdgovor.wav");
			zvuci.pustiMuziku(soundURL);
			t1 = new Timer(3500, e -> {
				zvuci.stop();
				Frame.soundURL = Frame.class.getResource("/paket1/resursi/Zvuci/pogresanOdgovor.wav");
				zvuci.pustiMuziku(soundURL);
				t2 = new Timer(350, e2 -> {
					if (counter >= 15) {
						btn.setBackground(Color.RED);
						btn.setForeground(Color.WHITE);
						t2.stop();
						OpcijePaneli.porukaNakonNetacnogOdg();
						try {
							Frame.zameniPitanjeHolder.setEnabled(false);
							Frame.predajaMoguca = false;
							Frame.fiftyFiftyHolder.setEnabled(false);
							Frame.pomocPrijateljaHolder.setEnabled(false);
							gameLoop(rBrTrenutnogPitanja, getLvl1(), getLvl2(), getLvl3(), getTxtArea(), getBtn1(),
									getBtn2(), getBtn3(), getBtn4());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					} else {

						btn.setBackground(Color.RED);
						btn.setForeground(Color.WHITE);
						if (flag) {
							dobarButton.setBackground(Color.GREEN);
							dobarButton.setForeground(Color.WHITE);
						} else {
							dobarButton.setBackground(Color.YELLOW);
							dobarButton.setForeground(Color.BLACK);

						}
						flag = !flag;
						counter++;

					}
				});
				t2.start();
			});
			t1.setRepeats(false);
			t1.start();
			counter = 0;

		}

	}

	public static void removeLabels(JPanel panel, JPanel panel2) {
		Component[] components = panel.getComponents();
		Component[] components2 = panel2.getComponents();

		for (Component component : components) {
			if (component instanceof JLabel) {
				panel.remove(component);
			}
		}
		for (Component component : components2) {
			if (component instanceof JLabel) {
				panel2.remove(component);
			}
		}

	}

}