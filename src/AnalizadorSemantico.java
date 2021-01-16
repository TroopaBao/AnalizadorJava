import java.util.Vector;

public class AnalizadorSemantico 
{
	int v=0, condiciones=0;
	static Interfaz I;
	public AnalizadorSemantico(int var, int cancond, Interfaz Inter)
	{
		v=var;
		condiciones=cancond;
		I=Inter;
	}
	String tipodato[]={"int","double", "char", "String"}, datoactual=""; //El datoactual es para la etiqueta que est� en ese momento
	ExpresionesRegulares er=new ExpresionesRegulares();
	Datos D=new Datos();
	boolean error=false, igual=false, dentroelse=false;
	//Nombres de variables
	Vector <String> secuencias=new Vector<>(), vif=new Vector<>(), vdo=new Vector<>(), vbe=new Vector<>();
	int variables=v, nivelif=0, niveldowhile=0,nivelbeginend=0, variable=1, posvariableactual=0,poscondicion=0; 
	
	int nivelifmax=-1, niveldowhilemax=-1, nivelbeginendmax=-1;
	
	String tipodatoactual="", nomvaractual="", mensaje="", condicionactual="", expresion="", mensajesyso=""; 
	String datos[]={"Posici�n", "TipoDato", "NombreVariable", "Valor", "NivelIf", "DentroElse(True o false)", "NivelDoWhile", "NivelBeginEnd"};
	String TablaVariables[][], TablaCondiciones[][];
	
