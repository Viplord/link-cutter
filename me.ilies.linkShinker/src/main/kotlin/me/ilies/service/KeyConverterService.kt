package me.ilies.service

/**********************************************************************************************************************
 * Created by viplord on 8/10/2016.
 *
 **********************************************************************************************************************/
interface KeyConverterService {
    fun idToKey(id:Long):String
    fun keyToId(key:String):Long
}