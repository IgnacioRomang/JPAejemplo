package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import callbacks.CallbackProduct;
import dto.ProductDTO;
import managers.ProductManager;

public class NewProductView extends JDialog {
	private JTextField txtName;
	private JTextField txtCode;
	private ProductDTO nProductDTO;
	private JTable tableProduct;
	private DefaultTableModel modelProduct;

	public NewProductView(ProductDTO newProductDTO) {
		setModal(true);
		setResizable(false);
		this.nProductDTO = newProductDTO;
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 723, 247);
		JButton addNewButton = new JButton("Add");

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		addNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				soSomething();
				int selectedValue = JOptionPane.YES_OPTION;
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				if (ProductManager.getIntenace().exist(txtName.getText())) {
					selectedValue = JOptionPane.showConfirmDialog(frame,
							"Aready exist a Product whit that name, are you sure?", "Warning",
							JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				}
				if (txtCode.getText().isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Code field can't be empty");
				} else {
					ProductDTO dto = new ProductDTO();
					dto.setName(txtName.getText());
					dto.setCode(Long.valueOf(txtCode.getText()).longValue());
					switch (selectedValue) {
					case JOptionPane.YES_OPTION:
						ProductManager.getIntenace().saveProduct(dto);
						break;
					case JOptionPane.NO_OPTION:
					
						break;
					}
					dispose();
				}
			}
		});
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		getContentPane().add(addNewButton);

		txtName = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtName, 75, SpringLayout.NORTH, getContentPane());
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		txtName.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				soSomething();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				soSomething();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				soSomething();

			}
		});
		txtCode = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, txtCode, 118, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, txtName, 0, SpringLayout.EAST, txtCode);
		getContentPane().add(txtCode);
		txtCode.setColumns(10);

		JLabel lblNewLabel = new JLabel("Add a new Product");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel, -176, SpringLayout.SOUTH, getContentPane());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		getContentPane().add(lblNewLabel);

		JButton cancelButton = new JButton("Cancel");
		springLayout.putConstraint(SpringLayout.NORTH, addNewButton, 0, SpringLayout.NORTH, cancelButton);
		springLayout.putConstraint(SpringLayout.WEST, addNewButton, 36, SpringLayout.EAST, cancelButton);
		springLayout.putConstraint(SpringLayout.WEST, cancelButton, 36, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, cancelButton, -573, SpringLayout.EAST, getContentPane());
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nProductDTO = null;
				dispose();

			}

		});
		getContentPane().add(cancelButton);

		JLabel lblNewLabel_1 = new JLabel("Name");
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_1, 47, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -115, SpringLayout.SOUTH, getContentPane());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Code");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 120, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_2, -618, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, txtCode, 6, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel_2);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel_2, 47, SpringLayout.WEST, getContentPane());
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.EAST, lblNewLabel, -35, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.EAST, addNewButton, -21, SpringLayout.WEST, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 289, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 1, SpringLayout.NORTH, scrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, cancelButton, 0, SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 15, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -15, SpringLayout.SOUTH, getContentPane());
		getContentPane().add(scrollPane);

		tableProduct = new JTable();
		tableProduct.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelProduct = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Code" }) {
			Class[] columnTypes = new Class[] { Long.class, String.class, String.class };
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tableProduct.setModel(modelProduct);
		tableProduct.getColumnModel().getColumn(0).setResizable(false);
		tableProduct.getColumnModel().getColumn(1).setResizable(false);
		tableProduct.getColumnModel().getColumn(2).setResizable(false);
		tableProduct.setDefaultRenderer(String.class, centerRenderer);
		tableProduct.setDefaultRenderer(Long.class, centerRenderer);
		scrollPane.setViewportView(tableProduct);
	}

	private void soSomething() {
		ProductManager.getIntenace().getProductsLike(new CallbackProduct() {

			@Override
			public void findProduct(List<ProductDTO> result) {

				modelProduct.setRowCount(0);
				for (ProductDTO product : result) {
					Object[] row = new Object[3];
					row[0] = product.getId();
					row[1] = product.getName();
					row[2] = product.getCode();
					modelProduct.addRow(row);
				}
				tableProduct.updateUI();
			}
		}, txtName.getText());
	}
}
