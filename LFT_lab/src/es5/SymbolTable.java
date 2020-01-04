/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 5
 *  
 *  Descrizione: Classe SymbolTable
 *               Traduttore per programmi scritti in linguaggio "P".
 *
 *  Output previsto: File output.j utilizzabile dal programma Jasmin per la 
 *                   transormazione in un file .class eseguibile dalla JVM.
 *
 */
package Esercizio5;
import java.util.*;

public class SymbolTable {

     Map <String, Integer> OffsetMap = new HashMap <String,Integer>();

	public void insert( String s, int address ) {
            if( !OffsetMap.containsValue(address) ) 
                OffsetMap.put(s,address);
            else 
                throw new IllegalArgumentException("Reference to a memory location already occupied by another variable");
	}

	public int lookupAddress ( String s ) {
            if( OffsetMap.containsKey(s) ) 
                return OffsetMap.get(s);
            else
                return -1;
	}
}
