package dao;

import java.util.ArrayList;
import java.util.List;
import bean.Pelicula;

public class PeliculaDAO {
	db.Db db = new db.Db("CineStar");

	public Object getPeliculas(int id, boolean bLista ) {
		db.Sentencia( String.format("call sp_getPeliculas(%s)", id ) );
		String[][] mRegistros = db.getRegistros();
		
		if (mRegistros == null ) return null;		
		if ( !bLista ) return mRegistros;
		
		List<Pelicula> peliculas = new ArrayList<>();
		for (String[] aregistro : mRegistros)
			peliculas.add(new Pelicula( aregistro));
		
		return peliculas;
	} 
																							
	public Object getPelicula(String id, boolean bEntidad ) {
		db.Sentencia( String.format("call sp_getPelicula(%s)", id ) );
		String[] aRegistro = db.getRegistro();		
		if (aRegistro == null ) return null;	
		
		db.Sentencia( String.format("select getGenerosDetalle('%s')", aRegistro[4]) );
		aRegistro [4] = db.getCampo();
		
		if ( !bEntidad ) return aRegistro;
				
		return  new Pelicula ( aRegistro );
	} 

}

