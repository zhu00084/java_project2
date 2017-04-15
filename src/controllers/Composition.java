package controllers;


import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.SQLException;

import sql.DAOSysException;
import sql.CreateException;
import sql.DAOFactory;
import sql.NoSuchEntityException;
import sql.FinderException;

import java.sql.Date;

import primarykeys.ComposerPK;
import primarykeys.CompositionPK;
import daos.CompositionDAO;
import models.ComposerModel;
import models.CompositionModel;

/**
 * Boat class
 *		with Customer reference variable and methods 
 *		adding Slip reference variable
 */
public class Composition		{
	/* STATIC PRE-OBJECT BEHAVIOR	-----------------------------------	*/
	
	public Composition(String compositionName, String composerName){
		this(new CompositionModel(compositionName, composerName));
	}
	
	public Composition(CompositionModel model){
		setModel(model);
		setComposer(new Composer(model.getComposerPK().getName()));
	}
	
	public void setModel(CompositionModel model){
		this.model = model;
	}
	/* FINDERS	-----------------------------------------------------	*/
	/*	Finder methods are used to search for a particular instance
	 *	or a collection of instances, therefore finders either return
	 *	and instance to the entity, or a collection of instances.
	 */
	/**
	 *	Find a boat by primary key.
	 *	@return	An instance of a boat entity.
	 *	@param	primarykey	The primary key for entity to find.
	 *	@throws	ObjectNotFoundException
	 */
	public static Composition findByPrimarykey(CompositionPK primarykey)
								throws FinderException, NoSuchEntityException					{

		CompositionModel model = null;
		Composition composition = null;
		CompositionDAO dao = null;
		try	{
			dao = getDAO();
			model = (CompositionModel) dao.dbSelectByPrimaryKey(primarykey);

			composition = new Composition(model);
		
		} catch (Exception ex)	{
			throw new FinderException(ex.getMessage());
		}

		return composition;
	}

	/**
	 *	Find all boat entities for a particular customer.
	 *	@return	A collection of boat instances.
	 *	@throws	FinderException
	 * @throws	CreateException
	 */
	public static Collection<Composition> findByComposer(Composer composer)
			throws FinderException {
		//create an array list of compositions
		ArrayList<Composition> listOfCompositions = new ArrayList<Composition>();
		CompositionDAO dao = null;
	
		try	{
			dao = getDAO();
			//select compositions by composer
			Collection compositions = dao.dbSelectByComposer(composer.getName());
			//change the collection into iterator
			Iterator itr = compositions.iterator();
			
			//use a loop to get all the compositions of the composer
			while (itr.hasNext())	{
	
				try	{
					//add composition by its primary key into a listOfCompositions
					listOfCompositions.add(Composition.findByPrimarykey((CompositionPK) itr.next()));
				} catch (Exception ex)	{
					System.out.println("Composition: Error processing list < " + ex.toString() + " >");
					ex.printStackTrace(System.out);
				}

			}

		} catch (Exception ex)	{
			ex.printStackTrace(System.out);
			throw new FinderException(ex.getMessage());
		}


		return listOfCompositions;
	}



	/* ACCESSORS	--------------------------------------------------	*/
	public CompositionModel getModel()				{ return model;}
	public String getName()				{ return model.getName();}
	public Composer getComposer()				{ return composer;}

	
	/**
	 *	Custom method to assign a Boat to a Customer
	 *	@param	customer	The customer that owns this boat.
	 */
	public void setComposer(Composer composer)		{ 
		this.composer = composer;
	}
	

	/**
	 *	Implemenation of the "object" equals method.  Boat objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Composition
			&&	(getModel().getPrimarykey().equals(((Composition) obj).getModel().getPrimarykey())
			);
	}

	/**
	 *	Implementation of the "object"hashCode()" method.
	 * Whenever it is invoked on the same object more than once during
	 * an execution of a Java application, the hashCode method
	 * must consistently return the same integer, provided no information
	 * used in equals comparisons on the object is modified.
	 *	@return	A hash code value for the object.
	 */
	public int hashCode() {
		return	model.getComposerPK().hashCode();
	}


	
	/**
	 *	Convert this boat object to a meaningful string.
	 *	@return	This object as a string.
	 */
	public String toString()		{
		return model.toString();
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


	
	/** Persistence model for a boat object.									*/
	private CompositionModel model;

	/** Data access object for boat entities.									*/
	private static CompositionDAO dao;

	
	/* REFERENCE ATTRIBUTES	-----------------------------------------	*/
	/** Reference to the customer object for this boat.					*/
	private Composer composer;


}	/*	End of CLASS:	Boat.java			*/
