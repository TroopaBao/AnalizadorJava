import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class Lector 
{
	FileInputStream entrada;
	FileOutputStream salida;
	//File ArchJonBich;
	ExpresionesRegulares expresion=new ExpresionesRegulares();
	Vector <String> archivooriginal=new Vector(), archivodatos=new Vector();
	int elementos=0, condiciones=0;
	Datos D=new Datos();
	boolean tipodato=false;
	
	public String AbrirTexto(File archivo)
	{
		String contenido="";
		try
		{
			entrada=new FileInputStream(archivo);
			int num;
			while((num=entrada.read())!=-1)
			{
				char caracter=(char)num;
				contenido+=caracter;
			}
			entrada.close();
		}
		catch(IOException e)
		{
			System.out.println("Error de lectura");
		}
		return contenido;
	}
	public void GuardarArchivo(File archivo, String contenido)
	{
		try
		{
			
			salida=new FileOutputStream(archivo);
			
			byte[] b=contenido.getBytes();
			salida.write(b);
			salida.close();
		}
		catch(IOException e)
		{
			
		}
		
	}
	
	
	public void Lectura(File archivo, String texto, Interfaz Int)
	{

		try
		{
			elementos=0;
			String cad="", lexema="";
			String elementoanterior="";
			int renglon=0, columna=0;
			boolean simbolo=false, mensaje=false;
			FileReader lec=new FileReader(archivo);
			BufferedReader buffer=new BufferedReader(lec);
			do
			{
				cad=buffer.readLine(); //Lee la l�nea y hace un salto de l�nea
				//Int.imprimirConsola("Rengl�n "+renglon+": "+cad);
				//Int.imprimirConsola("Rengl�n \t  Columna \t  Token  \t  Tipo de Dato");
				if(cad!=null) 
				{
					cad=cad.trim();
					//El rengl�n tiene elementos
					for (int i = 0; i < cad.length(); i++) 
					{
						String caracter=cad.charAt(i)+"";
						//Recorrer cada elemento del rengl�n
						if(mensaje) 
						{ //Todo lo que escriba ser� mensaje, hasta que escriba un par�ntesis
							if(caracter.equals("'"))
							{
								//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Mensaje");
								archivooriginal.add(lexema);
								archivodatos.add("mensaje");
								mensaje=false;
								lexema="";
								columna=i+1;
							}
							else
							{
								lexema+=caracter;
							}
						}
						else
						{
							if(!caracter.equals("=") )
							{
								if(elementoanterior.equals("<"))
								{
									//Int.imprimirConsola(renglon+"\t"+i+"\t"+elementoanterior+"\t Menor");
									archivooriginal.add(elementoanterior);
									archivodatos.add("<");
									condiciones++;
								}
								if(elementoanterior.equals(">"))
								{
									//Int.imprimirConsola(renglon+"\t"+i+"\t"+elementoanterior+"\t Mayor");
									archivooriginal.add(elementoanterior);
									archivodatos.add(">");
									condiciones++;
								}
								if(elementoanterior.equals("="))
								{
									//Int.imprimirConsola(renglon+"\t"+i+"\t"+elementoanterior+"\t Igual (asignaci�n) ");
									archivooriginal.add(elementoanterior);
									archivodatos.add("=");
								}
								elementoanterior="";
							}
							if(expresion.isEspacio(caracter) || i==cad.length()-1
									|| expresion.isCaracterPuntuacion(caracter) ||
							expresion.isOperadorMatematico(caracter) ||
							expresion.isOperadorLogico(caracter) || expresion.isDelimitador(caracter)
							 || expresion.isSeparador(caracter) || expresion.isPunto(caracter))
							{
								
								if(expresion.isEntero(lexema) && expresion.isPunto(caracter))
								{ 
									//En caso de que el punto que tengamos sea doble
									lexema+=caracter;
								}
								else
								{
									if(i==cad.length()-1)
									{
										if(expresion.isCaracter(caracter)|| expresion.isDigito(caracter)
											|| expresion.isGuionBajo(caracter))
										lexema+=caracter;
										else
											simbolo=true;
									}
									System.out.println(lexema+": Lexema");	
									//Encontr� un separador
									if(expresion.isPalabraReservada(lexema))
									{
										if(lexema.equals("int"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t TD entero");
											archivooriginal.add(lexema);
											archivodatos.add("int");
											tipodato=true;
										}
										if(lexema.equals("double"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t TD Doble");
											archivooriginal.add(lexema);
											archivodatos.add("double");
											tipodato=true;
										}
										if(lexema.equals("char"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t TD Caracter");
											archivooriginal.add(lexema);
											archivodatos.add("char");
											tipodato=true;
										}
										if(lexema.equals("String"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t TD Cadena");
											archivooriginal.add(lexema);
											archivodatos.add("String");
											tipodato=true;
										}
										if(lexema.equals("if"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Condici�n IF");
											archivooriginal.add(lexema);
											archivodatos.add("if");
										}
										if(lexema.equals("else"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Condici�n ELSE");
											archivooriginal.add(lexema);
											archivodatos.add("else");
										}
										if(lexema.equals("begin"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Abertura");
											archivooriginal.add(lexema);
											archivodatos.add("begin");
										}
										if(lexema.equals("end"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Cerradura");
											archivooriginal.add(lexema);
											archivodatos.add("end");
										}
										if(lexema.equals("do"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Ciclo DO");
											archivooriginal.add(lexema);
											archivodatos.add("do");
										}
										if(lexema.equals("while"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Ciclo WHILE");
											archivooriginal.add(lexema);
											archivodatos.add("while");
										}
										if(lexema.equalsIgnoreCase("Syso"))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t M�todo Imprimir");
											archivooriginal.add(lexema);
											archivodatos.add("Syso");
										}
									}
									else
									{
										if(expresion.isIdentificador(lexema))
										{
											//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Identificador");
											archivooriginal.add(lexema);
											archivodatos.add("id");
											if(tipodato)   
												elementos++;
										}
										else
										{
											if(expresion.isIdentificadorNoValido(lexema))
											{
												//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Identificador no v�lido");
												archivooriginal.add(lexema);
												archivodatos.add("IDNoV�lido");
											}
										}
									}
									if(expresion.isEntero(lexema))
									{
										//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t N�mero entero");
										archivooriginal.add(lexema);
										archivodatos.add("num");
									}
										
									if(expresion.isDecimal(lexema))
									{
										//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t N�mero decimal");
										archivooriginal.add(lexema);
										archivodatos.add("num");
									}
										
									if(expresion.isFloat(lexema))
									{
										//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t N�mero Flotante");
										archivooriginal.add(lexema);
										archivodatos.add("num");
									}
									if(expresion.isCadena(lexema))
									{
										//Int.imprimirConsola(renglon+"\t"+columna+"\t"+lexema+"\t Cadena");
										archivooriginal.add(lexema);
										archivodatos.add("num");
									}
									while(simbolo || i<cad.length()-1)
									{
										if(expresion.isPunto(caracter))
										{
											//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Punto");
											archivooriginal.add(caracter);
											archivodatos.add(".");
										}
										if(expresion.isEspacio(caracter))
										{
											//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Espacio en blanco");
											//archivooriginal.add(caracter);
											//archivodatos.add("Espacio");
											break;
										}
										if(expresion.isSeparador(caracter))
										{
											if(caracter.equals(";"))
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Punto y Coma");
												archivooriginal.add(caracter);
												archivodatos.add(";");
												tipodato=false;
											}
											if(caracter.equals("("))
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Par�ntesis que abre");
												archivooriginal.add(caracter);
												archivodatos.add("(");
											}
											if(caracter.equals(")"))
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Par�ntesis que cierra");
												archivooriginal.add(caracter);
												archivodatos.add(")");
											}
											break;
										}
										if(expresion.isCaracterPuntuacion(caracter))
										{
											if(caracter.equals("="))
											{
												if(elementoanterior.length()!=0) 
												{
													if(elementoanterior.equals("<"))
													{
														//Int.imprimirConsola(renglon+"\t"+i+"\t"+"<="+"\t Menor o igual");
														archivooriginal.add("<=");
														archivodatos.add("<=");
														condiciones++;
													}
													if(elementoanterior.equals(">"))
													{
														//Int.imprimirConsola(renglon+"\t"+i+"\t"+">="+"\t Mayor o igual");
														archivooriginal.add(">=");
														archivodatos.add(">=");
														condiciones++;
													}
													if(elementoanterior.equals("="))
													{
														//Int.imprimirConsola(renglon+"\t"+i+"\t"+"=="+"\t Igual (Comparaci�n) ");
														archivooriginal.add("==");
														archivodatos.add("==");
														condiciones++;
													}
													elementoanterior="";
												}
												else
												{
													elementoanterior=caracter; 
												}
												
												
											}
											else
											{
												if(caracter.equals("'"))
												{
													mensaje=true;
												}
												else
												{
													//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Caracter de puntuaci�n");
													archivooriginal.add(caracter);
													archivodatos.add("CaracterPuntuacion");
												}
												
											}
											break;
										}
										if(expresion.isOperadorLogico(caracter))
										{
											//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Operador L�gico");
											archivooriginal.add(lexema);
											archivodatos.add("OperadorLogico");
											break;
										}
										if(expresion.isOperadorMatematico(caracter))
										{
											if(caracter.equals("+"))
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Suma");
												archivooriginal.add(caracter);
												archivodatos.add("+");
											}
											if(caracter.equals("-"))
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Resta");
												archivooriginal.add(caracter);
												archivodatos.add("-");
											}
											if(caracter.equals("*"))
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Multiplicaci�n");
												archivooriginal.add(caracter);
												archivodatos.add("*");
											}
											if(caracter.equals("/"))
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Divisi�n");
												archivooriginal.add(caracter);
												archivodatos.add("/");
											}
											break;
										}
										if(expresion.isDelimitador(caracter))
										{

											if(caracter.equals("<") || caracter.equals(">") || caracter.equals(","))
											{
												if(caracter.equals("<"))
												{
													elementoanterior="<";
												}
												if(caracter.equals(">"))
												{
													elementoanterior=">";
												}
												if(caracter.equals(","))
												{
													//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Coma");
													archivooriginal.add(caracter);
													archivodatos.add(",");
												}
											}
											else
											{
												//Int.imprimirConsola(renglon+"\t"+i+"\t"+caracter+"\t Delimitador");
												archivooriginal.add(caracter);
												archivodatos.add("Delimitador");
											}
											break;
										}
										simbolo=false;
									}
									lexema="";
									columna=i+1;
								
								}
							}
							else
							{
								//No contiene ; ni espacio, {, ( [
								lexema+=caracter;   
							}	
						}
					}
			
				}
				renglon++;
				columna=0;
			}
			while(cad!=null);
		}
		catch(IOException e)
		{
			
		}
		System.out.println();
		
		String carmen="";
		for (int j = 0; j < archivooriginal.size(); j++) {
			System.out.println("Elemento: "+j + "\n");
			carmen+="Elemento: "+j+"	\n";
			System.out.println("Original: "+archivooriginal.get(j));
			carmen+="Original: "+archivooriginal.get(j)+"	\n";
			System.out.println("Tipo: "+archivodatos.get(j));
			carmen+="Tipo: "+archivodatos.get(j)+"	\n";
			System.out.println();
			carmen+="	\n";
		}
		
		String rutta="C:\\Users\\mrviv\\Desktop\\holi.txt";
		 try {
			 //String ruta = "/home/mario/archivo.c";
		        File archivoid = new File(rutta);
		        BufferedWriter bw;
		        if(archivoid.exists()) {
		            bw = new BufferedWriter(new FileWriter(archivoid));
		            bw.write(carmen);
		        } else {
		            bw = new BufferedWriter(new FileWriter(archivoid));
		            bw.write(carmen);
		        }
		        bw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		D.cantidadelementos=elementos;
		D.condiciones=condiciones;
		System.out.println("Elementos: "+D.cantidadelementos+". Condiciones: "+D.condiciones);
		//Int.imprimirConsola("Analizador L�xico");
		AnalisisSintactico an=new AnalisisSintactico(archivooriginal, archivodatos, D.cantidadelementos, D.condiciones, Int);
		an.Analizar(Int);
	}
}
