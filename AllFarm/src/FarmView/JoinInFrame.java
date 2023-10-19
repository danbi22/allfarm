package FarmView;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import LoginController.LoginDaoImpl;
import logModel.LoginInfo;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JoinInFrame {

	private JFrame frame;
	private JTextField idTextField;
	private JTextField pwTextField;
	private JButton btnLogup;
	private Component parent;
	private FarmMainFrame app;
	
	private final LoginDaoImpl dao = LoginDaoImpl.getinstance();

	/**
	 * Launch the application.
	 */
	public static void showJoininFrame(Component parent) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinInFrame window = new JoinInFrame(parent);
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
	public JoinInFrame(Component parent) {
		this.parent = parent;
		this.app = app;
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
		frame.setBounds(x, y, 667, 455);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원가입");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 39));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(161, 36, 343, 74);
		frame.getContentPane().add(lblNewLabel);
		
		btnLogup = new JButton("회원가입");
		btnLogup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = dao.joinin(getLogInfo());
				if (result == 2) {
					JOptionPane.showMessageDialog(frame, "중복된 아이디 입니다.", "에러", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(frame, "회원가입을 축하드립니다.");
					frame.dispose();
				}
			}
		});
		btnLogup.setBounds(271, 310, 135, 53);
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
	}
	
	private LoginInfo getLogInfo() {
		String id = idTextField.getText();
		String pw = pwTextField.getText();
		
		LoginInfo login = new LoginInfo(id, pw);
		return login;
	}
	

}