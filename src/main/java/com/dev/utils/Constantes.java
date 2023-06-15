package com.dev.utils;

public final class Constantes {

	protected Constantes() {
	}

	/**
	 * estado de la denuncia
	 *
	 */
	public static final class estadoInvestigacion {

		protected estadoInvestigacion() {

		}

		public static final Integer DENUNCIA = 5;

		public static final Integer DEVOLVER = 6;

		public static final Integer DESESTIMAR = 7;

		public static final Integer PRELIMINAR = 8;

		public static final Integer PREPARATORIA = 9;

		public static final Integer OTROS = 10;

	}

	/**
	 * codigo de denuncia
	 */

	public static final class codigoInvestigacion {

		protected codigoInvestigacion() {

		}

		public static final String DENUNCIA = "DCIA";

		public static final String DEVOLVER = "DVER";

		public static final String DESESTIMAR = "DST";

		public static final String PRELIMINAR = "PRM";

		public static final String PREPARATORIA = "PRPA";

		public static final String OTROS = "OTR";

	}

	/**
	 * indica el tipo de persona si es denunciante o denunciado
	 *
	 */
	public static final class tipoPersona {

		protected tipoPersona() {

		}

		public static final String PERSONA_DENUNCIANTE = "DNCTE"; // --> por definir

		public  static final String PERSONA_DENUNCIADA = "DNCDO";
	}


	public static final class tipoPersonaId {

		protected tipoPersonaId() {

		}

		public static final Integer DENUNCIANTE = 33; // --> por definir

		public  static final Integer DENUNCIADO = 34;
	}

	/**
	 * tipo de catalogos
	 */
	public static final class tipoCatalogos {

		protected tipoCatalogos() {

		}

		/***
		 * Indica el codigo de catalogo #3 (Estados de la denuncia)
		 */
		public static final Integer CATALOGO_ESTADO_INVESTIGACION = 3;
	}


	public static final class nombreCatalogos {

		protected nombreCatalogos() {

		}

		/***
		 * Indica el nombre  de catalogo  (Tipo denunciante {denunciado , denunciante})
		 */
		public static final String CATALOGO_TIPO_DENUNCIANTE = "TIPO DENUNCIANTE";

		public static final String CATALOGO_ESTADO_DENUNCIA = "ESTADO DE LA DENUNCIA";
	}

	/**
	 * Afirmacion si o no
	 *
	 */
	public static final class Afirmacion {

		protected Afirmacion() {

		}

		public static final String S = "S";
		public static final String N = "N";

	}


	public static final class Roles {

		protected Roles() {

		}

		public static final Integer ID_ROL_ADMINISTRADOR = 1;
		public static final Integer ID_ROL_MESA_DE_PARTES = 2;

		public static final Integer ID_ROL_INVESTIGADOR = 3;

		public static final String ROL_ADMINISTRADOR = "ADMINISTRADOR";
		public static final String ROL_MESA_DE_PARTES = "MESA DE PARTES";

		public static final String ROL_INVESTIGADOR = "AUXILIAR INVESTIGADOR";

	}


	public static final class Fiscalias {

		protected Fiscalias() {

		}
		public static final Integer ID_FISCALIA_13 = 39;

		public static final Integer ID_FISCALIA_14 = 40;

	}
}
