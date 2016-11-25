package com.salesrevolution.todo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import com.datastax.driver.core.Session;
import com.salesrevolution.util.DBConnection;

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
	public String title;

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
		todo.setTitle(title);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/* 仮置き */
	// FIXME クラスを切ること
	// FIXME 今はただのモックです。
	public List<String> completeText(String query) {
		List<String> clients = new ArrayList<String>();
		List<String> results = new ArrayList<String>();

		clients.add("MocksApplications");
		clients.add("原尾コーポレーション");
		clients.add("大藪証券");
		clients.add("高山不動産");
		clients.add("Bar Imamura");
		clients.add("山小屋つねやま");

		for (String client : clients) {
			if (client.startsWith(query)) {
				results.add(client);
			}
		}

		//test code
		Session session = DBConnection.connect();
		session.execute("select * from TODO;");

		return results;
	}

}
