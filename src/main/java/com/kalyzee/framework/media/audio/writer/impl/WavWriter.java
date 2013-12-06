package com.kalyzee.framework.media.audio.writer.impl;

import java.io.IOException;
import java.io.OutputStream;

import com.kalyzee.framework.media.audio.api.IAudio;
import com.kalyzee.framework.media.audio.writer.api.AudioWriterException;
import com.kalyzee.framework.media.audio.writer.api.IWriter;
/**
 * Specifications : https://ccrma.stanford.edu/courses/422/projects/WaveFormat/
 * 
 * @author Ludovic Bouguerra <ludovic.bouguerra@kalyzee.com>
 *
 */
public class WavWriter extends AbstractWriter implements IWriter{

	public void write(OutputStream outputStream, IAudio audio) throws AudioWriterException {
		try {
			writeString(outputStream, "RIFF");		
			writeInt4(outputStream, 4 + 8 + 16 + 8 + audio.getAudioData().getSize());
			writeString(outputStream, "WAVE");
			writeString(outputStream, "fmt ");
			writeInt4(outputStream, 16);
			writeInt2(outputStream, 1);
			writeInt2(outputStream, audio.getHeader().getNumChannels());
			writeInt4(outputStream, audio.getHeader().getSampleRate());
			writeInt4(outputStream, audio.getHeader().getByteRate());
			writeInt2(outputStream, audio.getHeader().getByteRate()/audio.getHeader().getSampleRate());
			writeInt2(outputStream, audio.getHeader().getByteRate()/audio.getHeader().getSampleRate());
			writeInt2(outputStream, audio.getHeader().getByteRate()/audio.getHeader().getSampleRate()/audio.getHeader().getNumChannels()*8);
			writeString(outputStream, "data");
			writeInt4(outputStream, audio.getAudioData().getSize());
			byte[] b = new byte[1024];
			while (audio.getAudioData().getInputStream().read(b) != -1){
				outputStream.write(b);
			}
		} catch (IOException e) {
			throw new AudioWriterException(e.getMessage()); 
		}
	}

	public boolean isCompatible(String extension) {
		return extension.trim().toLowerCase().equals("wav");
	}
}
