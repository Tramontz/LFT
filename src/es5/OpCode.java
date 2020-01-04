/*  Laboratorio Linguaggi Formali e Traduttori 17/18  
 *
 *  Autore: Stracci Maicol
 *  
 *  Matricola: 821115 
 *  
 *  Esercizio: Esercizio 5
 *  
 *  Descrizione: Classe OpCode
 *               Traduttore per programmi scritti in linguaggio "P".
 *
 *  Output previsto: File output.j utilizzabile dal programma Jasmin per la 
 *                   transormazione in un file .class eseguibile dalla JVM.
 *
 */
package Esercizio5;
public enum OpCode {
    ldc, imul, ineg, idiv, iadd,
    isub, istore, ior, iand, iload,
    if_icmpeq, if_icmple, if_icmplt, if_icmpne, if_icmpge,
    if_icmpgt, ifne, GOto, invokestatic, label 
}

