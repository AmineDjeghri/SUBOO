package ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
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
		int i = 1;
		for(IAction a : actions)
		{
			if(a.getConstructedUnite()!=null)
			{
				sl.addElement(i + ": " + a.getConstructedUnite().getNom());
				time++;
			}
			else
			{
				sl.addElement(i + ": Wait " + a.getWaitedTime());
				time+=a.getWaitedTime();
			}
			i++;
		}
		
		JLabel labelTime = new JLabel("Temps :" + Integer.toString(time));
		
		JList listAction = new JList(sl);
		
		mainPan.setLayout(new BoxLayout(mainPan,BoxLayout.PAGE_AXIS));
		JPanel pan1 = new JPanel();
		
		pan1.setLayout(new BoxLayout(pan1, BoxLayout.LINE_AXIS));
		pan1.add(Box.createHorizontalGlue());
		pan1.add(labelTime);
		pan1.add(Box.createHorizontalGlue());

		JPanel pan2 = new JPanel();
		
		pan2.setLayout(new BoxLayout(pan2, BoxLayout.LINE_AXIS));
		pan2.add(Box.createHorizontalGlue());
		pan2.add(listAction);
		pan2.add(Box.createHorizontalGlue());

		
		mainPan.add(pan1);
		mainPan.add(pan2);
		
		this.setContentPane(mainPan);
		this.setVisible(true);
	}
	
}
