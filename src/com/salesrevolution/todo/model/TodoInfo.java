package com.salesrevolution.todo.model;

import java.util.Date;

/**
 * @author mokao TODOのmodelクラス
 */
public class TodoInfo {
	public int id;
	public int clientId;
	public String clientName;
	public int piroirty;
	public Date dueDate;
	public String content;
	public String title;

	public void setId(int id) {
		this.id = id;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setPiroirty(int piroirty) {
		this.piroirty = piroirty;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
