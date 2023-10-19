package FarmView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import farmController.FarmDaoImpl;
import LoginController.LoginDaoImpl;
import farmModel.Crop;
import logModel.LoginInfo;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

public class FarmMainFrame {

	private JFrame frame;
	private JButton btnLogin;
	private JTextField idTextField;
	private JTextField pwTextField;
	private JButton btnLogup;
	private LoginDaoImpl loginDao = LoginDaoImpl.getinstance();
	private FarmDaoImpl farmDao = FarmDaoImpl.getinstance();
	private ImageIcon bakcIcon;
	private ImageIcon logoIcon;
	private int resultDuplicateIDCheck;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FarmMainFrame window = new FarmMainFrame();
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
	public FarmMainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 660, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 39));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(140, 0, 400, 160);
		logoIcon = new ImageIcon("image/올팜로고2.png");
		lblNewLabel.setIcon(logoIcon);
		frame.getContentPane().add(lblNewLabel);
		
		btnLogin = new JButton("로그인");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = loginDao.loginCheck(getLogInfo());
				if (result == 0) {
					JOptionPane.showMessageDialog(frame, "회원정보를 확인하세요", "에러", JOptionPane.ERROR_MESSAGE);
				} else {
					result = loginDao.checkRecorde(getLogInfo());
					System.out.println(result);
					if (result == 0) {
						CreateCropFrame.showCreateFramFrame(frame, getLogInfo());
					} else {
						//TODO GrowingCropFrame으로 이동
						Crop crop = farmDao.getCropInfo(getLogInfo());
						GrowingCropFrame.showGrowingCropFrame(frame, crop);
					}
				}
				//TODO 프라이머리키로 연결된 crop 테이블이 있는지 확인 있으면 GrowingCropFrame으로 이동
				// 없으면 CreateFarmFrame으로 이동
			}
		});
		btnLogin.setBounds(194, 310, 135, 53);
		frame.getContentPane().add(btnLogin);
		
		btnLogup = new JButton("회원가입");
		btnLogup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinInFrame.showJoininFrame(frame);
			}
		});
		btnLogup.setBounds(369, 310, 135, 53);
		frame.getContentPane().add(btnLogup);
		
		idTextField = new JTextField();
		idTextField.setBounds(210, 147, 275, 45);
		frame.getContentPane().add(idTextField);
		idTextField.setColumns(10);
		
		pwTextField = new JTextField();
		pwTextField.setColumns(10);
		pwTextField.setBounds(210, 217, 275, 45);
		frame.getContentPane().add(pwTextField);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("굴림", Font.BOLD, 39));
		lblId.setBounds(119, 147, 59, 45);
		frame.getContentPane().add(lblId);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("굴림", Font.BOLD, 39));
		lblPw.setBounds(119, 217, 79, 45);
		frame.getContentPane().add(lblPw);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 644, 421);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(0, 0, 644, 421);
		bakcIcon = new ImageIcon("image/농장농장.jpg");
		lblNewLabel_1.setIcon(bakcIcon);
		panel.add(lblNewLabel_1);
	}

	private LoginInfo getLogInfo() {
		String id = idTextField.getText();
		String pw = pwTextField.getText();
		
		LoginInfo login = new LoginInfo(id, pw);
		return login;
	}
}