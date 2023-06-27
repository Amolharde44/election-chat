package com.ElectionChatApp.Service;

public class ECQueryConstant {
	
	public static final String DATA_BASE_PLACE_HOLDER = "#$DataBaseName#$";

	public static final String INSERT_INTO_SIGNUP_TABLE = "INSERT INTO #$DataBaseName#$.dbo.Signup values(?, ?, ?)";
	
	
	public static final String SELECT_USER_FOR_LOGIN = "SELECT * FROM #$DataBaseName#$.dbo.Signup WHERE user_name= ?";
	
	
	public static final String SELECT_REGISTRATION_DETAILS="SELECT * FROM #$DataBaseName#$.dbo.Signup";


}
