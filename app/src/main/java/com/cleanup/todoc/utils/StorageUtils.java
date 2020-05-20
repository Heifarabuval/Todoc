package com.cleanup.todoc.utils;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class StorageUtils {

    /**createOrGetFile() sera appelée pour créer ou récupérer un fichier*/
    private static File createOrGetFile(File destination, String fileName, String folderName){
        File folder = new File(destination,folderName);
        return new File(folder,fileName);

    }

    public static String getTextFromStorage(File rootDestination,Context context,String fileName,String folderName){
        File file = createOrGetFile(rootDestination,fileName,folderName);
        return readOnFile(context,file);
    }
    public static void setTextInStorage(File rootDestination,Context context,String fileName,String folderName,String text){
        File file = createOrGetFile(rootDestination,fileName,folderName);
        writeOnFile(context,text,file);
    }

    public static File getFileFromStorage(File rootDestination,Context context,String fileName,String folderName){
       return createOrGetFile(rootDestination,fileName,folderName);
    }

    // ----------------------------------
    // EXTERNAL STORAGE
    // ----------------------------------

    public static boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state));
    }

    public static boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        return (Environment.MEDIA_MOUNTED.equals(state)||Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));

    }

    // ----------------------------------
    // READ & WRITE ON STORAGE
    // ----------------------------------

    /**readOnFile()  va nous permettre de lire le contenu d'un fichier passé en paramètre.*/
    private static String readOnFile(Context context,File file){
        String result=null;
        if (file.exists()){
            BufferedReader bufferedReader;
            try {
                bufferedReader=new BufferedReader(new FileReader(file));
                try{
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = bufferedReader.readLine();
                    while(line!=null){
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                        line = bufferedReader.readLine();
                    }
                    result= stringBuilder.toString();
                }
                finally {
                    bufferedReader.close();
                }
            }
            catch (IOException e){
              Toast.makeText(context,context.getString(com.cleanup.todoc.R.string.error_happened),Toast.LENGTH_LONG).show();

        }
    }
        return result;
}

/**writeOnFile()   va nous permettre d'écrire du texte dans un fichier*/
private static void writeOnFile (Context context, String text, File file){
        try {
            file.getParentFile().mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Writer writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            try {
                writer.write(text);
                writer.flush();
                fileOutputStream.getFD().sync();
            } finally {
                writer.close();
                Toast.makeText(context,context.getString(com.cleanup.todoc.R.string.saved),Toast.LENGTH_LONG).show();

            }
        } catch(IOException e){
            Toast.makeText(context,context.getString(com.cleanup.todoc.R.string.error_happened),Toast.LENGTH_LONG);

        }
}

}
