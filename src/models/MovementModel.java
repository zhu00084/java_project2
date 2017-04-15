package models;


import primarykeys.CompositionPK;
import primarykeys.MovementPK;
import sql.CorePersistenceModel;

public class MovementModel extends CorePersistenceModel{
	
	/**
	 * Creates a new instance of BoatModel
	 */
	public MovementModel() { super();}


	/**
	 * Creates a new instance of BoatModel
	 */
	public MovementModel(String name,
						 int number,
						 String compositionName,
						 String composerName){
		setPrimarykey(new MovementPK(name, number));
		setCompositionPK(new CompositionPK(compositionName, composerName));
	}


	public void setCompositionPK(CompositionPK compositionPK) {
		this.compositionPK = compositionPK;
		
	}


	/* ACCESSORS	--------------------------------------------------	*/
	public MovementPK getPrimarykey(){
		return (MovementPK) super.getPrimarykey();			
	}
	public CompositionPK getCompositionPK(){
		return this.compositionPK;		
	}
	public String getName(){
	   return getPrimarykey().getName();	
	}
	public int getNumber(){
	   return getPrimarykey().getNumber();	
	}
	public String getCompositionName(){
	   return this.compositionPK.getName();
	}
	public String getComposerName(){
	   return this.compositionPK.getComposerPK().getName();
	}


	/* MODIFIERS	--------------------------------------------------	*/
	private void setPrimarykey(MovementPK pk){
		super.setPrimarykey(pk);					
	}
 

	/* REFERENCE ATTRIBUTES	-----------------------------------------	*/
	/** The primary key for a concert season											*/
	private MovementPK primarykey;
	private CompositionPK compositionPK;
}

