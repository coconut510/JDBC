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

	public ArrayList<Product> serach(boolean id, boolean name, String searchKey) {
		 ArrayList<Product> list =new ArrayList<Product>();
		if(id) list = pmService.search(searchKey,"id");
		else list = pmService.search(searchKey,"name");
		System.out.println(list.size());
		return list;
	}
	

	public int addProduct(Product p) {
		int result = 0;
		result = pmService.addProduct(p);
		return result;
	}

	public int editProduct(Product p) {
		int result = 0;
		result = pmService.editProduct(p);
		return result;
	}

	public int delProduct(String id) {
		int result = 0;
		result  = pmService.delProduct(id);
		return result;
	}
}