	public void Analizar(Vector archoriginal, Vector archivodatos, Interfaz Int)
	{
		//Int.imprimirConsola("Analizador Sem�ntico");
		String impmensj="";
		variables=v+1;
		System.out.println(v);
		TablaVariables=new String [variables][datos.length];
		TablaCondiciones=new String [condiciones][4];
		System.out.println("Datos: "+datos.length+", Variables: "+v);
		
		if(!archoriginal.isEmpty())
		{
			for (int i = 0; i < datos.length; i++) {
				TablaVariables[0][i]=datos[i];
			}
			//El texto contiene elementos
			for (int i = 0; i < archivodatos.size(); i++) 
			{
				//Leer� los datos y crear� la tabla de variables
				String etiqueta=archivodatos.get(i).toString();
				System.out.print(etiqueta+" ");
				if(etiqueta.equals("int"))
				{
					tipodatoactual="int";
				}
				if(etiqueta.equals("double"))
				{
					tipodatoactual="double";
				}
				if(etiqueta.equals("char"))
				{
					tipodatoactual="char";
				}
				if(etiqueta.equals("String"))
				{
					tipodatoactual="String";
				}
				if(etiqueta.equals("id"))
				{
					String nant=nomvaractual;
					nomvaractual=archoriginal.get(i).toString();
					
					if(datoactual.equals("Syso"))
					{
						secuencias.add("Etq Imprimir id "+nomvaractual);
					}
					else
					{
						if(!condicionactual.isEmpty()) //Si ese id se utilizar� para condiciones
						{
							//Tiene elementos
							TablaCondiciones[poscondicion][0]=condicionactual;
							TablaCondiciones[poscondicion][1]=nant;
							TablaCondiciones[poscondicion][2]=nomvaractual;
							
							secuencias.add("Comparacion "+condicionactual+" "+nant+" "+nomvaractual);
							condicionactual="";
							poscondicion++;
						}
						else
						{	
							if(tipodatoactual.equals("")) 
							{
								//Asignaci�n
								boolean encontrado=false;
								for (int j = 0; j < TablaVariables.length; j++) 
								{
									if(nomvaractual.equals(TablaVariables[j][2]))
									{
										//Busca la posici�n de la variable en la tabla
										posvariableactual=j;
										encontrado=true;
										break;
									}
								}
								if(!encontrado)
								{
									mensaje="Variable no existe: "+nomvaractual;
									error=true;
									break;
								}
								if(igual)
								{
									expresion+=nomvaractual;
									//expresion+=TablaVariables[posvariableactual][3];
								}
							}
							else
							{
								if(!tipodatoactual.isEmpty())
								{
									//Declaraci�n de Variables
									for (int j = 0; j < TablaVariables[2].length; j++) {
										if(nomvaractual.equals(TablaVariables[2][j]))
										{
											error=true;
											mensaje="Ya existe una variable con ese nombre: "+nomvaractual;
											break;
										}
									}
									if(error)
										break;
									TablaVariables[variable][0]=variable+"";
									TablaVariables[variable][1]=tipodatoactual;
									TablaVariables[variable][2]=nomvaractual;
									TablaVariables[variable][3]="";
									TablaVariables[variable][4]=nivelif+"";
									TablaVariables[variable][5]=dentroelse+"";
									TablaVariables[variable][6]=niveldowhile+"";
									TablaVariables[variable][7]=nivelbeginend+"";
								}
							}
						}
					}
				}
				if(etiqueta.equals("num"))
				{
					//Valor
					expresion+=archoriginal.get(i).toString();
				}
				if(etiqueta.equals("if"))
				{
					datoactual="if";
					nivelif++;
					if(nivelif<=nivelifmax)
					{
						//Ese n�mero de if ya lleg� a usarse
						nivelif=nivelifmax+1;
						nivelifmax=nivelif;
					}
					if(nivelifmax==-1 || nivelif>=nivelifmax )
					{
						nivelifmax=nivelif;
					}
					dentroelse=false;
					vif.add(nivelif+"");
					secuencias.add("Etq IF "+nivelif);
				}
				if(etiqueta.equals("else"))
				{
					datoactual="else";
					String x=vif.elementAt(vif.size()-1);
					dentroelse=true;
					secuencias.add("Etq ELSE "+x);
					vif.removeElementAt(vif.size()-1);
				}
				if(etiqueta.equals("do"))
				{
					niveldowhile++;
					datoactual="do";
					if(niveldowhile<=niveldowhilemax)
					{
						//Ese n�mero de dowhile ya lleg� a usarse
						niveldowhile=niveldowhilemax+1;
						niveldowhilemax=niveldowhile;
					}
					if(niveldowhilemax==-1 || niveldowhile>=niveldowhilemax )
					{
						niveldowhilemax=niveldowhile;
					}
					vdo.add(niveldowhile+"");
					secuencias.add("Etq DO "+niveldowhile);
				}
				if(etiqueta.equals("while"))
				{
					datoactual="while";
					int xD=vdo.size()-1;
					String x=vdo.elementAt(xD);
					secuencias.add("Etq WHILE "+ x);
					vdo.removeElementAt(xD);
					niveldowhile--;
				}
				if(etiqueta.equals("Syso"))
				{
					//Imprimir
					datoactual="Syso";
				}
				if(etiqueta.equals("mensaje"))
				{
					impmensj=archoriginal.get(i).toString();
					secuencias.add("Etq Imprimir mensaje "+impmensj);
				}
				if(etiqueta.equals("begin"))
				{
					nivelbeginend++;
					if(nivelbeginend<=nivelbeginendmax)
					{
						//Ese n�mero de beginend ya lleg� a usarse
						nivelbeginend=nivelbeginendmax+1;
						nivelbeginendmax=nivelbeginend;
					}
					if(nivelbeginendmax==-1 || nivelbeginend>=nivelbeginendmax )
					{
						nivelbeginendmax=nivelbeginend;
					}
					vbe.add(nivelbeginend+"");
					secuencias.add("BEGIN "+nivelbeginend);
				}
				if(etiqueta.equals("end"))
				{
					String x=vbe.elementAt(vbe.size()-1);
					secuencias.add("END "+x);
					vbe.removeElementAt(vbe.size()-1);
					nivelbeginend--;
					try
					{
						if(!archivodatos.get(i+1).equals("else") && datoactual.equals("if"))
						{//Si no encuentra un else, se termin� el ciclo if
							nivelif--;
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						//T�rmino de archivo
					}
				}
				if(etiqueta.equals(";"))
				{
					if(!tipodatoactual.isEmpty() && !nomvaractual.isEmpty())
					{
						secuencias.add("DeclaracionVariables "+nomvaractual);
					}
					//Termino l�nea de c�digo
					if(!expresion.isEmpty())
					{
						for (int j = 0; j < TablaVariables[2].length; j++) {
							if(TablaVariables[j][2].equals(nomvaractual))
							{
								TablaVariables[j][3]=expresion;
								break;
							}
						}
						secuencias.add("Asignacion "+nomvaractual+" "+expresion);
						expresion="";
					}
					tipodatoactual="";
					variable++;
					nomvaractual="";
					igual=false;
				}
				if(etiqueta.equals(","))
				{
					//Siguiente id
					secuencias.add("DeclaracionVariables "+nomvaractual);
					variable++;
				}
				if(etiqueta.equals("="))
				{
					//Parte de asignaci�n
					igual=true;
				}
				if(etiqueta.equals("=="))
				{
					condicionactual="==";
				}
				if(etiqueta.equals("<"))
				{
					condicionactual="<";
				}
				if(etiqueta.equals("<="))
				{
					condicionactual="<=";
				}
				if(etiqueta.equals(">"))
				{
					condicionactual=">";
				}
				if(etiqueta.equals(">="))
				{
					condicionactual=">=";
				}
				if(etiqueta.equals("'"))
				{
					
				}
				//Los siguientes t�rminos se agregar�n a una expresi�n
				if(etiqueta.equals("+"))
				{
					if(igual)
					{
						expresion+="+";
					}
				}
				if(etiqueta.equals("-"))
				{
					expresion+="-";
				}
				if(etiqueta.equals("*"))
				{
					expresion+="*";
				}
				if(etiqueta.equals("/"))
				{
					expresion+="/";
				}
				if(etiqueta.equals("("))
				{
					expresion+="(";
				}
				if(etiqueta.equals(")"))
				{
					expresion+=")";
				}
				
			}
			if(error)
			{
				System.out.println(mensaje);
			}
			else
			{
				/*boolean valido=true, analiza;
				for (int i = 1; i < v; i++)
				{
						analiza=ValidarVariables(TablaVariables[i][1], TablaVariables[i][3]);
						if(!analiza)
						{
							valido=false;
							break;
						}
				}*/
				//if(valido)
				{
					System.out.println();
					GeneradorCodigo gc=new GeneradorCodigo();
					for (int i = 0; i < secuencias.size(); i++) {
						System.out.println(secuencias.get(i));
					}
					for (int i = 0; i < TablaCondiciones.length; i++) {
						for (int j = 0; j < TablaCondiciones[i].length; j++) {
							System.out.print(TablaCondiciones[i][j]+"\t");
						}
						System.out.println();
					}
					System.out.println();
					for (int i = 0; i < TablaVariables.length; i++) {
						for (int j = 0; j < TablaVariables[i].length; j++) {
							System.out.print(TablaVariables[i][j]+"\t");
						}
						System.out.println();
					}
					System.out.println();
					System.out.println("Ya chingaste :D");
					gc.Ejecutar(archoriginal, archivodatos, TablaVariables, TablaCondiciones, secuencias, Int);
				}
				
				/*else
				{
					System.out.println("Tipos de datos err�neos...");
				}*/
			}
		}
	}
	public boolean ValidarVariables(String tipodato, String valor)
	{
		try
		{
			switch(tipodato)
			{
				case "int":
					return ResultadoExpresion(tipodato,valor);
				case "double":
					return ResultadoExpresion(tipodato,valor);
				case "char":
					return er.isCaracter(valor);
				case "String":
					return er.isCadena(valor);
				default:
					return false;
			}
		}
		catch(NumberFormatException e)
		{
			return false;
		}
	}
	

	public boolean ResultadoExpresion(String tipodato, String valor)
	{
		boolean resultado=true;
		
		if(valor.length()!=0)
		{
			if(tipodato.equals("int") || tipodato=="double")
			{
				if(tipodato=="int")
				{
					for (int i = 0; i < valor.length(); i++) 
					{
						String x=valor.substring(i, i+1);
						if(er.isDigito(x)  || er.isOperadorMatematico(x) ||
								x.equals("(") || x.equals(")"))
						{
							resultado=true;
						}
						else
						{
							resultado=false;
							break;
						}
					}
				}
				if(tipodato=="double")
				{
					for (int i = 0; i < valor.length(); i++) 
					{
						String x=valor.substring(i, i+1);
						if(er.isDigito(x)  || er.isOperadorMatematico(x) ||
								x.equals("(") || x.equals(")") || er.isPunto(x))
						{
							resultado=true;
						}
						else
						{
							resultado=false;
							break;
						}
					}
					return true;
				}
				if(resultado)
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		return false;		
	}
	
}
