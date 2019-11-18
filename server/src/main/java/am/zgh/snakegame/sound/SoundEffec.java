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

package am.zgh.snakegame.sound;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

/**
 * The <code>SoundEffec</code> enum encapsulates all the sound effects of a game, so as to separate the sound playing
 * codes from the game codes.
 * 1. Define all your sound effect names and the associated wave file.
 * 2. To play a specific sound, simply invoke SoundEffect.SOUND_NAME.play().
 * 3. You might optionally invoke the static method SoundEffect.init() to pre-load all the
 *    sound files, so that the play is not paused while loading the file for the first time.
 * 4. You can use the static variable SoundEffect.volume to mute the sound
 * 
 * @author Lyes KHERBICHE {@literal <kerbiche@gmail.com>}
 * @since 0.0.1-RELEASE
 */
public enum SoundEffec {
	EAT("eat.wav"), 					// eating frog
	GAMEOVER("gameover.wav"),			// gong
	TOUCH("touch.wav");					//

	// Nested Enum for volume
	public static enum Volume {
		MUTE, LOW, MEDIUM, HIGH
	}

	public static Volume volume = Volume.LOW;
	private Clip clip;

	// Constructor
	SoundEffec(String soundFileName) {
		try{
			URL url = this.getClass().getClassLoader().getResource(soundFileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);

		} catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	// Play or Re-play the sound effect from the beginning, by rewinding.
	public void play() {
		if(volume != Volume.MUTE) {
			if(clip.isRunning()) {
				clip.stop(); // Stop the player if it is still running
			}
			clip.setFramePosition(0); // rewind to the beginning
			clip.start(); // Start playing
		}
	}

	// Optional static method to pre-load all the sound files.
	static void init() {
		values(); // calls the constructor for all the elements
	}
}