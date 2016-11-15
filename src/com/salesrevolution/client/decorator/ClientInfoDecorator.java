package com.salesrevolution.client.decorator;

import com.salesrevolution.client.model.ClientInfo;

/**
 * 
 * @author mokabuu
 * @param TodoInfo
 * @return String
 * 
 * 渡されら顧客情報を元に一覧画面に表示するhtmlを生成する
 *
 */
public class ClientInfoDecorator {
	public String makeClientInfoHTML(ClientInfo client) {
		StringBuilder html = new StringBuilder();
		html.append("<div style=\"display: block; width: 100%;\">");
		if (client.isNowMeeting) {
			html.append("<div style=\"display: inline-block; width: 100%; background: rgba(255, 255, 0, 0.7); color: #000; max-width: 680px; margin-top: 10px;\">");
			html.append("<span style=\"border-bottom: 3px #000 double; display: block; margin: 1em;\">");
			html.append("<font style=\"font-weight: bold;\">[営業商談中]</font>");
			html.append("<a href=\"#\" style=\"color: #000;\" onclick=\"alert('未実装');\">"
					+ client.clientName + "</a></span>");
		} else if (client.hasQuestions) {
			html.append("<div style=\"display: inline-block; width: 100%; background: rgba(255, 0, 0, 0.7); color: #FFF; max-width: 680px; margin-top: 10px;\">");
			html.append("<span style=\"border-bottom: 3px #FFF double; display: block; margin: 1em;\">");
			html.append("<font style=\"font-weight: bold;\">[問い合わせ2件]</font>");
			html.append("<a href=\"#\" style=\"color: #FFF;\" onclick=\"alert('未実装');\">"
					+ client.clientName + "</a></span>");
		} else {
			html.append("<div style=\"display: inline-block; width: 100%; background: rgba(255, 0, 0, 0.7); color: #FFF; max-width: 680px; margin-top: 10px;\">");
			html.append("<span style=\"border-bottom: 3px #FFF double; display: block; margin: 1em;\">");
			html.append("<a href=\"#\" style=\"color: #FFF;\" onclick=\"alert('未実装');\">"
					+ client.clientName + "</a></span>");
		}
		html.append("<p style=\"margin: 1em;\">");
		html.append(client.clientSales);
		html.append("<br />");
		html.append(client.clientInfo);
		html.append("</p>");
		html.append("<div style=\"display: block; margin-top: 10px; text-align: center; padding-bottom: 10px;\">");
		html.append("<input type=\"button\" class=\"btn btn-success\" value=\"顧客情報編集\" />");
		html.append("<input type=\"button\" class=\"btn btn-warning\" value=\"TODO追加\" onclick=\"PF('dlg1').show();\" />");
		if (client.isNowMeeting) {
			html.append("<input type=\"button\" class=\"btn btn-default\" value=\"営業を助ける\" />");
		}
		html.append("</div>");
		html.append("</div>");

		return html.toString();
	}
}
