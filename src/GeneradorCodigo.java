import java.util.Stack;
import java.util.Vector;

import javax.accessibility.AccessibleIcon;



public class GeneradorCodigo 
{
	
	ExpresionesRegulares er=new ExpresionesRegulares();
	String ordenprioridad[][]= 
		{ {")", "4"	},
		  {"(", "4"	},
		  {"^", "3"	},
		  {"*", "2"	},
		  {"/", "2"	},
		  {"+", "1"	},
		  {"-", "1"	},
		};
	
	Vector variables=new Vector<>(), pasos=new Vector<>(), omitir=new Vector<>();
	String procesos[]=new String [4]; //Separar cada uno de los procesos que tiene acci�n
	int inicio;
	boolean condicion=false, omitir_else=false;
	String datoactual="", nomaccion="";
	
	
	public void Ejecutar(Vector archoriginal, Vector archdatos, String TVariables[][], String TCondiciones[][], Vector secuencias, Interfaz Int)
	{
		//Int.imprimirConsola("Generador de C�digo");
		for (inicio = 0; inicio < secuencias.size(); inicio++) 
		{
			String accion=secuencias.get(inicio).toString().trim();
			procesos[0]="";
			procesos[1]="";
			procesos[2]="";
			procesos[3]="";
			String lexema="";
			condicion=false;
			int x=0;
			Agregar(accion,x,lexema,TVariables);
			//Comparaciones de secuencias
			if(accion.startsWith("DeclaracionVariables"))
			{
				variables.add(procesos[1]);
			}
			if(accion.startsWith("Etq"))
			{
				//Etiquetas: IF, Else, Do, While, Imprimir
				Etiquetas(Int);
			}
			if(accion.startsWith("Comparacion"))
			{
				String v1=BuscaValorVariables(TVariables, procesos[2]);
				String v2=BuscaValorVariables(TVariables, procesos[3]);
				condicion=resComparaciones(procesos[1], v1, v2);
				if(condicion)
				{
					//Buscaremos si es un if o un while
					int jaja=pasos.size()-1;
					String xD=pasos.elementAt(jaja).toString();
					if(xD.startsWith("etq if"))
					{
						datoactual="if";
						omitir.add("Etq else "+nomaccion);
					}
					if(xD.startsWith("etq while"))
					{
						//Se regresar� al Do 
						String buscaproceso="Etq DO "+nomaccion;
						pasos=EliminaPasos(pasos, buscaproceso);
						inicio=BuscaSecuencia(secuencias, buscaproceso);
					}
				}
				else
				{
					int jaja=pasos.size()-1;
					String xD=pasos.elementAt(jaja).toString();
					//No se cumpli� la condici�n, igual buscaremos si es if o while
					if(xD.startsWith("etq if"))
					{
						datoactual="else";
						String buscaproceso="Etq ELSE "+nomaccion;
						inicio=BuscaSecuencia(secuencias, buscaproceso);
						pasos.add("etq else "+procesos[2]);
					}
					if(xD.startsWith("etq while"))
					{
						//Termina el ciclo DoWhile
						pasos.removeElementAt(pasos.size()-1);
						pasos=EliminaPasos(pasos, "Etq DO "+nomaccion);
					}
					
				}
			}
			if(accion.startsWith("Asignacion"))
			{
				for (int i = 0; i < variables.size(); i++) {
					if(variables.get(i).equals(procesos[1]))
					{//La variable si est� declarada
						AnalizadorSemantico an=new AnalizadorSemantico(x, i, Int);
						
						for (int j = 0; j < TVariables.length; j++) 
						{
							if(procesos[1].equals(TVariables[j][2]))
							{
								if(an.ValidarVariables(TVariables[j][1], procesos[2]))
								{	//No hay necesidad de buscar valores
									TVariables[j][3]=EvaluacionPosfija(procesos[2], TVariables[j][1]);
								}
								else
								{  //Tenemos variables
									AsignarVariables(TVariables, j);
								}
								break;
							}
						}
						break;
					}
				}
			}
			if(accion.startsWith("BEGIN"))
			{
				//Terminar� hasta que encuentre un end
				if(omitir_else)
				{
					omitir_else=false;
					String reatziona="END "+procesos[1];
					inicio=BuscaSecuencia(secuencias, reatziona);
				}
				else
				{
					pasos.add("begin "+procesos[1]);
					nomaccion=procesos[1];
				}
				
			}
			if(accion.startsWith("END"))
			{
				//Eliminar� todo lo que tenga entre el begin y el end
				pasos=EliminaPasos(pasos, "etq begin "+procesos[1]);
				nomaccion=procesos[1];
			}
		}
	}
	
