package FarmView;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import farmController.FarmDaoImpl;
import farmModel.Crop;
import logModel.LoginInfo;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import static farmModel.Crop.Entity.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateCropFrame {

	private JFrame frame;
	private JTextField CropNameTextField;
	private JButton btnGoToFarm;
	private JComboBox comboBox;
	private Component parent;
	private LoginInfo loginInfo;
	private final FarmDaoImpl dao = FarmDaoImpl.getinstance();

	/**
	 * Launch the application.
	 */
	public static void showCreateFramFrame(Component parent, LoginInfo loginInfo) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateCropFrame window = new CreateCropFrame(parent, loginInfo);
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
	public CreateCropFrame(Component parent, LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
		this.parent = parent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		int x = 100, y = 100;
		if (parent != null) {
			x = 660 + parent.getX();
			y = parent.getY();
		}
		frame.setBounds(x, y, 482, 339);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("작물 선택하기");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 46));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(84, 28, 308, 63);
		frame.getContentPane().add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(123, 112, 231, 23);
		comboBox.addItem(CROP_NAME_GRAPE);
		comboBox.addItem(CROP_NAME_BANANA);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("이름");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(25, 168, 82, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		CropNameTextField = new JTextField();
		CropNameTextField.setBounds(123, 168, 231, 37);
		frame.getContentPane().add(CropNameTextField);
		CropNameTextField.setColumns(10);
		
		btnGoToFarm = new JButton("작물 키우러가기");
		btnGoToFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = dao.createNewCrop(getCropInfo());
				if (result == 1) {
					GrowingCropFrame.showGrowingCropFrame(parent, getCropInfo());
					frame.dispose();
				}
			}
		});
		btnGoToFarm.setBounds(164, 236, 134, 38);
		frame.getContentPane().add(btnGoToFarm);
		
	}
	
	private Crop getCropInfo() {
		String id = loginInfo.getId();
		String type = (String) comboBox.getSelectedItem();
		String name = CropNameTextField.getText();
		Crop crop = new Crop(id, name, type);
		return crop;
	}
}