package com.aditya.server.resource;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.aditya.model.Student;
import com.aditya.server.dao.StudentDao;

@Path("/students")
public class StudentResource {

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	public List<Student> getAllStudents() {
		return StudentDao.INSTANCE.getAllStudents();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML })
	@Path("/{name}")
	public List<Student> getStudentsByName(@PathParam("name") String name) {
		return StudentDao.INSTANCE.getStudentsByName(name);
	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	@Path("/{id}")
	public String deleteStudent(@PathParam("id") String id) {
		return StudentDao.INSTANCE.deleteStudent(Integer.parseInt(id)) ? "Student Deleted Successfully" : "Error Deleting Student";
	}

	@DELETE
	@Produces(MediaType.TEXT_HTML)
	public String deleteAllStudent() {
		return StudentDao.INSTANCE.deleteAllStudents() ? "All Students Deleted Successfully" : "Error Deleting Students";
	}

	@PUT
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Path("/{id}")
	public String updateStudent(
			@PathParam("id") int id,
			@FormParam("name") String newName) {
		return StudentDao.INSTANCE.updateStudent(id, newName) ? "Updated Student Successfully" : "Error Updating Students";
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String addStudent(
			@FormParam("name") String name) {
		return StudentDao.INSTANCE.addStudent(name) ? "Added Student Successfully" : "Error Adding Students";
	}
}