	public void Agregar(String accion, int x, String lexema, String[][] TVariables)
	{
		
		for (int j = 0; j < accion.length(); j++) 
		{
			String caracter=accion.substring(j,(j+1));
			if(x==3)
			{
				if(procesos[1].equals("Imprimir"))
				{						
						lexema+=caracter;
				}
				else
				{
					if(caracter.equals("\\s") || caracter.equals(" ") || caracter.length()==0)
					{
						procesos[x]=lexema;
						x++;
						lexema="";
					}
					else
						lexema+=caracter;
				}
			}
			else
			{
				if(caracter.equals("\\s") || caracter.equals(" ") || caracter.length()==0)
				{
					procesos[x]=lexema;
					x++;
					lexema="";
				}
				else
					lexema+=caracter;
			}
			
		}
		if(lexema.length()!=0)
			procesos[x]=lexema;
		if(procesos[1].equals("Imprimir"))
		{
			if(procesos[2].equals("mensaje"))
			{
				procesos[3]=lexema;
			}
			if(procesos[2].equals("id"))
			{
				procesos[3]=BuscaValorVariables(TVariables, procesos[3]);
			}
		}
	}
	
	public boolean resComparaciones(String simbolo, String v1, String v2)
	{
		String tipodato="", vstring1=null, vstring2=null;
		int varint1=0, varint2=0;
		double vardou1=0, vardou2=0;
		char varchar1=' ', varchar2=' ';
		
		boolean datosiguales=false;
		if(er.isEntero(v1) && er.isEntero(v2))
		{
			datosiguales=true;
			tipodato="int";
			varint1=Integer.parseInt(v1);
			varint2=Integer.parseInt(v2);
		}
		if(er.isDecimal(v1) && er.isDecimal(v2))
		{
			tipodato="double";
			datosiguales=true;
			vardou1=Double.parseDouble(v1);
			vardou2=Double.parseDouble(v2);
		}
		if(er.isCaracter(v1) && er.isCaracter(v2))
		{
			datosiguales=true;
			tipodato="char";
			varchar1=v1.charAt(0);
			varchar2=v2.charAt(0);
		}
		if(er.isCadena(v1) && er.isCadena(v2))
		{
			datosiguales=true;
			tipodato="String";
			vstring1=v1;
			vstring2=v2;
		}
		if(datosiguales)
		{
			//Variables tipo entero
			if(tipodato.equals("int"))
			{
				if(simbolo.equals("=="))
				{
					if(varint1==varint2)
					{
						return true;
					}
				}
				if(simbolo.equals("<="))
				{
					if(varint1<=varint2)
					{
						return true;
					}
				}
				if(simbolo.equals("<"))
				{
					if(varint1<varint2)
					{
						return true;
					}
				}
				if(simbolo.equals(">="))
				{
					if(varint1>=varint2)
					{
						return true;
					}
				}
				if(simbolo.equals(">"))
				{
					if(varint1>varint2)
					{
						return true;
					}
				}
				return false;
			}
			//Variables decimales
			if(tipodato.equals("double"))
			{
				if(simbolo.equals("=="))
				{
					if(varint1==vardou2)
					{
						return true;
					}
				}
				if(simbolo.equals("<="))
				{
					if(vardou1<=vardou2)
					{
						return true;
					}
				}
				if(simbolo.equals("<"))
				{
					if(vardou1<vardou2)
					{
						return true;
					}
				}
				if(simbolo.equals(">="))
				{
					if(vardou1>=vardou2)
					{
						return true;
					}
				}
				if(simbolo.equals(">"))
				{
					if(vardou1>vardou2)
					{
						return true;
					}
				}
				return false;
			}
			//Caracteres
			if(tipodato.equals("char"))
			{
				if(simbolo.equals("=="))
				{
					if(varchar1==varchar2)
					{
						return true;
					}
				}
				else
				{
					System.out.println("Las variables char no pueden son valores num�ricos, as� que solo se pueden comparar con el s�mbolo ==");
				}
			}
			//String
			if(tipodato.equals("String"))
			{
				if(simbolo.equals("=="))
				{
					if(vstring1.equals(vstring2))
					{
						return true;
					}
				}
				else
				{
					System.out.println("Las variables String no pueden son valores num�ricos, as� que solo se pueden comparar con el s�mbolo ==");
				}
			}
			return false;
		}
		else
		{
			System.out.println("Las dos variables no coinciden con el mismo tipo de datos...");
			return false;
		}
			
	}
	
	
	
