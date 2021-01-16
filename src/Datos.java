public class Datos
{
	int cantidadelementos=0, condiciones=0;
	String TAnalisis[][]=
	{ //Tabla de An�lisis
			{"      ", "id", "if", "do", "Syso", "int", "double", "char", "String", "begin", ",", ";", "else" , "==", "<", "<=", ">", ">=", "mensaje", "+", "end", "-", "num", "(", "*", "/", ".", "while", ")", "$"},
			{"Programa", "0", "0", "0", "0", "0", "0", "0", "0", "0", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Bloque", "1", "1", "1", "1", "1", "1", "1", "1", "1", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Variables", "3", "3", "3", "3", "2", "2", "2", "2", "3", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"V", "8", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"VarID", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "9", "10", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"VarT", "11", "11", "11", "11", "12", "12", "12", "12", "11", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Proposicion", "13", "14", "15", "16", "17", "17", "17", "17", "18", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Asignacion", "19", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"If", "ERROR",  "20", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"CadIf", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "22", "21", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "22", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "22", "22", "ERROR", "ERROR"},
			{"DoWhile", "ERROR", "ERROR", "23", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Condicion", "24", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"CadCondicion", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "25", "26", "27", "28", "29", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Imprimir", "ERROR", "ERROR", "ERROR", "30", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"CadImprimir", "32", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "31", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Z", "33", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "33", "33", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "34", "33", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "33", "33", "ERROR", "ERROR"},
			{"Lectura", "ERROR", "ERROR", "ERROR", "ERROR", "35", "35", "35", "35", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR" },
			{"CadLectura", "36", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"A", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "37", "38", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"TipoDato", "ERROR", "ERROR", "ERROR", "ERROR", "4", "5", "6", "7", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"BeginEnd", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "39", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"CadBegin", "40", "40", "40", "40", "40", "40", "40", "40", "40", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "41", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"CadEnd", "42", "42", "42", "42", "42", "42", "42", "42", "42", "ERROR", "42", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Expresion", "43", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "46", "46", "46", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "43", "46", "43", "43", "43", "ERROR", "ERROR", "46", "46", "46", "ERROR"},
			{"CadExp", "46", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "44", "ERROR", "45", "46", "46", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"CadExpTer", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "47", "47", "47", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "48", "47", "48", "ERROR", "ERROR", "ERROR", "ERROR", "47", "47", "47", "ERROR"},
			{"CadExpSim", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "49", "ERROR", "50", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Termino", "51", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "51", "51", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"CadTer", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "52", "52", "52", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "52", "52", "52", "ERROR", "ERROR", "53", "53", "52", "52", "52", "ERROR"},
			{"CadTerSim", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "54", "55", "ERROR", "ERROR", "ERROR", "ERROR"},
			{"Factor", "56", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "57", "58", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR"},
	};
	
	String P[][]={ //Procedimientos
	/*0 */			{"Programa", "Bloque", "."},
	/*1 */			{"Bloque", "Variables","Proposicion"},
	/*2 */			{"Variables", "TipoDato", "V"},
	/*3 */			{"Variables", "E"},
	/*4 */			{"TipoDato", "int"},
	/*5 */			{"TipoDato", "double"},
	/*6 */			{"TipoDato", "char"},
	/*7 */			{"TipoDato", "String"},
	/*8 */			{"V", "id", "VarID"},
	/*9 */			{"VarID", ",", "V"},
	/*10 */			{"VarID", ";", "VarT"},
	/*11 */			{"VarT", "E"},
	/*12 */			{"VarT", "Variables"},
	/*13 */			{"Proposicion", "Asignacion"},
	/*14 */			{"Proposicion", "If"},
	/*15 */			{"Proposicion", "DoWhile"},
	/*16 */			{"Proposicion", "Imprimir"},
	/*17 */			{"Proposicion", "Lectura"},
	/*18 */			{"Proposicion", "BeginEnd"},
	/*19 */			{"Asignacion", "id", "=", "Expresion", ";"},
	/*20 */			{"If", "if", "Condicion", "Proposicion", "CadIf"},
	/*21 */			{"CadIf", "else", "Proposicion"},
	/*22 */			{"CadIf", "E"},
	/*23 */			{"DoWhile", "do", "Proposicion", "while", "Condicion"},
	/*24 */			{"Condicion", "id", "CadCondicion", "id"},
	/*25 */			{"CadCondicion", "=="},
	/*26 */			{"CadCondicion", "<"},
	/*27 */			{"CadCondicion", "<="},
	/*28 */			{"CadCondicion", ">"},
	/*29 */			{"CadCondicion", ">="},
	/*30 */			{"Imprimir", "Syso", "CadImprimir"},
	/*31 */			{"CadImprimir", "mensaje", "Z"},
	/*32 */			{"CadImprimir", "id", "Z"},
	/*33 */			{"Z", "E"},
	/*34 */			{"Z", "+", "CadImprimir"},
	/*35 */			{"Lectura", "TipoDato", "CadLectura"},
	/*36 */			{"CadLectura", "Asignacion", "A"},
	/*37 */			{"A", ",", "CadLectura"},
	/*38 */			{"A", ";"},
	/*39 */			{"BeginEnd", "begin", "Proposicion", "CadBegin"},
	/*40 */			{"CadBegin", "CadEnd"},
	/*41 */			{"CadBegin", "end"},
	/*42 */			{"CadEnd", "Proposicion", "CadBegin"},
	/*43 */			{"Expresion", "CadExp", "Termino", "CadExpTer"},
	/*44 */			{"CadExp", "+"},
	/*45 */			{"CadExp", "-"},
	/*46 */			{"CadExp", "E"},
	/*47 */			{"CadExpTer", "E"},
	/*48 */			{"CadExpTer", "CadExpSim", "Termino", "CadExpTer"},
	/*49 */			{"CadExpSim", "+"},
	/*50 */			{"CadExpSim", "-"},
	/*51 */			{"Termino", "Factor", "CadTer"},
	/*52 */			{"CadTer", "E"},
	/*53 */			{"CadTer", "CadTerSim", "Factor", "CadTer"},
	/*54 */			{"CadTerSim", "*"},
	/*55 */			{"CadTerSim", "/"},
	/*56 */			{"Factor", "id"},
	/*57 */			{"Factor", "num"},
	/*58 */			{"Factor", "(", "Expresion", ")"}
	};
	
	String NoTerminales[]={"Programa", "Bloque", "Variables", "V", "VarID", "VarT", "Proposicion", "Asignacion"
			, "If", "CadIf", "DoWhile", "Condicion", "CadCondicion", "Imprimir", "CadImprimir", "Z", "Lectura", 
			"CadLectura", "A", "TipoDato", "BeginEnd", "CadBegin", "CadEnd", "Expresion", "CadExp", "CadExpTer"
			, "CadExpSim", "Termino", "CadTer", "CadTerSim", "Factor"};
	
	String Terminales[]={"id", "if", "do", "Syso", "int", "double", "char", "String", "begin", ",", ";", "else"
			, "==", "<", "<=", ">", ">=", "'", "+", "end", "-", "num", "(", "*", "/", ".", "while", ")","=", "$"};
	
	public static void main(String[] args) {
		Datos D=new Datos();
		
		for (int i = 0; i < D.TAnalisis.length; i++) {
			int datos=0;
			for (int j = 0; j < D.TAnalisis[i].length; j++) {
				System.out.print(D.TAnalisis[i][j]+"\t");
				datos++;
			}
			System.out.println(datos);
		}
	}
}
