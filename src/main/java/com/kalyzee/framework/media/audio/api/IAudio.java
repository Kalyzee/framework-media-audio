package com.kalyzee.framework.media.audio.api;

import com.kalyzee.framework.media.audio.data.api.IAudioData;
import com.kalyzee.framework.media.audio.header.api.IAudioHeader;

public interface IAudio {
	IAudioHeader getHeader();
	IAudioData getAudioData();
}