	public void Etiquetas(Interfaz Int)
	{
		if(procesos[1].equals("Imprimir"))
		{
			Int.imprimirConsola(procesos[3]+"\n");
		}
		if(procesos[1].equals("IF"))
		{
			pasos.add("etq if "+procesos[2]);
			nomaccion=procesos[2];
		}
		if(procesos[1].equals("ELSE"))
		{
			int jaja=omitir.size()-1;
			String xD=omitir.elementAt(jaja).toString();
			String xV="Etq else "+procesos[2];
			if(xD.equals(xV))
			{
				omitir_else=true;
				omitir.removeElementAt(jaja);
			}
			else
			{
				pasos.add(xV);
				datoactual="else";
				nomaccion=procesos[2];
			}
			
		}
		if(procesos[1].equals("DO"))
		{
			datoactual="do";
			pasos.add("etq do "+procesos[2]);
			nomaccion=procesos[2];
		}
		if(procesos[1].equals("WHILE"))
		{
			if(datoactual.equals("do"))
			{
				pasos.add("etq while "+procesos[2]);
				nomaccion=procesos[2];
			}
			
		}
	}
	
	public void AsignarVariables(String TVariables[][], int j)
	{
		String jaja="", juntando="";
		char vecky[]=procesos[2].toCharArray();
		for (int j2 = 0; j2 < vecky.length; j2++) 
		{
			String caracter=vecky[j2]+"";
			if(er.isCaracter(caracter) || er.isDigito(caracter))
			{
				juntando+=caracter;
			}
			if(er.isOperadorMatematico(caracter))
			{
				if(er.isIdentificador(juntando))
				{
					juntando=BuscaValorVariables(TVariables, juntando);
					jaja+=juntando;
					jaja+=caracter;
					juntando="";
				}
				else
				{
					jaja+=juntando;
					jaja+=caracter;
					juntando="";
				}
			}
		}
		if(!juntando.isEmpty())
		{
			jaja+=juntando;
		}
		TVariables[j][3]=EvaluacionPosfija(jaja, TVariables[j][1]);
	}
	
	public int BuscaSecuencia(Vector secuencias, String accion)
	{
		for (int i = 0; i < secuencias.size(); i++) 
		{
			accion=accion.toUpperCase();
			String res=secuencias.get(i).toString();
			res=res.toUpperCase();
			if(accion.equals(res))
			{
				return i;
			}
		}
		return -1;
	}
	
	public Vector EliminaPasos(Vector pasos, String secuencia)
	{
		Vector p=new Vector<>();
		for (int i = 0; i < pasos.size(); i++) {
			secuencia=secuencia.toLowerCase();
			String ps=pasos.elementAt(i).toString();
			if(ps.equals(secuencia))
			{
				p.add(pasos.elementAt(i));
				break;
			}
			p.add(pasos.elementAt(i));
		}
		return pasos=p;
	}
	
