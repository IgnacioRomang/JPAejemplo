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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import callbacks.CallbackShop;
import dto.ProductDTO;
import dto.ShopDTO;
import dto.StockDTO;
import managers.ProductManager;
import managers.ShopManager;
import managers.StockManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddAndEditStock extends JDialog {
	private JTextField textAmount;
	private JTextField textPrice;
	private JTable tableProduct;
	private DefaultTableModel tableModel;

	private StockDTO nStockDTO;
	private ShopDTO nShop;

	/**
	 * Create the dialog.
	 */
	public AddAndEditStock(StockDTO nStockDTO) {
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 710, 253);
		getContentPane().setLayout(null);

		textAmount = new JTextField();
		textAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean num = key >= 48 && key <= 57;
				if (!num) {
					e.consume();
				}
			}

		});
		textAmount.setHorizontalAlignment(SwingConstants.CENTER);
		textAmount.setBounds(102, 81, 86, 20);
		getContentPane().add(textAmount);
		textAmount.setColumns(10);

		textPrice = new JTextField();
		textPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();

				boolean num = (key >= 48 && key <= 57) || key == 46;
				if (!num) {
					e.consume();
				}
			}
		});
		textPrice.setHorizontalAlignment(SwingConstants.CENTER);
		textPrice.setBounds(102, 117, 86, 20);
		getContentPane().add(textPrice);
		textPrice.setColumns(10);

		if (nStockDTO.getId() != -1) {
			this.nStockDTO = nStockDTO;
			textPrice.setText(String.valueOf(this.nStockDTO.getPrice()));
			textAmount.setText(String.valueOf(this.nStockDTO.getAmount()));
		} else {
			this.nStockDTO = new StockDTO();
			this.nStockDTO.setProduct(new ProductDTO());
			this.nStockDTO.getProduct().setId(-1);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(242, 15, 442, 187);
		getContentPane().add(scrollPane);

		tableProduct = new JTable();
		tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Code" }) {
			Class[] columnTypes = new Class[] { Long.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableProduct.setModel(tableModel);
		tableProduct.getColumnModel().getColumn(0).setResizable(false);
		tableProduct.getColumnModel().getColumn(1).setResizable(false);
		tableProduct.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(tableProduct);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setBounds(130, 179, 89, 23);
		getContentPane().add(cancelButton);

		JButton acceptButton = new JButton("Accept");
		acceptButton.setBounds(29, 179, 89, 23);
		getContentPane().add(acceptButton);
		acceptButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = tableProduct.getSelectedRow();
				StockDTO nStock = new StockDTO();
				
				JFrame frame = new JFrame();
				frame.setAlwaysOnTop(true);
				if (textPrice.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame, "Price field can't be empty");
				} else {
					if (textAmount.getText().length() == 0) {
						JOptionPane.showMessageDialog(frame, "Amount field can't be empty");
					} else {
						long  id_product = (long) tableModel.getValueAt(index, 0) ;
						long  shop_id =  nStockDTO.getShop().getId();
						nStock.setPrice(Double.valueOf(textPrice.getText()));
						nStock.setAmount(Integer.valueOf(textAmount.getText()));
						if (nStockDTO.getId() == -1) {
							StockManager.getIntenace().saveStock(nStock,id_product,shop_id);
							
						} else {
							nStock.setId(nStockDTO.getId());
							StockManager.getIntenace().updateStock(nStock, id_product,shop_id);
						}
						dispose();
					}
				}
			}
		});

		JLabel lblNewLabel = new JLabel(" Price");
		lblNewLabel.setBounds(46, 120, 46, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Amount");
		lblNewLabel_1.setBounds(46, 84, 46, 14);
		getContentPane().add(lblNewLabel_1);

		JLabel lblStocks = new JLabel("Stocks");
		lblStocks.setHorizontalAlignment(SwingConstants.CENTER);
		lblStocks.setFont(new Font("Dialog", Font.BOLD, 16));
		lblStocks.setBounds(10, 16, 222, 16);
		getContentPane().add(lblStocks);
		startup(this.nStockDTO.getProduct().getId());

	}

	public void startup(Long id) {
		tableModel.setRowCount(0);
		Object[] row = null;
		Integer indexToSelect = null;
		List<ProductDTO> listDto = ProductManager.getIntenace().getAll();
		for (int i = 0; i < listDto.size(); i++) {
			ProductDTO product = listDto.get(i);
			row = new Object[3];
			row[0] = product.getId();
			row[1] = product.getName();
			row[2] = product.getCode();
			tableModel.addRow(row);
			if (id != -1 && row[0] == id) {
				indexToSelect = i;
			}
		}
		if (indexToSelect != null) {
			tableProduct.setRowSelectionInterval(indexToSelect.intValue(), indexToSelect.intValue());
		}
		tableProduct.updateUI();

	}
}
