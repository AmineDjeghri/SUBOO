package ihm;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import calculator.CalculatorSingleton;
import itf.IRessource;
import itf.IUnite;
import version.VersionSingleton;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MainWindow extends JFrame implements ActionListener, ItemListener, ListSelectionListener, ChangeListener{
	
	ArrayList<ArrayList<String>> listUniteTBO = new ArrayList<ArrayList<String>>();
	ArrayList<ArrayList<Integer>> listUniteTBOValue = new ArrayList<ArrayList<Integer>>();
	int indiceCBTU = 0;
	int indiceLU = 0;

	private JPanel mainPan = new JPanel();
	private JPanel trouverBOPan = new JPanel();
	private JPanel startMenu = new JPanel();
	
	private JButton buttonToTBO = new JButton("TrouverBO");
	private JButton buttonTOVBO = new JButton("ValiderBO");
	private JButton buttonReturnTBO = new JButton("Retour");
	private JButton buttonValideTBO = new JButton("Valider");
	
	private JComboBox comboBoxVersion;
	private JComboBox comboBoxTypeUnite;
	
	private SpinnerModel spinModel = new SpinnerNumberModel(0,0,10000,1);
	private JSpinner spinner = new JSpinner(spinModel);
	
	private JList listTRouverBO;
	private DefaultListModel listModelTBO= new DefaultListModel();
	public MainWindow()
	{
		this.setTitle("SUBOO");
		this.setSize(300,200);
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
		comboBoxVersion.addItem("test000000000"); //TESTS
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
		comboBoxTypeUnite.addItemListener(this);
		
		listTRouverBO = new JList(listModelTBO);
		listTRouverBO.addListSelectionListener(this);
		spinner.setMaximumSize(spinner.getPreferredSize());
		spinner.addChangeListener(this);
		
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
			updateLists();
			comboBoxTypeUnite.setSelectedIndex(1);
			comboBoxTypeUnite.setSelectedIndex(0);
			
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
		
		if(arg0.getSource() == buttonValideTBO)
		{
			validerBO();
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		if(arg0.getSource() == comboBoxVersion)
		{
			//...
		}
		if(arg0.getSource() == comboBoxTypeUnite)
		{
			indiceLU = 0;
			indiceCBTU = 3;
			
			if(arg0.getItem() == "Unite")
				indiceCBTU = 0;
			else if (arg0.getItem() == "Batiments")
				indiceCBTU = 1;
			else if (arg0.getItem() == "Technologies")
				indiceCBTU = 2;
			
			listModelTBO.clear();
			for(String s : listUniteTBO.get(indiceCBTU))
				listModelTBO.addElement(s);
			
			listTRouverBO.setSelectedIndex(0);
		}
		
	}
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		if(arg0.getSource() == listTRouverBO)
		{
			indiceLU = ((JList)arg0.getSource()).getSelectedIndex();
			if(indiceCBTU != -1 && indiceLU !=-1)
				spinner.setValue(listUniteTBOValue.get(indiceCBTU).get(indiceLU));
		}
		
	}
	
	private void updateLists()
	{
		ArrayList<IUnite> unites = (ArrayList<IUnite>) VersionSingleton.getIversion().getUnites();
		ArrayList<IRessource> ressources = (ArrayList<IRessource>) VersionSingleton.getIversion().getRessources();
		
		listUniteTBO.clear();
		listUniteTBOValue.clear();
		
		for(int i = 0; i<4;i++)
		{
			listUniteTBO.add(new ArrayList<String>());
			listUniteTBOValue.add(new ArrayList<Integer>());
		}
		
		for (IUnite u : unites)
		{
			listUniteTBO.get(u.typeToInt()).add(u.getNom());
			listUniteTBOValue.get(u.typeToInt()).add(0);
		}
		
		for(IRessource r : ressources)
		{
			listUniteTBO.get(3).add(r.getRessourceName());
			listUniteTBOValue.get(3).add(0);
		}
		
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		if(arg0.getSource() == spinner)
		{
			int v = (int) ((JSpinner)arg0.getSource()).getValue();
			if(indiceCBTU != -1 && indiceLU !=-1)
				listUniteTBOValue.get(indiceCBTU).set(indiceLU, v);
		}
	}
	
	private void validerBO()
	{
		ArrayList<IUnite> lUnite = (ArrayList<IUnite>) VersionSingleton.getIversion().getUnites();
		
		ArrayList<Integer> lUniteF = new ArrayList<Integer>();
		for(int i = 0; i < (lUnite.size()); i++)
			lUniteF.add(0);
		for(int j = 0; j< listUniteTBO.size()-1;j++)
			for(int h = 0; h < listUniteTBO.get(j).size();h++)
			{
				for(int i = 0; i<lUnite.size();i++)
				{
					if(lUnite.get(i).getNom()== listUniteTBO.get(j).get(h))
					{
						lUniteF.set(i, listUniteTBOValue.get(j).get(h));
					}
				}
			}
		
		ArrayList<Integer> lRessourceF = listUniteTBOValue.get(3);
		System.out.println(lUniteF);
		System.out.println(lRessourceF);
		
		new FinalWindow(CalculatorSingleton.getInstance().calculBO(lUniteF, lRessourceF));
	}
}
