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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * The <code>GameFrame</code> class represents the <b>local</b> container in
 * witch to display the game.
 * 
 * @author Lyes KHERBICHE {@literal <kerbiche@gmail.com>}
 * @since 0.0.1-RELEASE
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame implements Constants {

	private GameModel gameModel;

	public GameFrame() {
		super("Snake");

		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- GameFrame()1 In EDT");
		}	else {
				System.out.println("-- GameFrame()1 ! EDT");
			}

		gameModel = new GameModel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		final JPanel content = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				if (SwingUtilities.isEventDispatchThread()) {
					System.out.println("-- GameFrame() -> paintComponent(Graphics g) In EDT");
				}	else {
						System.out.println("-- GameFrame() -> paintComponent(Graphics g) ! EDT");
					}
				super.paintComponent(g);
				gameModel.display(g);
			}
		};
		content.setPreferredSize(new Dimension(NBR_OF_COL * PIX_SQUARE, NBR_OF_LIGN * PIX_SQUARE));
		content.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (SwingUtilities.isEventDispatchThread()) {
					System.out.println("-- GameFrame() -> keyPressed(KeyEvent e) In EDT");
				}	else {
						System.out.println("-- GameFrame() -> pkeyPressed(KeyEvent e ! EDT");
					}
				gameModel.manageKeyBoard(e);
			}
		});

		setContentPane(content);
		setFocusable(false);
		content.setFocusable(true);

		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- GameFrame()2 In EDT");
		}	else {
				System.out.println("-- GameFrame()2 ! EDT");
			}
		
		Thread thread = new Thread(new Runnable() { // This Thread will exit exec from EDT
			@Override
			public void run() {
				while (true) {

					if (SwingUtilities.isEventDispatchThread()) {
						System.out.println("-- GameFrame() -> new Thread().run() In EDT");
					}	else {
							System.out.println("-- GameFrame() -> new Thread().run() ! EDT");
						}

					GameFrame.this.gameModel.calculate();
					content.repaint();
					try {
						Thread.sleep(FPS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		});
		thread.start();
	}

	public static void main(String[] args) {
        
		Runnable code = new Runnable() {
			@Override
			public void run() {
				GameFrame gf = new GameFrame();
				gf.pack();
				gf.setLocationRelativeTo(null);
				gf.setVisible(true);
			}
		};

		
		if (SwingUtilities.isEventDispatchThread()) {
			System.out.println("-- main() In EDT");
			code.run();
		}	else {
				System.out.println("-- main() ! EDT");
				SwingUtilities.invokeLater(code);
			}
		/*
		GameFrame gf = new GameFrame();
		gf.pack();
		gf.setLocationRelativeTo(null);
		gf.setVisible(true);
		*/
	}
}
