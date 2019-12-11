package ihm;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ActionListener, ItemListener, ListSelectionListener{
	
	ArrayList<ArrayList<String>> listUniteTBO = new ArrayList<ArrayList<String>>();

	private JPanel mainPan = new JPanel();
	private JPanel trouverBOPan = new JPanel();
	private JPanel startMenu = new JPanel();
	
	private JButton buttonToTBO = new JButton("TrouverBO");
	private JButton buttonTOVBO = new JButton("ValiderBO");
	private JButton buttonReturnTBO = new JButton("retour");
	private JButton buttonValideTBO = new JButton("Valider");
	
	private JComboBox comboBoxVersion;
	private JComboBox comboBoxTypeUnite;
	
	private SpinnerModel spinModel = new SpinnerNumberModel(0,0,100,1);
	private JSpinner spinner = new JSpinner(spinModel);
	
	private JList listTRouverBO;
	public MainWindow()
	{
		this.setTitle("SUBOO");
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//
		//StartMenu
		//
		JPanel pan2 = new JPanel();
		pan2.setLayout(new BoxLayout(pan2,BoxLayout.LINE_AXIS));
		JPanel pan3 = new JPanel();
		pan3.setLayout(new BoxLayout(pan3, BoxLayout.PAGE_AXIS));
		
		
		buttonToTBO.addActionListener(this);
		buttonReturnTBO.addActionListener(this);
		buttonValideTBO.addActionListener(this);
		
		
		comboBoxVersion = new JComboBox<String>();
		comboBoxVersion.addItem("test000000000");
		comboBoxVersion.setMaximumSize(comboBoxVersion.getPreferredSize());
		pan2.add(Box.createHorizontalGlue());
		pan2.add(comboBoxVersion);
		pan3.add(buttonToTBO);
		pan3.add(buttonTOVBO);
		
		
		startMenu.setLayout(new BoxLayout(startMenu,BoxLayout.PAGE_AXIS));
		startMenu.add(Box.createVerticalGlue());
		startMenu.add(pan2);
		startMenu.add(Box.createVerticalGlue());
		startMenu.add(pan3);
		startMenu.add(Box.createVerticalGlue());
		
		//
		//TrouverBO
		//
		JPanel pan4 = new JPanel();
		JPanel pan5 = new JPanel();
		JPanel pan6 = new JPanel();
		
		pan4.setLayout(new BoxLayout(pan4,BoxLayout.LINE_AXIS));
		pan5.setLayout(new BoxLayout(pan5,BoxLayout.LINE_AXIS));
		pan6.setLayout(new BoxLayout(pan6,BoxLayout.LINE_AXIS));
		
		String []l = {"Unite", "Batiments", "Technologies", "Ressources"};
		comboBoxTypeUnite = new JComboBox(l);
		
		pan4.add(comboBoxTypeUnite);
		comboBoxTypeUnite.setMaximumSize(comboBoxTypeUnite.getPreferredSize());
		
		String[]l1 = {"Soldat","Ouvrier","Boss"};
		listTRouverBO = new JList(l1);
		listTRouverBO.addListSelectionListener(this);
		spinner.setMaximumSize(spinner.getPreferredSize());
		
		pan5.add(listTRouverBO);
		pan5.add(spinner);
		
		pan6.add(buttonReturnTBO);
		pan6.add(buttonValideTBO);
		trouverBOPan.add(pan4);
		trouverBOPan.add(pan5);
		trouverBOPan.add(pan6);
		
		trouverBOPan.setLayout(new BoxLayout(trouverBOPan,BoxLayout.PAGE_AXIS));
		
		
		
		
		mainPan.setLayout(new BorderLayout());
		mainPan.add(startMenu);
		
		this.setContentPane(mainPan);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Container contain = this.getContentPane();;
		if(arg0.getSource() == buttonToTBO)
		{
			contain.removeAll();
			contain.add(trouverBOPan);
			this.revalidate();
			this.repaint();
		}
		if(arg0.getSource() == buttonReturnTBO)
		{
			contain.removeAll();
			contain.add(startMenu);
			this.revalidate();
			this.repaint();
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getSource() == comboBoxVersion)
		{
			//...
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(arg0.getSource() == listTRouverBO)
		{
			System.out.println("it1");
		}
		
	}
	
	
	
}
