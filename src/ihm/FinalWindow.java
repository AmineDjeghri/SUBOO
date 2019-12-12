package ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import itf.IAction;

public class FinalWindow extends JFrame {
	
	private JPanel mainPan = new JPanel();
	
	public FinalWindow(List<IAction> actions)
	{
		this.setTitle("Resultat");
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultListModel sl = new DefaultListModel();
		
		int time = 0;
		
		for(IAction a : actions)
		{
			if(a.getConstructedUnite()!=null)
			{
				sl.addElement(a.getConstructedUnite().getNom());
				time++;
			}
			else
			{
				sl.addElement("Wait: " + a.getWaitedTime());
				time+=a.getWaitedTime();
			}
		}
		
		JLabel labelTime = new JLabel("Temps :" + Integer.toString(time));
		
		JList listAction = new JList(sl);
		
		mainPan.setLayout(new BoxLayout(mainPan,BoxLayout.PAGE_AXIS));
		
		mainPan.add(labelTime);
		mainPan.add(listAction);
		
		this.setContentPane(mainPan);
		this.setVisible(true);
	}
	
}
