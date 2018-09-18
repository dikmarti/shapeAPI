package test.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jackson.map.ObjectMapper;
import test.dao.shape.Shape;
import test.dao.shape.ShapeDAO;
import test.dao.shape.model.ShapeRootModel;
import test.dao.shape.type.Circle;
import test.dao.shape.type.Square;
import test.dao.shape.type.Triangle;


@Path("/shape")
public class ShapeRestService {
	
	private static final String SHAPE_NOT_FOUND = "Shape not found!";
	private static final String SHAPE_FOUND = "Shape found!";
	private static final String SAVE_ERROR = "Error saving shape";
	private static final String SAVE_OK = "Save OK";
	@Inject
	ShapeDAO shapeDAO;

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ShapeRootModel getShape(@PathParam("id") String id) {
	  
		ShapeRootModel model = new ShapeRootModel();
		
		try{
			Shape shape = shapeDAO.getShape(id);
			
			model.setShape(shape);
			
			if (shape != null){
				model.setMessage(SHAPE_FOUND);
			}else {
				model.setMessage(SHAPE_NOT_FOUND);
			}	
		}catch(Exception e) {
			model.setMessage(SHAPE_NOT_FOUND);
		}		
		
		return model;
	}
	
	@POST
	@Path("/circle")
    @Consumes(MediaType.APPLICATION_JSON)
    public ShapeRootModel createCircle(String jsonShape) {
       
		ObjectMapper mapper=new ObjectMapper();
		Circle circle = null;
		ShapeRootModel model = new ShapeRootModel();
		
		try {
			mapper.enableDefaultTyping();
			
			circle = mapper.readValue(jsonShape, Circle.class);			
			Circle addShape = (Circle) shapeDAO.addShape(circle);
			model.setShape(addShape);
			model.setMessage(SAVE_OK);
			
		} catch (Exception e) {
			
			model.setMessage(SAVE_ERROR);
			
		}
		
		return model;
    }
	
	@POST
	@Path("/triangle")
    @Consumes(MediaType.APPLICATION_JSON)
    public ShapeRootModel createTriangle(String jsonShape) {
       
		ObjectMapper mapper=new ObjectMapper();
		Triangle triangle = null;
		ShapeRootModel model = new ShapeRootModel();
		
		try {
			mapper.enableDefaultTyping();
			triangle = mapper.readValue(jsonShape, Triangle.class);
			
			shapeDAO.addShape(triangle);
			model.setShape(triangle);
			model.setMessage(SAVE_OK);
			
		} catch (Exception e) {			
			model.setMessage(SAVE_ERROR);
		}
		
		return model;
    }
	
	@POST
	@Path("/square")
    @Consumes(MediaType.APPLICATION_JSON)
    public ShapeRootModel createSquare(String jsonShape) {
       
		ObjectMapper mapper=new ObjectMapper();
		Square square = null;
		ShapeRootModel model = new ShapeRootModel();
		
		try {
			mapper.enableDefaultTyping();
			square = mapper.readValue(jsonShape, Square.class);
				
			shapeDAO.addShape(square);
			model.setShape(square);
			model.setMessage(SAVE_OK);
			
		} catch (Exception e) {			
			model.setMessage(SAVE_ERROR);
		}
		
		return model;
    }
  
}