package com.revature.trms.dao;

import java.util.List;

import com.revature.trms.GradeFormat;

public interface GradeFormatDAO {

	public GradeFormat getGradeFormat(int g_ID);
	
	public List<GradeFormat> getAllGradeFormats();
	
}
