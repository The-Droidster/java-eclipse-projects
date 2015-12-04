package com.swingcomponents;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SwingUI {

	private int selectedIndex;
	
	private JFrame frame;
	private JPanel panel,
	btnPanel,
	ckBoxItemPanel, ckBoxSelItemPanel, ckBoxFruitPanel, ckBoxFontPanel,
	radioPanel, 
	colChBannerPanel, colChBannerBgColPanel, colChBannerTxtColPanel, colChBottomPanel;
	private JLabel label, lbl1, lbl2, lbl3, lbl4, //Label
	lblCounter, //used with Button
	lblCkBoxItems, lblCkBoxSelItems, lblCkBoxApple, lblCkBoxOrange, lblCkBoxGrapes, lblCkBoxBanana, lblCkBoxMango, lblCkBoxText, //CheckBoxLabels
	lblRadio, 
	lblColChBanner, 
	lblComboBoxText; 
	private JButton button, btnBack, 
	btnCount, btnReset, 
	btnColChBannerBgColor;
	private JCheckBox ckBoxApple, ckBoxOrange, ckBoxGrapes, ckBoxBanana, ckBoxMango, 
	ckBoxBold, ckBoxItalic;
	private JRadioButton radioBtnNexus, radioBtnIPhone, radioBtnLumia;
	ButtonGroup radioGroup;
	private Color bannerBgColor, bannerTextColor; //To be used with JColorChooser
	private JColorChooser bannerTextColorChooser;
	private JComboBox<String> comboBox;
	private int count;
	
	
	
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
		
		lblComboBoxText = new JLabel("", SwingConstants.CENTER);
		
		String[] list = {"Buttons", "CheckBoxes", "RadioButtons", "Labels", "ColorChooser", "ComboBoxes"};		
		comboBox = new JComboBox<>(list);
		
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
				case 0: showButton();					
					break;
				
				case 1:	showCheckBox();			
					break;	
					
				case 2:	showRadioButton();			
					break;	
					
				case 3:	showLabel();				
					break;
					
				case 4:	showColorChooser();
					break;
					
				case 5:	showComboBox();
						break;
					
				default:
					break;
				}
				
			}
		});
		
		backEventListener();
		
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
		
		backEventListener();
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
		
		backEventListener();		
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
		
		
		backEventListener();
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
		
		backEventListener();
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
		
		backEventListener();
	}
	
	private void showComboBox() {
		panel.setLayout(new GridLayout(0, 1, 5, 5));
		lblComboBoxText.setText("Oh! You are using one! ;-)");
		lblComboBoxText.setPreferredSize(new Dimension(300, 20));
		btnPanel.add(button);
		panel.add(btnPanel);
		updateUI();
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
