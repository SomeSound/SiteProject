package com.example.SiteProject.SiteProject.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {

	DATA_NOT_FOUND("DATA_NOT_FOUND", "Data not found"),
	INVALID_MUSIC_ERROR("INVALID_MUSIC_ERROR", "Trying to save data that already exists"),
	INVALID_ARTIST_ERROR("INVALID_ARTIST_ERROR", "Trying to save data that already exists");

	private final String code;
	private final String message;

	ErrorCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
