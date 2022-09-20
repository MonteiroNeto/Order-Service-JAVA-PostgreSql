package com.mtech.services.util;

import java.text.DateFormat;
import java.util.Date;

public class GetDate {

	public String getFormatDate() {
		String dateFomated = "";

		Date date = new Date();
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);

		dateFomated = format.format(date);

		return dateFomated;
	}

}
