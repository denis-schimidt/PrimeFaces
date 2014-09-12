package br.com.schimidtsolutions.jsf.constantes;

public enum TemaTela {
	AFTERDARK("Afterdark","afterdark"),                                                    
	AFTERNOON("Afternoon","afternoon"),                                                    
	AFTERWORK("Afterwork","afterwork"),                                                    
	BLITZER("Blitzer","blitzer"),
	BLUESKY("Bluesky","bluesky"),                                                  
	BOOTSTRAP("Bootstrap","bootstrap"),                                 
	BLACK_TIE("Black Tie","black-tie"),                                                    
	CASABLANCA("Casablanca","casablanca"),                                                      
	CUPERTINO("Cupertino","cupertino"),                                                     
	CRUZE("Cruze","cruze"),                                                 
	DARK_HIVE( "Dark Hive","dark-hive"),                                                     
	DELTA("Delta","delta"),                                                
	DOT_LUV("Dot Luv","dot-luv"),                                                  
	EGGPLANT("Eggplant","eggplant"),                                                   
	EXCITE_BIKE("Excite Bike","excite-bike"),                                                      
	FLICK("Flick","flick"),                                                
	GLASS_X("Glass X","glass-x"),                                                  
	HOME("Home","home"),                                            
	HOT_SNEAKS("Hot Sneaks","hot-sneaks"),                                                     
	HUMANITY("Humanity","humanity"),                                                   
	LE_FROG("Le Frog","le-frog"),                                                  
	MIDNIGHT("Midnight","midnight"),                                                   
	MINT_CHOC("Mint Choc","mint-choc"),                                                    
	OVERCAST("Overcast","overcast"),                                                    
	PEPPER_GRINDER("Pepper Grinder","pepper-grinder"),                                                          
	REDMOND("Redmond","redmond"),                                                   
	ROCKET("Rocket","rocket"),                                                  
	SAM("Sam","sam"),                                           
	SMOOTHNESS ("Smoothness","smoothness"),                                                      
	SOUTH_STREET("South Street","south-street"),                                                         
	START("Start","start"),                                                  
	SUNNY("Sunny","sunny"),                                                
	SWANKY_PURSE("Swanky Purse","swanky-purse"),                                                        
	TRONTASTIC("Trontastic","trontastic"),                                                      
	UI_DARKNESS("Ui Darkness","ui-darkness"),                                                       
	UI_LIGHTNESS("Ui Lightness","ui-lightness"),                                                       
	VADER("Vader","vader");       
	
	private final String apelido;
	private final String nomeReal;
	
	private TemaTela(final String apelido, final String nomeReal) {
		this.apelido = apelido;
		this.nomeReal = nomeReal;
	}
	
	public static final TemaTela getInstancePeloNomeReal( final String nomeReal ){
		
		for( TemaTela temaTela : TemaTela.values() ) {
			if( temaTela.getNomeReal().equals(nomeReal) ){
				return temaTela;
			}
		}
		
		return TemaTela.BOOTSTRAP;
	}

	public String getApelido() {
		return apelido;
	}

	public String getNomeReal() {
		return nomeReal;
	}
}
