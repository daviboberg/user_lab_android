package Model

/**
 * Created by leonardoapiovezan on 09/03/18.
 */
interface DataManager {

    fun save(key:String,value:String)

    fun read(key:String):String

    fun remove(key:String)

    fun removeAll()

    fun save(key:String,value:Any)


}