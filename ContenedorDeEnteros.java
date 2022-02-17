public class ContenedorDeEnteros {
	class Nodo {
        int info;
        Nodo sig;
    }

    private Nodo raiz;

    /*
     * Es el constructor de ContenedorDeEnteros()
     */
    public ContenedorDeEnteros() {
        raiz = null;
    }
    
    /*
     * Método cardinal() obtiene el número de elementos en el contenedor de enteros
     * return result -> int
     */
    public int cardinal() {
    	int result = 0;
    	if (raiz != null) {
    		Nodo actual = raiz;
    		while (actual != null) {
    			result++;
    			actual = actual.sig;
    		}
    	}
    	return result;
    }
    
    /*
     * Método insertar(), añade el elemento a el contenedor de enteros
     * param: int elemento -> Elemento a insertar
     * return añadido -> boolean, True si se ha añadido | False si no se ha añadido
     */
    public boolean insertar(int elemento) {
    	boolean añadido = false;
    	boolean found = false;
    	
    	Nodo actual = raiz;
    	while (actual != null) {		//Ve si ya existe
            if (actual.info == elemento) {
            	found = true;
            	break;
            }
            actual = actual.sig;
        }
    	
    	if (!found) {		//Si no existe, se añade
    		Nodo nuevo;
            nuevo = new Nodo();
            nuevo.info = elemento;
            if (raiz == null) {
                nuevo.sig = null;
                raiz = nuevo;
            } else {
                nuevo.sig = raiz;
                raiz = nuevo;
            }
            añadido = true;
    	}					//Si existe, no se añade
    	return añadido;
    }
    
    /*
     * Método extraer(), extrae el elemento del contenedor de enteros
     * param: int elemento -> Elemento a extraer
     * return extraido -> boolean, True si se ha extraido | False si no se ha extraido
     */
    public boolean extraer(int elemento) {
    	Nodo actual = raiz;
        boolean extraido = false;
        if (cardinal() != 0) {	//No está vacía
        	if (actual.info == elemento) {	//Se extrae el primero
        		raiz = actual.sig;
        		extraido = true;
        	} else {						//Se extrae en otra posición distinta del primero
        		actual = actual.sig;
        		Nodo anterior = raiz;
            	while ((actual != null) && (actual.info != elemento)) {
            		anterior = actual;
            		actual = actual.sig;
            	}
            	if (actual != null) {
            		if (actual.sig == null) anterior.sig = null;
            		else anterior.sig = actual.sig;
            		extraido = true;
            	}
        	}
        }
        return extraido;
        
    }
    
    /*
     * Método buscar(), busca el elemento en el contenedor de enteros
     * param: int elemento -> Elemento a buscar
     * return boolean, True si se ha encontardo | False si no se ha encontrado
     */
    public boolean buscar(int elemento) {
    	Nodo actual = raiz;
        while (actual !=null) {
            if (actual.info == elemento) return true;
            actual = actual.sig;
        }
        return false;
    }
    
    /*
     * El método vaciar(), vacia el contenedor de enteros
     */
    public void vaciar() {
    	raiz =null;
    }
    
    /*
     * El método elementos(), muestra los elementos de el contenedor
     * return int[], lista de enteros de los elemntos de el contenedor
     */
    public int[] elementos() {
    	int[] result;
        if (raiz != null) {
        	result = new int[cardinal()];
        	Nodo actual = raiz;
        	int i = 0;
            while (actual !=null) {
                result[i] = actual.info;
                i++;
                actual = actual.sig;
            }
        } else {
        	result = new int[0];
        }
        return result;
    }
    	
}
