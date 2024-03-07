package br.com.hyper.constants;

import lombok.Getter;

@Getter
public enum ErrorCodes {

	DATA_NOT_FOUND("DATA_NOT_FOUND", "Data not found"),
	DUPLICATED_DATA("DUPLICATED_DATA", "Trying to save data that already exists"),
	AWS_SECRETS_ERROR("AWS_SECRETS_ERROR", "Something went wrong"),
	AWS_CONNECTION_ERROR("AWS_CONNECTION_ERROR", "Something went wrong");

	private final String code;
	private final String message;

	ErrorCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
