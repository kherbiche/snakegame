/*
 * Copyright 2018-2019 snakegame_Lyes_Kherbiche. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * <kerbiche@gmail.com>
 */
package am.zgh.snakegame.server;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.SwingUtilities;

/**
 * The <code>Frog</code> class represents
 * 
 * @author Lyes KHERBICHE {@literal <kerbiche@gmail.com>}
 * @since 0.0.1-RELEASE
 */
public class Frog extends Square {

	private final static Random RND = new Random();
	private int angle;


	public Frog() {
		super(getRandomX(), getRandomY());
		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- Frog() In EDT");
		}	else {
				System.out.println("-- Frog() ! EDT");
			}
	}

	private static int getRandomX() {
		return RND.nextInt(NBR_OF_COL);
	}
	private static int getRandomY() {
		return RND.nextInt(NBR_OF_LIGN);
	}

	public void newFrog() {
		setxIndex(getRandomX());
		setyIndex(getRandomY());
		angle = 0;
	}

	public void calculate() {
		angle += 4;
		if (angle > 360) {
			angle = 360;
		}
	}

	public void display(Graphics g) {

		Graphics2D g2D = (Graphics2D)g;
		AffineTransform afTran = g2D.getTransform();
		g2D.setTransform(AffineTransform.getRotateInstance(Math.toRadians(angle), getX() + getWidth()/2, getY() + getHight()/2));
		
		g.setColor(Color.red);
		g.fillRect(getX() + 2, getY() + 2, getWidth() - 4, getHight() - 4);

		g2D.setTransform(afTran);
	}
}
