package com.salesrevolution.todo.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@RequestScoped
@Named
@ManagedBean(name = "TodoList")
public class TodoList {
	private Map<Integer, TodoInfo> todoList = new HashMap<Integer, TodoInfo>();
	public int id;
	public int clientId;
	public String clientName;
	public int priority;
	public Date dueDate;
	public String content;

	public TodoList() {
		// 握りつぶし
	}

	public Map<Integer, TodoInfo> getInstance() {
		return todoList;
	}

	public void setMap() {
		TodoInfo todo = new TodoInfo();
		todo.setClientId(clientId);
		todo.setClientName(clientName);
		todo.setContent(content);
		todo.setDueDate(dueDate);
		todo.setId(id);
		todo.setPiroirty(priority);
		todoList.put(id, todo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
