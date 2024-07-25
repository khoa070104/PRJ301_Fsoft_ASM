package com.se4f7.prj301.model;

import java.util.List;

public class PaginationModel {
	private int page;
	private int size;
	private int totalRecord;
	private List<? extends Object> records;

	public PaginationModel() {
		super();
	}

	public PaginationModel(int page, int size, int totalRecord, List<? extends Object> records) {
		super();
		this.page = page;
		this.size = size;
		this.totalRecord = totalRecord;
		this.records = records;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<? extends Object> getRecords() {
		return records;
	}

	public void setRecords(List<? extends Object> records) {
		this.records = records;
	}

}
