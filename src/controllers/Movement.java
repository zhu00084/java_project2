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

import primarykeys.MovementPK;
import daos.MovementDAO;
import models.ComposerModel;
import models.CompositionModel;
import models.MovementModel;

/**
 * Boat class
 *		with Customer reference variable and methods 
 *		adding Slip reference variable
 */
public class Movement		{
	/* STATIC PRE-OBJECT BEHAVIOR	-----------------------------------	*/
	
	public Movement(String movementName, 
					int movementNumber, 
					String compositionName, 
					String composerName){
		this(new MovementModel(movementName, movementNumber, compositionName, composerName));
		
	}
	
	public Movement(MovementModel model){
		setModel(model);
		setComposition(new Composition(model.getCompositionName(), model.getComposerName()));
	}
	
	public void setModel(MovementModel model){
		this.model = model;
	}
	/**
	 *	Custom method to assign a Boat to a Customer
	 *	@param	customer	The customer that owns this boat.
	 */
	public void setComposition(Composition composition)		{ 
		this.composition = composition;
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
	public static Movement findByPrimarykey(MovementPK primarykey)
								throws FinderException, NoSuchEntityException					{

		MovementModel model = null;
		Movement Movement = null;
		MovementDAO dao = null;
		try	{
			dao = getDAO();
			model = (MovementModel) dao.dbSelectByPrimaryKey(primarykey);

			Movement = new Movement(model);
		
		} catch (Exception ex)	{
			throw new FinderException(ex.getMessage());
		}

		return Movement;
	}

	/**
	 *	Find all boat entities for a particular customer.
	 *	@return	A collection of boat instances.
	 *	@throws	FinderException
	 * @throws	CreateException
	 */
	public static Collection<Movement> findByComposition(Composition composition)
			throws FinderException {

		ArrayList<Movement> listOfMovements = new ArrayList<Movement>();
		MovementDAO dao = null;
	
		try	{
			dao = getDAO();
			Collection Movements = dao.dbSelectByComposition(composition.getComposer().getName(), composition.getName());

			Iterator itr = Movements.iterator();

			while (itr.hasNext())	{

	
				try	{
					listOfMovements.add(Movement.findByPrimarykey((MovementPK) itr.next()));
				} catch (Exception ex)	{
					System.out.println("Movement: Error processing list < " + ex.toString() + " >");
					ex.printStackTrace(System.out);
				}

			}

		} catch (Exception ex)	{
			ex.printStackTrace(System.out);
			throw new FinderException(ex.getMessage());
		}


		return listOfMovements;
	}



	/* ACCESSORS	--------------------------------------------------	*/
	public MovementModel getModel()				{ return model;													}
	public Composition getComposition()				{ return composition;													}
	public String getName()				{ return model.getName();													}
	public int getNumber()				{ return model.getNumber();	}

	/**
	 *	Implemenation of the "object" equals method.  Boat objects are equal
	 *	if their primary key's are equal.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof Movement
			&&	(getModel().getPrimarykey().equals(((Movement) obj).getModel().getPrimarykey())
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
		return	model.getPrimarykey().hashCode();
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
	private static MovementDAO getDAO() throws DAOSysException {
		if (dao == null)	{
			dao = (MovementDAO) DAOFactory.getDAO("daos.Movement");
		}

		return dao;
	}


	
	/** Persistence model for a boat object.									*/
	private MovementModel model;

	/** Data access object for boat entities.									*/
	private static MovementDAO dao;

	
	/* REFERENCE ATTRIBUTES	-----------------------------------------	*/
	/** Reference to the customer object for this boat.					*/
	private Composition composition;


}	/*	End of CLASS:	Boat.java			*/
