package com.salesrevolution.client.model;

/**
 * 
 * @author mokao
 * 
 * 顧客情報の定義クラス(のつもり)
 *
 */
public class ClientInfo {
	public int clientId; //顧客ID
	public String clientName; //顧客名
	public String clientInfo; //顧客情報
	public String clientSales; //営業担当者
	public int clientStatus; //営業状況
	public boolean isNowMeeting; //商談中フラグ
	public boolean hasQuestions; //問い合わせ
	
	//TODO 時間ある時に営業開始日とか次回訪問日ももてるようにする
	
	public ClientInfo getClient(){
		ClientInfo client = new ClientInfo();
		client.clientId = this.clientId;
		client.clientName = this.clientName;
		client.clientInfo = this.clientInfo;
		client.clientSales = this.clientSales;
		client.clientStatus = this.clientStatus;
		client.isNowMeeting = this.isNowMeeting;
		client.hasQuestions = this.hasQuestions;
		
		return client;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}

	public void setClientSales(String clientSales) {
		this.clientSales = clientSales;
	}

	public void setClientStatus(int clientStatus) {
		this.clientStatus = clientStatus;
	}

	public void setNowMeeting(boolean isNowMeeting) {
		this.isNowMeeting = isNowMeeting;
	}

	public void setHasQuestions(boolean hasQuestions) {
		this.hasQuestions = hasQuestions;
	}
	
	
}
