import java.util.Vector;

public class AnalisisSintactico {
	Vector <String>  archivoriginal=new Vector<>(),archivodatos=new Vector<>();
	Datos D=new Datos();
	int cv=0, cc=0;
	public AnalisisSintactico(Vector archor, Vector archdat, int cant, int cancond, Interfaz I)
	{
		archivoriginal=archor;
		archivodatos=archdat;
		cv=cant;
		cc=cancond;
	}

	
	public void Analizar(Interfaz Int)
	{
		//Int.imprimirConsola("Analizador Sint�ctico");
		Vector pila=new Vector<String>(); //Cadena analizadora o Pila
		int posprocedimientos=-1;
		for (int i = 0; i < archivodatos.size(); i++) {
			System.out.println("Cadena :v "+archivodatos.get(i));
		}
		System.out.println("Analiz SInta Elementos: "+cv);
		System.out.println("Analiz SInta Condiciones: "+cc);
		
		if(archivodatos.size()>0)
		{
			pila.addElement("$");
			pila.add("Programa");
			
			archivodatos.add("$");
			String carmen="", elemento="";
			int punterocad=0, punteropila=pila.size()-1, x=0, y=0, cad=archivodatos.size();  //x posici�n de terminales, y posici�n de NT
			boolean valido=true, terminado=false;
			while(valido)
			{
				for(int i=0; i<=cad; i++)
				{
					try
					{

						//Recorrer� la cadena a validar
						carmen=archivodatos.get(i).toString(); //Obtiene la palabra
						for (int j = 0; j < pila.size(); j++) {
							//Muestra la pila
								System.out.print(pila.get(j)+" ");
						}
						System.out.println();
						boolean siguiente=false;
						while(siguiente==false) {
							//Buscar� si el primer elemento de la pila es un terminal o no
							String pilacar=pila.get(punteropila).toString();
							
							if(Noterminal(pilacar, D.NoTerminales))
							{
								//Pilacar es no terminal
								for (int j = 0; j< D.TAnalisis[0].length; j++) {
									//Buscar la cadena en la tabla predictiva
									if(D.TAnalisis[0][j].equals(carmen))
									{
										x=j;
										break;
									}
									x=-1;
								}
								int jaja=0;
								while(true)
								{
									
									try
									{
										
										if(D.TAnalisis[jaja][0].equals(pilacar))
										{
											y=jaja;
											break;
										}
										else
										{
											jaja++;
										}
									}
									catch(ArrayIndexOutOfBoundsException e)
									{
										y=-1;
										break;
									}
								}
								if(y==-1 || x==-1)
								{
									valido=false;
									break;
								}
								else
								{
									elemento=D.TAnalisis[y][x];
								}
								if(elemento.equals("ERROR"))
								{
									valido=false;
									break;
								}
								posprocedimientos=Integer.parseInt(elemento);
								String agr[]=new String[D.P[posprocedimientos].length-1];
								
								for (int j = 1, m=0; j < D.P[posprocedimientos].length; j++, m++) {
									agr[m]=D.P[posprocedimientos][j];
								}
								pila.remove(punteropila);
								for (int j = agr.length-1; j >= 0; j--) {
									pila.add(agr[j]);
								}
								for (int j = 0; j < pila.size(); j++) {
									System.out.print(pila.get(j)+" ");
								}
								System.out.println();
								punteropila=pila.size()-1;
							}
							else
							{
								//Pilacar es terminal
								if(pilacar.equals(carmen) || pilacar.equals("E"))
								{
									pila.remove(punteropila);
									for (int j = 0; j < pila.size(); j++) {
										System.out.print(pila.get(j)+" ");
									}
									System.out.println();
									punteropila=pila.size()-1;
									if(pilacar.equals(carmen))
										break;
								}
								else
								{
									valido=false;
									break;
								}
							}
						}
							
						if(!valido)
						{
							break;
						}
					}
					catch(ArrayIndexOutOfBoundsException e)
					{
						System.out.println("T�rmino de cadena.");
						terminado=true;
						break;
					}
					
				}
				if(terminado)
					break;
			}
			if(valido)
			{
				System.out.println("Cadena v�lida para el aut�mata");
				AnalizadorSemantico as=new AnalizadorSemantico(cv, cc, Int);
				as.Analizar(archivoriginal, archivodatos, Int);
			}
			else
				System.out.println("Cadena no v�lida para el aut�mata");
			
		}
	}
	public boolean Noterminal(String pilacar, String array[])
	{
		
		for (int i = 0; i < array.length; i++) {
			if(pilacar.equals(array[i]))
				return true;
		}
		return false;
	}
}
