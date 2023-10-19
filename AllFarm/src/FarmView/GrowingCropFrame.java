package FarmView;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JProgressBar;

import farmController.FarmDaoImpl;
import farmModel.Crop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GrowingCropFrame {

	private JFrame frame;
	private JProgressBar lPBar;
	private Component parent;
	private Crop crop;
	private JButton btnWatering;
	private ImageIcon banana1Icon = new ImageIcon("image/banana1.png");
	private ImageIcon banana2Icon = new ImageIcon("image/banana2.png");
	private ImageIcon banana3Icon = new ImageIcon("image/banana3.png");
	private ImageIcon deliveryIcon = new ImageIcon("image/배송.jpg");
	
	private ImageIcon wateringIcon;
	private FarmDaoImpl farmDao = FarmDaoImpl.getinstance();
	private JLabel lblCropImage;
	private JLabel lblCropName;
	private JLabel lblBackground;
	private ImageIcon background;

	/**
	 * Launch the application.
	 */
	public static void showGrowingCropFrame(Component parent, Crop crop) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrowingCropFrame window = new GrowingCropFrame(parent, crop);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GrowingCropFrame(Component parent, Crop crop) {
		this.parent = parent;
		this.crop = crop;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 472, 598);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		wateringIcon = new ImageIcon("image/watering.png");
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 472, 564);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		lblCropImage = new JLabel("New label");
		lblCropImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblCropImage.setBounds(116, 93, 229, 225);
		if(crop.getType().equals("바나나")) {
			lblCropImage.setIcon(banana1Icon);
		} else {
			lblCropImage.setIcon(wateringIcon);
		}
		panel.add(lblCropImage);
		
		
		lblCropName = new JLabel(crop.getName());
		lblCropName.setHorizontalAlignment(SwingConstants.CENTER);
		lblCropName.setBounds(145, 328, 161, 24);
		panel.add(lblCropName);
		
		lblBackground = new JLabel("New label");
		lblBackground.setBounds(0, 0, 460, 554);
		background = new ImageIcon("image/farm.png");
		lblBackground.setIcon(background);
		panel.add(lblBackground);
		
		lPBar = new JProgressBar(0, 100);
		lPBar.setBounds(116, 362, 229, 23);
		panel.add(lPBar);
			lPBar.setValue((int)crop.getLevelPoint());
		
		btnWatering = new JButton("물주기");
		btnWatering.setBounds(237, 420, 200, 110);
		panel.add(btnWatering);
		btnWatering.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				crop = farmDao.watering(crop);
				lPBar.setValue((int)crop.getLevelPoint());
				lPBar.setStringPainted(true);
				if (crop.getType().equals("바나나"))	{
					if (crop.getLevelPoint() >= 100) {
						JOptionPane.showMessageDialog(frame, "수확을 축하드립니다.");
						lblCropImage.setIcon(deliveryIcon);
					} else if (crop.getLevelPoint() >= 80) {
						lblCropImage.setIcon(banana3Icon);
					} else if (crop.getLevelPoint() >= 50) {
						lblCropImage.setIcon(banana2Icon);
					}
				}
				
			}
		});
		btnWatering.setIcon(wateringIcon);
		
		
		
	
	}
}