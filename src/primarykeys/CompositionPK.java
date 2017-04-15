package primarykeys;

/**
 * CompositionPK is the primary key class for a Composion entity.
 * @author    Sizhe Chen
 * @version   1.0.0 April 2017
 */
public class CompositionPK implements java.io.Serializable	{
	/**
	 *	Default constructor.
	 */
	public CompositionPK()	{}

	/**
	 *	Constructor to build a primary key from an seasonID.
	 *	@param	registrationNo	The seasonID.
	 */
	public CompositionPK(String name, String composerName)	{ 
		this(name, new ComposerPK(composerName));
	}

	/**
	 *	Constructor to build a primary key from a another ConcertSeasonPK argument.
	 *	@param	primarykey	A ConcertSeasonPK object.
	 */
	public CompositionPK(String name, ComposerPK composerPK)	{ 
		setName(name);
		setComposerPK(composerPK);
	}
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	/**
	 *	Get the season ID.
	 *	@return	The season ID.
	 */
	public String getName()	{ return name;		}
	public ComposerPK getComposerPK()	{ return composerPK;		}
	
	/* MODIFIERS	--------------------------------------------------	*/
	public void setName(String name)		{ this.name = name;		}
	public void setComposerPK(ComposerPK pk)		{ composerPK = pk;		}

	/* BEHAVIOR	-----------------------------------------------------	*/
	/**
	 *	Convert this primary key object into a meaningful string.
	 *	@return	This object as a string.
	 */
	public String toString()	{ return	name + ": " + composerPK.getName();		}


	/**
	 *	Implemenation of the "object" equals method.
	 *	@return	True if the fields of this primary key object equal the
	 *	contents of the fields from the passed primary key object, otherwise
	 *	false, they are not equal.
	 */
	public boolean equals(Object obj)	{
		return	obj instanceof CompositionPK
			&&	getName() == ((CompositionPK) obj).getName()
			&&	getComposerPK() == ((CompositionPK) obj).getComposerPK();
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
		return	getName().hashCode() + getComposerPK().hashCode();
	}



	/*	Concert Season Entity PRIMARY KEY FIELDS ------------------------------	*/
	/** season ID.																	*/
	private String name;
	private ComposerPK composerPK;

}	/*	End of Class:	BoatPK.java				*/
