package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class BrickBreakerFrame extends JFrame implements ActionListener{
	private final int SIRINA = 1000;
    private final int VISINA = 500;

    private BrickBreakerPanel panel;

    public BrickBreakerFrame() {
        setSize(SIRINA, VISINA);
        setTitle("Brick Breaker");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new BrickBreakerPanel();
        panel.setBackground(new Color(213,243,254));
        add(panel, BorderLayout.CENTER);
        setResizable(false);
        setVisible(true);
    }
    
    public BrickBreakerPanel getPanel() {
    	return panel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.update();
    }
}
