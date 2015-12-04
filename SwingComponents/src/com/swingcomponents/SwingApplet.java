package com.swingcomponents;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JApplet;

/*
<applet code="SwingApplet.class" CodeBase="" width=300 height=300></applet>
*/

public class SwingApplet extends JApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() {
		super.init();
		System.out.println("Initiating Applet");
	}
	
	@Override
	public void start() {
		super.start();
		System.out.println("Starting Applet");
	}
	
	@Override
	public void paint(Graphics g) {
		g.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		g.setColor(Color.red);
		g.drawRect(this.getWidth()/2-50, this.getHeight()/2-50, 100, 100);
		g.drawString("Hello, JApplet!", this.getWidth()/4, this.getHeight() - 5);
		System.out.println("Applet Started");
	}
	
	@Override
	public void stop() {
		super.stop();
		System.out.println("Stopping Applet");
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("Unloading Applet");
	}
	
}
