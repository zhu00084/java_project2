package models;


import primarykeys.ComposerPK;
import primarykeys.CompositionPK;
import sql.CorePersistenceModel;

public class CompositionModel extends CorePersistenceModel{
	
	/**
	 * Creates a new instance of BoatModel
	 */
	public CompositionModel() { super();}

	
	/**
	 * Creates a new instance of BoatModel
	 */
	public CompositionModel(String name,
							String composerName){
		this(name, new ComposerPK(composerName));
	}
	
	/**
	 * Creates a new instance of BoatModel
	 */
	public CompositionModel(String name,
							ComposerPK composer){
		setPrimarykey(new CompositionPK(name, composer));
	}
	
	
	/* ACCESSORS	--------------------------------------------------	*/
	public CompositionPK getPrimarykey(){
		return (CompositionPK) super.getPrimarykey();			
	}
	public String getName(){
	   return getPrimarykey().getName();	
	}
	public ComposerPK getComposerPK(){
	   return getPrimarykey().getComposerPK();	
	}


	/* MODIFIERS	--------------------------------------------------	*/
	private void setPrimarykey(CompositionPK pk){
		super.setPrimarykey(pk);					
	}
 

	/* REFERENCE ATTRIBUTES	-----------------------------------------	*/
	/** The primary key for a concert season											*/
	private CompositionPK CompositionPrimarykey;

}

