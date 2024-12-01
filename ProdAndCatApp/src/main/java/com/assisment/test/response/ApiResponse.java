package com.assisment.test.response;

import lombok.Data;

@Data
public class ApiResponse {

	private String message;
	private Object data;
	private boolean status;
	private boolean error;
}
