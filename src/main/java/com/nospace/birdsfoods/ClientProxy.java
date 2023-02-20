package com.nospace.birdsfoods;

import com.nospace.birdsfoods.BaseProxy;
import com.nospace.birdsfoods.items.BirdItems;

public class ClientProxy implements BaseProxy {

   public void preInit() {
      BirdItems.initModels();
   }
}
