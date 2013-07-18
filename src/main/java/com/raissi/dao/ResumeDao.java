package com.raissi.dao;

import com.raissi.domain.Resume;

public interface ResumeDao extends BaseDao{
	public Resume getResumeByUser(Long userId);
}
