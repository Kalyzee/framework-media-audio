package com.kalyzee.framework.media.audio.reader.impl;

import java.io.IOException;
import java.io.InputStream;

import com.kalyzee.framework.media.audio.api.IAudio;
import com.kalyzee.framework.media.audio.api.InvalidFormatException;
import com.kalyzee.framework.media.audio.data.api.IAudioData;
import com.kalyzee.framework.media.audio.data.impl.AudioData;
import com.kalyzee.framework.media.audio.header.api.IAudioHeader;
import com.kalyzee.framework.media.audio.header.impl.AudioHeader;
import com.kalyzee.framework.media.audio.impl.Audio;
import com.kalyzee.framework.media.audio.reader.api.AudioReaderException;
import com.kalyzee.framework.media.audio.reader.api.IReader;

public class WavReader extends AbstractReader implements IReader {

	public IAudio read(InputStream inputStream) throws AudioReaderException,
			InvalidFormatException {
		Audio audio = new Audio();
		audio.setAudioHeader(readHeader(inputStream));
		audio.setAudioData(readData(inputStream));
		return audio;
	}

	public boolean isCompatible(String extension) {
		return extension.trim().toLowerCase().equals("wav");
	}

	private IAudioData readData(InputStream is) throws AudioReaderException {
		try {
			AudioData ad = new AudioData();

			byte[] reader4bits = new byte[4];
			
			InputStream audioInputStream = null;;
			
			int dataLength = -1; 

			String dataType = "";

			while (!dataType.equals("data") && is.read(reader4bits) != -1) {
				dataType = new String(reader4bits);
				dataLength = read4ToInt(is);
				
				if (dataType.equals("data")){
					
					ad.setInputStream(audioInputStream);
					ad.setSize(dataLength);
					System.out.println(dataLength);
				}else{
					is.skip(dataLength);
				}
				
			}
			
			return ad;
		} catch (IOException e) {
			throw new AudioReaderException("Can't read stream" + e.getMessage());
		}

	}

	public IAudioHeader readHeader(InputStream inputStream)
			throws AudioReaderException, InvalidFormatException {
		try {
			if (readToString(inputStream, 4).equals("RIFF")) {
				inputStream.skip(4);
				if (!readToString(inputStream, 4).equals("WAVE")) {
					throw new InvalidFormatException("WAVE not found");
				}
				if (!readToString(inputStream, 4).equals("fmt ")) {
					throw new InvalidFormatException("fmt not found");
				}
				int subChunkSize = read4ToInt(inputStream);
				int audioFormat = read2ToInt(inputStream);
				if (audioFormat != 1) {
					throw new InvalidFormatException(
							"Only WAV PCM is supported");
				}
				int numChannels = read2ToInt(inputStream);

				int sampleRate = read4ToInt(inputStream);
				int byteRate = read4ToInt(inputStream);
				inputStream.skip(2);
				inputStream.skip(2);

				if (subChunkSize > 16) {
					inputStream.skip(subChunkSize - 16);
				}
				AudioHeader ah = new AudioHeader();
				ah.setByteRate(byteRate);
				ah.setNumChannels(numChannels);
				ah.setSampleRate(sampleRate);
				return ah;

			} else {
				throw new InvalidFormatException("RIFF not found");
			}
		} catch (IOException e) {
			throw new AudioReaderException(e.getMessage());
		}

	}

}
