package com.example.igift.data

import android.util.Log
import com.example.igift.model.Product
import com.example.igift.model.Wishlist
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

object WishlistRepository {

    suspend fun downloadListFromFirebase(email :String ): List<Product>{
        var list = mutableListOf<Product>()
            val wl  = Firestore.getWhishlist(email)
            if(wl == null){
                Firestore.postWhishlist(email)
                Log.v("WL", "No hay WL")
            }
            else{
                 list = wl.products
                Log.v("WL", "Hay WL")
            }
        return list
    }

    fun uploadWishListToFirebase(email : String){
        GlobalScope.launch(Dispatchers.IO) {
            val list  = PropertiesManager.getWhishList()
            Firestore.updateWishList(email, list)
        }
    }
}