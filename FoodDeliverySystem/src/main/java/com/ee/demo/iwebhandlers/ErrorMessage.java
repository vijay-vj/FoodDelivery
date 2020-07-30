package com.ee.demo.iwebhandlers;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorMessage {
    @SuppressWarnings("unused")
	private static String message;

    @SuppressWarnings("unused")
	private static String code;

    @SuppressWarnings("unused")
	private String detail;
}