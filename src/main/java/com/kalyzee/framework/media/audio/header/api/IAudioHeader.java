package com.kalyzee.framework.media.audio.header.api;

public interface IAudioHeader {
	String getMetadata(String key);

	int getByteRate();
	int getSampleRate();
	int getNumChannels();
	void setByteRate(int byteRate);
	void setSampleRate(int sampleRate);
	void setNumChannels(int channels);
}
