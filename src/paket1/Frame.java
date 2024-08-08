package paket1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JTextArea;
import org.json.simple.parser.ParseException;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;
import javax.swing.JSlider;

public class Frame extends JFrame implements ActionListener, MouseListener {

	static ArrayList<Integer> iskPitanja1 = new ArrayList<Integer>();
	static ArrayList<Integer> iskPitanja2 = new ArrayList<Integer>();
	static ArrayList<Integer> iskPitanja3 = new ArrayList<Integer>();
	static JTextArea textArea;
	JButton btnZapocniIgru = new JButton();
	JButton btnPodesavanja = new JButton("Podešavanja");
	JButton btnNapustiIgru = new JButton("Napusti igru");
	static JPanel menuPanel = new JPanel();
	static JPanel kvizPanel = new JPanel();
	static String[] sumeDinari = { "3 MILIONA", "1 500 000", "750 000", "375 000", "192 000", "96 000", "48 000",
			"24 000", "12 000", "6 000", "3 000", "1 500", "900", "600", "300" };
	static int rBrTrenutnogPitanja = 1;
	static JButton btn1;
	static JButton btn2;
	static JButton btn3;
	static JButton btn4;
	static Border borderAdvanced = BorderFactory.createLineBorder(Color.MAGENTA, 4);
	static Border borderDefault = BorderFactory.createLineBorder(Color.yellow, 3);
	JPanel panelPotvrda;
	JButton btnDa;
	boolean poslatOdgovor = false;
	static JLabel logoLabel;
	static JLabel lblRbrPitanja;
	static JPanel rBrPitanjaSidePanel;
	static JPanel panelHolderSuma;
	JLabel lblZagSuma;
	static JLabel holderZagSume;
	JLabel lblOsvojenaSuma;
	static JLabel holderOsvSume;
	static JLabel resignHolder;
	static JLabel fiftyFiftyHolder;
	static boolean fiftyFiftyMoguc = false;
	Scaling scaling = new Scaling();
	static JLabel pomocPrijateljaHolder;
	static boolean predajaMoguca = false;
	static JLabel zameniPitanjeHolder;
	JPanel podesavanjaPanel;
	JLabel lblPodesiZvuk;
	JPanel panel;
	JButton plusDugme;
	JButton minusDugme;
	JButton muteDugme;
	JButton backToMenuBtn;
	static JSlider zvukSlider;
	static Zvuci zvuci;
	static URL soundURL;
	static boolean daKliknuto = false;

	Frame() {
		btn1 = new JButton();
		btn2 = new JButton();
		btn3 = new JButton();
		btn4 = new JButton();

		setTitle("Kviz \"Želite li da postanete milioner?\"");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setSize(800, 500);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new CardLayout(0, 0));

		menuPanel = new JPanel();
		menuPanel.setForeground(new Color(255, 255, 255));
		menuPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(menuPanel, "name_11983483318000");
		menuPanel.setLayout(null);

		btnZapocniIgru = new JButton("Započni igru");
		btnZapocniIgru.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnZapocniIgru.setForeground(new Color(255, 255, 255));
		btnZapocniIgru.setBackground(new Color(0, 0, 64));
		btnZapocniIgru.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnZapocniIgru.setBorder(new LineBorder(new Color(255, 255, 0), 3, true));
		btnZapocniIgru.addActionListener(this);
		btnZapocniIgru.setBounds(248, 256, 288, 60);
		btnZapocniIgru.setFocusPainted(false);
		menuPanel.add(btnZapocniIgru);

		btnPodesavanja = new JButton("Podešavanja");
		btnPodesavanja.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPodesavanja.setBorder(new LineBorder(new Color(255, 255, 0), 3, true));
		btnPodesavanja.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnPodesavanja.setForeground(new Color(255, 255, 255));
		btnPodesavanja.setBackground(new Color(0, 0, 64));
		btnPodesavanja.addActionListener(this);
		btnPodesavanja.setBounds(248, 327, 137, 60);
		btnPodesavanja.addActionListener(this);
		btnPodesavanja.setFocusPainted(false);
		menuPanel.add(btnPodesavanja);

