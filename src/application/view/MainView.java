package application.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import application.service.ArtService;

public class MainView {

	private JFrame frmFalse;
	private JTextField txtImgPrompt;
	private ArtService artService = new ArtService();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frmFalse.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFalse = new JFrame();
		frmFalse.setTitle("Image Generator");
		frmFalse.setBounds(100, 100, 408, 145);

		frmFalse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFalse.getContentPane().setLayout(null);
		frmFalse.setResizable(false);
		JLabel lblPrompt = new JLabel("Write prompt to generate an image");
		lblPrompt.setBounds(10, 11, 236, 14);
		frmFalse.getContentPane().add(lblPrompt);

		txtImgPrompt = new JTextField();
		txtImgPrompt.setBounds(10, 36, 372, 20);
		frmFalse.getContentPane().add(txtImgPrompt);
		txtImgPrompt.setColumns(10);

		JButton btnGenerateImage = new JButton("Generate Image");
		btnGenerateImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prompt = txtImgPrompt.getText();
				try {
					String imageUrl = artService.generateImage(prompt);
					System.out.println("Imagem gerada: " + imageUrl);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnGenerateImage.setBounds(10, 67, 372, 23);
		frmFalse.getContentPane().add(btnGenerateImage);
	}
}
