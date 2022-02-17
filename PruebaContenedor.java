import java.io.*;
import java.io.FileInputStream;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class PruebaContenedor {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		
		System.out.println("INSERCIÓN");
		ContenedorDeEnteros b = insertion(a);
		System.out.println();
		
		System.out.println("EXTRACCIÓN");
		extraction(a);
		System.out.println();
		
		System.out.println("BUSQUEDA EXITOSA");
		busquedaExitosa(a);		
		System.out.println();
		
		System.out.println("BUSQUEDA NO EXITOSA");
		busquedaNoExitosa(a);
		
	}
	
	//---------------------------------------------------- FUNCIONES DE PRUEBA ----------------------------------------------------
	
	/*
	* Método para medir tiempos promedio por cada 10,000 inserciones en el contenedor de enteros
	*/
	private static ContenedorDeEnteros insertion(ContenedorDeEnteros a) throws Exception {
		FileInputStream file = null;
		DataInputStream dato = null;
		long t_inicial, t_final;
		int contadorMiles = 0;
		long promedio = 0;
		try {
			file = new FileInputStream("/Users/macbookpro/Downloads/datos.dat");
			dato = new DataInputStream(file);
			t_inicial = System.currentTimeMillis();
			for (int i = 1; i <= 100000; i++) {
				a.insertar(dato.readInt());
				contadorMiles++;
				if (contadorMiles == 1000) {
					t_final = System.currentTimeMillis();
					promedio += t_final - t_inicial;
					contadorMiles = 0;
					t_inicial = System.currentTimeMillis();
				}
				if (i%10000 == 0) {
					System.out.println("Se ha tardado " + promedio/10 + "ms en insertar 10,000 elementos");
					promedio = 0;
				}
			}
			
		} catch (EOFException e) {
			System.out.println("Fin de lectura de archivo con " + a.cardinal() + " elementos");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (file != null) {
					file.close();
				}
				if (dato != null) {
					dato.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		return a;
	}
	
	/*
	* Método para medir tiempos promedio por cada 10,000 extracciones en el contenedor de enteros
	*/
	private static void extraction(ContenedorDeEnteros b) {
		int c = 0;
		long t_inicial, t_final;
		int contadorMiles = 0;
		long promedio = 0;
		t_inicial = System.currentTimeMillis();
		int[] datos = b.elementos();
		for (int i = 1; i <= 100000; i++) {
			b.extraer(datos[i-1]);
			contadorMiles++;
			if (contadorMiles == 1000) {
				t_final = System.currentTimeMillis();
				promedio += t_final - t_inicial;
				contadorMiles = 0;
				t_inicial = System.currentTimeMillis();
			}
			if (i%10000 == 0) {
				System.out.println("Se ha tardado " + promedio/10 + "ms en extraer 10,000 elementos");
				promedio = 0;
			}
		}
	}
	
	/*
	* Método para medir tiempos promedio por cada n busquedas exitosas en el contenedor de enteros
	* Siendo 'n' el tamaño de el contenedor de enteros
	*/
	private static void busquedaExitosa(ContenedorDeEnteros a) throws Exception {
		FileInputStream file = null;
		DataInputStream dato = null;
		long t_inicial, t_final;
		int contadorMiles = 0;
		long promedio = 0;
		int contador = 0;
		int n;
		try {
			file = new FileInputStream("/Users/macbookpro/Downloads/datos.dat");
			dato = new DataInputStream(file);
			for (int i = 0; i < 100000; i++) {
				n = dato.readInt();		//Añade dato a contenedor
				a.insertar(n);
				contador++;		//Incrementa contador
				if (contador%10000 == 0) {		//Cada 10,000 entra en loop de buscar
					int[] datos = a.elementos();
					t_inicial = System.currentTimeMillis();
					for (int index = 0; index < datos.length; index++) {
						a.buscar(datos[index]);
						contadorMiles++;
						if (contadorMiles == 1000) {
							t_final = System.currentTimeMillis();
							promedio += t_final - t_inicial;
							contadorMiles = 0;
							t_inicial = System.currentTimeMillis();
						}
					}
					System.out.println("Se ha tardado " + promedio/10 + "ms en buscar " + (datos.length) + " elementos");
					promedio = 0;
				}
			}
		} catch (EOFException e) {
			System.out.println("Fin de lectura de archivo con " + a.cardinal() + " elementos");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (file != null) {
					file.close();
				}
				if (dato != null) {
					dato.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/*
	* Método que devuelve una lista de números sacadas de un archivo '.dat'
	* return int[], a.elementos() -> Elementos de el archivo 'datos_no.dat'
	*/
	private static int[] listaDeBusqueda() throws Exception {
		FileInputStream file = null;
		DataInputStream dato = null;
		file = new FileInputStream("/Users/macbookpro/Downloads/datos_no.dat");
		dato = new DataInputStream(file);
		ContenedorDeEnteros a = new ContenedorDeEnteros();
		
		for (int i = 0; i < 20000; i++) {
			a.insertar(dato.readInt());
		}
		
		return a.elementos();
	}
	
	/*
	* Método para medir tiempos promedio por cada n busquedas no exitosas en el contenedor de enteros
	* Siendo 'n' en el primer ciclo 10,000 y en el resto de ciclos 20,000
	*/
	private static void busquedaNoExitosa(ContenedorDeEnteros a) throws Exception {
		FileInputStream file = null;
		DataInputStream dato = null;
		long t_inicial, t_final;
		int contadorMiles = 0;
		long promedio = 0;
		int contador = 0;
		int n;
		int[] datosBusqueda = listaDeBusqueda();		//Datos de datos_no.dat
		try {
			file = new FileInputStream("/Users/macbookpro/Downloads/datos.dat");
			dato = new DataInputStream(file);
			for (int i = 0; i < 100000; i++) {
				n = dato.readInt();		//Añade dato a contenedor
				a.insertar(n);
				contador++;		//Incrementa contador
				if (contador%10000 == 0) {		//Cada 10,000 entra en loop de buscar
					if (contador == 10000) {			//Busca los 10,000
						t_inicial = System.currentTimeMillis();
						for (int index = 0; index < 10000; index++) {
							a.buscar(datosBusqueda[index]);
							contadorMiles++;
							if (contadorMiles == 1000) {
								t_final = System.currentTimeMillis();
								promedio += t_final - t_inicial;
								contadorMiles = 0;
								t_inicial = System.currentTimeMillis();
							}
						}
						System.out.println("Se ha tardado " + promedio/10 + "ms en buscar 10,000 elementos");
						promedio = 0;
					} else {			//Busca los 20,000
						t_inicial = System.currentTimeMillis();
						for (int index = 0; index < 20000; index++) {
							a.buscar(datosBusqueda[index]);
							contadorMiles++;
							if (contadorMiles == 1000) {
								t_final = System.currentTimeMillis();
								promedio += t_final - t_inicial;
								contadorMiles = 0;
								t_inicial = System.currentTimeMillis();
							}
						}
						System.out.println("Se ha tardado " + promedio/20 + "ms en buscar 20,000 elementos");
						promedio = 0;
					}
				}
			}
		} catch (EOFException e) {
			System.out.println("Fin de lectura de archivo con " + a.cardinal() + " elementos");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (file != null) {
					file.close();
				}
				if (dato != null) {
					dato.close();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

}