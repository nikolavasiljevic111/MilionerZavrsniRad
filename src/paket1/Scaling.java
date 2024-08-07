package paket1;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Scaling {
	
	public void scaleLogo() {
		ImageIcon iconLogo = new ImageIcon(getClass().getResource("/paket1/resursi/milionerLogo.png"));
		Image logo = iconLogo.getImage();
		Image imgScale = logo.getScaledInstance(Frame.logoLabel.getWidth(), Frame.logoLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledLogo = new ImageIcon(imgScale);
		Frame.logoLabel.setIcon(scaledLogo);
	}

	public void scaleResign() {
		ImageIcon iconLogo = new ImageIcon(getClass().getResource("/paket1/resursi/whiteFlag.png"));
		Image logo = iconLogo.getImage();
		Image imgScale = logo.getScaledInstance(Frame.resignHolder.getWidth(), Frame.resignHolder.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledLogo = new ImageIcon(imgScale);
		Frame.resignHolder.setIcon(scaledLogo);
	}
	
	public void scaleFiftyFifty() {
		ImageIcon iconLogo = new ImageIcon(getClass().getResource("/paket1/resursi/fiftyFifty.png"));
		Image logo = iconLogo.getImage();
		Image imgScale = logo.getScaledInstance(Frame.fiftyFiftyHolder.getWidth(), Frame.fiftyFiftyHolder.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledLogo = new ImageIcon(imgScale);
		Frame.fiftyFiftyHolder.setIcon(scaledLogo);
	}
	
	public void scalePomocPrijatelja() {
		ImageIcon iconLogo = new ImageIcon(getClass().getResource("/paket1/resursi/pomocPrijatelja.png"));
		Image logo = iconLogo.getImage();
		Image imgScale = logo.getScaledInstance(Frame.pomocPrijateljaHolder.getWidth(), Frame.pomocPrijateljaHolder.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledLogo = new ImageIcon(imgScale);
		Frame.pomocPrijateljaHolder.setIcon(scaledLogo);
	}
	
	public void scaleZameniPitanje() {
		ImageIcon iconLogo = new ImageIcon(getClass().getResource("/paket1/resursi/zameniPitanje.png"));
		Image logo = iconLogo.getImage();
		Image imgScale = logo.getScaledInstance(Frame.zameniPitanjeHolder.getWidth(), Frame.zameniPitanjeHolder.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledLogo = new ImageIcon(imgScale);
		Frame.zameniPitanjeHolder.setIcon(scaledLogo);
	}
}
