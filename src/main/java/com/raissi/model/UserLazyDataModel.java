package com.raissi.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.raissi.domain.User;
import com.raissi.service.UserService;

public class UserLazyDataModel extends LazyDataModel<User>{
	private static final long serialVersionUID = 2205121735691907666L;
	
	private UserService userService;
	
	public UserLazyDataModel() {
		super();
	}

	public UserLazyDataModel(UserService userService) {
		super();
		this.userService = userService;
		this.setRowCount(this.userService.countCandidates());
	}

	@Override
	public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters){
		return userService.getCandidates(first, pageSize);
	}

}
