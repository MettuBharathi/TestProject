package com.insight.support;

import com.insight.report.ConfigFileReadWrite;

public interface IFrameworkConstant {

	String FILE_FRAMEWORK = "resources/framework.properties";
	String LOCATION_DATATABLE_EXCEL = ConfigFileReadWrite.read(FILE_FRAMEWORK, "LOCATION_DATATABLE_EXCEL");
}
