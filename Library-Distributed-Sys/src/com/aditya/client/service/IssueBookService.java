package com.aditya.client.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.aditya.client.utils.ParseHelper;
import com.aditya.model.IssuedBook;

public class IssueBookService {
	public static String URL = "/Library-Distributed-Sys/issued-books/";
	
	public static boolean issueBook(int studentID, int bookID) {
		try {
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath(URL)
					.build();

			HttpPost httpPost = new HttpPost(uri);
			httpPost.setHeader("Accept", "text/html");
			CloseableHttpClient client = HttpClients.createDefault();
			
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("student_id", String.valueOf(studentID)));
			nameValuePairs.add(new BasicNameValuePair("book_id", String.valueOf(bookID)));
			
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			CloseableHttpResponse response = client.execute(httpPost);
			
			return EntityUtils.toString(response.getEntity()).equals("Issued Book Successfully");
		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static ArrayList<IssuedBook> getAllIssuedBooksList() {
		try {
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath(URL)
					.build();

			HttpGet httpReq = new HttpGet(uri);
			httpReq.setHeader("Accept", "application/xml");

			CloseableHttpResponse httpResponse = HttpClients.createDefault().execute(httpReq);

			return ParseHelper.parseListOfIssuedList(EntityUtils.toString(httpResponse.getEntity()));
			
		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static boolean deleteAllIssuedBooks() {
		try {
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath(URL)
					.build();

			HttpDelete httpDelete = new HttpDelete(uri);
			httpDelete.setHeader("Accept", "text/html");
			CloseableHttpClient client = HttpClients.createDefault();

			CloseableHttpResponse response = client.execute(httpDelete);
			
			return EntityUtils.toString(response.getEntity()).equals("All Issued Books Deleted Successfully");
		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
