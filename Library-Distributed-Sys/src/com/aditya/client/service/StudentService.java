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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.aditya.client.utils.ParseHelper;
import com.aditya.model.Student;

public class StudentService {
	public static String URL = "/Library-Distributed-Sys/students/";

	public static ArrayList<Student> getAllStudents() {
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

			return ParseHelper.parseListOfStudents(EntityUtils.toString(httpResponse.getEntity()));

		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static boolean updateStudent(int id, String name) {
		try {
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath(URL + id)
					.build();

			HttpPut httpPut = new HttpPut(uri);
			httpPut.setHeader("Accept", "text/html");
			CloseableHttpClient client = HttpClients.createDefault();

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("name", name));

			httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			CloseableHttpResponse response = client.execute(httpPut);

			return EntityUtils.toString(response.getEntity()).equals("Updated Student Successfully");
		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean deleteStudent(int id) {
		try {
			URI uri = new URIBuilder()
					.setScheme("http")
					.setHost("localhost")
					.setPort(8080)
					.setPath(URL + id)
					.build();

			HttpDelete httpDelete = new HttpDelete(uri);
			httpDelete.setHeader("Accept", "text/html");
			CloseableHttpClient client = HttpClients.createDefault();

			CloseableHttpResponse response = client.execute(httpDelete);

			return EntityUtils.toString(response.getEntity()).equals("Student Deleted Successfully");
		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean addStudent(String name) {
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
			nameValuePairs.add(new BasicNameValuePair("name", name));

			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			CloseableHttpResponse response = client.execute(httpPost);

			return EntityUtils.toString(response.getEntity()).equals("Added Student Successfully");
		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public static boolean deleteAllStudents() {
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

			return EntityUtils.toString(response.getEntity()).equals("All Students Deleted Successfully");
		} catch (URISyntaxException | IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