	public String BuscaValorVariables(String TVariables[][], String variable)
	{
		for (int j = 0; j < TVariables.length; j++) 
		{
			System.out.println(TVariables[j][2]+" xD "+variable);
			if(variable.equals(TVariables[j][2]))
			{
				return TVariables[j][3];
			}
		}
		return "";
	}
	
	public String EvaluacionPosfija(String expresion, String tipodato)
	{
		Vector infija=new Vector<>(), postfija=new Vector<>(), PilaOperadores=new Vector<>();
		
		String palabra="";
		if(tipodato.equals("int") || tipodato.equals("double"))
		{
			for (int i = 0; i < expresion.length(); i++)
			{
				String caracter=expresion.substring(i,i+1).trim();
				if(!caracter.equals("") || !caracter.equals(" "))
				{
					if(er.isDigito(caracter) || er.isCaracter(caracter))
					{
						palabra=palabra+caracter;
					}
					else
					{
						if(er.isPunto(caracter))
						{
							palabra+=caracter;
						}
						else
						{
							for (int j = 0; j < ordenprioridad.length; j++) {
								if(caracter.equals(ordenprioridad[j][0]))
								{
									if(palabra.isEmpty())
									{
										infija.add(caracter);
									}
									else
									{
										infija.add(palabra);
										infija.add(caracter);
									}
										
									palabra="";
								}
							}
							
						}
					}
				}
			}
			if(!palabra.isEmpty())
				infija.add(palabra);
			
			//Ya tenemos la cadena infija
			int posparen[]=new int [infija.size()], posparentesis=-1;
			for (int i = 0; i < infija.size(); i++) 
			{
				String caracter=infija.get(i).toString();
				if(caracter.equals("+") || caracter.equals("-") || caracter.equals("*") || caracter.equals("/")
						|| caracter.equals("^") || caracter.equals("(") || caracter.equals(")"))
				{
					if(caracter.equals("("))
					{
						posparentesis++;
						PilaOperadores.add("(");
						posparen[posparentesis]=i;						
					}
					else
					{
						if(caracter.equals(")"))
						{
							int pos=0;
							for (int j =PilaOperadores.size()-1; j >= 0 ; j--) 
							{
								//Buscar el par�ntesis que abre
								if(PilaOperadores.get(j).toString().equals("("))
								{
									PilaOperadores.remove(j);
									posparentesis--;
									break;
								}
								else
								{
									postfija.add(PilaOperadores.get(j));
									PilaOperadores.remove(j);
								}
							}
						}
						else
						{ //Quitar operadores de mayor o igual prioridad
							String elemento=caracter;
							int jaja=0, jaja2=0;
							if(PilaOperadores.isEmpty())
							{
								PilaOperadores.add(caracter);
							}
							else
							{
								for (int j2 = 0; j2 < ordenprioridad.length; j2++) 
								{
									//Buscaremos que prioridad tiene el primer elemento
									if(elemento.equals(ordenprioridad[j2][0]))
									{
										jaja=Integer.parseInt(ordenprioridad[j2][1]);
										break;
									}
								}
								//Recorre la pila de operadores
								int p=0;
								try{
									p=posparen[posparentesis];
								}
								catch(ArrayIndexOutOfBoundsException e)
								{
									p=0;
								}
								for (int j = PilaOperadores.size()-1; j >=p ; j--) 
								{
									String elemento2=PilaOperadores.get(j).toString();
									for (int j2 = 0; j2 < ordenprioridad.length; j2++) 
									{
										//Buscaremos que prioridad tiene el segundo elemento
										if(elemento2.equals(ordenprioridad[j2][0]))
										{
											jaja2=Integer.parseInt(ordenprioridad[j2][1]);
											break;
										}
									}
									if(jaja<=jaja2 && elemento2!="(" && elemento2!=")")
									{//El segundo elemento es de mayor prioridad
										postfija.add(elemento2);
										PilaOperadores.remove(j);
									}
								}
								PilaOperadores.add(elemento);
							}
						}
						
					}
				}
				else
				{
					postfija.add(caracter);
				}
			}
			if(PilaOperadores.size()!=0)
			{
				for (int i = PilaOperadores.size()-1; i>= 0 ; i--)
				{
					postfija.add(PilaOperadores.get(i));
					PilaOperadores.remove(i);
				}
			}
			for (int i = 0; i < postfija.size(); i++) {
				System.out.print(postfija.get(i)+" ");
			}
			//Operacional
			return OperPosfija(postfija,tipodato);
			
			
		}
		return "";
	}
	private String OperPosfija(Vector vecky, String tipodato)
	{
		Stack pila=new Stack<>();
		int res=0, v1, v2, vecint[]=new int [vecky.size()]; 
		double resul=0, re1, re2, vecdob[]=new double[vecky.size()];
		int pospila=0;
		
		for (int i = 0; i < vecky.size(); i++) 
		{
			boolean elimina=false;
			String elemento=vecky.get(i).toString();
			switch(elemento)
			{
			  case "":
			  case " ":
				  break;
			  case "+":
				  if(tipodato.equals("int"))
				  {
					  v1=vecint[pospila-2];
					  v2=vecint[pospila-1];
					  res=v1+v2;
				  }
				  if(tipodato.equals("double"))
				  {
					  re1=vecdob[pospila-2];
					  re2=vecdob[pospila-1];
					  resul=re1+re2;
				  }
				  elimina=true;
				  break;
			  case "-":
				  if(tipodato.equals("int"))
				  {
					  v1=vecint[pospila-2];
					  v2=vecint[pospila-1];
					  res=v1-v2;
				  }
				  if(tipodato.equals("double"))
				  {
					  re1=vecdob[pospila-2];
					  re2=vecdob[pospila-1];
					  resul=re1-re2;
				  }
				  elimina=true;
				  break;
			  case "*":
				  if(tipodato.equals("int"))
				  {
					  v1=vecint[pospila-2];
					  v2=vecint[pospila-1];
					  res=v1*v2;
				  }
				  if(tipodato.equals("double"))
				  {
					  re1=vecdob[pospila-2];
					  re2=vecdob[pospila-1];
					  resul=re1*re2;
				  }
				  elimina=true;
				  break;
			  case "/":
				  if(tipodato.equals("int"))
				  {
					  v1=vecint[pospila-2];
					  v2=vecint[pospila-1];
					  res=v1/v2;
				  }
				  if(tipodato.equals("double"))
				  {
					  re1=vecdob[pospila-2];
					  re2=vecdob[pospila-1];
					  resul=re1/re2;
				  }
				  elimina=true;
				  break;
			  case "^":
				  if(tipodato.equals("int"))
				  {
					  re1=vecint[pospila-2];
					  re2=vecint[pospila-1];
					  resul=Math.pow(re1, re2);
					  res=(int) resul;
				  }
				  if(tipodato.equals("double"))
				  {
					  re1=vecdob[pospila-2];
					  re2=vecdob[pospila-1];
					  resul=Math.pow(re1, re2);
				  }
				  
				  elimina=true;
				  break;
			  default:
				  if(tipodato.equals("int"))
				  {
					 vecint[pospila]=Integer.parseInt(elemento); 
				  }
				  if(tipodato.equals("double"))
				  {
					 vecdob[pospila]=Double.parseDouble(elemento); 
				  }
				  pila.add(elemento);
				  pospila++;
				  break;
			}
			if(elimina)
			{
				pospila=pospila-1;
				vecint[pospila]=0;
				vecdob[pospila]=0;
				pospila=pospila-1;
				vecint[pospila]=0;
				vecdob[pospila]=0;
				System.out.println(res);
				if(tipodato.equals("int"))
				{
					vecint[pospila]=res;
				}
				if(tipodato.equals("double"))
				{
					vecdob[pospila]=resul;
				}
				pospila++;
			}
			
		}
		if(tipodato.equals("int"))
		{
			return ""+vecint[0];
		}
		if(tipodato.equals("double"))
		{
			return ""+vecdob[0];
		}
		return "";
		
	}
	
}
