package com.kalyzee.framework.media.audio.data.api;

import java.io.InputStream;

public interface IAudioData {
	
	/**
	 * 
	 * @return Uncompressed audio data like WAV PCM
	 */
	InputStream getInputStream();
	int getSize();
}
