/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: E:\\workspace\\Protean\\aidlclinet\\src\\main\\aidl\\com\\frank\\protean\\IVetifyBackListener.aidl
 */
package com.frank.protean;
public interface IVetifyBackListener extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.frank.protean.IVetifyBackListener
{
private static final java.lang.String DESCRIPTOR = "com.frank.protean.IVetifyBackListener";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.frank.protean.IVetifyBackListener interface,
 * generating a proxy if needed.
 */
public static com.frank.protean.IVetifyBackListener asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.frank.protean.IVetifyBackListener))) {
return ((com.frank.protean.IVetifyBackListener)iin);
}
return new com.frank.protean.IVetifyBackListener.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_onVetifyBack:
{
data.enforceInterface(DESCRIPTOR);
com.frank.protean.Vetify _arg0;
if ((0!=data.readInt())) {
_arg0 = com.frank.protean.Vetify.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.onVetifyBack(_arg0);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.frank.protean.IVetifyBackListener
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void onVetifyBack(com.frank.protean.Vetify vetify) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((vetify!=null)) {
_data.writeInt(1);
vetify.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_onVetifyBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_onVetifyBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void onVetifyBack(com.frank.protean.Vetify vetify) throws android.os.RemoteException;
}
