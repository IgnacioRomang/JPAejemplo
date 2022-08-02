package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import dto.ProductDTO;

public class NewProductView extends JDialog {
	private JTextField txtName;
	private JTextField txtCode;
	private ProductDTO nProductDTO;
	
	public NewProductView(ProductDTO newProductDTO) {
		setModal(true);
		setResizable(false);
		this.nProductDTO = newProductDTO;
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 260, 225);
		
		JButton addNewButton = new JButton("Add");
		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 46, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtName, 85, SpringLayout.WEST, getContentPane());
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtCode = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCode, 89, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtCode, 85, SpringLayout.WEST, getContentPane());
		getContentPane().add(txtCode);
		txtCode.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Add a new Product");
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
				nProductDTO = null;
				dispose();
				
			}
			
		});
		springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 147, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, cancelButton, 12, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cancelButton, 110, SpringLayout.WEST, getContentPane());
		getContentPane().add(cancelButton);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 48, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 25, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 67, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Code");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 91, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 25, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, 67, SpringLayout.WEST, getContentPane());
		getContentPane().add(lblNewLabel_2);
	}
}