		btnNapustiIgru = new JButton("Napusti igru");
		btnNapustiIgru.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNapustiIgru.setBackground(new Color(0, 0, 64));
		btnNapustiIgru.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNapustiIgru.setForeground(new Color(255, 255, 255));
		btnNapustiIgru.setBorder(new LineBorder(new Color(255, 255, 0), 3, true));
		btnNapustiIgru.addActionListener(this);
		btnNapustiIgru.setBounds(395, 327, 141, 60);
		btnNapustiIgru.setFocusPainted(false);
		menuPanel.add(btnNapustiIgru);

		logoLabel = new JLabel("");
		logoLabel.setBounds(280, 28, 224, 202);
		menuPanel.add(logoLabel);
		scaling.scaleLogo();

		zvuci = Zvuci.getInstance();

		if (menuPanel.isVisible()) {
			if (Zvuci.clip != null) {
				zvuci.stop();
			}
			soundURL = getClass().getResource("/paket1/resursi/Zvuci/glavnaTema.wav");
			zvuci.pustiMuzikuRepeat(soundURL);
		}

		kvizPanel = new JPanel();
		kvizPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(kvizPanel, "name_11994137989200");
		kvizPanel.setLayout(null);

		textArea = new JTextArea();
		textArea.setBorder(new LineBorder(new Color(255, 255, 0), 2));
		textArea.setFont(new Font("Tahoma", Font.BOLD, 16));
		textArea.setForeground(new Color(255, 255, 0));
		textArea.setBackground(new Color(0, 0, 64));
		textArea.setBounds(50, 75, 483, 94);
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setLineWrap(true);
		textArea.setRows(5);
		textArea.setWrapStyleWord(true);
		textArea.setMargin(new Insets(6, 6, 2, 2));
		textArea.setFocusable(false);
		textArea.setEditable(false);
		kvizPanel.add(textArea);

		lblRbrPitanja = new JLabel("");
		lblRbrPitanja.setOpaque(true);
		lblRbrPitanja.setBackground(new Color(0, 0, 64));
		lblRbrPitanja.setBorder(new LineBorder(new Color(255, 255, 0), 2));
		lblRbrPitanja.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblRbrPitanja.setForeground(new Color(255, 255, 0));
		lblRbrPitanja.setBounds(50, 180, 145, 28);
		kvizPanel.add(lblRbrPitanja);

		btn1.setHorizontalAlignment(SwingConstants.LEFT);
		btn1.setForeground(new Color(255, 255, 255));
		btn1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn1.setBackground(new Color(0, 0, 64));
		btn1.setBorder(borderDefault);
		btn1.addActionListener(this);
		btn1.setBounds(50, 257, 224, 48);
		btn1.setFocusPainted(false);
		kvizPanel.add(btn1);

		btn2.setHorizontalAlignment(SwingConstants.LEFT);
		btn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn2.setForeground(new Color(255, 255, 255));
		btn2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn2.setBackground(new Color(0, 0, 64));
		btn2.setBorder(borderDefault);
		btn2.addActionListener(this);
		btn2.setBounds(304, 257, 224, 48);
		btn2.setFocusPainted(false);
		kvizPanel.add(btn2);

		btn3.setHorizontalAlignment(SwingConstants.LEFT);
		btn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn3.setForeground(new Color(255, 255, 255));
		btn3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn3.setBackground(new Color(0, 0, 64));
		btn3.setBorder(borderDefault);
		btn3.addActionListener(this);
		btn3.setBounds(50, 331, 224, 48);
		btn3.setFocusPainted(false);
		kvizPanel.add(btn3);

		btn4.setHorizontalAlignment(SwingConstants.LEFT);
		btn4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn4.setForeground(new Color(255, 255, 255));
		btn4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn4.setBackground(new Color(0, 0, 64));
		btn4.setBorder(borderDefault);
		btn4.addActionListener(this);
		btn4.setBounds(304, 331, 224, 48);
		btn4.setFocusPainted(false);
		kvizPanel.add(btn4);

