package com.kalyzee.framework.media.audio.reader.api;

import java.io.InputStream;

import com.kalyzee.framework.media.audio.api.IAudio;
import com.kalyzee.framework.media.audio.api.InvalidFormatException;
import com.kalyzee.framework.media.audio.header.api.IAudioHeader;

public interface IReader {
	
	
	/**
	 * Returns All Audio Header data
	 * @param inputStream
	 * @return
	 * @throws InvalidFormatException 
	 * @throws AudioReaderException 
	 */
	IAudio read(InputStream inputStream) throws AudioReaderException, InvalidFormatException;
	
	/**
	 * Returns Audio Header data
	 * @param inputStream
	 * @return
	 * @throws AudioReaderException 
	 * @throws InvalidFormatException 
	 */
	IAudioHeader readHeader(InputStream inputStream) throws AudioReaderException, InvalidFormatException;
	
	boolean isCompatible(String extension);
}
