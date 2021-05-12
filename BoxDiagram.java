package week5;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GPoint;
import acm.program.GraphicsProgram;
import acmx.export.javax.swing.JLabel;
import acmx.export.javax.swing.JTextField;

public class BoxDiagram extends GraphicsProgram {
	
	public void init() {
		contents = new HashMap<String, GLabel>();
		createController();
		addActionListeners();
		addMouseListeners();
	}
	
private void createController() {
	name = new JTextField(10);
	name.addActionListener(this);
	addButton = new JButton("Add");
	removeButton = new JButton("Remove");
	clearButton = new JButton("Clear");
	add(new JLabel ("Name"), SOUTH);
	add(name, SOUTH);
	add(addButton, SOUTH);
	add(removeButton, SOUTH);
	add(clearButton, SOUTH);
}

private void addLabel (String name) {
	GLabel label = new GLabel (name);
	double labelX = getWidth() / 2.0 - label.getWidth() / 2.0;
	double labelY = getHeight() / 2 + label.getAscent() / 2.0;
	add(label, labelX, labelY);
	contents.put(name,  label);
}

private void removeLabel (String name) {
	GLabel obj = contents.get(name);
	if (obj != null) {
		remove(obj);
	}
}

private void removeContents() {
	for (String labelName : contents.keySet()) {
		removeLabel(labelName);
	}
	contents.clear();
}

public void actionPerformed(ActionEvent e) {
	Object source = e.getSource();
	if (source == addButton || source == name ) {
		addLabel (name.getText());
	} else if (source == removeButton ) {
		removeLabel(name.getText());
	}else if (source == clearButton) {
		removeContents();
	}
}

public void mousePressed(MouseEvent e) {
	last = new GPoint(e.getPoint());
	currentLabel = getElementAt(last);
}

public void mouseDragged(MouseEvent e) {
	if (currentLabel !=  null) {
		currentLabel.move(e.getX() - last.getX(), e.getY() - last.getY());
		last =  new GPoint(e.getPoint());		
	}
}

public void mouseClicked(MouseEvent e) {
	if (currentLabel != null) currentLabel.sendToFront();
}

private HashMap<String, GLabel> contents;
private JLabel label;
private JTextField name;
private JButton addButton;
private JButton removeButton;
private JButton clearButton;
private GObject currentLabel;
private GPoint last;
}