		rBrPitanjaSidePanel = new JPanel();
		rBrPitanjaSidePanel.setForeground(new Color(255, 255, 255));
		rBrPitanjaSidePanel.setBorder(new MatteBorder(2, 2, 2, 0, (Color) new Color(192, 192, 192)));
		rBrPitanjaSidePanel.setBackground(new Color(0, 128, 255));
		rBrPitanjaSidePanel.setBounds(559, 41, 45, 383);
		kvizPanel.add(rBrPitanjaSidePanel);
		rBrPitanjaSidePanel.setLayout(new GridLayout(15, 1));

		panelHolderSuma = new JPanel();
		panelHolderSuma.setForeground(new Color(255, 255, 255));
		panelHolderSuma.setBorder(new MatteBorder(2, 0, 2, 2, (Color) new Color(192, 192, 192)));
		panelHolderSuma.setBackground(new Color(0, 128, 255));
		panelHolderSuma.setBounds(604, 41, 163, 383);
		kvizPanel.add(panelHolderSuma);
		panelHolderSuma.setLayout(new GridLayout(15, 1));

		JLabel lblA = new JLabel("A)");
		lblA.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblA.setForeground(new Color(255, 255, 0));
		lblA.setBounds(25, 257, 18, 48);
		kvizPanel.add(lblA);

		JLabel lblC = new JLabel("C)");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblC.setForeground(new Color(255, 255, 0));
		lblC.setBounds(25, 331, 18, 48);
		kvizPanel.add(lblC);

		JLabel lblB = new JLabel("B)");
		lblB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblB.setForeground(new Color(255, 255, 0));
		lblB.setBounds(284, 257, 21, 48);
		kvizPanel.add(lblB);

		JLabel lblD = new JLabel("D)");
		lblD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblD.setForeground(new Color(255, 255, 0));
		lblD.setBounds(284, 331, 21, 48);
		kvizPanel.add(lblD);

		panelPotvrda = new JPanel();
		panelPotvrda.setFont(new Font("Tahoma", Font.PLAIN, 13));
		panelPotvrda.setBackground(new Color(128, 128, 128));
		panelPotvrda.setForeground(new Color(0, 0, 64));
		panelPotvrda.setVisible(false);
		panelPotvrda.setBorder(new LineBorder(new Color(255, 255, 0), 3));
		panelPotvrda.setBounds(50, 390, 475, 34);
		kvizPanel.add(panelPotvrda);
		panelPotvrda.setLayout(null);

		btnDa = new JButton("DA");
		btnDa.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDa.setForeground(new Color(255, 255, 255));
		btnDa.setBackground(new Color(128, 128, 255));
		btnDa.setBorder(borderDefault);
		btnDa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnDa.addActionListener(this);
		btnDa.setBounds(377, 0, 61, 34);
		btnDa.setFocusPainted(false);
		panelPotvrda.add(btnDa);

		JLabel lblPotvrda = new JLabel("Da li je ovo Vaš konačan odgovor?");
		lblPotvrda.setBackground(new Color(0, 0, 0));
		lblPotvrda.setForeground(new Color(0, 0, 0));
		lblPotvrda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPotvrda.setBounds(10, 0, 261, 34);
		panelPotvrda.add(lblPotvrda);

		lblOsvojenaSuma = new JLabel("Osvojena suma: ");
		lblOsvojenaSuma.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOsvojenaSuma.setForeground(new Color(255, 255, 0));
		lblOsvojenaSuma.setBounds(50, 35, 97, 21);
		kvizPanel.add(lblOsvojenaSuma);

		holderOsvSume = new JLabel("");
		holderOsvSume.setBorder(new LineBorder(new Color(255, 255, 0), 2));
		holderOsvSume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		holderOsvSume.setBackground(new Color(0, 0, 64));
		holderOsvSume.setOpaque(true);
		holderOsvSume.setForeground(new Color(255, 255, 255));
		holderOsvSume.setBounds(148, 33, 115, 28);
		kvizPanel.add(holderOsvSume);

		lblZagSuma = new JLabel("Zagarantovana suma:");
		lblZagSuma.setForeground(Color.YELLOW);
		lblZagSuma.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblZagSuma.setBounds(287, 35, 125, 21);
		kvizPanel.add(lblZagSuma);

