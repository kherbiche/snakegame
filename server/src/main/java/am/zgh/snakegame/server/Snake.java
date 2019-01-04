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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;

/**
 * The <code>Snake</code> class represents
 * 
 * @author Lyes KHERBICHE {@literal <kerbiche@gmail.com>}
 * @since 0.0.1-RELEASE
 */
public class Snake {

	private LinkedList<Square> list;
	private Direction direction;
	private Direction userPressedKey;
	private boolean isDead;

	public Snake() {
		list = new LinkedList<Square>();
		list.add(new Square(14, 15));
		list.add(new Square(15, 15));
		list.add(new Square(16, 15));
		direction = Direction.LEFT;
	}

	public void calculate(Frog frog) {

		turn();
		if (canEat(frog)) {
			eat();
			frog.newFrog();
		} else if (canMove()) {
			move();
		} else {
			isDead = true;
		}
	}

	public void display(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Square box : list) {
			g.fillOval(box.getX(), box.getY(), box.getWidth(), box.getHight());
		}
	}

	public boolean isDead() {
		return isDead;
	}

	public void setUserPressedKey(Direction key) {
		userPressedKey = key;
	}

	private Square getNextSquare() {

		Square head = list.getFirst();
		switch (direction) {
		case UP:
			return new Square(head.getxIndex(), head.getyIndex() - 1);
		case DOWN:
			return new Square(head.getxIndex(), head.getyIndex() + 1);
		case RIGHT:
			return new Square(head.getxIndex() + 1, head.getyIndex());
		case LEFT:
			return new Square(head.getxIndex() - 1, head.getyIndex());
		}
		return null;
	}

	private void move() {
		list.addFirst(getNextSquare());
		list.removeLast();
	}

	private boolean canMove() {
		return getNextSquare().isValide();
	}

	private void turn() {

		if (userPressedKey != null) {
			if (direction == Direction.UP || direction == Direction.DOWN) {
				if (userPressedKey == Direction.LEFT) {
					direction = Direction.LEFT;
				} else if (userPressedKey == Direction.RIGHT) {
					direction = Direction.RIGHT;
				}
			} else {
				if (userPressedKey == Direction.UP) {
					direction = Direction.UP;
				} else if (userPressedKey == Direction.DOWN) {
					direction = Direction.DOWN;
				}
			}
			userPressedKey = null;
		}
	}

	private void eat() {
		list.addFirst(getNextSquare());
	}

	private boolean canEat(Frog frog) {
		Square square = getNextSquare();
		return square.getxIndex() == frog.getxIndex() && square.getyIndex() == frog.getyIndex();
	}
}
