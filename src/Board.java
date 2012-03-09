import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class Board {
	private int size;
	boolean isAuto = false;
	protected int movesMade;
	private JFrame board;
	private Container cont;
	private Container playArea;
	protected JButton[][] buttons;
	protected JLabel status;
	private JDialog dialog;
	private JDialog hDialog;
	boolean turn = false;
	ImageIcon icon;
	BoardData boardData;

	public Board() {
		movesMade = 0;
		icon = new ImageIcon("src/cross.gif");
		dialog = new JDialog();
		hDialog = new JDialog();
		askSize();

	}

	// GUI formation of board
	private void makeBoard() {
		board = new JFrame();
		board.setTitle("Tic-tac-toe");
		board.setSize(new Dimension(size * 100, size * 100));
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cont = new JPanel(new BorderLayout());
		JMenuBar mBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem startnew = new JMenuItem("New");
		startnew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				reset();
				boardData.reset();
				status.setText("Game On!");
				movesMade = 0;
				turn = false;
			}
		});
		menu.add(startnew);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				board.dispose();
			}
		});
		menu.add(exit);

		JMenu help = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				aboutBox();
			}
		});
		help.add(about);
		mBar.add(menu);
		mBar.add(help);
		// adding button to the board
		playArea = new JPanel(new GridLayout(size, size));
		buttons = new JButton[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				final int bi = i;
				final int bj = j;
				buttons[i][j] = new JButton("");
				buttons[i][j].setFocusPainted(false);
				buttons[i][j].addMouseListener(new MouseListener() {

					@Override
					public void mouseReleased(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mousePressed(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub

						if (e.getButton() == MouseEvent.BUTTON1) {
							if (isAuto) {
								if (!buttons[bi][bj].isEnabled()) {
									return;
								}
								movesMade += 1;
								turn = true;
								buttons[bi][bj].setIcon(new ImageIcon(
										"src/cross.jpg"));
								boardData.updateBoardData(-1, bi, bj);
								int check;
								check = boardData.checkStatus(movesMade);
								System.out
										.println("\nValue of check after manual move= "
												+ check + "\n");
								if (check == 1) {
									buttons[bi][bj].setEnabled(false);
									status.setText("The guy with cross won");
									endGame(-1);
									return;
								} else if (check == 0) {
									buttons[bi][bj].setEnabled(false);
									status.setText("Game draw");

									endGame(0);
									return;
								}
								buttons[bi][bj].setEnabled(false);

								boardData.nextAIMove(movesMade);
								System.out.println("\nvalues got"
										+ boardData.aiR + " " + boardData.aiC);
								buttons[boardData.aiR][boardData.aiC]
										.setIcon(new ImageIcon("src/zero.jpg"));
								movesMade += 1;
								boardData.updateBoardData(1, boardData.aiR,
										boardData.aiC);
								check = boardData.checkStatus(movesMade);

								if (check == 1) {
									buttons[boardData.aiR][boardData.aiC]
											.setEnabled(false);
									status.setText("Computer won");

									endGame(1);
									return;
								} else if (check == 0) {
									buttons[boardData.aiR][boardData.aiC]
											.setEnabled(false);
									status.setText("Game draw");
									endGame(0);
									return;
								}
								buttons[boardData.aiR][boardData.aiC]
										.setEnabled(false);
							}

							else {

								if (!buttons[bi][bj].isEnabled()) {
									return;
								}

								if (turn == false) {
									movesMade += 1;
									turn = true;
									buttons[bi][bj].setIcon(new ImageIcon(
											"src/cross.jpg"));
									boardData.updateBoardData(-1, bi, bj);
									int check;
									check = boardData.checkStatus(movesMade);
									System.out
											.println("\nValue of check after manual move= "
													+ check + "\n");
									if (check == 1) {
										buttons[bi][bj].setEnabled(false);
										status.setText("The player with cross won");
										endGame(-1);
										return;
									} else if (check == 0) {
										buttons[bi][bj].setEnabled(false);
										status.setText("Game draw");
										endGame(0);
										return;
									}
									buttons[bi][bj].setEnabled(false);

								} else {
									System.out.println("\n\n came in true\n\n");
									movesMade += 1;
									turn = false;
									buttons[bi][bj].setIcon(new ImageIcon(
											"src/zero.jpg"));
									boardData.updateBoardData(1, bi, bj);
									int check = boardData
											.checkStatus(movesMade);
									if (check == 1) {
										buttons[bi][bj].setEnabled(false);
										status.setText("The player with zero won");
										endGame(1);
										return;
									} else if (check == 0) {
										buttons[bi][bj].setEnabled(false);
										status.setText("Game draw");
										endGame(0);
										return;
									}
									buttons[bi][bj].setEnabled(false);

								}
							}
						}

					}
				});
				playArea.add(buttons[i][j]);

			}
		}
		status = new JLabel("Tic-tac-toe");
		cont.add(mBar, BorderLayout.PAGE_START);
		cont.add(playArea, BorderLayout.CENTER);
		cont.add(status, BorderLayout.PAGE_END);
		board.setContentPane(cont);
		board.setVisible(true);
	}

	public void show() {
		board.setVisible(true);
	}

	public void reset() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				buttons[i][j].setText("");
				buttons[i][j].setEnabled(true);
			}
		}
	}

	// ask size of the board from the user
	private void askSize() {
		dialog.setTitle("New Tic-tac-toe");
		dialog.setSize(new Dimension(350, 200));
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		Container cont = dialog.getContentPane();
		cont.removeAll();
		cont.setLayout(new GridLayout(3, 3));
		JSeparator separators = new JSeparator();
		separators.setSize(5, 20);
		JLabel ask = new JLabel("Enter the size of board here");

		final JTextField text = new JTextField();
		text.setBounds(new Rectangle(new Dimension(200, 200)));
		text.setSize(100, 150);
		text.setSize(new Dimension(100, 100));

		final JCheckBox auto = new JCheckBox("Single Player");

		JButton button = new JButton("OK");
		button.setSize(new Dimension(10, 10));
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				size = Integer.parseInt(text.getText());
				if (auto.isSelected())
					isAuto = true;
				boardData = new BoardData(size);
				makeBoard();
				dialog.dispose();

			}
		});

		cont.add(ask);
		cont.add(text);
		cont.add(auto);
		cont.add(button);
		dialog.pack();
		dialog.setVisible(true);
	}

	// game over so reset the data and EXIT/REPLAY
	public void endGame(int flag) {
		JLabel ask = new JLabel();
		dialog.setTitle("Game Over");
		Container cont = dialog.getContentPane();
		cont.removeAll();

		dialog.setSize(new Dimension(350, 200));
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		cont.setLayout(new GridLayout(3, 3));
		JSeparator separators = new JSeparator();
		separators.setSize(5, 20);
		if (flag == 1)
			ask.setText("The Player with zero Won");
		else if (flag == -1)
			ask.setText("The Player with cross Won!");
		else
			ask.setText("Game Draw");

		JButton okButton = new JButton("That was cool Play again!");
		okButton.setSize(new Dimension(10, 10));
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				board.dispose();
				dialog.dispose();
				new Board();

			}
		});
		JButton exitButton = new JButton("Exit");
		exitButton.setSize(new Dimension(10, 10));
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// reset();
				board.dispose();
				dialog.dispose();

			}
		});
		cont.add(ask);
		cont.add(okButton);
		cont.add(exitButton);
		dialog.pack();
		dialog.setVisible(true);

	}

	public void aboutBox() {
		JLabel txt = new JLabel();
		txt.setText("Credit: Pankaj Kumar");
		hDialog.setTitle("Game Over");
		Container hcont = hDialog.getContentPane();
		hcont.removeAll();

		hDialog.setSize(new Dimension(350, 200));
		hDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		hcont.setLayout(new GridLayout(3, 3));
		JSeparator separators = new JSeparator();
		separators.setSize(5, 20);
		JButton exitButton = new JButton("OK");
		exitButton.setSize(new Dimension(10, 10));
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// reset();
				hDialog.dispose();

			}
		});
		hcont.add(txt);
		hcont.add(exitButton);
		hDialog.pack();
		hDialog.setVisible(true);

	}
}
