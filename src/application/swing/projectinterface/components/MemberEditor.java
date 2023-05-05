package application.swing.projectinterface.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import application.swing.main.Main;
import application.swing.projectinterface.actions.AddChild;
import application.swing.projectinterface.actions.AddParent;
import application.swing.projectinterface.actions.AddSpouse;
import application.swing.projectinterface.actions.RemoveThis;

public class MemberEditor extends JPanel {
	
	/**
	 * 
	 */
	Graphics2D g2;
	BufferedImage img;
	
	private static final long serialVersionUID = 1L;

	public Thread memberThread;
	
	public JTextField nameField;
	public JScrollPane bioFieldScroller;
	public JTextArea bioField;
	public JSpinner bDayField;
	
	JLabel nameLabel;
	JLabel nameLabelShadow;
	
	JLabel bioLabel;
	JLabel bioLabelShadow;
	
	JLabel bdayLabel;
	JLabel bdayLabelShadow;
	DateEditor dateEditor;
	
	public JButton addspouse;
	public JButton addparent;
	public JButton addchild;
	JButton removespouse;
	JButton removeparent;
	JButton removechild;
	public JButton removethis;
	
	List<JButton> buttons;
	
	public MemberEditor() {
		
		super();
		
		setupGraphics();
		setupFields();
		setupLabels();
		setupButtons();
		
		add(nameLabel);
		add(nameLabelShadow);
		add(nameField);
		add(bioFieldScroller);
		add(bioLabel);
		add(bioLabelShadow);
		add(bDayField);
								
	}
	
	
	private void setupButtons() {
		
		buttons = new ArrayList<JButton>();
		
		addspouse = new JButton("Add Spouse");
		addspouse.addActionListener(new AddSpouse());
		addparent = new JButton("Add Parent");
		addparent.addActionListener(new AddParent());
		addchild = new JButton("Add Child");
		addchild.addActionListener(new AddChild());
		removespouse = new JButton("Remove Spouse");
		removeparent = new JButton("Remove Parent");
		removechild = new JButton("Remove Child");
		removethis = new JButton("Remove Member");
		removethis.addActionListener(new RemoveThis());
		
		buttons.add(addspouse);
		buttons.add(addparent);
		buttons.add(addchild);
		buttons.add(removethis);
		
		Border line = BorderFactory.createLineBorder(new Color(255,150,150), 2);
		Border empty = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		
		for (JButton b : buttons) {
			b.setBorder(border);
			b.setFont(new Font("Lexend", Font.PLAIN, 16));
			add(b);
		}
		
	}


