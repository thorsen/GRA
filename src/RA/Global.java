package RA;

import general.Encriptacion;
import general.InteraccionFic;
import java.io.IOException;

public class Global {
	public static String IP_SERVER_GRA;
	public static String USER_SERVER_GRA;
	public static String PASS_SERVER_GRA;
	public static String LINKED_SERVER_GCP;

	//Para clases COPIADAS de GCP
	public static String IP_SERVER_GCP;
	public static String USER_SERVER_GCP;
	public static String PASS_SERVER_GCP;

	public static String RUTA_DATOS;
	public static String RUTA_IMAGENES;

	private static String FIC_CONF_GLOBAL = "GRA.conf";

	public static void cargaConfiguracionGlobal() throws IOException {
		InteraccionFic interfic = new InteraccionFic(FIC_CONF_GLOBAL, InteraccionFic.READ);
		IP_SERVER_GRA = interfic.leeLinea().trim();
		USER_SERVER_GRA = interfic.leeLinea().trim();
		PASS_SERVER_GRA = Encriptacion.decrypt(interfic.leeLinea().trim());
		LINKED_SERVER_GCP = interfic.leeLinea().trim();

		IP_SERVER_GCP = interfic.leeLinea().trim();
		USER_SERVER_GCP = interfic.leeLinea().trim();
		PASS_SERVER_GCP = Encriptacion.decrypt(interfic.leeLinea().trim());

		RUTA_DATOS = interfic.leeLinea().trim();
		interfic.finOpFichero();

		setRutaImagenes();
	}

	private static void setRutaImagenes() {
		RUTA_IMAGENES = Global.RUTA_DATOS + "Curva\\Imagenes\\";
	}
}
