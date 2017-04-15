package servlets;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.CreateException;
import sql.FinderException;
import controllers.Composer;
import controllers.Composition;
import controllers.Movement;

/**
 * Servlet implementation class HelloWorld
 */
public class SymphonyComposition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SymphonyComposition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //get the parameters of the request
		String composerIndex = request.getParameter("composerIndex");
	    String pageIndex = request.getParameter("page");
	    
	    //convert string to integer
	    int index = 0;
	    if (composerIndex != null) {
	    	index = Integer.parseInt(composerIndex);
	    }
	    int page = 0;
	    if (pageIndex != null) {
	    	page = Integer.parseInt(pageIndex);
	    }
	    
	    try{
	    	//find all the composers, and save into an arrayList
 		    ArrayList<Composer> composerList = new ArrayList<>(Composer.findAll());
    		//get the current composer 
    	    Composer currentComposer = composerList.get(index);
    	    
    	    ArrayList<Object> rows = new ArrayList<>();
    	    //find all the compositions by current composer
    	    Collection<Composition>  compositions = Composition.findByComposer(currentComposer); 
    	    //convert collection into iterator
    	    Iterator<Composition> iter = compositions.iterator();
    	    
    		while (iter.hasNext()){
    			//get the next composition
    			Composition next = (Composition)iter.next();
    			//find all the next movements by the composition
    			Collection<Movement>  movements = Movement.findByComposition(next); 
    			//convert collection into iterator
    			Iterator<Movement> subIter = movements.iterator();
    			//add the next composition into the row
    			rows.add(next);
    			while (subIter.hasNext()){
    				//add the movements into the row
    				rows.add(subIter.next());
    			}
    		}
    		
    		//Pagination
    		int i = page*10;
    		ArrayList<Object> pageRows = new ArrayList<>();
    		//add current 10 rows into current page 
    		while (i < rows.size() && i < (page+1)*10){
    			pageRows.add(rows.get(i));
    			i++;
    		}
    		//parse the variables into the JSP
    		request.setAttribute("currentIndex", index);
    		request.setAttribute("nextIndex", index < composerList.size() - 1 ? index + 1 : -1);
    		request.setAttribute("nextPage", page < (rows.size() - 1) / 10 ? page + 1 : -1);
    		request.setAttribute("composer", currentComposer); 
    		request.setAttribute("rows", pageRows); 
    		// Forward to JSP page to redisplay current rows for the current composer.
    	    request.getRequestDispatcher("/WEB-INF/SymphonyComposition.jsp").forward(request, response); 
    	} 
	    //It would be redirected to the error page if any exception be caught
    	catch (FinderException e)	{
    		request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		} 
	    catch (CreateException e) {
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