	private void setupGraphics() {
		
		Dimension newSize = new Dimension(Main.window.getWidth()/3, Main.window.getHeight());
		setPreferredSize(newSize);
		setSize(newSize);
		
		setBackground(new Color(255,175,175));
		setBorder(BorderFactory.createLineBorder(Color.white));
		setLayout(null);
		
		img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) img.getGraphics();
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
	}


	private void setupFields() {
		bioFieldScroller = getBioField();
		nameField = getNameField();
		bDayField = getNumberField();
	}
	
	
	private JSpinner getNumberField() {

		JSpinner spinner = new JSpinner(new SpinnerDateModel());
		
		spinner.setBounds(getWidth()-230,58, 200, 30);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		
		dateEditor = new JSpinner.DateEditor(spinner, dateFormat.toPattern());
		dateEditor.getTextField().setHorizontalAlignment(JTextField.RIGHT);
		
		spinner.setEditor(dateEditor);
		
		Border line = BorderFactory.createLineBorder(new Color(255,150,150), 2);
		Border empty = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		spinner.setFont(new Font("Lexend", Font.PLAIN, 16));
		spinner.setBorder(border);
				
		return spinner;
	}


	private void setupLabels() {
		
		nameLabel = getLabel("Name:", 10, false);
		nameLabelShadow = getLabelShadow("Name:", 10, false);
		
		bioLabel = getLabel("Bio:", 100, true);
		bioLabelShadow = getLabelShadow("Bio:", 100, true);
		
		bdayLabel = getLabel("Birth Date:", 58, false);
		bdayLabelShadow = getLabelShadow("Birth Date:", 58, false);
		
	}
	
	
	private JScrollPane getBioField() {
		
		
		bioField = new JTextArea();
		JScrollPane sp = new JScrollPane(bioField);

		sp.setBounds(10,140, getWidth()-40, getHeight()/3);
		
		Border line = BorderFactory.createLineBorder(new Color(255,150,150), 2);
		bioField.setLineWrap(true);
		
		bioField.setFont(new Font("Lexend", Font.PLAIN, 16));
				
		sp.setBorder(line);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		return sp;
	}
	
	public JLabel getLabel(String s, int y, boolean centered) {
		
		int x;
		
		if (centered) {
			x = getXforCenteredText(s);
		} else {
			x = 10;
		}
		
		JLabel l2 = new JLabel(s);
		l2.setFont(new Font("Lexend", Font.BOLD, 20));
		l2.setForeground(Color.white);
		l2.setBounds(x,y, 200, 30);
		add(l2);
		
		return l2;

	}
	
	private JLabel getLabelShadow(String s, int y, boolean centered) {
		
		int x;
		
		if (centered) {
			x = getXforCenteredText(s)+2;
		} else {
			x = 12;
		}
		
		JLabel l2 = new JLabel(s);
		l2.setFont(new Font("Lexend", Font.BOLD, 20));
		l2.setForeground(new Color(255,120,120));
		l2.setBounds(x,y+2, 200, 30);
		add(l2);

		return l2;
		
	}

	private JTextField getNameField() {

		JTextField f = new JTextField();
		
		f.setBounds(getWidth()-230,10, 200, 30);
		
		Border line = BorderFactory.createLineBorder(new Color(255,150,150), 2);
		Border empty = new EmptyBorder(0, 5, 0, 0);
		CompoundBorder border = new CompoundBorder(line, empty);
		f.setFont(new Font("Lexend", Font.PLAIN, 16));
		f.setBorder(border);
				
		return f;
	}
	
	public void resize() {
		
		Dimension newSize = new Dimension(Main.window.getWidth()/3, Main.window.getHeight());
		setPreferredSize(newSize);
		setSize(newSize);
		
		int x = (Main.window.getWidth()/3)*2;
		int y = Main.globNav.getHeight();
		int width = Main.window.getWidth()/3;
		int height = Main.window.getHeight() - Main.globNav.getHeight();
		
		setBounds(x,y,width,height);
		
		nameField.setBounds((getWidth()/2)-25,10, getWidth()/2, 30);
		bDayField.setBounds((getWidth()/2)-25,58, getWidth()/2, 30);
		dateEditor.setMinimumSize(bDayField.getSize());
		bioFieldScroller.setBounds(10,140, getWidth()-35, getHeight()/3);
		bioField.setMinimumSize(bioFieldScroller.getSize());
		
		bioFieldScroller.repaint();
		bioField.repaint();
		bDayField.repaint();
		dateEditor.repaint();
		
		nameLabel.setBounds(10, 10, nameLabel.getWidth(), nameLabel.getHeight());
		nameLabelShadow.setBounds(12, 12, nameLabel.getWidth(), nameLabel.getHeight());
		
		bioLabel.setBounds(getXforCenteredText(bioLabel.getText())-15,bioLabel.getY(),bioLabel.getWidth(),bioLabel.getHeight());
		bioLabelShadow.setBounds(bioLabel.getX()+2,bioLabel.getY()+2,bioLabel.getWidth(),bioLabel.getHeight());
		
		resizeButtons();
				
	}

	private void resizeButtons() {
		
		int height = (int) (getSize().getHeight()/10);
		int width = (int) (getSize().getWidth()/3)-10;
		int x1 = 5;
		int x2 = 8 + width;
		int x3 = 11 + (width*2);
		int y1 = (int) (getSize().getHeight() - (height*4));
		int y2 = y1+height+3;
		
		addspouse.setBounds(x1, y1, width, height);
		addparent.setBounds(x2, y1, width, height);
		addchild.setBounds(x3, y1, width, height);
		removethis.setBounds(x2-width/2, y2, width*2, height);
		
	}
	
	public int getXforCenteredText(String text) {
		
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = getWidth()/2 - length/2;
		return x;
	}

}
