/*
 * BoatSlipDAO.java
 *
 * Created on July 2, 2005, 3:08 PM
 */

package daos;

import java.util.*;
import java.sql.*;

import primarykeys.CompositionPK;
import primarykeys.MovementPK;
import models.CompositionModel;
import models.MovementModel;
import sql.CoreDAOImpl;
import sql.CreateException;
import sql.CoreDAO;
import sql.DAOSysException;
import sql.NoSuchEntityException;

/**
 *	Data access object for BoatSlip data.  This class bridges the
 *	object to non-object datastore layer.
 * @author Reg
 */
public class MovementDAO extends CoreDAOImpl	{
	/**
	 * Creates a new instance of BoatSlipDAO
	 */
	public MovementDAO() { this(CoreDAO.DRIVER_NAME, CoreDAO.URL, CoreDAO.USER, CoreDAO.PASSWORD);		}

	/**
	 *	Parameterized constructor.  When extending this class the
	 *	derived class must invoke one of this classes constructors
	 *	for proper initialization.
	 *	@param	driver	Database driver.
	 *	@param	url		Database URL.
	 *	@param	user		Database user name.
	 *	@param	password	Database password for access.
	 *	@param	stm		SQL Statement for this intantiation.
	 *	@throws	SQLException when a SQL error has occured.
	 */
	public MovementDAO(String drivername,
						String url,
						String user,
						String password)	{
		super(drivername, url, user, password);
	}

	
	/* ACCESSORS	-----------------------------------------------	*/

	
	/* MUTATORS	--------------------------------------------------	*/

	/**
	 * Called by findByPrimaryKey() to retrieve entity data by the primary key.
	 *	@param	primarykey The primary key for the entity.
	 *	@return	The persistence model for the entity.
	 *	@throws	DAOSysException
	 * @throws	NoSuchEntityException
	 */
	public Object dbSelectByPrimaryKey(Object primarykey)
				throws DAOSysException, NoSuchEntityException {
//		if (_debug)	System.out.println("BDAO:dbSelectByPrimaryKey(" + primarykey + ")");
		return dbSelectByPrimaryKey(primarykey, new MovementModel());
	}

	/**
	 * Called by findByPrimaryKey() to retrieve entity data by the primary key.
	 *	@param	primarykey The primary key for the entity.
	 *	@param	selectStm	Statement to retrieved the entity data from the data store.
	 *	@return	The persistence model for the entity.
	 *	@throws	DAOSysException
	 * @throws	NoSuchEntityException
	 */
	public Object dbSelectByPrimaryKey(Object primarykey, Object model)
				throws DAOSysException, NoSuchEntityException	{
		String selectStm = "SELECT compositionName, composer, movementNumber, movementName "
				+ "FROM " + "Movements WHERE movementNumber = ? and movementName=?";
		return dbSelectByPrimaryKey(primarykey, selectStm, model);
	}

	
	/**
	 * Called by findByPrimaryKey() to retrieve entity data by the primary key.
	 *	@param	primarykey The primary key for the entity.
	 *	@param	selectStm	Statement to retrieved the entity data from the data store.
	 *	@return	The persistence model for the entity.
	 *	@throws	DAOSysException
	 * @throws	NoSuchEntityException
	 */
	public Object dbSelectByPrimaryKey(Object primarykey, String selectStm, Object model)
				throws DAOSysException, NoSuchEntityException	{
		if (_debug)	System.out.println("BDAO:dbSelectByPrimaryKey(key,stm,model)");
		MovementPK pk = (MovementPK) primarykey;
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		boolean result = false;
		
		MovementModel persistenceModel = (MovementModel) model;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			preparedStm.setInt(1, pk.getNumber());
			preparedStm.setString(2, pk.getName());
			rs = preparedStm.executeQuery();

			result = rs.next();
			if (result)	{	
				persistenceModel.setPrimarykey(primarykey);
				persistenceModel.setCompositionPK(new CompositionPK(rs.getString(1), rs.getString(2)));
			}	else	{
				throw new NoSuchEntityException("Boat ID for <"
						+ primarykey + "> not found in the database.");
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
				"dbSelectByPrimaryKey() SQL Exception\n"
				+ sex.getMessage());

		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}

		return persistenceModel;

	}
	
	/**
	 * Called by findAll() to find all entities in the data store.
	 *	@return	A collection of primary keys representing all of the entities.
	 *	@throws	SQLException
	 */
	public Collection dbSelectAll()	throws DAOSysException {
		String selectStm = "SELECT movementNumber, movementName "
								+ "FROM " + "Movements";
		return dbSelectAll(selectStm);
	}
	
	/**
	 * Called by findBy() to find all entities in the data store.
	 *	@return	A collection of primary keys representing all of the entities.
	 *	@throws	SQLException
	 */
	public Collection dbSelectByComposition(String composer, String compositionName)	throws DAOSysException {
		String selectStm = "SELECT movementNumber, movementName  "
								+ "FROM " + "Movements "
								+ "WHERE composer='" + composer + "' and compositionName='" + compositionName +"'";
		return dbSelectAll(selectStm);
	}
	
	/**
	 * Called by findAll() to find all entities in the data store.
	 *	@return	A collection of primary keys representing all of the entities.
	 *	@throws	SQLException
	 */
	public Collection dbSelectAll(String selectStm) throws DAOSysException {
		Connection connection = null;
		PreparedStatement preparedStm = null;
		ResultSet rs = null;
		ArrayList<MovementPK> list = null;

		try	{
			connection = connectToDB();
			preparedStm = connection.prepareStatement(selectStm);
			rs = preparedStm.executeQuery();

			list = new ArrayList<MovementPK>();
			while (rs.next())	{
				list.add(new MovementPK(rs.getString(2), rs.getInt(1)));
			}

		}	catch (SQLException sex)	{
			throw new DAOSysException(
						"dbSelectAll() SQL Exception\n"
						+ sex.getMessage());
		}	finally	{
			try	{
				releaseAll(rs, preparedStm, connection);
			} catch (Exception ex)	{
				System.err.println("Error releasing resources <" + ex.toString());
			}
		}

		return list;
	}
	
	/* ATTRIBUTES	-----------------------------------------------	*/
	private final static boolean _debug = true;
	
}	/*	End of Class:	BoatSlipDAO.java				*/

