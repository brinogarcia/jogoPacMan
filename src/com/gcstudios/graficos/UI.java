package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.CENTER_BASELINE, 18));
		g.drawString("Frutas: " + Game.fruit_atual, 20, 20);
		g.drawString("Total de Frutas: " + Game.fruit_cont, 200, 20);
	}
	
}
