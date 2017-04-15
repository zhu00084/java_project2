package models;


import primarykeys.ComposerPK;
import primarykeys.CompositionPK;
import sql.CorePersistenceModel;

public class ComposerModel extends CorePersistenceModel{
	
	/**
	 * Creates a new instance of BoatModel
	 */
	public ComposerModel() { super();}

	
	/**
	 * Creates a new instance of BoatModel
	 */
	public ComposerModel(String name){
		this(new ComposerPK(name));
	}
	
	/**
	 * Creates a new instance of BoatModel
	 */
	public ComposerModel(ComposerPK primarykey){
		setPrimarykey(primarykey);
	}
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	public ComposerPK getPrimarykey(){
		return (ComposerPK) super.getPrimarykey();			
	}
	public String getName(){
	   return getPrimarykey().getName();	
	}


	/* MODIFIERS	--------------------------------------------------	*/
	private void setPrimarykey(ComposerPK pk){
		super.setPrimarykey(pk);					
	}
	public void setName(String name){
		setPrimarykey(new ComposerPK(name));					
	}
 

	/* REFERENCE ATTRIBUTES	-----------------------------------------	*/
	/** The primary key for a concert season											*/
	private ComposerPK primarykey;

}

