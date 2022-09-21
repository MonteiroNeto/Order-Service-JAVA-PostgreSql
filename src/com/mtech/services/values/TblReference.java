package com.mtech.services.values;

public class TblReference {

	public class TblUser {
		public static final String TBL_NAME = "tbusuarios";
		public static final String COLUMN_ID_USER = "iduser";
		public static final String COLUMN_NAME = "usuario";
		public static final String COLUMN_PHONE = "fone";
		public static final String COLUMN_LOGIN = "login";
		public static final String COLUMN_PASS = "senha";
		public static final String COLUMN_PERFIL = "perfil";

	}

	public class TblOs {
		public static final String TBL_NAME = "tbos";
		public static final String COLUMN_ID_OS = "idos";
		public static final String COLUMN_DATA_OS = "data_os";
		public static final String COLUMN_EQUIPAMENTO = "equipamento";
		public static final String COLUMN_DEFEITO = "defeito";
		public static final String COLUMN_SERVICO = "servico";
		public static final String COLUMN_TECNICO = "tecnico";
		public static final String COLUMN_VALOR = "valor";
		public static final String COLUMN_ID_CLIENTE = "idcli";
		public static final String COLUMN_STATUS = "status";
		public static final String COLUMN_TYPE_SERVICE = "tipo_servico";

	}

	public class TblClient {
		public static final String TBL_NAME = "tbclientes";
		public static final String COLUMN_ID_CLI = "idcli";
		public static final String COLUMN_NAME_CLI = "nomecli";
		public static final String COLUMN_ADDRES_CLI = "endcli";
		public static final String COLUMN_PHONE_CLI = "fonecli";
		public static final String COLUMN_EMAIL_CLI = "emailcli";

	}
}
