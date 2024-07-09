package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GameController;
import model.Player;
import model.PlayerModel;

public class Screen2 extends Screen {
	private static final long serialVersionUID = 1L;

	private JPanel screen2, jpl, jpl1;
	private JButton btnNext;

	private JComboBox<String> cbb;
	private JLabel lb;

	private int n = 2;
	GameController gameController;

	public Screen2(View mainFrame, GameController gameController) {
		super(mainFrame);
		this.gameController = gameController;
		screen2 = new JPanel();
		screen2.setLayout(new BorderLayout());

		lb = new JLabel("Thêm người chơi");
		String[] list = { "        2        ", "        3        ", "        4        " };

		cbb = new JComboBox<String>(list);

		jpl = new JPanel();

		jpl.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
		jpl.add(lb);
		jpl.add(cbb);
		cbb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == cbb) {
					n = Integer.valueOf(cbb.getSelectedItem().toString().trim());
				}
			}
		});

		btnNext = new JButton("Next");

		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNext) {
					try {
						for (int i = 0; i < n; i++) {
							Player player = new PlayerModel();
							gameController.addPlayer(player);
						}
						gameController.getGameModel().gameStart();
						gameController.notifyGameOb();

					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		});
		jpl1 = new JPanel();
		jpl1.add(new JLabel(new ImageIcon("src/img/background_screen2.png")));
		screen2.add(jpl, BorderLayout.NORTH);
		screen2.add(btnNext, BorderLayout.CENTER);
		screen2.add(jpl1, BorderLayout.SOUTH);
		this.add(screen2);
	}
}
