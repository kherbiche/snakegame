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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.SwingUtilities;

/**
 * The <code>GameModel</code> class represents
 * 
 * @author Lyes KHERBICHE {@literal <kerbiche@gmail.com>}
 * @since 0.0.1-RELEASE
 */
public class GameModel {

	private Snake snake;
	private Frog frog;
	private boolean gameOver;

	public GameModel() {
		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- GameModel() In EDT");
		}	else {
				System.out.println("-- GameModel() ! EDT");
			}
		snake = new Snake();
		frog = new Frog();
		gameOver = false;
	}

	public void calculate() {
		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- GameModel -> calculate() In EDT");
		}	else {
				System.out.println("-- GameModel -> calculate() ! EDT");
			}
		if (!gameOver) {
			frog.calculate();
			snake.calculate(frog, getLevel());
			if (snake.isDead()) {
				gameOver = true;
			}
		}
	}

	public void display(Graphics g) {
		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- GameModel -> display(Graphics g) In EDT");
		}	else {
				System.out.println("-- GameModel -> display(Graphics g) ! EDT");
			}
		snake.display(g);
		frog.display(g);
		if (gameOver) {
			String str = "Game Over";
			g.setColor(Color.RED);
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
			FontMetrics fm = g.getFontMetrics();
			int x = (g.getClipBounds().width - fm.stringWidth(str)) / 2;
			int y = (g.getClipBounds().height / 2) + fm.getMaxDescent();
			g.drawString(str, x, y);
		}
		g.setColor(Color.GREEN);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		g.drawString(String.valueOf(getLevel()), 5, 25);
	}

	public void manageKeyBoard(KeyEvent key) {

		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- GameModel -> manageKeyBoard(KeyEvent key) In EDT");
		}	else {
				System.out.println("-- GameModel -> manageKeyBoard(KeyEvent key) ! EDT");
			}
		switch (key.getKeyCode()) {
		case KeyEvent.VK_RIGHT:
			snake.setUserPressedKey(Direction.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			snake.setUserPressedKey(Direction.LEFT);
			break;
		case KeyEvent.VK_UP:
			snake.setUserPressedKey(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.setUserPressedKey(Direction.DOWN);
			break;
		}
	}

	@Deprecated
	public int getTempo() {

		switch (getLevel()) {
		case 1:
			return 500;
		case 2:
			return 400;
		case 3:
			return 350;
		case 4:
			return 300;
		case 5:
			return 250;
		case 6:
			return 200;
		case 7:
			return 160;
		case 8:
			return 120;
		case 9:
			return 80;
		default:
			return 50;
		}
	}

	private int getLevel() {
		return (snake.getEatCount() / 5) + 1;
	}
}
