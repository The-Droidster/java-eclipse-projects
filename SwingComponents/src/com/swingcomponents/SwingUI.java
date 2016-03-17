package com.swingcomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SwingUI {

	private int selectedIndex;
	
	private JFrame frame, frameDemo;
	private JPanel panel,
	btnPanel,
	ckBoxItemPanel, ckBoxSelItemPanel, ckBoxFruitPanel, ckBoxFontPanel,
	radioPanel, 
	colChBannerPanel, colChBannerBgColPanel, colChBannerTxtColPanel, colChBottomPanel, 
	listPanel, listColPanel, listShopPanel, listShopBtnPanel;
	private JLabel label, lbl1, lbl2, lbl3, lbl4, //Label
	lblCounter, //used with Button
	lblCkBoxItems, lblCkBoxSelItems, lblCkBoxApple, lblCkBoxOrange, lblCkBoxGrapes, lblCkBoxBanana, lblCkBoxMango, lblCkBoxText, //CheckBoxLabels
	lblRadio, 
	lblColChBanner, 
	lblComboBoxText, 
	lblFCLog, 
	frameLbl, 
	lblLyrdPane, 
	lblColList, lblShopList; 
	private JButton button, btnBack, 
	btnCount, btnReset, 
	btnColChBannerBgColor, 
	btnOpenFile, btnSaveFile,
	btnLPTop, btnLPMiddle, btnLPBottom, 
	btnShopListAdd, btnShopListRemove;
	private JCheckBox ckBoxApple, ckBoxOrange, ckBoxGrapes, ckBoxBanana, ckBoxMango, 
	ckBoxBold, ckBoxItalic;
	private JRadioButton radioBtnNexus, radioBtnIPhone, radioBtnLumia;
	private ButtonGroup radioGroup;
	private Color bannerBgColor, bannerTextColor; //To be used with JColorChooser
	private JColorChooser bannerTextColorChooser;
	private JComboBox<String> comboBox;
	//private JOptionPane optionPane; //Can be used with JDialog to initialize a JOptioPane and then add it to the JDialog container
	private JFileChooser fileChooser;
	private JTextArea tAreaFCLog;
	private JInternalFrame internalFrame;
	private JDesktopPane desktopPane; //container for internalFrame
	private JLayeredPane layeredPane;
	private JList<String> colorList, shopList;
	private DefaultListModel<String> listModel; //Required for a mutable list
	private JScrollPane jSPShopList;
	private JTextField tfShopListItem;
	private File file;
	private int count;
	private int fcRetVal;
	private String[] compList, colorNames;
	
	public SwingUI() {
		initUI();
	}
	
	private void initUI() {
		createFrame();		
	}
	
	private void createFrame() {
		frame = new JFrame("Swing Components Demo");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		createPanel();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	private void createPanel()  {
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		showMainMenu();
	}
	
	private void showMainMenu() {
		panelClear();
		
		panel.setLayout(new FlowLayout());
		
		btnPanel(); //Refresh btnPanel, Remove any additional buttons
		
		lblComboBoxText = new JLabel("Select a component to demo it! ==>", SwingConstants.CENTER);
		
		compList = new String[]{"Select Component", "Buttons", "CheckBoxes", "RadioButtons", "Labels", "ColorChooser", "ComboBoxes", "Dialogs", "FileChooser", "Frame", "InternalFrame", "LayeredPane", "Lists"};		
		comboBox = new JComboBox<>(compList);
		comboBox.setSelectedIndex(0);
		
		button = new JButton("Go!");
		
		panel.add(lblComboBoxText);
		panel.add(comboBox);
		panel.add(button);
		
		updateUI();
		//frame.getRootPane().setDefaultButton(button); //set button as default selected, consumes key presses
		
		defaultEventListener();
	}
	
	private void defaultEventListener() {
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selectedIndex = comboBox.getSelectedIndex(); //getSelectedItem() returns String name of the item
				
				switch (selectedIndex) {
				case 1: showButton();					
					break;
				
				case 2:	showCheckBox();			
					break;	
					
				case 3:	showRadioButton();			
					break;	
					
				case 4:	showLabel();				
					break;
					
				case 5:	showColorChooser();
					break;
					
				case 6:	showComboBox();
						break;
					
				case 7: showDialog();
					break;
				
				case 8: showFileChooser();
						break;
						
				case 9: showFrame();
						break;
					
				case 10: showFrame();
						showInternalFrame();
						break;
					
				case 11: showLayeredPane();
						 break;
				
				case 12: showList();
				 		 break;
						
				default: JOptionPane.showMessageDialog(frame, "Please select a component from the dropdown!", "No selection made!", JOptionPane.WARNING_MESSAGE);
					break;
				}
				
			}
		});
		
	}
	
	private void showLabel() {
		panelClear();
		
		panel.setLayout(new GridLayout(0, 1, 0, 10));
		
		label = new JLabel("Hello, World!");
		lbl1 = new JLabel("That's a label.");
		lbl2 = new JLabel("This is another label.");
		lbl3 = new JLabel("This is a BBBBBBBBBBIIIIIIIIIIIIIIIIIIIIGGGGGGGGGG label.");
		lbl4 = new JLabel("Ok! That's enough labels.");
		
		panel.add(label);
		panel.add(lbl1);
		panel.add(lbl2);
		panel.add(lbl3);
		panel.add(lbl4);
		
		panel.add(btnPanel);
		
		updateUI();
	}
	
	private void showButton() {
		count = 0;
		
		panelClear();
		
		panel.setLayout(new GridLayout(0, 1, 5, 10));
		
		lblCounter = new JLabel("Number Of Clicks: " + count);
		lblCounter.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnCount = new JButton("Click Me!");
		btnReset = new JButton("Reset");
				
		panel.add(lblCounter);
		btnPanel.add(btnCount);
		btnPanel.add(btnReset);
		panel.add(btnPanel);
		
		updateUI();
		
		btnCount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				lblCounter.setText("Number Of Clicks: "+ count);
				updateUI();
			}
		});
		
		btnReset.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				count = 0;
				lblCounter.setText("Number Of Clicks: "+ count);
				updateUI();
			}
		});
				
	}
	
	private void showCheckBox() {
		panelClear();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		//Fruits Panel
		ckBoxFruitPanel = new JPanel();
		ckBoxFruitPanel.setLayout(new FlowLayout());
		
		ckBoxItemPanel = new JPanel();
		ckBoxItemPanel.setLayout(new GridLayout(0, 1));
		ckBoxFruitPanel.add(ckBoxItemPanel);
		
		ckBoxSelItemPanel = new JPanel();
		ckBoxSelItemPanel.setLayout(new GridLayout(0, 1, 0, 8));
		ckBoxFruitPanel.add(ckBoxSelItemPanel);
		
		panel.add(ckBoxFruitPanel);	//Added on base panel - Vertical BoxLayout
		
		ckBoxApple = new JCheckBox("Apple");
		ckBoxOrange = new JCheckBox("Orange");
		ckBoxGrapes = new JCheckBox("Grapes");
		ckBoxBanana = new JCheckBox("Banana");
		ckBoxMango = new JCheckBox("Mango");
		
		lblCkBoxItems = new JLabel("Select Fruits:");
		lblCkBoxSelItems = new JLabel("Selected Fruits:");
		lblCkBoxApple = new JLabel("Apple");
		lblCkBoxOrange = new JLabel("Orange");
		lblCkBoxGrapes = new JLabel("Grapes");
		lblCkBoxBanana = new JLabel("Banana");
		lblCkBoxMango = new JLabel("Mango");
		
		ckBoxItemPanel.add(lblCkBoxItems);
		ckBoxItemPanel.add(ckBoxApple);
		ckBoxItemPanel.add(ckBoxOrange);
		ckBoxItemPanel.add(ckBoxGrapes);
		ckBoxItemPanel.add(ckBoxBanana);
		ckBoxItemPanel.add(ckBoxMango);
		
		ckBoxSelItemPanel.add(lblCkBoxSelItems);
		
		//Font Panel
		ckBoxFontPanel = new JPanel();
		ckBoxFontPanel.setLayout(new GridLayout(0, 1));
		ckBoxFontPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
		panel.add(ckBoxFontPanel);	//Added on base panel - Vertical BoxLayout
		
		lblCkBoxText = new JLabel("This is some text.", SwingConstants.CENTER);
		lblCkBoxText.setFont(new Font("Serif", Font.PLAIN, 14));
		
		ckBoxBold = new JCheckBox("Bold");
		ckBoxItalic = new JCheckBox("Italic");
		
		ckBoxFontPanel.add(lblCkBoxText);
		ckBoxFontPanel.add(ckBoxBold);
		ckBoxFontPanel.add(ckBoxItalic);
		
		panel.add(btnPanel);	//Added on base panel - Vertical BoxLayout
		
		updateUI();
		
		//Fruit ItemListeners
		ckBoxApple.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ckBoxApple.isSelected()) {					
					ckBoxSelItemPanel.add(lblCkBoxApple);					
				}
				else {
					ckBoxSelItemPanel.remove(lblCkBoxApple);
				}
				updateUI();				
			}
		});
		
		ckBoxOrange.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ckBoxOrange.isSelected()) {					
					ckBoxSelItemPanel.add(lblCkBoxOrange);					
				}
				else {
					ckBoxSelItemPanel.remove(lblCkBoxOrange);
				}
				updateUI();
			}
		});
		
		ckBoxGrapes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ckBoxGrapes.isSelected()) {					
					ckBoxSelItemPanel.add(lblCkBoxGrapes);					
				}
				else {
					ckBoxSelItemPanel.remove(lblCkBoxGrapes);
				}
				updateUI();
			}
		});

		ckBoxBanana.addItemListener(new ItemListener() {
	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ckBoxBanana.isSelected()) {					
					ckBoxSelItemPanel.add(lblCkBoxBanana);					
				}
				else {
					ckBoxSelItemPanel.remove(lblCkBoxBanana);
				}
				updateUI();
			}
		});

		ckBoxMango.addItemListener(new ItemListener() {
	
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(ckBoxMango.isSelected()) {					
					ckBoxSelItemPanel.add(lblCkBoxMango);					
				}
				else {
					ckBoxSelItemPanel.remove(lblCkBoxMango);
				}
				updateUI();
			}
		});
		
		//Font ItemListeners
		ckBoxBold.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				fontCheck();				
			}
		});
		
		ckBoxItalic.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				fontCheck();				
			}
		});
		
	}
	
	private void showRadioButton() {
		panelClear();
		
		panel.setLayout(new BorderLayout()); //Could also be done with GridLayout
		
		radioPanel = new JPanel();
		radioPanel.setLayout(new GridLayout(0, 1)); //Redundant if main panel set to GridLayout
		panel.add(radioPanel, BorderLayout.CENTER);
		
		lblRadio = new JLabel("Go On...Make Your Choice!", SwingConstants.CENTER);
		lblRadio.setPreferredSize(new Dimension(200, 40));
		lblRadio.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
		
		radioBtnNexus = new JRadioButton("Nexus Fan");
		radioBtnIPhone = new JRadioButton("iPhone Fan");
		radioBtnLumia = new JRadioButton("Lumia Fan");
		
		radioGroup = new ButtonGroup();
		radioGroup.add(radioBtnNexus);
		radioGroup.add(radioBtnIPhone);
		radioGroup.add(radioBtnLumia);
		
		panel.add(lblRadio, BorderLayout.NORTH);
		radioPanel.add(radioBtnNexus);
		radioPanel.add(radioBtnIPhone);
		radioPanel.add(radioBtnLumia);
		
		panel.add(btnPanel, BorderLayout.SOUTH);
		
		updateUI();
		
		radioBtnNexus.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				radioCheck();		
			}
		});
		
		radioBtnIPhone.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				radioCheck();		
			}
		});
		
		radioBtnLumia.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				radioCheck();		
			}
		});

	}
	
	private void showColorChooser() {
		panelClear();
		
		panel.setLayout(new BorderLayout());
		
		{
		colChBannerPanel = new JPanel(new BorderLayout());
		colChBannerPanel.setBorder(BorderFactory.createTitledBorder("Banner"));
		
		lblColChBanner = new JLabel("The Magic Happens Here!", SwingConstants.CENTER);
		lblColChBanner.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblColChBanner.setOpaque(true);
		lblColChBanner.setPreferredSize(new Dimension(colChBannerPanel.getWidth(), 100));
		colChBannerPanel.add(lblColChBanner, BorderLayout.CENTER);
		}	
		
		{
		colChBannerBgColPanel = new JPanel();
		colChBannerBgColPanel.setBorder(BorderFactory.createTitledBorder("Choose Banner Background Color"));

		btnColChBannerBgColor = new JButton("Show Color Chooser");
		colChBannerBgColPanel.add(btnColChBannerBgColor);
		}
		
		{
		colChBannerTxtColPanel = new JPanel();
		colChBannerTxtColPanel.setBorder(BorderFactory.createTitledBorder("Choose Banner Text Color"));				
		
		bannerTextColorChooser = new JColorChooser();
		colChBannerTxtColPanel.add(bannerTextColorChooser);		
		}
		
		{
		colChBottomPanel = new JPanel();
		colChBottomPanel.setLayout(new BorderLayout());
		colChBottomPanel.add(colChBannerTxtColPanel, BorderLayout.CENTER);
		colChBottomPanel.add(btnPanel, BorderLayout.PAGE_END);
		}
		
		panel.add(colChBannerPanel, BorderLayout.PAGE_START);
		panel.add(colChBannerBgColPanel, BorderLayout.CENTER);
		panel.add(colChBottomPanel, BorderLayout.PAGE_END);
		
		updateUI();
		
		bannerTextColorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				bannerTextColor = bannerTextColorChooser.getColor();
				lblColChBanner.setForeground(bannerTextColor);				
			}
		});
		
		btnColChBannerBgColor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				bannerBgColor = JColorChooser.showDialog(btnColChBannerBgColor, "Choose a Color", lblColChBanner.getBackground());
				
				if(bannerBgColor != null) {
					lblColChBanner.setBackground(bannerBgColor);
				}
				else {
					lblColChBanner.setBackground(lblColChBanner.getBackground());
				}
			}
		});
		
	}
	
	private void showComboBox() {		
		lblComboBoxText.setText("Oh! You are using one! ;-) ===> ");
		panel.add(btnPanel);
		updateUI();		
	}
	
	private void showDialog() {
		int option = JOptionPane.showConfirmDialog(frame, "This is a dialog. Show Another?", "A confirmation Dialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(option == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(null, "This is another dialog.\nThere are many more types and options. But we'll stop here.", "A message dialog.", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showMessageDialog(null, "Tsk, It really was a good dialog you know! :-(", "An error message dialog", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void showFileChooser() {
		panelClear();
		
		panel.setLayout(new BorderLayout(0, 5));
		
		fileChooser = new JFileChooser();
		
		btnOpenFile = new JButton("Open a File");
		btnSaveFile = new JButton("Save a File");
		
		lblFCLog = new JLabel("Task Log:-"); 
		
		tAreaFCLog = new JTextArea();		
		tAreaFCLog.setFont(new Font("Monotype", Font.BOLD, 12));
		tAreaFCLog.setMargin(new Insets(5, 5, 5, 5));
		tAreaFCLog.setEditable(false);
		
		btnPanel.add(btnSaveFile);
		btnPanel.add(btnOpenFile);		
		panel.add(btnPanel, BorderLayout.PAGE_START);
		panel.add(lblFCLog, BorderLayout.CENTER);
		panel.add(tAreaFCLog, BorderLayout.PAGE_END);
		
		updateUI();
		
		btnOpenFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fcRetVal = fileChooser.showOpenDialog(frame);
				
				if(fcRetVal == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					tAreaFCLog.append(">Opening: " + file.getName() +".\n");
				}
				else {
					tAreaFCLog.append(">Open command cancelled by user.\n");
				}
				updateUI();
			}
		});
		
		btnSaveFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fcRetVal = fileChooser.showSaveDialog(frame);
				
				if(fcRetVal == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					tAreaFCLog.append(">Saving: " + file.getName() +".\n");
				}
				else {
					tAreaFCLog.append(">Save command cancelled by user.\n");
				}
				updateUI();
			}
		});
		
	}
	
	private void showFrame() {
		
		frame.setVisible(false);
		frameDemo = new JFrame("A bare JFrame. This window is a frame!");	
		//frameDemo.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); => replaced by WindowListener
		frameDemo.setSize(500, 500);
		frameLbl = new JLabel("All previous windows were frames too. Close this window to return to the main menu.", SwingConstants.CENTER);
		frameDemo.getContentPane().add(frameLbl);//not adding a panel explicitly
		frameDemo.setLocationRelativeTo(null);
		frameDemo.setVisible(true);
		
		frameDemo.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				e.getWindow().dispose();
				frame.setVisible(true);
				showMainMenu();
			}
		});
	}
	
	//Utilizes the same frame from the showFrame method
	private void showInternalFrame() {
		
		/* 
		 * 1. Clear the frameDemo frame
		 * 2. Create and adds a desktopPane as its(frames) contentPane
		 * The desktopPane can hold internalFrames
		 * 3. create and add an internalFrame to the desktopPane
		 * 4. add a label/component to the contentPane/Panel of the interalFrame
		 */
		
		//1.
		frameDemo.getContentPane().removeAll();//getContentPane emulates a panel as it was not added explicitly to the frame
		frameDemo.getContentPane().revalidate();
		frameDemo.getContentPane().repaint();
		frameDemo.setTitle("Top Level Frame/Window, A container for InternalFrames");
		
		//2.
		desktopPane = new JDesktopPane(); //Container for InternalFrames, A container used to create a multiple-document interface or a virtual desktop.
		frameDemo.setContentPane(desktopPane);//setting the content pane to desktopPane NOT a Panel
		
		//3.
		internalFrame = new JInternalFrame("An Internal Frame.", true, true, true);
		internalFrame.setSize(300, 300);
		
		//4.
		frameLbl.setText("<html><center>This is an Internal Frame.<br>Close the outer window to return to the main menu!</center></html>");
		internalFrame.getContentPane().add(frameLbl);//Alternately add panel to internal frame and add other components to the panel
		
		//3.
		internalFrame.setVisible(true);
		desktopPane.add(internalFrame);		
		
	}
	
	private void showLayeredPane() {
		panelClear();
		
		panel.setLayout(new BorderLayout());
		
		layeredPane = new JLayeredPane(); //you can just get the default layered pane of the pane
										//by using layeredPane = frame.getLayeredPane(); method
		layeredPane.setPreferredSize(new Dimension(300, 250));
		
		lblLyrdPane = new JLabel("3 Buttons at different depths on a LayeredPane", SwingConstants.CENTER);
		
		btnLPTop = new JButton("Top");
		btnLPTop.setBackground(Color.white);
		btnLPTop.setBounds(60, 60, 100, 50);//LayeredPane by default does not have a layout manager
											//setting position explicitly is mandatory
											//Assigning a layout manager to the LayeredPane will put all the components as if in one layer 
		
		btnLPMiddle = new JButton("Middle");
		btnLPMiddle.setBackground(Color.gray);
		btnLPMiddle.setBounds(90, 90, 100, 50);
		
		btnLPBottom = new JButton("Bottom");
		btnLPBottom.setBackground(Color.black);
		btnLPBottom.setBounds(120, 120, 100, 50);
		
		layeredPane.add(btnLPTop, new Integer(3)); //Specify depth, lower integer = lower depth(deeper), index is opposite => topmost is 0
		layeredPane.add(btnLPMiddle, new Integer(2));
		layeredPane.add(btnLPBottom, new Integer(1));
		
		panel.add(lblLyrdPane, BorderLayout.PAGE_START);
		panel.add(layeredPane, BorderLayout.CENTER);
		panel.add(btnPanel, BorderLayout.PAGE_END);
		
		updateUI();	
		
		btnLPTop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblLyrdPane.setText("Top Button Clicked ==> Depth(" + JLayeredPane.getLayer(btnLPTop) + ")");
				updateUI();
			}
		});
		
		btnLPMiddle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblLyrdPane.setText("Middle Button Clicked ==> Depth(" + JLayeredPane.getLayer(btnLPMiddle) + ")");
				updateUI();
			}
		});
	
		btnLPBottom.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				lblLyrdPane.setText("Bottom Button Clicked ==> Depth(" + JLayeredPane.getLayer(btnLPBottom) + ")");
				updateUI();
			}
		});

	}
	
	private void showList() {
		panelClear();
		
		//=========Panels=========
		panel.setLayout(new BorderLayout());
		
		listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout());
		
		listColPanel = new JPanel();
		listColPanel.setLayout(new BoxLayout(listColPanel, BoxLayout.PAGE_AXIS));
		listColPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		listShopPanel = new JPanel();
		listShopPanel.setLayout(new BoxLayout(listShopPanel, BoxLayout.PAGE_AXIS));
		listShopPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		
		listShopBtnPanel = new JPanel();
		listShopBtnPanel.setLayout(new FlowLayout());
		
		lblShopList = new JLabel("Shopping List with ScrollPane =>", SwingConstants.CENTER);	
		
		//==========Color List===========
		colorNames = new String[]{"Default", "Red", "Green", "Blue", "White", "Orange", "Pink", "Magenta", "Yellow", "Cyan"};
		Color[] colors = {null, Color.RED, Color.GREEN, Color.BLUE, Color.WHITE, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.YELLOW, Color.CYAN};
		colorList = new JList<>(colorNames); //List with fixed number of items/immutable
		colorList.setPreferredSize(new Dimension(150, 190));
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		colorList.setLayoutOrientation(JList.VERTICAL);
		colorList.setVisibleRowCount(-1);
		colorList.setSelectedIndex(0);	
				
		lblColList = new JLabel("Select Color to change background=>", SwingConstants.CENTER);
		lblColList.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		
		listColPanel.add(lblColList);
		listColPanel.add(colorList); //List directly added to panel w/o ScrollPane(Fixed no. of items)
		
		//=========Shop List===========		
		listModel = new DefaultListModel<>(); //Required for mutable list
		shopList = new JList<>();
		shopList.setModel(listModel); //or shopList = new JList<>(listModel); => in one line
		shopList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		shopList.setLayoutOrientation(JList.VERTICAL);		
		shopList.setVisibleRowCount(10);
		
		listModel.addElement("Shopping List :==>");
		listModel.addElement("Milk");
		listModel.addElement("Books");
		listModel.addElement("Eggs");
		shopList.setSelectedIndex(0);
		
		jSPShopList = new JScrollPane(shopList); //List added to ScrollPane
		
		tfShopListItem = new JTextField("Enter Item");
		tfShopListItem.setPreferredSize(new Dimension(150, 25));
		
		btnShopListAdd = new JButton("Add Item");
		btnShopListRemove = new JButton("Remove Selected Item");
		btnShopListRemove.setEnabled(false);
		
		listShopBtnPanel.add(tfShopListItem);
		listShopBtnPanel.add(btnShopListAdd);
		listShopBtnPanel.add(btnShopListRemove);			
		
		listShopPanel.add(lblShopList);
		listShopPanel.add(jSPShopList);
		listShopPanel.add(listShopBtnPanel);
		
		//========Base List Panel========
		listPanel.add(listColPanel);
		listPanel.add(listShopPanel);
		
		//=========Base Panel==========
		panel.add(lblShopList, BorderLayout.PAGE_START);
		panel.add(listPanel, BorderLayout.CENTER);
		tfShopListItem.requestFocusInWindow();
		panel.add(btnPanel, BorderLayout.PAGE_END);
		updateUI();
		
		colorList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				listColPanel.setBackground(colors[colorList.getSelectedIndex()]);
				colorList.setBackground(colors[colorList.getSelectedIndex()]);
				updateUI();
			}
		});
		
		//Disable Remove Button if title is selected
		shopList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(shopList.getSelectedIndex() == 0) {
					btnShopListRemove.setEnabled(false);
				}
				else {
					btnShopListRemove.setEnabled(true);
				}
				
			}
		});
		
		btnShopListAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String shopListItem = tfShopListItem.getText();
				
				if(shopListItem.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter an item to add!", "Blank Item", JOptionPane.WARNING_MESSAGE);
				}
				else {
					listModel.addElement(shopListItem);
					tfShopListItem.requestFocusInWindow();
					tfShopListItem.setText("");
				}
				
				int size = listModel.getSize(); //store shop list size
				if(size > 1) {
					btnShopListRemove.setEnabled(true);
				}
				
				shopList.setSelectedIndex(size-1);
				updateUI();
			}
		});
		
		btnShopListRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = shopList.getSelectedIndex();
				listModel.remove(index);
				
				int size = listModel.getSize(); //store shop list size
				if(size == 0) {
					btnShopListRemove.setEnabled(false);
				}
				
				if(index == size) {
					shopList.setSelectedIndex(size-1);
				}
				else {
					shopList.setSelectedIndex(index);
				}
				updateUI();				
			}
		});
	}
	
 	private void fontCheck() {
		if(ckBoxBold.isSelected()&&ckBoxItalic.isSelected()) {
			lblCkBoxText.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 14));
		}
		else if(ckBoxBold.isSelected()) {
			lblCkBoxText.setFont(new Font("Serif", Font.BOLD, 14));
		}
		else if(ckBoxItalic.isSelected()) {
			lblCkBoxText.setFont(new Font("Serif", Font.ITALIC, 14));
		}
		else {
			lblCkBoxText.setFont(new Font("Serif", Font.PLAIN, 14));
		}
		updateUI();
	}
	
	private void radioCheck() {
		if(radioBtnNexus.isSelected()) {
			lblRadio.setText("My Man! ^_^");
		}
		else if (radioBtnIPhone.isSelected()) {
			lblRadio.setText("Sheeeeeeep!");
		}
		else if (radioBtnLumia.isSelected()) {
			lblRadio.setText("LOL! Good Luck!");
		}
		else {
			lblRadio.setText("");
		}
		updateUI();
	}
	
	private void backEventListener() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showMainMenu();
				
			}
		});
	}
		
	private void btnPanel() {
		btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnPanel.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		btnBack = new JButton("Back");
		btnPanel.add(btnBack);
		
		backEventListener();
	}
	
	private void panelClear() {
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
	}
	
	private void updateUI(){
		panel.revalidate();
		panel.repaint();
		frame.pack();
		frame.setLocationRelativeTo(null);
	}
		
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new SwingUI();
				
			}
		});
		

	}

}
