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
import java.util.Random;

/**
 * The <code>Frog</code> class represents
 * 
 * @author Lyes KHERBICHE {@literal <kerbiche@gmail.com>}
 * @since 0.0.1-RELEASE
 */
public class Frog extends Square {

	private final static Random RND = new Random();


	public Frog() {
		super(getRandomX(), getRandomY());
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
	}

	public void display(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(getX() + 2, getY() + 2, getWidth() - 4, getHight() - 4);
	}
}