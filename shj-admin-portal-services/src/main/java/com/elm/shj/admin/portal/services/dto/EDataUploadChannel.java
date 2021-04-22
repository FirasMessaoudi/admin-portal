/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.dto;

/**
 * Enum for channels used in the system for data upload
 * 
 * @author Aymen DHAOUI
 * @since 1.0.0
 */
public enum EDataUploadChannel {

	SYSTEM, WEB_SERVICE;

	public static EDataUploadChannel nullSafeValueOf(String channelStr) {
		for (EDataUploadChannel channel : EDataUploadChannel.values()) {
			if (channel.name().equalsIgnoreCase(channelStr)) { return channel; }
		}
		return SYSTEM;
	}

}
