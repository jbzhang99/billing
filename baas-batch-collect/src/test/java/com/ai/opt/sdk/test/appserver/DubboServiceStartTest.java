package com.ai.opt.sdk.test.appserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.opt.sdk.appserver.DubboServiceStart;

public final class DubboServiceStartTest {

	private static final Logger LOG = LoggerFactory
			.getLogger(DubboServiceStartTest.class.getName());

	private DubboServiceStartTest() {
	}

	public static void main(String[] args) {
		DubboServiceStart.main(args);
	}
}
