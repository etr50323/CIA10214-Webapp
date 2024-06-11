package com.cust.model;

import java.util.List;

public interface CustDAO_interface {
          public void insert(CustVO custVO);
          public void update(CustVO custVO);
          public void delete(Integer custId);
          public CustVO findByPrimaryKey(Integer custId);
          public List<CustVO> getAll();
	
}