package com.ee.demo.iwebhandlers;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;

import static java.util.Arrays.asList;

@SuppressWarnings({ "serial", "hiding" })
@Data
@NoArgsConstructor
public class RestErrorList<ErrorMessage> extends ArrayList<ErrorMessage> {


    @SuppressWarnings("unused")
	private HttpStatus status;

    @SuppressWarnings("unchecked")
	public RestErrorList(HttpStatus status, ErrorMessage... errors) {
       this(status.value(), errors);
    }

    @SuppressWarnings("unchecked")
	public RestErrorList(int status, ErrorMessage... errors) {
        super();
        this.status = HttpStatus.valueOf(status);
        addAll(asList(errors));
    }

}  