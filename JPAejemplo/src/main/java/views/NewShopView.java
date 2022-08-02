package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import dto.ShopDTO;

public class NewShopView extends JDialog {
	private JTextField txtName;
	private ShopDTO nShopDTO;

	public NewShopView(ShopDTO newShopDTO) {
		setModal(true);
		setResizable(false);
		this.nShopDTO = newShopDTO;
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 260, 225);

		JButton addNewButton = new JButton("Add");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nShopDTO.setName(txtName.getText());
				if(nShopDTO.getName().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Name field can't be empty");
				}
				else {
					dispose();
				}
			}
		});
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, addNewButton, 147, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, addNewButton, 134, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, addNewButton, 174, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, addNewButton, 232, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(springLayout);
		getContentPane().add(addNewButton);

		txtName = new JTextField();
		springLayout.putConstraint(SpringLayout.WEST, txtName, 81, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtName, -58, SpringLayout.EAST, getContentPane());
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Add a new Shop");
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 47, SpringLayout.SOUTH, lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 12, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, 28, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, 244, SpringLayout.WEST, getContentPane());
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		getContentPane().add(lblNewLabel);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nShopDTO.setName(null);;
				dispose();
			}

		});
		springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 147, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, cancelButton, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cancelButton, 110, SpringLayout.WEST, getContentPane());
		getContentPane().add(cancelButton);

		JLabel lblNewLabel_1 = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 3, SpringLayout.NORTH, txtName);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 24, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, -15, SpringLayout.WEST, txtName);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1);
	}

}
