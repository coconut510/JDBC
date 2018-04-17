package kh.jdbc.pm.controller;

import java.util.ArrayList;

import kh.jdbc.pm.model.service.PMService;
import kh.jdbc.pm.model.vo.Product;
import kh.jdbc.pm.view.PMView;

public class PMController {
	private PMView pmView ;
	private PMService pmService = new PMService();
	public PMController() {}
	public PMController(PMView pmView)
	{
		this.pmView = pmView;
	}
	public ArrayList<Product> showList()
	{
		return pmService.showListBtn();
	}

	public Product serachBtn(boolean id, boolean name, String searchKey) {
		Product p = null;
		if(id) p = pmService.search(searchKey,"id");
		else p = pmService.search(searchKey,"name");
		return p;
	}
	

	public void addProduct() {
	}

	public Object editProduct() {
		return null;
	}

	public Object delProduct() {
		return null;
	}
}
