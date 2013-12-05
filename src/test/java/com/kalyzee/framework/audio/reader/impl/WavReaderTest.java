package com.kalyzee.framework.audio.reader.impl;

import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.kalyzee.framework.media.audio.api.InvalidFormatException;
import com.kalyzee.framework.media.audio.header.api.IAudioHeader;
import com.kalyzee.framework.media.audio.reader.api.AudioReaderException;
import com.kalyzee.framework.media.audio.reader.impl.WavReader;

public class WavReaderTest {
	@Test
	public void testReadHeader() throws IOException, AudioReaderException, InvalidFormatException{
		WavReader wr = new WavReader();
		FileInputStream fis = new FileInputStream(getClass().getResource("/files/wav/europe1.wav").getFile());
		IAudioHeader ah = wr.readHeader(fis);
		Assert.assertEquals(1, ah.getNumChannels());
		Assert.assertEquals(16000, ah.getSampleRate());
		Assert.assertEquals(32000, ah.getByteRate());
		fis.close();
		
	}
	
	
}
