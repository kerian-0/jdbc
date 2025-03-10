package com.jdc.ps.test;

import org.junit.jupiter.api.BeforeAll;
import static com.jdc.ps.util.DbUtil.*;

public sealed class  BaseTest permits StateServiceTest {

	 @BeforeAll 
	 static void setUpBeforClass() throws Exception {
		 prepareSchema();
		 createTables();
	 }
	 
}
