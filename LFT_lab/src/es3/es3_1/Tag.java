/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 3.1
 *  
 *  Descrizione: Classe Tag
 *               Analizzatore sintattico di un file in input
 *
 *  Output previsto: Stampa elenco di Token analizzati dal file in input. 
 *               
 */
package Esercizio3_1;

public class Tag {
    public final static int
        EOF = -1,
        NUM = 256,
        ID = 257,
        RELOP = 258,
        IF = 259,
        THEN = 260, 
        ELSE = 261, 
        FOR = 262,
        DO = 263, 
        PRINT = 264, 
        READ = 265, 
        OR = 266,
        AND = 267, 
        BEGIN = 268, 
        END = 269;

}