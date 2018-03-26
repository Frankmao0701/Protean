// VetifyController.aidl
package com.frank.protean;

import com.frank.protean.Vetify;
import com.frank.protean.IVetifyBackListener;
interface VetifyController {
   Vetify getVetify();
   void addVetifyInOut(inout Vetify vetify);
   void registerListener(IVetifyBackListener listener);
   void unRegisterListener(IVetifyBackListener listener);
}