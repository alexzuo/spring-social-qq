package com.georges.social.qq.api.impl.json;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class QQProfileMixin {

	@JsonCreator
	QQProfileMixin(
			@JsonProperty("nickname") String nickName,
			@JsonProperty("figureurl") String figureUrl,
			@JsonProperty("gender") String gender,
			@JsonProperty("vip") int vip,
			@JsonProperty("level") int level) {
	}

}
