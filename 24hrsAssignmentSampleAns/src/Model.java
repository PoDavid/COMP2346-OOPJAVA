import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

public class Model implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2389978662675896198L;

	// private BufferedImage gameImage = null;
	private byte[] imageData;

	private int rows;
	private int cols;
	private int squareSize;
	private int[][] map;

	public Model() {
	}

	public Model(int rows, int cols, int cellSize, BufferedImage image) {
		this.rows = rows;
		this.cols = cols;
		this.squareSize = cellSize;
		this.map = new int[cols][rows];

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			imageData = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int count = 0;
		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				map[i][j] = count++;
			}
		}
	}

	public void ShuffleMap() {
		List<Integer> solution = new ArrayList<>();
		for (int i = 0; i < rows * cols; i++) {
			solution.add(i);
		}
		Collections.shuffle(solution);

		for (int i = 0; i < cols; i++) {
			for (int j = 0; j < rows; j++) {
				map[i][j] = solution.get(i * cols + j);
			}
		}
	}

	public void switchPosition(int mapX1, int mapY1, int mapX2, int mapY2) {
		int temp = map[mapY1][mapX1];
		map[mapY1][mapX1] = map[mapY2][mapX2];
		map[mapY2][mapX2] = temp;
	}

	/**
	 * @return the gameImage
	 */
	public BufferedImage getGameImage() {
		InputStream in = new ByteArrayInputStream(imageData);
		BufferedImage gameImage;
		try {
			gameImage = ImageIO.read(in);
			return gameImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @return the rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * @return the map
	 */
	public int[][] getMap() {
		return map;
	}

	/**
	 * @return the cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * @return the squareSize
	 */
	public int getSquareSize() {
		return squareSize;
	}

}
