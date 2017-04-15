package primarykeys;

/**
 * CompositionPK is the primary key class for a Composion entity.
 * @author    Sizhe Chen
 * @version   1.0.0 April 2017
 */
public class MovementPK implements java.io.Serializable	{
	/**
	 *	Default constructor.
	 */
	public MovementPK()	{}

	/**
	 *	Constructor to build a primary key from an seasonID.
	 *	@param	registrationNo	The seasonID.
	 */
	public MovementPK(String name, int number)	{ 
		setName(name);
		setNumber(number);
	}

	
	/* ACCESSORS	--------------------------------------------------	*/
	/**
	 *	Get the season ID.
	 *	@return	The season ID.
	 */
	public String getName()	{ return name;		}
	public int getNumber()	{ return number;		}
	
	/* MODIFIERS	--------------------------------------------------	*/
	public void setName(String name)		{ this.name = name;		}
	public void setNumber(int number)		{ this.number = number;		}

	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Convert this primary key object into a meaningful string.
	 *	@return	This object as a string.
	 */
	public String toString()	{ return	name;		}


	/**
	 *	Implemenation of the "object" equals method.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof MovementPK
			&&	getName() == ((MovementPK) obj).getName()
			&&	getNumber() == ((MovementPK) obj).getNumber();
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
		return	getName().hashCode() + getNumber();
	}



	/*	Concert Season Entity PRIMARY KEY FIELDS ------------------------------	*/
	/** season ID.																	*/
	private String name;
	private int number;

}	/*	End of Class:	BoatPK.java				*/
