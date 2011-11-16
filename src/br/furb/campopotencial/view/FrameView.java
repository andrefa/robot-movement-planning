package br.furb.campopotencial.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import br.furb.campopotencial.Constants;
import br.furb.campopotencial.Map;
import br.furb.campopotencial.MapFactory;

public class FrameView extends JFrame{

	private static final long serialVersionUID = -496170061145666037L;
	private View panelView;
	private JButton buttonStep;
	
	public FrameView(){
		this(MapFactory.createSimpleMap());
	}
	
	public FrameView(Map map){
		config(map);
		createComponents(map);
	}

	private void config(Map map) {
		setTitle("Campo potencial");
		setSize((map.getWidth() * Constants.OFFSET) + 30, (map.getHeight() * Constants.OFFSET) + 80);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setLocation(1500, 200);
	}

	private void createComponents(Map map) {
		panelView = new View(map);
		add(panelView);
		
		buttonStep = new JButton("Passo");
		buttonStep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				FrameView.this.panelView.step();
			}
		});
		add(buttonStep);
	}
	
	public static void main(String[] args) {
		new FrameView(MapFactory.createSimpleMap()).setVisible(true);
	}

}
