package main;

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

    public void GestorDocument() {
        hashTable = new TSB_OAHashtable(200);
        archivo = new Archivo();
    }

    public Archivo getArchivo() {
        return archivo;
    }

}
