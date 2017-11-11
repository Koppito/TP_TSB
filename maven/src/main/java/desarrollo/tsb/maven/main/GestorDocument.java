package desarrollo.tsb.maven.main;

/*
* Esta clase se encarga de tener la instancia de la hashtable y de la serialización del fichero
*/

public class GestorDocument {

    //region SINGLETON
    private static GestorDocument instance;
    public static GestorDocument getInstance() {
        if (instance == null) {
            instance = new GestorDocument();
        }
        return instance;
    }
    //endregion

    private TSB_OAHashtable hashTable;
    private Archivo archivo;

    public GestorDocument() {
        hashTable = new TSB_OAHashtable(200);
        archivo = new Archivo();
    }

    public Archivo getArchivo() {
        return archivo;
    }

}
