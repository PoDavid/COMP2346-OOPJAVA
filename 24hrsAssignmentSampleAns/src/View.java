import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View {

	private JFrame frame;
	private JPanel[] panels;
	private Puzzle puzzle;

	private JButton loadImageButton;
	private JButton saveGameButton;
	private JButton loadGameButton;
	private JButton showOriginalButton;

	private JFileChooser imageFileChooser;
	private JFileChooser saveGameFileChooser;

	private JFileChooser loadGameFileChooser;

	public View() {
		setFrame();
		setPuzzlePanel();
		setControlPanel();
		setIO();
	}

	private void setIO() {
		// imageFileChooser
		imageFileChooser = new JFileChooser();
		imageFileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
		imageFileChooser.setDialogTitle("Select an image");
		imageFileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Image file", ImageIO.getReaderFileSuffixes());
		imageFileChooser.addChoosableFileFilter(filter);

		// saveGameFileChooser
		saveGameFileChooser = new JFileChooser();
		saveGameFileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
		saveGameFileChooser.setDialogTitle("Save as");

		// imageFileChooser
		loadGameFileChooser = new JFileChooser();
		loadGameFileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
		loadGameFileChooser.setDialogTitle("Select game save");
	}

	private void setFrame() {
		frame = new JFrame("Puzzle Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 650);
		frame.setVisible(true);

		Container cp = frame.getContentPane();
		cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));

		panels = new JPanel[2];
		for (int i = 0; i < panels.length; i++) {
			panels[i] = new JPanel();
			// panels[i].setLayout(new BoxLayout(panels[i], BoxLayout.X_AXIS));
			cp.add(panels[i]);
		}
	}

	private void setPuzzlePanel() {
		panels[0].setPreferredSize(new Dimension(500, 500));
		// panels[0].setBorder(BorderFactory.createLineBorder(Color.black));
	}

	private void setControlPanel() {
		JPanel panelsContainer = new JPanel();
		panelsContainer.setLayout(new BoxLayout(panelsContainer, BoxLayout.X_AXIS));

		loadImageButton = new JButton("Load New Image");
		saveGameButton = new JButton("Save Game");
		loadGameButton = new JButton("Load Game");
		showOriginalButton = new JButton("Show Original Image");

		panelsContainer.add(loadImageButton);
		panelsContainer.add(saveGameButton);
		panelsContainer.add(loadGameButton);
		panelsContainer.add(showOriginalButton);

		panels[1].add(panelsContainer);
	}

	/**
	 * @param puzzle the puzzle to set
	 */
	public void setPuzzle(Puzzle puzzle) {
		this.panels[0].removeAll();
		this.puzzle = puzzle;
		this.puzzle.setPreferredSize(new Dimension(500, 500));
		this.panels[0].add(this.puzzle);

		this.puzzle.update();

		this.panels[0].revalidate();
		this.panels[0].repaint();
	}

	/**
	 * @return the puzzle
	 */
	public Puzzle getPuzzle() {
		return puzzle;
	}

	/**
	 * @return the loadImageButton
	 */
	public JButton getLoadImageButton() {
		return loadImageButton;
	}

	/**
	 * @return the saveGameButton
	 */
	public JButton getSaveGameButton() {
		return saveGameButton;
	}

	/**
	 * @return the loadGameButton
	 */
	public JButton getLoadGameButton() {
		return loadGameButton;
	}

	/**
	 * @return the fileChooser
	 */
	public JFileChooser getImageFileChooser() {
		return imageFileChooser;
	}

	/**
	 * @return the testButton
	 */
	public JButton getShowOriginalButton() {
		return showOriginalButton;
	}

	/**
	 * @return the saveGameFileChooser
	 */
	public JFileChooser getSaveGameFileChooser() {
		return saveGameFileChooser;
	}

	/**
	 * @return the loadGameFileChooser
	 */
	public JFileChooser getLoadGameFileChooser() {
		return loadGameFileChooser;
	}

}
