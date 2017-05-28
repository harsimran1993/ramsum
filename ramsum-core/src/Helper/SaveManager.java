package Helper;

	import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter.OutputType;
import com.badlogic.gdx.utils.ObjectMap;

	public class SaveManager {
	    
	    private boolean encoded;
	    private static Save save;
	    private FileHandle file = Gdx.files.local("bin/save.json");
	    
	    public SaveManager(boolean encoded){
	        this.encoded = encoded;
	        if(!file.exists())
	        {
	        	try {
					file.file().createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        save = new Save();
	        	
	    }
	    
	    public static class Save{
	        public ObjectMap<String, Object> data = new ObjectMap<String, Object>();
	    }


	    private Save getSave(){
	    	Save s = new Save();
	   try{  if(file.exists()){
	        Json json = new Json();
	        if(encoded)s = json.fromJson(Save.class, Base64Coder.decodeString(file.readString()));
	        else s = json.fromJson(Save.class,file.readString());
	        //System.out.println(json.prettyPrint(save));
	        }
	   } catch(Exception e) { e.printStackTrace();}
	        return s;
	    }
	    
	    public void saveToJson(){
	        Json json = new Json();
	        json.setOutputType(OutputType.json);
	        if(encoded) Gdx.files.local("bin/save.json").writeString(Base64Coder.encodeString(json.prettyPrint(save)), false);
	        else Gdx.files.local("bin/save.json").writeString(json.prettyPrint(save), false);
	        //System.out.println(json.prettyPrint(save));
	    }
	    
	    @SuppressWarnings("unchecked")
	    public <T> T loadDataValue(String key, Class type){
	    	save = getSave();
	    	System.out.println(key);
	        if(save.data.containsKey(key))return (T) save.data.get(key);
	        else return null;   //this if() avoids and exception, but check for null on load.
	    }
	    public void saveDataValue(String key, Object object){
	    	   save.data.put(key, object);
	        saveToJson(); //Saves current save immediatly.
	       }
	    public ObjectMap<String, Object> getAllData(){
	        return save.data;
	    }
	    public Boolean isEmpty() {
	    	if (save.data.size == 0)
	    	return true;
	    	else
	    	return false;
	    	}
	    
	    public static void saveobject(Object object) throws IOException {
	        FileHandle file = Gdx.files.local("player.dat");
	        try {
	            file.writeBytes(serialize(object), false);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public static <T> T readPlayer() throws IOException, ClassNotFoundException {
	        FileHandle file = Gdx.files.local("player.dat");
	        return (T)deserialize(file.readBytes());
	    }

	    private static byte[] serialize(Object obj) throws IOException {
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        ObjectOutputStream o = new ObjectOutputStream(b);
	        o.writeObject(obj);
	        return b.toByteArray();
	    }

	    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
	        ByteArrayInputStream b = new ByteArrayInputStream(bytes);
	        ObjectInputStream o = new ObjectInputStream(b);
	        return o.readObject();
	    }
	}
