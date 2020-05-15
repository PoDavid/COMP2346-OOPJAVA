import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Puzzle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4208199424923711593L;
	private BufferedImage picture = null;
	private int rows;
	private int cols;
	private int squareSize;

	private int[][] map;

	Puzzle(int rows, int cols, int cellSize, BufferedImage image) {
		this.rows = rows;
		this.cols = cols;
		this.squareSize = cellSize;
		this.picture = image;
		this.map = new int[cols][rows];

		// this.setBorder(BorderFactory.createLineBorder(Color.black));
		int count = 0;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				map[i][j] = count++;
			}
		}
	}

	public void render(int[][] map) {
		this.map = map;
		this.repaint();
	}

	public void update() {
		this.repaint();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (picture != null) {
			for (int i = 0; i < cols; i++) {
				for (int j = 0; j < rows; j++) {
					int posToDisplay = map[i][j];

					int imageW = picture.getWidth();
					int imageH = picture.getHeight();

					int imageCellW = imageW / cols;
					int imageCellH = imageH / rows;

					BufferedImage subimage = picture.getSubimage(imageCellW * (posToDisplay % cols),
							imageCellH * (posToDisplay / cols), imageCellW, imageCellH);

					g.drawImage(subimage, j * squareSize, i * squareSize, squareSize, squareSize, this);
				}
			}
		}
		// System.out.println("repaint");
	}
}
