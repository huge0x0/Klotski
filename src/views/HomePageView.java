package views;


import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePageView extends JPanel {
	
	private JButton mButtonChoseLevel;
	private JButton mButtonCreateLevel;
	
	public HomePageView() {
		mButtonChoseLevel=new JButton("Chose Level");
		mButtonCreateLevel=new JButton("Create Level");
		
		add(mButtonChoseLevel);
		add(mButtonCreateLevel);
	}

	public JButton getButtonChoseLevel() {
		return mButtonChoseLevel;
	}

	public JButton getButtonCreateLevel() {
		return mButtonCreateLevel;
	}
	
}
