package com.ee.demo.iwebhandlers;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(NON_NULL)
///@AllArgsConstructor
//@NoArgsConstructor
public class ResponseWrapper {
	private Object data;

    private Object metadata;
 

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Object getMetadata() {
		return metadata;
	}

	public void setMetadata(Object metadata) {
		this.metadata = metadata;
	}
 
	public ResponseWrapper(Object data, Object metadata) {
		super();
		this.data = data;
		this.metadata = metadata;
 	}

}

//#00000001 Ends