		holderZagSume = new JLabel("");
		holderZagSume.setOpaque(true);
		holderZagSume.setForeground(new Color(255, 255, 255));
		holderZagSume.setFont(new Font("Tahoma", Font.PLAIN, 14));
		holderZagSume.setBorder(new LineBorder(new Color(255, 255, 0), 2));
		holderZagSume.setBackground(new Color(0, 0, 64));
		holderZagSume.setBounds(418, 33, 115, 28);
		kvizPanel.add(holderZagSume);

		fiftyFiftyHolder = new JLabel("");
		fiftyFiftyHolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fiftyFiftyHolder.setBounds(304, 180, 69, 48);
		fiftyFiftyHolder.addMouseListener(this);
		fiftyFiftyHolder.setEnabled(false);
		kvizPanel.add(fiftyFiftyHolder);
		scaling.scaleFiftyFifty();
		fiftyFiftyHolder.setVisible(true);

		resignHolder = new JLabel("");
		resignHolder.setBorder(new LineBorder(new Color(255, 255, 255)));
		resignHolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		resignHolder.setBounds(205, 180, 38, 48);
		resignHolder.addMouseListener(this);
		resignHolder.setEnabled(false);
		kvizPanel.add(resignHolder);
		scaling.scaleResign();
		resignHolder.setVisible(true);

		pomocPrijateljaHolder = new JLabel("");
		pomocPrijateljaHolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pomocPrijateljaHolder.setBounds(383, 180, 62, 48);
		pomocPrijateljaHolder.addMouseListener(this);
		pomocPrijateljaHolder.setEnabled(false);
		kvizPanel.add(pomocPrijateljaHolder);
		scaling.scalePomocPrijatelja();

		zameniPitanjeHolder = new JLabel("");
		zameniPitanjeHolder.setBounds(455, 180, 66, 48);
		zameniPitanjeHolder.addMouseListener(this);
		zameniPitanjeHolder.setEnabled(false);
		zameniPitanjeHolder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		kvizPanel.add(zameniPitanjeHolder);
		scaling.scaleZameniPitanje();

		podesavanjaPanel = new JPanel();
		podesavanjaPanel.setBackground(new Color(0, 0, 128));
		getContentPane().add(podesavanjaPanel, "name_99202282636200");
		podesavanjaPanel.setVisible(false);
		podesavanjaPanel.setLayout(null);

		lblPodesiZvuk = new JLabel("Podesi zvuk");
		lblPodesiZvuk.setOpaque(true);
		lblPodesiZvuk.setForeground(new Color(255, 255, 255));
		lblPodesiZvuk.setBackground(new Color(0, 0, 64));
		lblPodesiZvuk.setBorder(new LineBorder(new Color(255, 255, 0), 2));
		lblPodesiZvuk.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPodesiZvuk.setHorizontalAlignment(SwingConstants.CENTER);
		lblPodesiZvuk.setBounds(293, 54, 197, 88);
		podesavanjaPanel.add(lblPodesiZvuk);

		panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setBounds(223, 166, 337, 66);
		podesavanjaPanel.add(panel);

