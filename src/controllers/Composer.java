package controllers;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import primarykeys.ComposerPK;
import primarykeys.CompositionPK;
import daos.CompositionDAO;
import sql.CreateException;
import sql.DAOFactory;
import sql.DAOSysException;
import sql.FinderException;
import models.ComposerModel;

/**
 * Boat class
 *		with Customer reference variable and methods 
 *		adding Slip reference variable
 */
public class Composer		{
	/* STATIC PRE-OBJECT BEHAVIOR	-----------------------------------	*/
	/* CREATORS	-----------------------------------------------------	*/
	/**
	 *	Create an instance of a new boat.
	 *	@return	An instance of a boat entity.
	 *	@param	number	The customer number.
	 *	@param	name		The name of the customer.
	 *	@param	address	The address for this customer.
	 *	@param	phoneno	The phone number for this customer.
	 */
	public Composer(String composerName){
		this(new ComposerModel(composerName));
	}
	
	public Composer(ComposerModel model){
		setModel(model);
	}
	
	public void setModel(ComposerModel model){
		this.model = model;
	}
	
	public String getName(){
		return model.getName();
	}
	
	/**
	 *	Find all boat entities.
	 *	@return	A collection of boat instances.
	 *	@throws	FinderException
	 * @throws	CreateException
	 */
	public static Collection<Composer> findAll() throws FinderException, CreateException{
		//create an arrayList of composers
		ArrayList<Composer> listOfComposers = new ArrayList<Composer>();
		
		CompositionDAO dao = null;
	
		try	{
			//get composition DAO object
			dao = getDAO();
			//get all of the composers 
			Collection c = dao.dbSelectAllComposers();
			Iterator itr = c.iterator();
			//use a loop to get all the composers
			while (itr.hasNext())	{
				ComposerPK pk = (ComposerPK) itr.next();
				try	{
					//get the composer by its name and add it into the composer list
					listOfComposers.add(new Composer(pk.getName()));
				} catch (Exception ex)	{
					System.err.println("Composer: Error processing list < " + ex.toString()+ " >");
				}
			}

		} catch (Exception sqlex)	{
			throw new FinderException(sqlex.getMessage());
		}

		return listOfComposers;
	}
	
	/**
	 * Get a data access object for the Boat entity.
	 *	@return	A Boat data access object.
	 */
	private static CompositionDAO getDAO() throws DAOSysException {
		if (dao == null)	{
			dao = (CompositionDAO) DAOFactory.getDAO("daos.Composition");
		}

		return dao;
	}
	
	/* ATTRIBUTES	--------------------------------------------------	*/
	/** Persistence model for a composition object.									*/
	private static CompositionDAO dao;
	private ComposerModel model;
}