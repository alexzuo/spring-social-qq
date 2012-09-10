package com.georges.social.qq.api.impl.json;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;

import com.georges.social.qq.api.QQProfile;

public class QQModule extends SimpleModule {

	public QQModule() {
		super("WeiboModule", new Version(1, 0, 0, null));
	}

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.module.SimpleModule#setupModule(org.codehaus.jackson.map.Module.SetupContext)
	 */
	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(QQProfile.class, QQProfileMixin.class);
	}

}
