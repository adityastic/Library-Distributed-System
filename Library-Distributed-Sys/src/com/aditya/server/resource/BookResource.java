package com.aditya.server.resource;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.aditya.model.Book;
import com.aditya.server.dao.BookDao;

@Path("/books")
public class BookResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<Book> getAllBooks() {
		return BookDao.INSTANCE.getAllBooks();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Path("/{name}")
	public List<Book> getBooksByName(@PathParam("name") String name) {
		return BookDao.INSTANCE.getBooksByName(name);
	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("/{id}")
	public String deleteBook(@PathParam("id") String id) {
		return BookDao.INSTANCE.deleteBook(Integer.parseInt(id)) ? "Book Deleted Successfully" : "Error Deleting Book";
	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	public String deleteAllBook() {
		return BookDao.INSTANCE.deleteAllBooks() ? "All Books Deleted Successfully" : "Error Deleting Books";
	}

	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/{id}")
	public String updateBook(
			@PathParam("id") int id,
			@FormParam("name") String newName,
			@FormParam("price") String newPrice) {
		return BookDao.INSTANCE.updateBook(id, newName, newPrice) ? "Updated Book Successfully" : "Error Updating Books";
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addBook(
			@FormParam("name") String name,
			@FormParam("price") String price) {
		return BookDao.INSTANCE.addBook(name, price) ? "Added Book Successfully" : "Error Adding Books";
	}
}
