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

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The <code>GameFrame</code> class represents the <b>local</b> container in
 * witch to display the game.
 * 
 * @author L KHERBICHE
 * @since 0.0.1-RELEASE
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	private GameModel gm;

	public GameFrame() {

		super("Snake");
		gm = new GameModel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		final JPanel content = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				GameFrame.this.gm.display(g);
			}
		};
		content.setPreferredSize(new Dimension(300, 300));
		setContentPane(content);
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true) {
					GameFrame.this.gm.calculate();
					content.repaint();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		thread.start();
	}

	public static void main(String[] args) {
		GameFrame gf = new GameFrame();
		gf.pack();
		gf.setLocationRelativeTo(null);
		gf.setVisible(true);
	}
}
