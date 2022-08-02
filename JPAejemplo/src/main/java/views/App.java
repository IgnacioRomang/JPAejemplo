package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import callbacks.CallbackShop;
import dto.ProductDTO;
import dto.ShopDTO;
import managers.ShopManager;

public class App {

	private JFrame frame;
	private JTable tableShop;
	private JTable tableStock;
	private NewProductView newProductView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
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
	public App() {
		initialize();
	}

	private static String[] tableShopNames = new String[] { "Id", "Name" };
	private static String[] tableProductNames = new String[] { "Id", "Product", "Price", "Amount" };
	private DefaultTableModel modelShop;
	private DefaultTableModel modelProduct;
	private JLabel idLabel, nameLabel;
	private JButton loadButton, addStockButton, editStockButton;

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 860, 489);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		
		modelShop = new DefaultTableModel(new Object[][] {}, tableShopNames) {
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		modelProduct = new DefaultTableModel(new Object[][] {}, tableProductNames) {
			boolean[] columnEditables = new boolean[] { false, false,false,false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		// carcar shops
		ShopManager.getIntenace().getAllShop(new CallbackShop() {

			@Override
			public void createShop(ShopDTO shop) {
				// TODO Auto-generated method stub

			}

			@Override
			public void startTable(List<ShopDTO> list) {
				for (ShopDTO shop : list) {
					Object[] row = new Object[2];
					row[0] = shop.getId();
					row[1] = shop.getName();
					modelShop.addRow(row);
				}
			}
		});

		frame.setJMenuBar(menuBar);

		JMenu menuNew = new JMenu("New");
		menuBar.add(menuNew);

		JMenuItem menuNewItem = new JMenuItem("Product");
		menuNewItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ProductDTO newProductDTO = new ProductDTO();
				newProductView = new NewProductView(newProductDTO);
				newProductView.setVisible(true);
				// TODO enviar a guardar

			}
		});
		menuNewItem.setIcon(null);
		menuNew.add(menuNewItem);

		JMenuItem menuNewShop = new JMenuItem("Shop");
		menuNewShop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShopDTO nShopDTO = new ShopDTO();
				NewShopView newShopView = new NewShopView(nShopDTO);
				newShopView.setVisible(true);
				if (!Objects.isNull(nShopDTO.getName())) {
					// TODO ENVIAR A GUARDAR DEVUELVE UN LONG QUE ES ID

					ShopManager.getIntenace().addNewShop(new CallbackShop() {
						@Override
						public void createShop(ShopDTO shop) {
							Object[] row = new Object[2];
							row[0] = shop.getId();
							row[1] = shop.getName();
							modelShop.addRow(row);
							tableShop.updateUI();
						}

						@Override
						public void startTable(List<ShopDTO> lista) {
							// TODO Auto-generated method stub

						}
					}, nShopDTO);
				}
			}
		});
		menuNew.add(menuNewShop);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 196, 427);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(scrollPane);

		tableShop = new JTable();
		tableShop.setModel(modelShop);
		tableShop.getColumnModel().getColumn(0).setResizable(false);
		tableShop.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableShop.getColumnModel().getColumn(0).setMaxWidth(75);
		tableShop.getColumnModel().getColumn(1).setMaxWidth(100);
		tableShop.setFillsViewportHeight(true);
		tableShop.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableShop);
		tableShop.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				Runnable runneable = new Runnable() {

					@Override
					public void run() {
						int index = tableShop.getSelectedRow();
						Object[] selectedrow =new  Object[2];
						for (int i = 0; i < 2; i++) {
							selectedrow[i] = tableShop.getModel().getValueAt(index, i);
						}
						if(!addStockButton.isEnabled()) {
							startButtonsALabels();
						}
						idLabel.setText("ID: " + selectedrow[0]);
						nameLabel.setText("Name: " + selectedrow[1]);	
						idLabel.updateUI();
						nameLabel.updateUI();
					}
					
				};
				runneable.run();
			}
		});

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(517, 0, 317, 428);
		frame.getContentPane().add(scrollPane_1);

		tableStock = new JTable();
		tableStock.setRowSelectionAllowed(false);
		tableStock.setFillsViewportHeight(true);
		tableStock.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableStock.setModel(modelProduct);
		tableStock.getColumnModel().getColumn(0).setResizable(false);
		tableStock.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableStock.getColumnModel().getColumn(0).setMaxWidth(60);
		tableStock.getColumnModel().getColumn(1).setResizable(false);
		tableStock.getColumnModel().getColumn(1).setMaxWidth(120);
		tableStock.getColumnModel().getColumn(2).setResizable(false);
		tableStock.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableStock.getColumnModel().getColumn(2).setMaxWidth(60);
		tableStock.getColumnModel().getColumn(3).setResizable(false);
		tableStock.getColumnModel().getColumn(3).setPreferredWidth(50);
		tableStock.getColumnModel().getColumn(3).setMaxWidth(60);
		scrollPane_1.setViewportView(tableStock);

		/*
		 * modelShop = new DefaultTableModel(); modelProduct = new DefaultTableModel();
		 * modelShop.setColumnIdentifiers(tableShopNames);
		 * modelProduct.setColumnIdentifiers(tableProductNames);
		 * 
		 */

		idLabel = new JLabel("ID: NUMBER");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setEnabled(false);
		idLabel.setBounds(286, 146, 205, 14);
		frame.getContentPane().add(idLabel);

		nameLabel = new JLabel("Name: NAME");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setEnabled(false);
		nameLabel.setBounds(286, 192, 205, 14);
		frame.getContentPane().add(nameLabel);

		loadButton = new JButton("Load");
		loadButton.setEnabled(false);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		loadButton.setBounds(339, 245, 100, 23);
		frame.getContentPane().add(loadButton);

		addStockButton = new JButton("Add Stock");
		addStockButton.setEnabled(false);
		addStockButton.setBounds(339, 297, 100, 23);
		frame.getContentPane().add(addStockButton);

		editStockButton = new JButton("Edit Stock");
		editStockButton.setEnabled(false);
		editStockButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		editStockButton.setBounds(339, 331, 100, 23);
		frame.getContentPane().add(editStockButton);
	}

	private void startButtonsALabels() {
		idLabel.setEnabled(true);
		;
		nameLabel.setEnabled(true);
		loadButton.setEnabled(true);
		;
		loadButton.setEnabled(true);
		;
		addStockButton.setEnabled(true);
		;
		editStockButton.setEnabled(true);
		;
	}
}
