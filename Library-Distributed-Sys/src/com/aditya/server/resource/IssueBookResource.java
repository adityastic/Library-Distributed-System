package com.aditya.server.resource;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.aditya.model.IssuedBook;
import com.aditya.server.dao.BookDao;
import com.aditya.server.dao.IssueBookDao;

@Path("/issued-books")
public class IssueBookResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<IssuedBook> getAllIssuedBooks() {
		return IssueBookDao.INSTANCE.getAllIssuedBooks();
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String issueBook(
			@FormParam("student_id") int studentID,
			@FormParam("book_id") int bookID) {
		return IssueBookDao.INSTANCE.issueBook(studentID, bookID) ? "Issued Book Successfully" : "Error Issuing Book";
	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	public String deleteAllBook() {
		return IssueBookDao.INSTANCE.deleteAllIssuedBooks() ? "All Issued Books Deleted Successfully" : "Error Deleting Issued Books";
	}

}
