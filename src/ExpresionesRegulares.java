import java.util.regex.*;

public class ExpresionesRegulares 
{
	String palres[]={"int", "double", "char","String", "while", "do", "if", "else", "Syso", "begin", "end"}; 
	
	
	
	// Clase de n�meros
	public boolean isDigito(String cad)
	{
		return Pattern.matches("[0-9]", cad);
	}
	public boolean isEntero(String cad)
	{
		return Pattern.matches("[0-9]+",cad);
	}
	public boolean isDecimal(String cad)
	{
		return Pattern.matches("[0-9]*[\\.][0-9]+", cad);
	}
	
	//Clase de Letras
	public boolean isCaracter(String cad)
	{
		return Pattern.matches("[a-zA-Z]", cad);
	}
	public boolean isPalabra(String cad)
	{
		return Pattern.matches("[a-zA-Z]+", cad);
	}
	public boolean isCadena(String cad)
	{
		return Pattern.matches("'.*'", cad);
	}
	//Clase de Letras y N�meros 
	
	public boolean isIdentificador(String cad)
	{
		return Pattern.matches("[a-zA-Z_]+[a-zA-Z0-9]*", cad);
	}
	
	public boolean isIdentificadorNoValido(String cad)
	{
		return Pattern.matches("[0-9]+[a-zA-Z][a-zA-Z0-9]*", cad);
	}
	
	public boolean isFloat(String cad)
	{
		if(cad.length()<=1)
		{
			System.out.println("Solo 1 ");
			return false;
		}
		String numeros=cad.substring(0, cad.length()-1);
		System.out.println(numeros);
		if(cad.endsWith("f"))
			return Pattern.matches("[0-9]*[\\.][0-9]+", numeros);
		else
			return false;
	}
	public boolean isPalabraReservada(String cad)
	{
		for (int i = 0; i < palres.length; i++) 
		{
			if(cad.equalsIgnoreCase(palres[i]))
            {
				return true;
            }
		}
		return false;
	}
	
	//Clase de Signos de puntuaci�n y Delimitadores
	
	public boolean isDelimitador(String cad)
	{
		return Pattern.matches("[^0-9A-Za-z|~\\\\~|~\\.~|~\\s~]+", cad);
	}
	public boolean isEspacio(String cad)
	{
		return Pattern.matches("\\s", cad);
	}
	public boolean isPunto(String cad)
	{
		return Pattern.matches("\\.", cad);
	}
	public boolean isSeparador(String cad)
	{
		return Pattern.matches("[\\{\\(\\s\\;\\)\\}]", cad);
	}
	public boolean isCaracterPuntuacion(String cad)
	{
		return Pattern.matches("[:=\\']", cad);
	}
	public boolean isGuionBajo(String cad)
	{
		return Pattern.matches("[_]", cad);
	}
	public boolean isIgual(String cad)
	{
		return Pattern.matches("=", cad);
	}
	public boolean isComa(String cad)
	{
		return Pattern.matches(",", cad);
	}
	public boolean isPuntoComa(String cad)
	{
		return Pattern.matches(";", cad);
	}
	public boolean isMenor(String cad)
	{
		return Pattern.matches("<", cad);
	}
	public boolean isMayor(String cad)
	{
		return Pattern.matches(">", cad);
	}
	
	//Extra
	
	public boolean isOperadorLogico(String cad)
	{
		return Pattern.matches("[&|!]", cad);
	}
	
	public boolean isOperadorMatematico(String cad)
	{
		return Pattern.matches("[\\+\\-\\*\\/]", cad);
	}
	
	public String[] MetodoOrdenamiento(String cad[])
	{
		String cadena[]=new String [cad.length];
		int insertados=0;
		for (int i = 0; i < cadena.length; i++) 
		{ //Recorre palabras
			if(insertados==0)
			{
				cadena[i]=cad[i];
				insertados++;
			}
			else
			{
				int index=0;
				while(insertados<=cad.length) 
				{
					cadena[i]=cadena[i].toLowerCase();
					if(cad[i].codePointAt(0)<cadena[index].codePointAt(0))
					{
						//Izquierda
						for (int j = 0; j < cadena.length; j++) {
							
						}
					}
				}
				insertados++;
			}
		}
		return cad;
	}
	public static void main(String[] args) {
		String lexema="'ddjd'";
		ExpresionesRegulares ex=new ExpresionesRegulares();
		
		System.out.println(ex.isCadena(lexema));
	}
}
