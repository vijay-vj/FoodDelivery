package com.ee.demo.iutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ioutils {
	private Logger logger = LoggerFactory.getLogger(Ioutils.class);
	
	 
	public boolean iStrNoCaseSenMatchT(String str1, String str2) {
		try {
			str1 = (str1 == null) ? "~" : str1;
			str2 = (str2 == null) ? "~" : str2;
			return (str1.toUpperCase()).trim().equals((str2.toUpperCase()).trim());
		} catch (Exception e) {
			logger.debug(e.getMessage().toString());
			return null != null;
		}
	};

	public int $intValNullIf(Integer val, int Nullif) {
		try {
			if (val != null)
				return val;
			else
				return Nullif;
		} catch (Exception e) {
			return Nullif;
		}
	};
	 
	public String $strValNullIf(String val, String Nullif) {
		try {
			if (!val.equals(null) && !$iStrBlank(val))
				return val;
			else
				return Nullif;
		} catch (Exception e) {
			return Nullif;
		}
	}; 
	

	public int $intValNullIf(String val, int Nullif) {
		try {
			if (val != null)
				return Integer.valueOf(val);
			else
				return Integer.valueOf(Nullif);
		} catch (Exception e) {
			return Integer.valueOf(Nullif);
		}
	};

	
	public boolean $iStrFuzzyMatch(String str1, String str2) {
		try {
			str1 = (str1 == null) ? "~" : str1;
			str2 = (str2 == null) ? "~" : str2;
			str1 = str1.replaceAll("(?i)UNAUTHORISED", "UNAUTHORIZED");
			str2 = str2.replaceAll("(?i)UNAUTHORISED", "UNAUTHORIZED");
			str1 = str1.replaceAll("(?i)COLOUR", "COLOR");
			str2 = str2.replaceAll("(?i)COLOUR", "COLOR");
			str1 = str1.replaceAll("(?i)CENTER", "CENTRE");
			str2 = str2.replaceAll("(?i)CENTER", "CENTRE");
			str1 = str1.replaceAll("^\"|\"$", "");
			str2 = str2.replaceAll("^\"|\"$", "");
			str1 = str1.toUpperCase().replaceAll("\\s+", "");
			str2 = str2.toUpperCase().replaceAll("\\s+", "");
			return str1.equals(str2);
		} catch (Exception e) {
			logger.debug(e.getMessage().toString());
			return null != null;
		}
	};
	
	public boolean $iStrBlank(String str1) {
		try {
			return (str1 == null || "".equals(str1));
		} catch (Exception e) {
			logger.debug(e.getMessage().toString());
			return null != null;
		}
	};
	
	public Ioutils() {
		// Constructor
	};
}
