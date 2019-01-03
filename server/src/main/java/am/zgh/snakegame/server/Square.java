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

/**
 * The <code>Square</code> class represents
 * 
 * @author Lyes KHERBICHE {@literal <kerbiche@gmail.com>}
 * @since 0.0.1-RELEASE
 */
public class Square implements Constants {

	private int xIndex;
	private int yIndex;

	public Square(int xIndex, int yIndex) {
		super();
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

	public int getxIndex() {
		return xIndex;
	}

	public void setxIndex(int xIndex) {
		this.xIndex = xIndex;
	}

	public int getyIndex() {
		return yIndex;
	}

	public void setyIndex(int yIndex) {
		this.yIndex = yIndex;
	}

	public int getX() {
		return xIndex * PIX_SQUARE;
	}

	public int getY() {
		return yIndex * PIX_SQUARE;
	}

	public int getWidth() {
		return PIX_SQUARE;
	}

	public int getHight() {
		return PIX_SQUARE;
	}

	public boolean isValide() {
		return xIndex >= 0 && xIndex < NBR_OF_COL && yIndex >= 0 && yIndex < NBR_OF_LIGN;
	}
}
