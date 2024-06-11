package com.cust.model;

import java.util.List;

public class CustService {

	private CustDAO_interface dao;

	public CustService() {
		dao = new CustJDBCDAO();
	}

	public CustVO addCust(Integer custId, String custName) {

		CustVO custVO = new CustVO();

		custVO.setCustId(custId);
		custVO.setCustName(custName);
	
		dao.insert(custVO);

		return custVO;
	}

	public CustVO updateCust(Integer custId, String custName) {

		CustVO custVO = new CustVO();

		custVO.setCustId(custId);
		custVO.setCustName(custName);

		dao.update(custVO);

		return custVO;
	}

	public void deleteCust(Integer custId) {
		dao.delete(custId);
	}

	public CustVO getOneCust(Integer custId) {
		return dao.findByPrimaryKey(custId);
	}

	public List<CustVO> getAll() {
		return dao.getAll();
	}
}
