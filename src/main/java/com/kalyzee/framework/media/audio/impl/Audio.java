package com.kalyzee.framework.media.audio.impl;

import com.kalyzee.framework.media.audio.api.IAudio;
import com.kalyzee.framework.media.audio.data.api.IAudioData;
import com.kalyzee.framework.media.audio.header.api.IAudioHeader;

public class Audio implements IAudio{
	
	private IAudioHeader audioHeader;
	private IAudioData audioData;
	
	
	public void setAudioHeader(IAudioHeader audioHeader){
		this.audioHeader = audioHeader;
	}
	public void setAudioData(IAudioData audioData){
		this.audioData = audioData;
	}
	
	public IAudioHeader getHeader() {
		return audioHeader;
	}

	public IAudioData getAudioData() {
		return audioData;
	}

}