		plusDugme = new JButton("+");
		plusDugme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		plusDugme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zvuci.pojacajZvuk();
			}
		});
		panel.add(plusDugme);

		minusDugme = new JButton("-");
		minusDugme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minusDugme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zvuci.smanjiZvuk();

			}
		});
		panel.add(minusDugme);

		muteDugme = new JButton("MUTE");
		muteDugme.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		muteDugme.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				zvuci.muteZvuk();

			}

		});
		panel.add(muteDugme);

		zvukSlider = new JSlider(-40, 6, -10);
		zvukSlider.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		zvukSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				float novaJacina = zvukSlider.getValue();
				if (novaJacina == -40) {
					novaJacina = -80;
				}
				zvuci.setTrenutnaJacina(novaJacina);
				zvuci.applyVolume();

			}
		});
		panel.add(zvukSlider);

		backToMenuBtn = new JButton("Vrati se u meni");
		backToMenuBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backToMenuBtn.setBounds(10, 23, 138, 30);
		backToMenuBtn.addActionListener(this);
		podesavanjaPanel.add(backToMenuBtn);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnZapocniIgru) {
			rBrTrenutnogPitanja = 1;
			Pomocna.zamenaAktivirana = false;
			Pomocna.zamenaIsUsed = false;
			Pomocna.fiftyFiftyIsUsed = false;
			Pomocna.pomocPrijateljaIsUsed = false;
			menuPanel.setVisible(false);
			kvizPanel.setVisible(true);
			btn1.setText("");
			btn2.setText("");
			btn3.setText("");
			btn4.setText("");
			try {
				Pomocna.gameLoop(rBrTrenutnogPitanja, iskPitanja1, iskPitanja2, iskPitanja3, textArea, btn1, btn2, btn3,
						btn4);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}

		}
		if (arg0.getSource() == btnNapustiIgru) {
			System.exit(0);
		}

		if (arg0.getSource() == btnPodesavanja) {
			menuPanel.setVisible(false);
			podesavanjaPanel.setVisible(true);
			podesavanjaPanel.setFocusable(true);
		}

		if (arg0.getSource() == backToMenuBtn) {
			podesavanjaPanel.setVisible(false);
			menuPanel.setVisible(true);
			menuPanel.setFocusable(true);
		}

		if (arg0.getSource() == btn1 && !btn4.getText().equals("") && daKliknuto == false) {
			btn1.setBorder(borderAdvanced);
			btn2.setBorder(borderDefault);
			btn3.setBorder(borderDefault);
			btn4.setBorder(borderDefault);
			panelPotvrda.setVisible(true);

		}
		if (arg0.getSource() == btn2 && !btn4.getText().equals("") && daKliknuto == false) {
			btn1.setBorder(borderDefault);
			btn2.setBorder(borderAdvanced);
			btn3.setBorder(borderDefault);
			btn4.setBorder(borderDefault);
			panelPotvrda.setVisible(true);
		}
		if (arg0.getSource() == btn3 && !btn4.getText().equals("") && daKliknuto == false) {
			btn1.setBorder(borderDefault);
			btn2.setBorder(borderDefault);
			btn3.setBorder(borderAdvanced);
			btn4.setBorder(borderDefault);
			panelPotvrda.setVisible(true);
		}
		if (arg0.getSource() == btn4 && !btn4.getText().equals("") && daKliknuto == false) {
			btn1.setBorder(borderDefault);
			btn2.setBorder(borderDefault);
			btn3.setBorder(borderDefault);
			btn4.setBorder(borderAdvanced);
			panelPotvrda.setVisible(true);
		}
		if (arg0.getSource() == btnDa) {
			daKliknuto = true;
			resignHolder.setEnabled(false);
			predajaMoguca = false;
			fiftyFiftyHolder.setEnabled(false);
			fiftyFiftyMoguc = false;
			Pomocna.zameniPitanjeHolder.setEnabled(false);
			pomocPrijateljaHolder.setEnabled(false);
			if (btn1.getBorder().equals(borderAdvanced)) {
				if (btn1.getText().equals(Pomocna.dobarOdg)) {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				rBrTrenutnogPitanja++;

			}
			if (btn2.getBorder().equals(borderAdvanced)) {
				if (btn2.getText().equals(Pomocna.dobarOdg)) {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				rBrTrenutnogPitanja++;

			}
			if (btn3.getBorder().equals(borderAdvanced)) {
				if (btn3.getText().equals(Pomocna.dobarOdg)) {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				rBrTrenutnogPitanja++;

			}
			if (btn4.getBorder().equals(borderAdvanced)) {
				if (btn4.getText().equals(Pomocna.dobarOdg)) {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {
					try {
						Pomocna.nakonPoslatogOdgovora(panelPotvrda, btn1, btn2, btn3, btn4, borderDefault,
								borderAdvanced);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				rBrTrenutnogPitanja++;

			}

			if (btn1.getBorder() != borderAdvanced && btn2.getBorder() != borderAdvanced
					&& btn3.getBorder() != borderAdvanced && btn4.getBorder() != borderAdvanced) {
				OpcijePaneli.unesiteOdgovor();
			}

			Pomocna.flagDugme = false;
		}
	}

	public static JButton getCorrectButton() {
		if (btn1.getText().equals(Pomocna.dobarOdg)) {
			return btn1;
		} else if (btn2.getText().equals(Pomocna.dobarOdg)) {
			return btn2;
		} else if (btn3.getText().equals(Pomocna.dobarOdg)) {
			return btn3;
		} else if (btn4.getText().equals(Pomocna.dobarOdg)) {
			return btn4;
		}
		return null;
	}

	public static int getRBr() {
		return rBrTrenutnogPitanja;
	}

	public static JLabel getLabel() {
		return lblRbrPitanja;
	}

	public static ArrayList<Integer> getLvl1() {
		return iskPitanja1;
	}

	public static ArrayList<Integer> getLvl2() {
		return iskPitanja2;
	}

	public static ArrayList<Integer> getLvl3() {
		return iskPitanja3;
	}

	public static JTextArea getTxtArea() {
		return textArea;
	}

	public static JButton getBtn1() {
		return btn1;
	}

	public static JButton getBtn2() {
		return btn2;
	}

	public static JButton getBtn3() {
		return btn3;
	}

	public static JButton getBtn4() {
		return btn4;
	}

	public static JLabel returnHolderZag() {
		return holderZagSume;

	}

	public static JLabel returnHolderOsv() {
		return holderOsvSume;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == resignHolder && predajaMoguca == true && resignHolder.isEnabled()
				&& btn4.getText() != "") {
			OpcijePaneli.odustajanje();
			zameniPitanjeHolder.setEnabled(false);
			fiftyFiftyHolder.setEnabled(false);
			pomocPrijateljaHolder.setEnabled(false);
		}
		if (e.getSource() == fiftyFiftyHolder && fiftyFiftyMoguc && getCorrectButton() != null && btn4.getText() != ""
				&& Pomocna.fiftyFiftyIsUsed == false) {
			fiftyFiftyHolder.setEnabled(false);
			ArrayList<JButton> dugmad = new ArrayList<JButton>();
			dugmad.add(btn1);
			dugmad.add(btn2);
			dugmad.add(btn3);
			dugmad.add(btn4);
			dugmad.remove(getCorrectButton());
			Collections.shuffle(dugmad);
			dugmad.get(0).setEnabled(false);
			dugmad.get(1).setEnabled(false);
			dugmad.get(0).setBorder(borderDefault);
			dugmad.get(1).setBorder(borderDefault);
			dugmad.get(2).setBorder(borderDefault);
			getCorrectButton().setBorder(borderDefault);
			dugmad.get(2).setEnabled(true);
			getCorrectButton().setEnabled(true);
			fiftyFiftyMoguc = false;
			Pomocna.fiftyFiftyIsUsed = true;

		}
		if (e.getSource() == pomocPrijateljaHolder && pomocPrijateljaHolder.isEnabled()
				&& Pomocna.pomocPrijateljaIsUsed == false && btn4.getText() != "") {
			ArrayList<JButton> dugmad = new ArrayList<JButton>();
			dugmad.add(btn1);
			dugmad.add(btn2);
			dugmad.add(btn3);
			dugmad.add(btn4);
			dugmad.remove(getCorrectButton());
			Random random = new Random();
			int randomBroj = random.nextInt(100) + 1;

			if (randomBroj <= 80) {
				OpcijePaneli.predloziDobarOdgovor();
			} else {
				OpcijePaneli.predloziPogresanOdgovor();
			}
			Pomocna.pomocPrijateljaIsUsed = true;
			pomocPrijateljaHolder.setEnabled(false);
		}
		if (e.getSource() == zameniPitanjeHolder && zameniPitanjeHolder.isEnabled() && Pomocna.zamenaMoguca == true
				&& !Pomocna.zamenaIsUsed) {
			try {
				zameniPitanjeHolder.setEnabled(false);
				Pomocna.zamenaIsUsed = true;
				Pomocna.zamenaAktivirana = true;
				if (panelPotvrda.isVisible()) {
					panelPotvrda.setVisible(false);
				}
				btn1.setBorder(borderDefault);
				btn2.setBorder(borderDefault);
				btn3.setBorder(borderDefault);
				btn4.setBorder(borderDefault);
				textArea.setText("Zamena pitanja glasi...");
				Pomocna.gameLoop(rBrTrenutnogPitanja, iskPitanja1, iskPitanja2, iskPitanja3, textArea, btn1, btn2, btn3,
						btn4);
			} catch (IOException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static JSlider getSlider() {
		return zvukSlider;
	}

}
