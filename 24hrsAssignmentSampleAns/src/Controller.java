import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Controller {

	private Model model;
	private View view;
	private MouseListener mouseListener;

	private int pressedX = -1;
	private int pressedY = -1;

	private int noOfcols = 5;
	private int noOfrows = 5;
	private int cellSize = 100;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;

	}

	public void start() {
		setMouseListener();
		setLoadImageListenerToLoadImageButton();
		setSaveGameButtonListenerToSaveGameButton();
		setLoadGameButtonListenerToLoadGameButton();
		setShowOriginalListenerToShowOriginalButton();

		// load test image when launch
		// startNewGame("IMG_5411_ed2.jpg");

	}

	private void setShowOriginalListenerToShowOriginalButton() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				JFrame frame = new JFrame();
				frame.setSize(550, 550);
				frame.setVisible(true);
				Container cp = frame.getContentPane();
				cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
				JLabel photo = new JLabel("");
				photo.setIcon(new ImageIcon(model.getGameImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
				photo.setPreferredSize(new Dimension(500, 500));
				JPanel p = new JPanel();
				p.add(photo);
				cp.add(p);
			}
		};
		view.getShowOriginalButton().addActionListener(listener);
	}

	private void setLoadGameButtonListenerToLoadGameButton() {
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				JFileChooser fileChooser = view.getLoadGameFileChooser();

				Object model = null;
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					try {
						File selectedFile = fileChooser.getSelectedFile();
						FileInputStream file = new FileInputStream(selectedFile.getAbsolutePath());
						ObjectInputStream in = new ObjectInputStream(file);
						model = (Model) in.readObject();
						in.close();
						file.close();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(null, "Error in loading the save file");

					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "The save file is corrupted");

					}

					if (model != null) {
						loadFromGameSave((Model) model);
					}

				}
			}
		};

		view.getLoadGameButton().addActionListener(listener);
	}

	private void setSaveGameButtonListenerToSaveGameButton() {
		ActionListener saveGameButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-HHmmss");
				Date date = new Date();
				try {

					JFileChooser fileChooser = view.getSaveGameFileChooser();
					fileChooser.setSelectedFile(new File(formatter.format(date) + ".save"));

					if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						File fileToSave = fileChooser.getSelectedFile();
						System.out.println("Save as file: " + fileToSave.getAbsolutePath());
						FileOutputStream fileOut = new FileOutputStream(fileToSave.getAbsolutePath());
						ObjectOutputStream out = new ObjectOutputStream(fileOut);
						out.writeObject(model);
						out.close();
						fileOut.close();
						System.out.printf("saved in " + fileToSave.getAbsolutePath());
					}

				} catch (IOException i) {
					i.printStackTrace();
				}
			}
		};
		view.getSaveGameButton().addActionListener(saveGameButtonListener);
	}

	private void setLoadImageListenerToLoadImageButton() {
		ActionListener loadImageListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				JFileChooser fileChooser = view.getImageFileChooser();

				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					File selectedFile = fileChooser.getSelectedFile();
					startNewGame(selectedFile.getAbsolutePath());

				}
			}
		};

		view.getLoadImageButton().addActionListener(loadImageListener);
	}

	private void setMouseListener() {
		mouseListener = new MouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				// System.out.println(e.getX() + "," + e.getY());
//				pressedX = e.getX();
//				pressedY = e.getY();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// System.out.println(e.getX() + "," + e.getY());

//				if (isPositionVaild(pressedX, pressedY) && isPositionVaild(e.getX(), e.getY())) {
//					// prevent switching the same cell
//					if (pressedX / model.getSquareSize() != e.getX() / model.getSquareSize()
//							|| pressedY / model.getSquareSize() != e.getY() / model.getSquareSize()) {
//						model.switchPosition(pressedX / model.getSquareSize(), pressedY / model.getSquareSize(),
//								e.getX() / model.getSquareSize(), e.getY() / model.getSquareSize());
//						view.getPuzzle().render(model.getMap());
//
//						if (isWinGame(model.getMap())) {
//							JOptionPane.showMessageDialog(null, "You Win!");
//						}
//					}
//				}

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (pressedX == -1 || pressedY == -1) {
					pressedX = e.getX();
					pressedY = e.getY();
				} else {
					if (isPositionVaild(pressedX, pressedY) && isPositionVaild(e.getX(), e.getY())) {
						model.switchPosition(pressedX / model.getSquareSize(), pressedY / model.getSquareSize(),
								e.getX() / model.getSquareSize(), e.getY() / model.getSquareSize());

						pressedX = -1;
						pressedY = -1;

						view.getPuzzle().render(model.getMap());

						if (isWinGame(model.getMap())) {
							JOptionPane.showMessageDialog(null, "You Win!");
						}

					}

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}
		};
	}

	private boolean isPositionVaild(int x, int y) {
		int cols = model.getCols();
		int rows = model.getRows();
		int squareSize = model.getSquareSize();

		if (x >= 0 && x <= cols * squareSize && y >= 0 && y <= rows * squareSize) {
			return true;
		}
		return false;
	}

	private boolean isWinGame(int[][] map) {
		int count = 0;
		int cols = map.length;
		int rows = map[0].length;

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				if (map[i][j] != count++) {
					return false;
				}
			}
		}
		return true;
	}

	private void loadFromGameSave(Model model) {
		this.model = model;
		Puzzle puzzle = new Puzzle(model.getRows(), model.getCols(), model.getSquareSize(), model.getGameImage());
		puzzle.addMouseListener(mouseListener);
		view.setPuzzle(puzzle);
		puzzle.render(model.getMap());
	}

	private void startNewGame(String imagePath) {
		File selectedFile = new File(imagePath);
		BufferedImage image = null;
		try {
			image = ImageIO.read(selectedFile);

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (image != null) {
			this.model = new Model(noOfrows, noOfcols, cellSize, image);
			Puzzle puzzle = new Puzzle(noOfrows, noOfcols, cellSize, image);
			puzzle.addMouseListener(mouseListener);
			view.setPuzzle(puzzle);
			model.ShuffleMap();
			puzzle.render(model.getMap());

		} else {
			System.out.println("Error in loading image");
			JOptionPane.showMessageDialog(null, "Error in loading image");
		}
	}

}
